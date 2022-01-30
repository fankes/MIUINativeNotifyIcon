/**
 * Copyright (C) 2022. Fankes Studio(qzmmcn@163.com)
 *
 * This file is part of MIUINativeNotifyIcon.
 *
 * MIUINativeNotifyIcon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MIUINativeNotifyIcon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * This file is Created by fankes on 2022/01/24.
 */
@file:Suppress("unused")

package com.fankes.miui.notify.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class MNvApplication : Application() {

    companion object {

        /** 全局静态实例 */
        private var context: MNvApplication? = null

        /** 调用全局静态实例 */
        val appContext get() = context ?: error("App is death")
    }

    override fun onCreate() {
        super.onCreate()
        /** 设置静态实例 */
        context = this
        /** 跟随系统夜间模式 */
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}