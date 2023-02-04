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
 * This file is Created by fankes on 2022/2/8.
 */
package com.fankes.miui.notify.utils.tool

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.fankes.miui.notify.const.PackageName
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.ui.activity.MainActivity
import com.fankes.miui.notify.utils.factory.delayedRun
import com.fankes.miui.notify.utils.factory.execShell
import com.fankes.miui.notify.utils.factory.showDialog
import com.fankes.miui.notify.utils.factory.snake
import com.google.android.material.snackbar.Snackbar
import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.hook.factory.dataChannel
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.highcapable.yukihookapi.hook.xposed.channel.data.ChannelData

/**
 * 系统界面工具
 */
object SystemUITool {

    private val CALL_HOST_REFRESH_CACHING = ChannelData("call_host_refresh_caching", false)
    private val CALL_MODULE_REFRESH_RESULT = ChannelData("call_module_refresh_result", false)

    /**
     * 宿主注册监听
     */
    object Host {

        /**
         * 监听系统界面刷新改变
         * @param param 实例
         * @param result 回调 - ([Boolean] 是否成功)
         */
        fun onRefreshSystemUI(param: PackageParam, result: (Boolean) -> Boolean) {
            param.dataChannel.with { wait(CALL_HOST_REFRESH_CACHING) { put(CALL_MODULE_REFRESH_RESULT, result(it)) } }
        }
    }

    /**
     * 检查模块是否激活
     * @param context 实例
     * @param result 成功后回调
     */
    fun checkingActivated(context: Context, result: (Boolean) -> Unit) =
        context.dataChannel(PackageName.SYSTEMUI).checkingVersionEquals(result = result)

    /** 当 Root 权限获取失败时显示对话框 */
    private fun Context.showWhenAccessRootFail() =
        showDialog {
            title = "获取 Root 权限失败"
            msg = "当前无法获取 Root 权限，请确认你的设备已经被 Root 且同意授予 Root 权限。\n" +
                    "如果你正在使用 Magisk 并安装了 Shamiko 模块，" +
                    "请确认当前是否正处于白名单模式。 (白名单模式将导致无法申请 Root 权限)"
            confirmButton(text = "我知道了")
        }

    /**
     * 打开 MIUI 通知显示设置界面
     * @param context 实例
     */
    fun openMiuiNotificationDisplaySettings(context: Context) {
        runCatching {
            context.startActivity(Intent().apply {
                component = ComponentName(
                    "com.miui.notification",
                    "miui.notification.management.activity.NotificationDisplaySettingsActivity"
                )
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
        }.onFailure {
            execShell(
                cmd = "am start -a" +
                        "com.miui.notification " +
                        "com.miui.notification/miui.notification.management.activity.NotificationDisplaySettingsActivity"
            ).also {
                when {
                    it.isBlank() -> context.showWhenAccessRootFail()
                    else -> context.snake(msg = "已发送请求，如果未打开则是当前系统不支持此功能")
                }
            }
        }
    }

    /**
     * 重启系统界面
     * @param context 实例
     */
    fun restartSystemUI(context: Context) {
        /** 动态刷新功能是否可用 */
        val isDynamicAvailable = ConfigData.isEnableModule && MainActivity.isModuleRegular && MainActivity.isModuleValied
        context.showDialog {
            title = "重启系统界面"
            msg = "你确定要立即重启系统界面吗？\n\n" +
                    "部分 MIUI 内测和开发版中使用了状态栏主题可能会发生主题失效的情况，这种情况请再重启一次即可。\n\n" +
                    "重启过程会黑屏并等待进入锁屏重新解锁。" + (if (isDynamicAvailable)
                "\n\n你也可以选择“立即生效”来动态刷新系统界面并生效当前模块设置。" else "")
            confirmButton {
                execShell(cmd = "pgrep systemui").also { pid ->
                    if (pid.isNotBlank())
                        execShell(cmd = "kill -9 $pid")
                    else context.showWhenAccessRootFail()
                }
            }
            cancelButton()
            if (isDynamicAvailable) neutralButton(text = "立即生效") { refreshSystemUI(context) }
        }
    }

    /**
     * 刷新系统界面状态栏与通知图标
     * @param context 实例
     * @param isRefreshCacheOnly 仅刷新缓存不刷新图标和通知改变 - 默认：否
     * @param callback 成功后回调
     */
    fun refreshSystemUI(context: Context? = null, isRefreshCacheOnly: Boolean = false, callback: () -> Unit = {}) {
        /**
         * 刷新系统界面
         * @param result 回调结果
         */
        fun doRefresh(result: (Boolean) -> Unit) {
            context?.dataChannel(PackageName.SYSTEMUI)?.with {
                wait(CALL_MODULE_REFRESH_RESULT) { result(it) }
                put(CALL_HOST_REFRESH_CACHING, isRefreshCacheOnly)
            }
        }
        when {
            YukiHookAPI.Status.isXposedModuleActive && context is AppCompatActivity ->
                context.showDialog {
                    title = "请稍后"
                    progressContent = "正在等待系统界面刷新"
                    /** 是否等待成功 */
                    var isWaited = false
                    /** 设置等待延迟 */
                    delayedRun(ms = 5000) {
                        if (isWaited) return@delayedRun
                        cancel()
                        context.snake(msg = "预计响应超时，建议重启系统界面", actionText = "立即重启") { restartSystemUI(context) }
                    }
                    checkingActivated(context) { isValied ->
                        when {
                            isValied.not() -> {
                                cancel()
                                isWaited = true
                                context.snake(msg = "请重启系统界面以生效模块更新", actionText = "立即重启") { restartSystemUI(context) }
                            }
                            else -> doRefresh {
                                cancel()
                                isWaited = true
                                callback()
                                if (it.not()) context.snake(msg = "刷新失败，建议重启系统界面", actionText = "立即重启") { restartSystemUI(context) }
                            }
                        }
                    }
                    noCancelable()
                }
            YukiHookAPI.Status.isXposedModuleActive.not() && context is AppCompatActivity -> context.snake(msg = "模块没有激活，更改不会生效")
            else -> doRefresh { callback() }
        }
    }

    /**
     * 显示需要重启系统界面的 [Snackbar]
     * @param context 实例
     */
    fun showNeedRestartSnake(context: Context) =
        if (YukiHookAPI.Status.isXposedModuleActive)
            context.snake(msg = "设置需要重启系统界面才能生效", actionText = "立即重启") { restartSystemUI(context) }
        else context.snake(msg = "模块没有激活，更改不会生效")
}