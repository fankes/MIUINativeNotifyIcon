/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2019-2022 Fankes Studio(qzmmcn@163.com)
 * https://github.com/fankes/MIUINativeNotifyIcon
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version and our eula as published
 * by ferredoxin.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * and eula along with this software.  If not, see
 * <https://www.gnu.org/licenses/>
 *
 * This file is Created by fankes on 2022/1/24.
 */
@file:Suppress("SetTextI18n")

package com.fankes.miui.notify.ui

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.view.isVisible
import com.fankes.miui.notify.BuildConfig
import com.fankes.miui.notify.R
import com.fankes.miui.notify.hook.HookConst.ENABLE_COLOR_ICON_HOOK
import com.fankes.miui.notify.hook.HookConst.ENABLE_HIDE_ICON
import com.fankes.miui.notify.hook.HookConst.ENABLE_MODULE
import com.fankes.miui.notify.hook.HookConst.ENABLE_MODULE_LOG
import com.fankes.miui.notify.hook.HookConst.ENABLE_NOTIFY_ICON_HOOK
import com.fankes.miui.notify.ui.base.BaseActivity
import com.fankes.miui.notify.utils.*
import com.highcapable.yukihookapi.hook.factory.modulePrefs
import com.highcapable.yukihookapi.hook.xposed.YukiHookModuleStatus

class MainActivity : BaseActivity() {

    companion object {

        /** 模块版本 */
        private const val moduleVersion = BuildConfig.VERSION_NAME
    }

