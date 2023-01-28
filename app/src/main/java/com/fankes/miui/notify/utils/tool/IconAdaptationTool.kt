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
import androidx.core.graphics.drawable.toBitmap
import com.fankes.miui.notify.BuildConfig
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
     * 使用的小图标
     * @return [Bitmap]
     */
    private val smallIcon by lazy {
        ("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAACXBIWXMAAAsTAAALEwEAmpwYAAAG\n" +
                "x2lUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkP\n" +
                "SJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG\n" +
                "9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDYgNzkuZGF\n" +
                "iYWNiYiwgMjAyMS8wNC8xNC0wMDozOTo0NCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6\n" +
                "cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gP\n" +
                "HJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYW\n" +
                "RvYmUuY29tL3hhcC8xLjAvIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1\n" +
                "lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90\n" +
                "b3Nob3AvMS4wLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuM\n" +
                "C9tbS8iIHhtbG5zOnN0RXZ0PSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cG\n" +
                "UvUmVzb3VyY2VFdmVudCMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIDI\n" +
                "yLjQgKE1hY2ludG9zaCkiIHhtcDpDcmVhdGVEYXRlPSIyMDIyLTAzLTIzVDAyOjU1OjM2\n" +
                "KzA4OjAwIiB4bXA6TW9kaWZ5RGF0ZT0iMjAyMi0wMy0yM1QwMzowMDoyMSswODowMCIge\n" +
                "G1wOk1ldGFkYXRhRGF0ZT0iMjAyMi0wMy0yM1QwMzowMDoyMSswODowMCIgZGM6Zm9ybW\n" +
                "F0PSJpbWFnZS9wbmciIHBob3Rvc2hvcDpDb2xvck1vZGU9IjMiIHBob3Rvc2hvcDpJQ0N\n" +
                "Qcm9maWxlPSJzUkdCIElFQzYxOTY2LTIuMSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlp\n" +
                "ZDpiZGY1YWY2NS1lYjYxLTRiOGUtOTk1NS01OTE5OThkN2ExNDAiIHhtcE1NOkRvY3VtZ\n" +
                "W50SUQ9ImFkb2JlOmRvY2lkOnBob3Rvc2hvcDo2MDFlYjQ1OC01ZTdlLWFjNDYtODA0Mi\n" +
                "1iNWJmYTFhYWQwNGMiIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDpjYTU\n" +
                "4YTA1ZS04OTU1LTQyNzItODg2NC0xNmI5MWI0YzcxMmUiPiA8eG1wTU06SGlzdG9yeT4g\n" +
                "PHJkZjpTZXE+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJjcmVhdGVkIiBzdEV2dDppbnN0Y\n" +
                "W5jZUlEPSJ4bXAuaWlkOmNhNThhMDVlLTg5NTUtNDI3Mi04ODY0LTE2YjkxYjRjNzEyZS\n" +
                "Igc3RFdnQ6d2hlbj0iMjAyMi0wMy0yM1QwMjo1NTozNiswODowMCIgc3RFdnQ6c29mdHd\n" +
                "hcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIDIyLjQgKE1hY2ludG9zaCkiLz4gPHJkZjps\n" +
                "aSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjg5Y\n" +
                "mIxNTI1LWVjYmMtNGY2NC1iZTRlLWU1N2EyNjQ1NDE1YyIgc3RFdnQ6d2hlbj0iMjAyMi\n" +
                "0wMy0yM1QwMjo1NzoxMCswODowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGh\n" +
                "vdG9zaG9wIDIyLjQgKE1hY2ludG9zaCkiIHN0RXZ0OmNoYW5nZWQ9Ii8iLz4gPHJkZjps\n" +
                "aSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOmJkZ\n" +
                "jVhZjY1LWViNjEtNGI4ZS05OTU1LTU5MTk5OGQ3YTE0MCIgc3RFdnQ6d2hlbj0iMjAyMi\n" +
                "0wMy0yM1QwMzowMDoyMSswODowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGh\n" +
                "vdG9zaG9wIDIyLjQgKE1hY2ludG9zaCkiIHN0RXZ0OmNoYW5nZWQ9Ii8iLz4gPC9yZGY6\n" +
                "U2VxPiA8L3htcE1NOkhpc3Rvcnk+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+I\n" +
                "DwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+QdGsggAAA35JREFUaIHtmE2IVW\n" +
                "Ucxn9z0Ukm7cMSo1AxUwjNIclLRVSL0CAoyD5oYwS1iogWGW0rwQpcJNjCRdKmTR+SBhF\n" +
                "BWWZlZllmOgsT/EqHKRuqMVv8XJy5zPGd95x75hyh03Ae+C/O//2/z3Oee97P26MyGdD6\n" +
                "r1/gQqExUjc0RuqGxkjd0BipGxojdcOkMTKlS/tCYBXQBvqBWcA/wI/AD8AW4NML9C79w\n" +
                "APAYmAOcCUwCBwCvgTeB37J7K3Goq1utRgG1NUZPEXibnVnQa231CUxnhjxmoKkId5WWx\n" +
                "M08XpJrSe7GXmlJHEH36l9oUgketRPKmqtS3P2OHaxWgO8HBl9vwPbgM+AU8B0YAnJ3Fk\n" +
                "Uqd8HLAfO5MyHz4HbIvkh4ENgJ/Dn6DxZCdxFfGF6GngtPUeuz3D9qnpZzi/7mDoS6fe9\n" +
                "Oi1SP0XdnqH1vDo9Q2eeujmj33WmhtbuSMFTOQbSsUg9Gem/3/HDbEuk7ox6c0GtZyP9d\n" +
                "3WMLIg0vlGQuBPz1cEIzx51aqouxN/qsglqvRnhWYj6UpAcVnsnSN75/MciIntTfC+m8r\n" +
                "+qi0vo9Kp/BBrrUD8KkutLkHdijvpbxMy3jn2Z1eoL6uUVdDYE/NtRjwfJ+yoIoF6rnoi\n" +
                "Y2VORNx33B9wHWsDMYEk7m7NsFsEhYBlwMsjfCOwFplbkBzgdPPe2gOEg2e38VQQnSMwM\n" +
                "BvmlwBfAtIr8FwXPIy1gf5BsVxS5ieTQd5zEzFDQvhzYBfRU0OgPnk/j+GPJYMlx26d+P\n" +
                "MpxNjXX5hlfmneYbJBltAYCrk2YnCZDnHeOKRAXqz8FHMOp9mtMltsQu0uYeTzC085yqL\n" +
                "qqIPFMdV+k/46gbr7xpflrz98086Kt/hv0P6JjR5RbIwKqz5h/NF/p+OVb9bA6O1I/N6P\n" +
                "+Z/WWLiYezXjHO9JGMBlOMRxW16r3mhwn7jS5D2Rdho6ps3JeKGuYaXKnedhkL5qhLlWf\n" +
                "UL/KqN/Y4Q1FNmd0KIqj6tU5JtJmjnThGu7S/k6aMyayvpSF5FfL+xJhXKF+U1JrU8iXJ\n" +
                "bLC7M8Z4pTJXJrIypOO59ShgloH1YdiPOkbYgy3A48ANwBXAZcCIyQ79gDwAfAe8FeFzQ\n" +
                "3gEuBB4B5gATAb6BvlPUpy63wX2JpF0M3I/waT5g+6xkjd0BipGxojdUNjpG5ojNQNjZG\n" +
                "64Rx5J7QeMy++3AAAAABJRU5ErkJggg==").bitmap
    }

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
                setSmallIcon(Icon.createWithBitmap(smallIcon))
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