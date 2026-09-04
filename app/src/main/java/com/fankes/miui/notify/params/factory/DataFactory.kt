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
 * This file is created by fankes on 2022/2/15.
 * This file is Modified by fankes on 2023/2/3.
 */
package com.fankes.miui.notify.params.factory

import android.content.Context
import com.fankes.miui.notify.utils.factory.base64
import com.highcapable.anip.sdk.entity.NotificationIcon
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.param.PackageParam

private const val ENABLE_SUFFIX = "_enable"
private const val OVERLAY_SUFFIX = "_enable_all"

private fun NotificationIcon.preferenceName(suffix: String) = (label + packageName).base64 + suffix

/**
 * 获取此 APP 的 ANIP 通知图标是否启用
 * @param icon ANIP 图标实体
 */
fun PackageParam.isAppNotifyEnabledOf(icon: NotificationIcon) = prefs.getBoolean(icon.preferenceName(ENABLE_SUFFIX), true)

/**
 * 获取此 APP 的 ANIP 通知图标是否启用
 * @param icon ANIP 图标实体
 */
fun Context.isAppNotifyEnabledOf(icon: NotificationIcon) = prefs().getBoolean(icon.preferenceName(ENABLE_SUFFIX), true)

/**
 * 设置此 APP 的 ANIP 通知图标是否启用
 * @param icon ANIP 图标实体
 * @param isEnabled 是否启用
 */
fun Context.putAppNotifyEnabledOf(icon: NotificationIcon, isEnabled: Boolean) =
    prefs().edit { putBoolean(icon.preferenceName(ENABLE_SUFFIX), isEnabled) }

/**
 * 获取此 APP 的 ANIP 通知图标是否覆盖原有单色图标
 * @param icon ANIP 图标实体
 */
fun PackageParam.isAppNotifyOverlayOf(icon: NotificationIcon) = prefs.getBoolean(icon.preferenceName(OVERLAY_SUFFIX), icon.overlay)

/**
 * 获取此 APP 的 ANIP 通知图标是否覆盖原有单色图标
 * @param icon ANIP 图标实体
 */
fun Context.isAppNotifyOverlayOf(icon: NotificationIcon) = prefs().getBoolean(icon.preferenceName(OVERLAY_SUFFIX), icon.overlay)

/**
 * 设置此 APP 的 ANIP 通知图标是否覆盖原有单色图标
 * @param icon ANIP 图标实体
 * @param isOverlay 是否覆盖
 */
fun Context.putAppNotifyOverlayOf(icon: NotificationIcon, isOverlay: Boolean) =
    prefs().edit { putBoolean(icon.preferenceName(OVERLAY_SUFFIX), isOverlay) }