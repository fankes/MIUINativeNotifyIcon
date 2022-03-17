/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2019-2022 Fankes Studio(qzmmcn@163.com)
 * https://github.com/fankes/MIUINativeNotifyIcon
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version.
 * <p>
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
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.fankes.miui.notify.BuildConfig
import com.fankes.miui.notify.R
import com.fankes.miui.notify.hook.HookConst.ENABLE_COLOR_ICON_COMPAT
import com.fankes.miui.notify.hook.HookConst.ENABLE_COLOR_ICON_HOOK
import com.fankes.miui.notify.hook.HookConst.ENABLE_HIDE_ICON
import com.fankes.miui.notify.hook.HookConst.ENABLE_HOOK_STATUS_ICON_COUNT
import com.fankes.miui.notify.hook.HookConst.ENABLE_MODULE
import com.fankes.miui.notify.hook.HookConst.ENABLE_MODULE_LOG
import com.fankes.miui.notify.hook.HookConst.ENABLE_NOTIFY_ICON_FIX
import com.fankes.miui.notify.hook.HookConst.HOOK_STATUS_ICON_COUNT
import com.fankes.miui.notify.ui.base.BaseActivity
import com.fankes.miui.notify.utils.factory.*
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.google.android.material.textfield.TextInputEditText
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
        findViewById<TextView>(R.id.main_text_version).text = "模块版本：$moduleVersion"
        findViewById<TextView>(R.id.main_text_miui_version).text = "系统版本：$miuiFullVersion"
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
            YukiHookModuleStatus.isActive() -> {}
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
        val statusIconCountItem = findViewById<View>(R.id.config_item_s_count_hook)
        val statusIconCountChildItem = findViewById<View>(R.id.config_item_s_count_child_hook)
        val statusIconCountSwitch = findViewById<SwitchCompat>(R.id.config_status_icon_count_switch)
        val statusIconCountText = findViewById<TextView>(R.id.config_status_icon_count_text)
        val colorIconHookItem = findViewById<View>(R.id.config_item_color_hook)
        val notifyIconConfigItem = findViewById<View>(R.id.config_item_notify)
        val hideIconInLauncherSwitch = findViewById<SwitchCompat>(R.id.hide_icon_in_launcher_switch)
        val colorIconHookSwitch = findViewById<SwitchCompat>(R.id.color_icon_fix_switch)
        val colorIconCompatSwitch = findViewById<SwitchCompat>(R.id.color_icon_compat_switch)
        val colorIconCompatText = findViewById<View>(R.id.color_icon_compat_text)
        val notifyIconFixSwitch = findViewById<SwitchCompat>(R.id.notify_icon_fix_switch)
        val notifyIconFixButton = findViewById<View>(R.id.config_notify_app_button)

        /** 获取 Sp 存储的信息 */
        var statusBarIconCount = modulePrefs.getInt(HOOK_STATUS_ICON_COUNT, default = 5)
        colorIconHookItem.isVisible = modulePrefs.getBoolean(ENABLE_MODULE, default = true)
        statusIconCountItem.isVisible = modulePrefs.getBoolean(ENABLE_MODULE, default = true)
        colorIconCompatSwitch.isVisible = modulePrefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)
        colorIconCompatText.isVisible = modulePrefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)
        notifyIconConfigItem.isVisible = modulePrefs.getBoolean(ENABLE_MODULE, default = true) &&
                modulePrefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)
        notifyIconFixButton.isVisible = modulePrefs.getBoolean(ENABLE_NOTIFY_ICON_FIX, default = true)
        statusIconCountSwitch.isChecked = modulePrefs.getBoolean(ENABLE_HOOK_STATUS_ICON_COUNT, default = true)
        statusIconCountChildItem.isVisible = modulePrefs.getBoolean(ENABLE_HOOK_STATUS_ICON_COUNT, default = true)
        moduleEnableSwitch.isChecked = modulePrefs.getBoolean(ENABLE_MODULE, default = true)
        moduleEnableLogSwitch.isChecked = modulePrefs.getBoolean(ENABLE_MODULE_LOG, default = false)
        hideIconInLauncherSwitch.isChecked = modulePrefs.getBoolean(ENABLE_HIDE_ICON)
        colorIconHookSwitch.isChecked = modulePrefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)
        colorIconCompatSwitch.isChecked = modulePrefs.getBoolean(ENABLE_COLOR_ICON_COMPAT)
        notifyIconFixSwitch.isChecked = modulePrefs.getBoolean(ENABLE_NOTIFY_ICON_FIX, default = true)
        statusIconCountText.text = statusBarIconCount.toString()
        moduleEnableSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_MODULE, b)
            moduleEnableLogSwitch.isVisible = b
            colorIconHookItem.isVisible = b
            statusIconCountItem.isVisible = b
            notifyIconConfigItem.isVisible = b && colorIconHookSwitch.isChecked
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
        statusIconCountSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_HOOK_STATUS_ICON_COUNT, b)
            statusIconCountChildItem.isVisible = b
            SystemUITool.showNeedRestartSnake(context = this)
        }
        colorIconHookSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_COLOR_ICON_HOOK, b)
            notifyIconConfigItem.isVisible = b
            colorIconCompatSwitch.isVisible = b
            colorIconCompatText.isVisible = b
            SystemUITool.showNeedRestartSnake(context = this)
        }
        colorIconCompatSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_COLOR_ICON_COMPAT, b)
            SystemUITool.showNeedRestartSnake(context = this)
        }
        notifyIconFixSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            modulePrefs.putBoolean(ENABLE_NOTIFY_ICON_FIX, b)
            notifyIconFixButton.isVisible = b
            SystemUITool.showNeedRestartSnake(context = this)
        }
        /** 通知图标优化名单按钮点击事件 */
        notifyIconFixButton.setOnClickListener { startActivity(Intent(this, ConfigureActivity::class.java)) }
        /** 设置警告 */
        findViewById<View>(R.id.config_warn_s_count_dis_tip).isGone = miuiVersionCode > 12.5
        /** 修改状态栏通知图标个数按钮点击事件 */
        findViewById<View>(R.id.config_status_icon_count_button).setOnClickListener {
            showDialog {
                title = "设置最多显示的图标个数"
                var editText: TextInputEditText
                addView(R.layout.dia_status_icon_count).apply {
                    editText = findViewById<TextInputEditText>(R.id.dia_status_icon_count_input_edit).apply {
                        requestFocus()
                        invalidate()
                        setText(statusBarIconCount.toString())
                        setSelection(statusBarIconCount.toString().length)
                    }
                }
                confirmButton {
                    when {
                        (runCatching { editText.text.toString().toInt() }.getOrNull() ?: -1)
                                !in 0..100 -> snake(msg = "请输入有效数值")
                        editText.text.toString().isNotBlank() -> runCatching {
                            statusBarIconCount = editText.text.toString().trim().toInt()
                            modulePrefs.putInt(HOOK_STATUS_ICON_COUNT, statusBarIconCount)
                            statusIconCountText.text = statusBarIconCount.toString()
                            SystemUITool.showNeedRestartSnake(context = this@MainActivity)
                        }.onFailure { snake(msg = "数值格式无效") }
                        else -> snake(msg = "请输入有效数值")
                    }
                }
                cancelButton()
            }
        }
        /** 重启按钮点击事件 */
        findViewById<View>(R.id.title_restart_icon).setOnClickListener { SystemUITool.restartSystemUI(context = this) }
        /** 项目地址按钮点击事件 */
        findViewById<View>(R.id.title_github_icon).setOnClickListener {
            openBrowser(url = "https://github.com/fankes/MIUINativeNotifyIcon")
        }
        /** 恰饭！ */
        findViewById<View>(R.id.link_with_follow_me).setOnClickListener {
            openBrowser(url = "https://www.coolapk.com/u/876977", packageName = "com.coolapk.market")
        }
    }

    /** 刷新模块状态 */
    private fun refreshModuleStatus() {
        findViewById<LinearLayout>(R.id.main_lin_status).setBackgroundResource(
            when {
                YukiHookModuleStatus.isActive() && isMiuiNotifyStyle -> R.drawable.bg_yellow_round
                YukiHookModuleStatus.isActive() -> R.drawable.bg_green_round
                else -> R.drawable.bg_dark_round
            }
        )
        findViewById<ImageFilterView>(R.id.main_img_status).setImageResource(
            when {
                YukiHookModuleStatus.isActive() && !isMiuiNotifyStyle -> R.mipmap.ic_success
                else -> R.mipmap.ic_warn
            }
        )
        findViewById<TextView>(R.id.main_text_status).text =
            when {
                YukiHookModuleStatus.isActive() && isMiuiNotifyStyle -> "模块已激活，但未在工作"
                YukiHookModuleStatus.isActive() -> "模块已激活"
                else -> "模块未激活"
            }
    }

    override fun onResume() {
        super.onResume()
        /** 刷新模块状态 */
        refreshModuleStatus()
        /** 经典样式启用后给出警告 */
        if (!isWarnDialogShowing && YukiHookModuleStatus.isActive() && isMiuiNotifyStyle)
            showDialog {
                isWarnDialogShowing = true
                title = "经典通知栏样式已启用"
                msg = "当你启用了经典通知栏样式后，为防止 MIUI 自身不规范 APP 图标被破坏，状态栏图标将不再做原生处理。\n\n" +
                        "若要使用原生样式，请前往 设置>通知管理>通知显示设置 中将样式设置为“原生样式”，新版本为 设置>通知与控制中心>通知显示设置。"
                confirmButton(text = "去设置") {
                    runCatching {
                        startActivity(Intent().apply {
                            component = ComponentName(
                                "com.miui.notification",
                                "miui.notification.management.activity.NotificationDisplaySettingsActivity"
                            )
                            /** 防止顶栈一样重叠在自己的 APP 中 */
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    }.onFailure {
                        toast(msg = "启动失败，请手动调整设置")
                    }
                    isWarnDialogShowing = false
                }
                cancelButton { isWarnDialogShowing = false }
                noCancelable()
            }
    }
}