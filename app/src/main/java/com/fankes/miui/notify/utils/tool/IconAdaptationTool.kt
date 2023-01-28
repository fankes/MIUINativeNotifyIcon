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
 * This file is Created by fankes on 2022/3/21.
 */
package com.fankes.miui.notify.utils.tool

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import androidx.core.graphics.drawable.toBitmap
import com.fankes.miui.notify.BuildConfig
import com.fankes.miui.notify.R
import com.fankes.miui.notify.hook.HookEntry
import com.fankes.miui.notify.utils.factory.*

/**
 * 通知图标适配推送通知类
 *
 * 这个类需要在 [HookEntry] 中调用
 */
object IconAdaptationTool {

    /** 当前模块的包名 */
    private const val MODULE_PACKAGE_NAME = BuildConfig.APPLICATION_ID

    /** 推送通知的渠道名称 */
    private const val NOTIFY_CHANNEL = "notifyRuleSupportId"

    /** 已过期的日期 */
    private val outDateLimits = HashSet<String>()

    /**
     * 推送新 APP 安装适配通知
     * @param context 实例
     * @param packageName 安装的 APP 包名
     */
    fun pushNewAppSupportNotify(context: Context, packageName: String) {
        if (packageName.startsWith("com.google.android.trichromelibrary")) return
        if (context.isSystemApp(packageName) || context.isDebugApp(packageName)) return
        context.getSystemService(NotificationManager::class.java)?.apply {
            createNotificationChannel(
                NotificationChannel(
                    NOTIFY_CHANNEL, "通知图标优化适配",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply { enableLights(false) }
            )
            notify(packageName.hashCode(), Notification.Builder(context, NOTIFY_CHANNEL).apply {
                setShowWhen(true)
                setContentTitle("您已安装 ${context.appNameOf(packageName)}")
                setContentText("尚未适配此应用，点按打开在线规则。")
                setColor(0xFF2993F0.toInt())
                setAutoCancel(true)
                setSmallIcon(Icon.createWithResource(MODULE_PACKAGE_NAME, R.drawable.ic_unsupported))
                setLargeIcon(context.appIconOf(packageName)?.toBitmap())
                setContentIntent(
                    PendingIntent.getActivity(
                        context, packageName.hashCode(),
                        Intent().apply {
                            component = ComponentName(
                                MODULE_PACKAGE_NAME,
                                "${MODULE_PACKAGE_NAME}.ui.activity.ConfigureActivity"
                            )
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }.apply {
                            putExtra("isNewAppSupport", true)
                            putExtra("appName", context.appNameOf(packageName))
                            putExtra("pkgName", packageName)
                        }, if (Build.VERSION.SDK_INT < 31) PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_IMMUTABLE
                    )
                )
            }.build())
        }
    }

    /**
     * 检测 APP 卸载后移除相关通知
     * @param context 实例
     * @param packageName 卸载的 APP 包名
     */
    fun removeNewAppSupportNotify(context: Context, packageName: String) = runInSafe {
        context.getSystemService(NotificationManager::class.java)?.cancel(packageName.hashCode())
    }

    /**
     * 自动更新通知图标优化在线规则
     *
     * 一天执行一次
     * @param context 实例
     * @param timeSet 设定的时间
     */
    fun prepareAutoUpdateIconRule(context: Context, timeSet: String) = runInSafe {
        System.currentTimeMillis().also {
            if (it.stampToDate(format = "HH:mm") == timeSet && (outDateLimits.isEmpty() || outDateLimits.none { each ->
                    each == it.stampToDate(format = "yyyy-MM-dd")
                })) {
                outDateLimits.add(it.stampToDate(format = "yyyy-MM-dd"))
                context.startActivity(
                    Intent().apply {
                        component = ComponentName(
                            MODULE_PACKAGE_NAME,
                            "${MODULE_PACKAGE_NAME}.ui.activity.auto.NotifyIconRuleUpdateActivity"
                        )
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                )
            }
        }
    }
}