    private var isWarnDialogShowing = false // 警告对话框是否显示

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /** 设置文本 */
        findViewById<TextView>(R.id.main_text_version).text = "当前版本：$moduleVersion"
        findViewById<TextView>(R.id.main_text_miui_version).text = "MIUI 版本：$miuiFullVersion"
        when {
            /** 判断是否为 MIUI 系统 */
            isNotMIUI ->
                showDialog {
                    title = "不是 MIUI 系统"
                    msg = "此模块专为 MIUI 系统打造，当前无法识别你的系统为 MIUI，所以模块无法工作。\n" +
                            "如有问题请联系 酷安 @星夜不荟"
                    confirmButton(text = "退出") { finish() }
                    noCancelable()
                }
            /** 判断最低 Android 系统版本 */
            isLowerAndroidP ->
                showDialog {
                    title = "Android 系统版本过低"
                    msg = "此模块最低支持基于 Android 9 的 MIUI 系统，你的系统版本过低不再进行适配。\n" +
                            "如有问题请联系 酷安 @星夜不荟"
                    confirmButton(text = "退出") { finish() }
                    noCancelable()
                }
            /** 判断最低 MIUI 版本 */
            isNotSupportMiuiVersion ->
                showDialog {
                    title = "MIUI 版本过低"
                    msg = "此模块最低支持 MIUI 12 系统，你的 MIUI 版本为 ${miuiVersion}，不再进行适配。\n" +
                            "如有问题请联系 酷安 @星夜不荟"
                    confirmButton(text = "退出") { finish() }
                    noCancelable()
                }
            /** 判断是否 Hook */
            isHooked() -> {
                findViewById<LinearLayout>(R.id.main_lin_status).setBackgroundResource(R.drawable.green_round)
                findViewById<ImageFilterView>(R.id.main_img_status).setImageResource(R.mipmap.succcess)
                findViewById<TextView>(R.id.main_text_status).text = "模块已激活"
            }
            else ->
                showDialog {
                    title = "模块没有激活"
                    msg = "检测到模块没有激活，模块需要 Xposed 环境依赖，" +
                            "同时需要系统拥有 Root 权限，" +
                            "请自行查看本页面使用帮助与说明第二条。\n" +
                            "由于需要修改系统应用达到效果，模块不支持太极阴、应用转生。"
                    confirmButton(text = "我知道了")
                    noCancelable()
                }
        }
        /** 初始化 View */
        val moduleEnableSwitch = findViewById<SwitchCompat>(R.id.module_enable_switch)
        val moduleEnableLogSwitch = findViewById<SwitchCompat>(R.id.module_enable_log_switch)
        val notifyIconConfigItem = findViewById<View>(R.id.config_item_notify)
        val hideIconInLauncherSwitch = findViewById<SwitchCompat>(R.id.hide_icon_in_launcher_switch)
        val colorIconHookSwitch = findViewById<SwitchCompat>(R.id.color_icon_fix_switch)
        val notifyIconHookSwitch = findViewById<SwitchCompat>(R.id.notify_icon_fix_switch)
        /** 设置旧版本警告 */
        findViewById<View>(R.id.config_notify_app_icon_warn).isVisible = miuiVersion == "12"
        /** 获取 Sp 存储的信息 */
        notifyIconConfigItem.isVisible = modulePrefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)
        moduleEnableLogSwitch.isVisible = modulePrefs.getBoolean(ENABLE_MODULE, default = true)
        moduleEnableSwitch.isChecked = modulePrefs.getBoolean(ENABLE_MODULE, default = true)
        moduleEnableLogSwitch.isChecked = modulePrefs.getBoolean(ENABLE_MODULE_LOG, default = false)
        hideIconInLauncherSwitch.isChecked = modulePrefs.getBoolean(ENABLE_HIDE_ICON)
        colorIconHookSwitch.isChecked = modulePrefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)
        notifyIconHookSwitch.isChecked = modulePrefs.getBoolean(ENABLE_NOTIFY_ICON_HOOK, default = true)
        moduleEnableSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_MODULE, b)
            moduleEnableLogSwitch.isVisible = b
            SystemUITool.showNeedRestartSnake(context = this)
        }
        moduleEnableLogSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_MODULE_LOG, b)
            SystemUITool.showNeedRestartSnake(context = this)
        }
        hideIconInLauncherSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_HIDE_ICON, b)
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, "com.fankes.miui.notify.Home"),
                if (b) PackageManager.COMPONENT_ENABLED_STATE_DISABLED else PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
        }
        colorIconHookSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_COLOR_ICON_HOOK, b)
            notifyIconConfigItem.isVisible = b
            SystemUITool.showNeedRestartSnake(context = this)
        }
        notifyIconHookSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_NOTIFY_ICON_HOOK, b)
            SystemUITool.showNeedRestartSnake(context = this)
        }
        /** 重启按钮点击事件 */
        findViewById<View>(R.id.title_restart_icon).setOnClickListener { SystemUITool.restartSystemUI(context = this) }
        /** 通知图标优化名单按钮点击事件 */
        findViewById<View>(R.id.config_notify_app_button).setOnClickListener {
            startActivity(Intent(this, ConfigureActivity::class.java))
        }
        /** 恰饭！ */
        findViewById<View>(R.id.link_with_follow_me).setOnClickListener {
            runCatching {
                startActivity(Intent().apply {
                    setPackage("com.coolapk.market")
                    action = "android.intent.action.VIEW"
                    data = Uri.parse("https://www.coolapk.com/u/876977")
                    /** 防止顶栈一样重叠在自己的 APP 中 */
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }.onFailure {
                Toast.makeText(this, "你可能没有安装酷安", Toast.LENGTH_SHORT).show()
            }
        }
        /** 项目地址点击事件 */
        findViewById<View>(R.id.link_with_project_address).setOnClickListener {
            runCatching {
                startActivity(Intent().apply {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse("https://github.com/fankes/MIUINativeNotifyIcon")
                    /** 防止顶栈一样重叠在自己的 APP 中 */
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }.onFailure {
                Toast.makeText(this, "无法启动系统默认浏览器", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        /** MIUI 12 的版本特殊 - 所以给出提示 */
        if (!isWarnDialogShowing && isHooked() && miuiVersion == "12" && isMiuiNotifyStyle)
            showDialog {
                isWarnDialogShowing = true
                title = "经典通知栏样式已启用"
                msg = "在 MIUI 12 中启用了经典通知栏样式后状态栏图标将不再做原生处理，模块停止工作，" +
                        "这取决于系统设置，你应当在 设置>通知管理>通知显示设置 中将样式设置为“原生样式”。"
                confirmButton(text = "我知道了") { isWarnDialogShowing = false }
                noCancelable()
            }
    }

    /**
     * 判断模块是否激活
     * @return [Boolean] 激活状态
     */
    private fun isHooked() = YukiHookModuleStatus.isActive()
}