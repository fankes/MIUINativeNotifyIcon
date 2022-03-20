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
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.os.Build
import com.fankes.miui.notify.BuildConfig
import com.fankes.miui.notify.hook.HookEntry
import com.fankes.miui.notify.utils.factory.bitmap
import com.fankes.miui.notify.utils.factory.findAppIcon
import com.fankes.miui.notify.utils.factory.findAppName
import com.fankes.miui.notify.utils.factory.runInSafe

/**
 * 通知图标适配推送通知类
 *
 * 这个类需要在 [HookEntry] 中调用
 */
object IconAdaptationTool {

    /** 当前模块的包名 */
    private const val PACKAGE_NAME = BuildConfig.APPLICATION_ID

    /**
     * 使用的小图标
     * @return [Bitmap]
     */
    private val smallIcon by lazy {
        ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ix\n" +
                "AAAAAXNSR0IArs4c6QAAA7RJREFUaEPtmk2oVVUUx3//qYUDsaDUEISeqKEogVGkVjyzw\n" +
                "GwmTlREJRSESupJolJRlNCgSUGkSPDImaL5hWip4GehEmFUKERSVgNJHS5Zsq8+r8/7zt\n" +
                "pnn0uICw53cNfa6//b5569117ninvEdI9w0BiImQ0DetI1EZgC/AF8D/zkl6RLpSayERA\n" +
                "zew14G3hsCKEfSuorAVMcxMx2AC8HxJ0FXpD0VyDmDteiIGZmNcQ8KulibnwxEDN7H1iT\n" +
                "KwT4UdKk3PgiIGY2F9iWK2JA3FpJ7+WMUxvEzB4ATgPjgPPABuAgsA5YlCGqV9K+aFwJk\n" +
                "CeB4ynxBknrWyLM7B9gRFDU65I+CcbU30fMzGd9U0q8VNIXA0B+B0YFRX0paUkwpgjIR8\n" +
                "DqlPgbYIWk822AEV3HJE2PBLhviZ/WTuCltsT+rIyNikn+VyQ9GI0tAfI5sCyauIP/z5K\n" +
                "8tAlZCZDlwGehrJ2d+yUtiI5XAuRZ4Nto4g7+ayR9EB2vBMhDQK06qU30PEnhzbU2iIsw\n" +
                "M59Br3br2h5JL+YMUgQkwZwBnsgRMSBmjCTfe8JWEuRh4M+wglsBCyVtyY0vBpLuyiOA1\n" +
                "0l+IoyYn0f2RwLafYuCtAY3s3eAdysI2wy8IenfCr4dXRoBSXdnNuClxlPpGp6U/AAc9k\n" +
                "vS1roArfjGQNoFmtlU4IIkr4iLW20QM/OZ9lmfAYxPz8hJSSeKq+0wYC0QM3sTWAWMHiT\n" +
                "HUaBPkh+yGrdsEDPbBVTZvNZL8lNjo5YFYmYrgU8DyiZI8qZcYxYGMbP5QH9QUXbpUTVP\n" +
                "Dogfa3OaCuMlnasqLOqXA+KNBm84RO0VSdujQVX9c0D+A7wFFLW3JPn5vhHLAfGfx+MZa\n" +
                "hZIij5bldPkgLgYf+CjNlWSlyeNWA6IN+C8ixgxP2P0SLoaCYr4hkF8cDM7AMwMJFotaW\n" +
                "PAP+yaBZJgqr5C2C1pTkuZmb0KTE612V7glCT/rGXZIAnGXyP464TB7BqwWNLXAyBOAtM\n" +
                "Gcf7Km9+SfsmlqQWSYLzeeh54LnXkvwMOAb6b+zn+hpnZHqC3g9Bdkto7lpW5aoNUyWRm\n" +
                "vjjc7NJ3iLmtm19l7JZPt0CqLg4HJc2KAHQbpOrCgKSsyc0Kis5YYLn+39+RjwE/TQ5lG\n" +
                "yW13rUM5Xvb9926IyOBX4FWJ2UwkZd91ZP0d4ggOXcFJC2/T6c20N10PiPpSA6Ex3QNJM\n" +
                "FMAPzFkP+1w6/fAC8kl9dtE3UVJHe2q8TdB6kyS930uQ7NEhVCNv0J7AAAAABJRU5ErkJ\n" +
                "ggg==").bitmap
    }

    /**
     * 推送新 APP 安装适配通知
     * @param context 实例
     * @param packageName 安装的 APP 包名
     */
    fun pushNewAppSupportNotify(context: Context, packageName: String) {
        context.getSystemService(NotificationManager::class.java)?.apply {
            createNotificationChannel(
                NotificationChannel(
                    "notifyRuleAdaptId", "通知图标优化适配",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply { enableLights(false) }
            )
            notify(packageName.hashCode(), Notification.Builder(context, "notifyRuleAdaptId").apply {
                setShowWhen(true)
                setContentTitle("您已安装 ${context.findAppName(packageName)}")
                setContentText("尚未适配此应用，点按打开在线规则。")
                setColor(0xFF2993F0.toInt())
                setAutoCancel(true)
                setSmallIcon(Icon.createWithBitmap(smallIcon))
                setLargeIcon(context.findAppIcon(packageName))
                setContentIntent(
                    PendingIntent.getActivity(
                        context, packageName.hashCode(),
                        Intent().apply {
                            component = ComponentName(
                                PACKAGE_NAME,
                                "$PACKAGE_NAME.ui.activity.ConfigureActivity"
                            )
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }.apply {
                            putExtra("isNewAppSupport", true)
                            putExtra("appName", context.findAppName(packageName))
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
}