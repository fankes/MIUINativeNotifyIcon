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
 * This file is Created by zpp0196 on 2018/4/11.
 */
package com.fankes.miui.notify.utils

import com.fankes.miui.notify.BuildConfig
import de.robv.android.xposed.XSharedPreferences

object XPrefUtils {

    private var xPrefCacheKeyValueBooleans = HashMap<String, Boolean>()

    fun getBoolean(key: String, default: Boolean = false) =
        xPrefCacheKeyValueBooleans[key].let {
            it ?: pref.getBoolean(key, default).let { e ->
                xPrefCacheKeyValueBooleans[key] = e
                e
            }
        }

    private val pref: XSharedPreferences
        get() {
            val preferences = XSharedPreferences(BuildConfig.APPLICATION_ID)
            preferences.makeWorldReadable()
            preferences.reload()
            return preferences
        }
}