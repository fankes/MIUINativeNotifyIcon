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
 * This file is created by fankes on 2022/3/21.
 */
package com.fankes.miui.notify.utils.tool

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.core.graphics.drawable.IconCompat
import androidx.core.graphics.drawable.toBitmap
import com.fankes.miui.notify.R
import com.fankes.miui.notify.hook.HookEntry
import com.fankes.miui.notify.utils.factory.appIconOf
import com.fankes.miui.notify.utils.factory.appNameOf
import com.fankes.miui.notify.utils.factory.isDebugApp
import com.fankes.miui.notify.utils.factory.isSystemApp
import com.fankes.miui.notify.utils.factory.runInSafe
import com.fankes.miui.notify.wrapper.BuildConfigWrapper
import com.highcapable.betterandroid.system.extension.utils.AndroidVersion
import com.highcapable.betterandroid.ui.component.notification.factory.NotificationChannel
import com.highcapable.betterandroid.ui.component.notification.factory.createNotification
import com.highcapable.betterandroid.ui.component.notification.factory.notificationManager

/**
 * 通知图标适配推送通知类
 *
 * 这个类需要在 [HookEntry] 中调用
 */
object IconAdaptationTool {

    /** 当前模块的包名 */
    private const val MODULE_PACKAGE_NAME = BuildConfigWrapper.APPLICATION_ID

    /** 推送通知的渠道名称 */
    private const val NOTIFY_CHANNEL = "notifyRuleSupportId"

    /**
     * 推送新 APP 安装适配通知
     * @param context 实例
     * @param packageName 安装的 APP 包名
     * @param resources 用于读取文案的模块资源，SystemUI 中需要传入模块资源 - 默认：当前实例资源
     */
    fun pushNewAppSupportNotify(context: Context, packageName: String, resources: Resources = context.resources) {
        if (packageName.startsWith("com.google.android.trichromelibrary")) return
        if (context.isSystemApp(packageName) || context.isDebugApp(packageName)) return
        @Suppress("MissingPermission")
        context.createNotification(
            channel = NotificationChannel(NOTIFY_CHANNEL) {
                name = resources.getString(R.string.notify_channel_adaptation)
                isLightsEnabled = false
            }
        ) {
            isShowWhen = true
            subText = context.appNameOf(MODULE_PACKAGE_NAME)
            contentTitle = resources.getString(R.string.notify_app_installed, context.appNameOf(packageName))
            contentText = resources.getString(R.string.notify_app_not_adapted)
            color = 0xFF2993F0.toInt()
            isAutoCancel = true
            @Suppress("RestrictedApi")
            smallIcon = IconCompat.createWithResource(resources, MODULE_PACKAGE_NAME, R.drawable.ic_unsupported)
            extras = Bundle().apply {
                putParcelable("miui.appIcon", Icon.createWithBitmap(context.appIconOf(MODULE_PACKAGE_NAME)?.toBitmap()))
            }
            contentIntent = PendingIntent.getActivity(
                context, packageName.hashCode(),
                Intent().apply {
                    component = ComponentName(
                        MODULE_PACKAGE_NAME,
                        "$MODULE_PACKAGE_NAME.ui.activity.ConfigureActivity"
                    )
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }.apply {
                    putExtra("isNewAppSupport", true)
                    putExtra("appName", context.appNameOf(packageName))
                    putExtra("pkgName", packageName)
                }, if (AndroidVersion.isLessThan(AndroidVersion.S)) PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_IMMUTABLE
            )
        }.post(packageName.hashCode())
    }

    /**
     * 检测 APP 卸载后移除相关通知
     * @param context 实例
     * @param packageName 卸载的 APP 包名
     */
    fun removeNewAppSupportNotify(context: Context, packageName: String) = runInSafe {
        context.notificationManager.cancel(packageName.hashCode())
    }
}