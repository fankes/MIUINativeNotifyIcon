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
 * This file is Created by fankes on 2022/01/30.
 */
package com.fankes.miui.notify.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fankes.miui.notify.R
import com.fankes.miui.notify.hook.HookMedium
import com.fankes.miui.notify.utils.isNotSystemInDarkMode
import com.gyf.immersionbar.ktx.immersionBar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** 隐藏系统的标题栏 */
        supportActionBar?.hide()
        /** 初始化沉浸状态栏 */
        immersionBar {
            statusBarColor(R.color.colorThemeBackground)
            autoDarkModeEnable(true)
            statusBarDarkFont(isNotSystemInDarkMode)
            navigationBarColor(R.color.colorThemeBackground)
            navigationBarDarkIcon(isNotSystemInDarkMode)
            fitsSystemWindows(true)
        }
    }

    override fun onResume() {
        super.onResume()
        HookMedium.setWorldReadable(this)
    }

    override fun onRestart() {
        super.onRestart()
        HookMedium.setWorldReadable(this)
    }

    override fun onPause() {
        super.onPause()
        HookMedium.setWorldReadable(this)
    }

    override fun onBackPressed() {
        HookMedium.setWorldReadable(this)
        super.onBackPressed()
    }
}