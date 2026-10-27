/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2017 Fankes Studio(qzmmcn@163.com)
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
 * This file is created by fankes on 2026/10/24.
 */
package com.fankes.miui.notify.service

import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import com.fankes.miui.notify.R
import com.fankes.miui.notify.utils.tool.IconRuleManagerTool
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.highcapable.anip.sdk.Anip
import com.highcapable.betterandroid.ui.component.notification.factory.Notification
import com.highcapable.betterandroid.ui.component.notification.factory.NotificationChannel
import com.highcapable.betterandroid.ui.component.notification.factory.startForeground
import com.highcapable.betterandroid.ui.component.notification.type.NotificationImportance
import com.highcapable.yukihookapi.YukiHook
import com.highcapable.yukihookapi.runtime.YukiHookRuntime
import com.highcapable.yukihookapi.runtime.bridge.FrameworkServiceBridge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.resume
import kotlin.time.Duration.Companion.milliseconds

/**
 * 由 SystemUI 定时启动的通知图标优化名单更新服务
 *
 * SystemUI 只能读取共享文件，获取资源并写入的工作需要在模块进程中完成
 */
class IconRuleUpdateService : Service() {

    private companion object {

        /** 等待 Hook 框架服务连接的最长时间 */
        const val FRAMEWORK_SERVICE_TIMEOUT = 15_000L

        const val NOTIFY_CHANNEL = "iconRuleUpdatingId"
        const val NOTIFY_ID = 1
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    /** 是否正在更新 */
    private var isUpdating = false

    override fun onBind(intent: Intent?) = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground()
        // 告知 SystemUI 已收到更新请求，取消它的兜底提醒
        SystemUITool.notifyIconRuleUpdateStarted(context = this)
        if (isUpdating) return START_NOT_STICKY
        isUpdating = true
        scope.launch {
            val result = if (awaitFrameworkService())
                runCatching { IconRuleManagerTool.fetch(context = this@IconRuleUpdateService) }.getOrElse {
                    Anip.FetchResult(it.message ?: getString(R.string.anip_fetch_failed), Anip.FetchResult.Status.FAILED)
                }
            else Anip.FetchResult(getString(R.string.service_hook_not_connected), Anip.FetchResult.Status.FAILED)
            IconRuleManagerTool.notifyFetchResult(context = this@IconRuleUpdateService, result)
            if (result.status == Anip.FetchResult.Status.SUCCESS)
                SystemUITool.refreshSystemUI(context = this@IconRuleUpdateService, isRefreshIconData = true)
            stopSelf()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    /** 启动前台服务并显示同步中的通知 */
    private fun startForeground() {
        val notification = Notification(
            context = this,
            channel = NotificationChannel(NOTIFY_CHANNEL, importance = NotificationImportance.LOW) {
                name = getString(R.string.sync_service_channel)
            }
        ) {
            smallIcon(R.drawable.ic_nf_icon_update)
            contentTitle = getString(R.string.sync_syncing)
            contentText = getString(R.string.sync_fetching_anip)
        }
        @Suppress("InlinedApi")
        startForeground(NOTIFY_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
    }

    /** 冷启动的模块进程需要等待 Hook 框架服务异步连接后才能写入共享文件 */
    private suspend fun awaitFrameworkService(): Boolean {
        // legacy 运行时没有框架服务，监听会直接抛出异常
        if (YukiHook.Status.runtime != YukiHookRuntime.LIBXPOSED) return true
        var subscription: FrameworkServiceBridge? = null
        return try {
            withTimeoutOrNull(FRAMEWORK_SERVICE_TIMEOUT.milliseconds) {
                suspendCancellableCoroutine { continuation ->
                    subscription = YukiHook.Status.observeFrameworkService { available ->
                        if (available && continuation.isActive) continuation.resume(true)
                    }
                }
            } == true
        } finally {
            subscription?.unregister()
        }
    }
}