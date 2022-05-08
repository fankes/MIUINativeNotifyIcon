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

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.fankes.miui.notify.const.Const
import com.fankes.miui.notify.utils.factory.*
import com.google.android.material.snackbar.Snackbar
import com.highcapable.yukihookapi.hook.factory.isXposedModuleActive
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication.Companion.appContext

/**
 * 系统界面工具
 */
object SystemUITool {

    /** 宿主广播回调 */
    private var moduleHandlerCallback: ((Boolean, Boolean) -> Unit)? = null

    /** 通知广播回调 */
    private var remindHandlerCallback: ((Boolean, Boolean) -> Unit)? = null

    /**
     * 注册广播
     * @param context 实例
     */
    fun register(context: Context) = runInSafe {
        /** 注册广播检查模块激活状态 */
        context.registerReceiver(moduleHandlerReceiver, IntentFilter().apply { addAction(Const.ACTION_MODULE_HANDLER_RECEIVER) })
        /** 注册广播通知系统界面改变 */
        context.registerReceiver(remindHandlerReceiver, IntentFilter().apply { addAction(Const.ACTION_REMIND_HANDLER_RECEIVER) })
    }

    /**
     * 取消注册广播
     * @param context 实例
     */
    fun unregister(context: Context) = runInSafe {
        context.unregisterReceiver(moduleHandlerReceiver)
        context.unregisterReceiver(remindHandlerReceiver)
    }

    /**
     * 检查模块是否激活
     * @param context 实例
     * @param it 成功后回调 - ([Boolean] 是否激活,[Boolean] 是否有效)
     */
    fun checkingActivated(context: Context, it: (Boolean, Boolean) -> Unit) {
        moduleHandlerCallback = it
        context.sendBroadcast(Intent().apply {
            action = Const.ACTION_MODULE_CHECKING_RECEIVER
            putExtra(Const.MODULE_VERSION_VERIFY_TAG, Const.MODULE_VERSION_VERIFY)
        })
    }

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
     * @param it 成功后回调
     */
    fun refreshSystemUI(context: Context? = null, isRefreshCacheOnly: Boolean = false, it: () -> Unit = {}) = runInSafe {
        fun sendMessage() {
            (context ?: appContext).sendBroadcast(Intent().apply {
                action = Const.ACTION_REMIND_CHECKING_RECEIVER
                putExtra("isRefreshCacheOnly", isRefreshCacheOnly)
                putExtra(Const.MODULE_VERSION_VERIFY_TAG, Const.MODULE_VERSION_VERIFY)
            })
        }
        if (isXposedModuleActive)
            context?.showDialog {
                title = "请稍后"
                progressContent = "正在等待系统界面刷新"
                /** 是否等待成功 */
                var isWaited = false
                /** 设置等待延迟 */
                delayedRun(ms = 5000) {
                    if (isWaited) return@delayedRun
                    remindHandlerCallback = null
                    cancel()
                    context.snake(msg = "预计响应超时，建议重启系统界面", actionText = "立即重启") { restartSystemUI(context) }
                }
                remindHandlerCallback = { isGrasp, isValied ->
                    remindHandlerCallback = null
                    cancel()
                    isWaited = true
                    when {
                        isGrasp && !isValied ->
                            context.snake(msg = "请重启系统界面以生效模块更新", actionText = "立即重启") { restartSystemUI(context) }
                        else -> it()
                    }
                }
                sendMessage()
                noCancelable()
            } ?: sendMessage()
        else context?.snake(msg = "模块没有激活，更改不会生效")
    }

    /**
     * 显示需要重启系统界面的 [Snackbar]
     * @param context 实例
     */
    fun showNeedRestartSnake(context: Context) =
        if (isXposedModuleActive)
            context.snake(msg = "设置需要重启系统界面才能生效", actionText = "立即重启") { restartSystemUI(context) }
        else context.snake(msg = "模块没有激活，更改不会生效")

    /** 宿主广播接收器 */
    private val moduleHandlerReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val isRegular = intent?.getBooleanExtra("isRegular", false) ?: false
                val isValied = intent?.getBooleanExtra("isValied", false) ?: false
                moduleHandlerCallback?.invoke(isRegular, isValied)
            }
        }
    }

    /** 通知广播接收器 */
    private val remindHandlerReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val isGrasp = intent?.getBooleanExtra("isGrasp", false) ?: false
                val isValied = intent?.getBooleanExtra("isValied", false) ?: false
                remindHandlerCallback?.invoke(isGrasp, isValied)
            }
        }
    }
}