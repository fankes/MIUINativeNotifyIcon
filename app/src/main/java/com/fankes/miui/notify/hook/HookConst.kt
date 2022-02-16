/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2019-2022 Fankes Studio(qzmmcn@163.com)
 * https://github.com/fankes/MIUINativeNotifyIcon
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version and our eula as published
 * by ferredoxin.
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
    const val ENABLE_COLOR_ICON_HOOK = "_color_icon_hook"
    const val ENABLE_NOTIFY_ICON_FIX = "_notify_icon_fix"

    const val SYSTEMUI_PACKAGE_NAME = "com.android.systemui"
}