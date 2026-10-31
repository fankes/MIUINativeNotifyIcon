/*
 * MIUINativeNotifyIcon - Optimize notification icons.
 * Copyright (C) 2017 Fankes Studio(qzmmcn@163.com)
 * https://github.com/fankes/MIUINativeNotifyIcon
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
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
 * This file is created by fankes on 2026/11/1.
 */
package com.fankes.miui.notify.ui.widget

import android.content.Context
import android.util.AttributeSet
import com.fankes.miui.notify.R
import com.google.android.material.chip.Chip
import com.google.android.material.R as MaterialR

/**
 * 项目内的可选中 Chip
 */
class AppChip(context: Context, attrs: AttributeSet? = null) : Chip(context, attrs) {

    init {
        chipIconSize = resources.getDimension(R.dimen.app_chip_icon_size)
        setEnsureMinTouchTargetSize(false)
        @Suppress("PrivateResource")
        setCheckedIconResource(MaterialR.drawable.ic_m3_chip_check)
        // Material 替换勾选图标后不会重新应用 checkedIconTint，需给新图标设置文字色
        checkedIcon?.setTintList(textColors)
        isCheckable = true
        isChipIconVisible = false
        isCloseIconVisible = false
    }
}