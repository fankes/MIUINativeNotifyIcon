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
 * This file is Created by fankes on 2022/3/26.
 */
package com.fankes.miui.notify.data

import com.fankes.miui.notify.hook.HookConst
import com.highcapable.yukihookapi.hook.xposed.prefs.data.PrefsData

object DataConst {

    val ENABLE_MODULE = PrefsData("_enable_module", true)
    val ENABLE_MODULE_LOG = PrefsData("_enable_module_log", false)
    val ENABLE_HIDE_ICON = PrefsData("_hide_icon", false)
    val ENABLE_COLOR_ICON_COMPAT = PrefsData("_color_icon_compat", false)
    val ENABLE_NOTIFY_ICON_FIX = PrefsData("_notify_icon_fix", true)
    val ENABLE_NOTIFY_ICON_FORCE_APP_ICON = PrefsData("_notify_icon_force_app_icon", false)
    val ENABLE_NOTIFY_ICON_FIX_NOTIFY = PrefsData("_notify_icon_fix_notify", true)
    val ENABLE_HOOK_STATUS_ICON_COUNT = PrefsData("_enable_hook_status_icon_count", true)
    val ENABLE_NOTIFY_ICON_FIX_AUTO = PrefsData("_enable_notify_icon_fix_auto", true)
    val NOTIFY_ICON_DATAS = PrefsData("_notify_icon_datas", "")
    val NOTIFY_ICON_FIX_AUTO_TIME = PrefsData("_notify_icon_fix_auto_time", "07:00")
    val HOOK_STATUS_ICON_COUNT = PrefsData("_hook_status_icon_count", 5)

    val IGNORED_ANDROID_VERSION_TO_LOW = PrefsData("_ignored_android_version_to_low", false)

    val SOURCE_SYNC_WAY = PrefsData("_rule_source_sync_way", HookConst.TYPE_SOURCE_SYNC_WAY_1)
    val SOURCE_SYNC_WAY_CUSTOM_URL = PrefsData("_rule_source_sync_way_custom_url", "")
}