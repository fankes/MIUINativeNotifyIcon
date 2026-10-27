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
 * This file is created by fankes on 2022/1/8.
 */
@file:Suppress("SameParameterValue")

package com.fankes.miui.notify.ui.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.TextViewCompat
import com.fankes.miui.notify.utils.factory.dp
import com.fankes.miui.notify.utils.factory.isSystemInDarkMode
import top.defaults.drawabletoolbox.DrawableBuilder

class MaterialSwitch(context: Context, attrs: AttributeSet?) : SwitchCompat(context, attrs) {

    private fun toColors(selected: Int, pressed: Int, normal: Int): ColorStateList {
        val colors = intArrayOf(selected, pressed, normal)
        val states = arrayOfNulls<IntArray>(3)
        states[0] = intArrayOf(android.R.attr.state_checked)
        states[1] = intArrayOf(android.R.attr.state_pressed)
        states[2] = intArrayOf()
        return ColorStateList(states, colors)
    }

    private val thumbColor get() = if (context.isSystemInDarkMode) 0xFF7C7C7C else 0xFFCCCCCC

    init {
        trackDrawable = DrawableBuilder()
            .rectangle()
            .rounded()
            .solidColor(0xFF656565.toInt())
            .height(20.dp(context))
            .cornerRadius(15.dp(context))
            .build()
        thumbDrawable = DrawableBuilder()
            .rectangle()
            .rounded()
            .solidColor(Color.WHITE)
            .size(20.dp(context), 20.dp(context))
            .cornerRadius(20.dp(context))
            .strokeWidth(8.dp(context))
            .strokeColor(Color.TRANSPARENT)
            .build()
        trackTintList = toColors(
            0xFF656565.toInt(),
            thumbColor.toInt(),
            thumbColor.toInt()
        )
        maxLines = 1
        switchPadding = 10.dp(context)
        ellipsize = TextUtils.TruncateAt.END
        // 文字显示不下时从布局里设置的字号向下缩小，最小 10sp，缩到最小仍显示不下才省略
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
            this,
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f, resources.displayMetrics).toInt(),
            textSize.toInt(),
            1,
            TypedValue.COMPLEX_UNIT_PX
        )
        // Material3 主题的开关样式自带 48dp 最小高度，会把一行撑高，这里去掉让高度跟随内容
        minHeight = 0
        minimumHeight = 0
    }
}