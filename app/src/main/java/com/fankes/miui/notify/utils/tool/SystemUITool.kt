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

import android.content.Context
import com.fankes.miui.notify.hook.HookConst.SYSTEMUI_PACKAGE_NAME
import com.fankes.miui.notify.utils.factory.*
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
        context.dataChannel(SYSTEMUI_PACKAGE_NAME).checkingVersionEquals(result = result)

    /**
     * 重启系统界面
     * @param context 实例
     */
    fun restartSystemUI(context: Context) =
        context.showDialog {
            title = "重启系统界面"
            msg = "你确定要立即重启系统界面吗？\n\n" +
                    "部分 MIUI 内测和开发版中使用了状态栏主题可能会发生主题失效的情况，这种情况请再重启一次即可。\n\n" +
                    "重启过程会黑屏并等待进入锁屏重新解锁。"
            confirmButton {
                execShell(cmd = "pgrep systemui").also { pid ->
                    if (pid.isNotBlank())
                        execShell(cmd = "kill -9 $pid")
                    else toast(msg = "ROOT 权限获取失败")
                }
            }
            cancelButton()
        }

    /**
     * 刷新系统界面状态栏与通知图标
     * @param context 实例
     * @param isRefreshCacheOnly 仅刷新缓存不刷新图标和通知改变 - 默认：否
     * @param callback 成功后回调
     */
    fun refreshSystemUI(context: Context? = null, isRefreshCacheOnly: Boolean = false, callback: () -> Unit = {}) = runInSafe {
        if (YukiHookAPI.Status.isXposedModuleActive)
            context?.showDialog {
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
                        else -> context.dataChannel(SYSTEMUI_PACKAGE_NAME).with {
                            wait(CALL_MODULE_REFRESH_RESULT) {
                                cancel()
                                isWaited = true
                                callback()
                                if (it.not()) context.snake(msg = "刷新失败，建议重启系统界面", actionText = "立即重启") { restartSystemUI(context) }
                            }
                            put(CALL_HOST_REFRESH_CACHING, isRefreshCacheOnly)
                        }
                    }
                }
                noCancelable()
            }
        else context?.snake(msg = "模块没有激活，更改不会生效")
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