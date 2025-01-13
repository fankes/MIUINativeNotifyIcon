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
 * This file is created by fankes on 2022/1/30.
 */
package com.fankes.miui.notify.bean

import android.graphics.Bitmap
import com.fankes.miui.notify.utils.factory.base64
import java.io.Serializable

/**
 * 通知栏小图标 bean
 * @param appName APP 名称
 * @param packageName 包名
 * @param iconBitmap 图标位图
 * @param iconColor 通知栏中显示的图标颜色 - 设置为 0 使用系统默认颜色
 * @param contributorName 贡献者昵称
 * @param isEnabled 是否默认启用替换彩色图标 - 关闭后将全部停止替换
 * @param isEnabledAll 是否默认启用替换全部图标
 */
data class IconDataBean(
    var appName: String,
    var packageName: String,
    var iconBitmap: Bitmap,
    var iconColor: Int = 0,
    var contributorName: String,
    var isEnabled: Boolean,
    var isEnabledAll: Boolean,
) : Serializable {
    fun toEnabledName() = ("$appName$packageName").base64 + "_enable"
    fun toEnabledAllName() = ("$appName$packageName").base64 + "_enable_all"
    override fun toString() = """
      {
        "appName": "$appName",
        "packageName": "$packageName",
        "iconBitmap": "${iconBitmap.base64}",
        "iconColor": "#${Integer.toHexString(iconColor)}",
        "contributorName": "$contributorName",
        "isEnabled": $isEnabled,
        "isEnabledAll": $isEnabledAll
      }
    """.trimIndent()
}