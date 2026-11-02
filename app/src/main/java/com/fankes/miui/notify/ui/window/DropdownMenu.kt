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
 * This file is created by fankes on 2026/11/2.
 */
package com.fankes.miui.notify.ui.window

import android.content.Context
import android.graphics.Rect
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import com.fankes.miui.notify.R
import com.highcapable.betterandroid.ui.extension.component.base.getColorStateListCompat
import com.highcapable.betterandroid.ui.extension.component.base.getDrawableCompat
import com.highcapable.betterandroid.ui.extension.component.base.toPx
import androidx.appcompat.R as AppCompat_R

/**
 * 下拉选择菜单
 * @param context 当前实例
 * @param items 菜单项，文本资源 ID 与对应值
 * @param selectedItem 当前选中项
 * @param widthMatchedToAnchor 是否与锚点等宽
 * @param onItemSelected 菜单项被点击的回调
 */
class DropdownMenu<T>(
    context: Context,
    items: List<Pair<Int, T>>,
    selectedItem: T,
    private val widthMatchedToAnchor: Boolean = false,
    onItemSelected: (T) -> Unit
) : PopupWindow(context, null, AppCompat_R.attr.popupMenuStyle) {

    companion object {

        /** 下拉后距离锚点的垂直偏移 */
        private const val VERTICAL_OFFSET_DP = 4

        /** 弹窗最小宽度 */
        private const val MIN_WIDTH_DP = 196
    }

    init {
        isFocusable = true
        isOutsideTouchable = true
        contentView = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            minimumWidth = MIN_WIDTH_DP.toPx(context)
            items.forEachIndexed { index, (labelRes, item) ->
                addView(TextView(context).apply {
                    text = context.getString(labelRes)
                    textSize = 15f
                    gravity = Gravity.CENTER_VERTICAL or Gravity.START
                    isSingleLine = true
                    setPadding(12.toPx(context), 10.toPx(context), 12.toPx(context), 10.toPx(context))
                    setTextColor(context.getColorStateListCompat(R.color.popup_menu_item_text))
                    background = context.getDrawableCompat(R.drawable.popup_menu_item_background)
                    isSelected = item == selectedItem
                    setOnClickListener {
                        dismiss()
                        onItemSelected(item)
                    }
                }, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .apply { if (index > 0) topMargin = 2.toPx(context) })
            }
        }
    }

    /**
     * 在锚点下方显示
     * @param anchor 锚点视图
     */
    fun show(anchor: View) {
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val backgroundPadding = Rect().also { background?.getPadding(it) }
        width = if (widthMatchedToAnchor)
            anchor.width
        else contentView.measuredWidth + backgroundPadding.left + backgroundPadding.right
        showAsDropDown(anchor, 0, VERTICAL_OFFSET_DP.toPx(anchor.context), Gravity.END)
    }
}