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
 * This file is Created by fankes on 2022/1/24.
 */
@file:Suppress("DEPRECATION", "SetWorldReadable")

package com.fankes.miui.notify.hook

object HookConst {

    const val ENABLE_MODULE = "_enable_module"
    const val ENABLE_MODULE_LOG = "_enable_module_log"
    const val ENABLE_HIDE_ICON = "_hide_icon"
    const val ENABLE_COLOR_ICON_COMPAT = "_color_icon_compat"
    const val ENABLE_NOTIFY_ICON_FIX = "_notify_icon_fix"
    const val ENABLE_NOTIFY_ICON_FIX_NOTIFY = "_notify_icon_fix_notify"
    const val ENABLE_HOOK_STATUS_ICON_COUNT = "_enable_hook_status_icon_count"
    const val NOTIFY_ICON_DATAS = "_notify_icon_datas"
    const val HOOK_STATUS_ICON_COUNT = "_hook_status_icon_count"

    const val SOURCE_SYNC_WAY = "_rule_source_sync_way"
    const val SOURCE_SYNC_WAY_CUSTOM_URL = "_rule_source_sync_way_custom_url"

    const val TYPE_SOURCE_SYNC_WAY_1 = 1000
    const val TYPE_SOURCE_SYNC_WAY_2 = 2000
    const val TYPE_SOURCE_SYNC_WAY_3 = 3000

    const val SYSTEMUI_PACKAGE_NAME = "com.android.systemui"
}