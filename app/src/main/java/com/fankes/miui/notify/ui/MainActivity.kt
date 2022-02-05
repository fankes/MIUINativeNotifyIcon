/**
 * Copyright (C) 2022. Fankes Studio(qzmmcn@163.com)
 *
 * This file is part of MIUINativeNotifyIcon.
 *
 * MIUINativeNotifyIcon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MIUINativeNotifyIcon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * This file is Created by fankes on 2022/01/24.
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
import com.fankes.miui.notify.hook.HookMedium
import com.fankes.miui.notify.ui.base.BaseActivity
import com.fankes.miui.notify.utils.*

class MainActivity : BaseActivity() {

    companion object {

        /** 模块版本 */
        private const val moduleVersion = BuildConfig.VERSION_NAME
    }

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
        /** 获取 Sp 存储的信息 */
        notifyIconConfigItem.isVisible = getBoolean(HookMedium.ENABLE_COLOR_ICON_HOOK, default = true)
        moduleEnableLogSwitch.isVisible = getBoolean(HookMedium.ENABLE_MODULE, default = true)
        moduleEnableSwitch.isChecked = getBoolean(HookMedium.ENABLE_MODULE, default = true)
        moduleEnableLogSwitch.isChecked = getBoolean(HookMedium.ENABLE_MODULE_LOG, default = false)
        hideIconInLauncherSwitch.isChecked = getBoolean(HookMedium.ENABLE_HIDE_ICON)
        colorIconHookSwitch.isChecked = getBoolean(HookMedium.ENABLE_COLOR_ICON_HOOK, default = true)
        notifyIconHookSwitch.isChecked = getBoolean(HookMedium.ENABLE_NOTIFY_ICON_HOOK, default = true)
        moduleEnableSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            putBoolean(HookMedium.ENABLE_MODULE, b)
            moduleEnableLogSwitch.isVisible = b
        }
        moduleEnableLogSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            putBoolean(HookMedium.ENABLE_MODULE_LOG, b)
        }
        hideIconInLauncherSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            putBoolean(HookMedium.ENABLE_HIDE_ICON, b)
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, "com.fankes.miui.notify.Home"),
                if (b) PackageManager.COMPONENT_ENABLED_STATE_DISABLED else PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
        }
        colorIconHookSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            putBoolean(HookMedium.ENABLE_COLOR_ICON_HOOK, b)
            notifyIconConfigItem.isVisible = b
        }
        notifyIconHookSwitch.setOnCheckedChangeListener { btn, b ->
            if (!btn.isPressed) return@setOnCheckedChangeListener
            putBoolean(HookMedium.ENABLE_NOTIFY_ICON_HOOK, b)
        }
        /** 重启按钮点击事件 */
        findViewById<View>(R.id.title_restart_icon).setOnClickListener {
            showDialog {
                title = "重启系统界面"
                msg = "你确定要立即重启系统界面吗？\n\n" +
                        "部分 MIUI 系统使用了状态栏主题可能会发生主题失效的情况，这种情况请再重启一次即可。"
                confirmButton { restartSystemUI() }
                cancelButton()
            }
        }
        /** 通知图标优化名单按钮点击事件 */
        findViewById<View>(R.id.config_notify_app_button).setOnClickListener {
            startActivity(Intent(this, ConfigureActivity::class.java))
        }
        /** 恰饭！ */
        findViewById<View>(R.id.link_with_follow_me).setOnClickListener {
            try {
                startActivity(Intent().apply {
                    setPackage("com.coolapk.market")
                    action = "android.intent.action.VIEW"
                    data = Uri.parse("https://www.coolapk.com/u/876977")
                    /** 防止顶栈一样重叠在自己的 APP 中 */
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            } catch (e: Exception) {
                Toast.makeText(this, "你可能没有安装酷安", Toast.LENGTH_SHORT).show()
            }
        }
        /** 项目地址点击事件 */
        findViewById<View>(R.id.link_with_project_address).setOnClickListener {
            try {
                startActivity(Intent().apply {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse("https://github.com/fankes/MIUINativeNotifyIcon")
                    /** 防止顶栈一样重叠在自己的 APP 中 */
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            } catch (e: Exception) {
                Toast.makeText(this, "无法启动系统默认浏览器", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 判断模块是否激活
     * @return [Boolean] 激活状态
     */
    private fun isHooked() = HookMedium.isHooked()

    /** 重启系统界面 */
    private fun restartSystemUI() =
        execShellCmd(cmd = "pgrep systemui").also { pid ->
            if (pid.isNotBlank())
                execShellCmd(cmd = "kill -9 $pid")
            else Toast.makeText(this, "ROOT 权限获取失败", Toast.LENGTH_SHORT).show()
        }

    /**
     * 获取保存的值
     * @param key 名称
     * @param default 默认值
     * @return [Boolean] 保存的值
     */
    private fun getBoolean(key: String, default: Boolean = false) = HookMedium.getBoolean(key, default)

    /**
     * 保存值
     * @param key 名称
     * @param bool 值
     */
    private fun putBoolean(key: String, bool: Boolean) = HookMedium.putBoolean(key, bool)
}