/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2017-2023 Fankes Studio(qzmmcn@163.com)
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
 * This file is Created by fankes on 2023/2/3.
 */
package com.fankes.miui.notify.utils.tool

import android.content.Context
import com.fankes.miui.notify.utils.factory.showDialog
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.xposed.prefs.data.PrefsData
import java.util.Locale

/**
 * I18n 适配警告提示工具类
 */
object I18nWarnTool {

    /** 推广已读存储键值 */
    private val LOCALE_WARN_READED = PrefsData("locale_warn_readed", false)

    /**
     * 检查并显示 I18n 适配警告对话框
     * @param context 实例
     */
    fun checkingOrShowing(context: Context) {
        fun saveReaded() = context.prefs().edit { put(LOCALE_WARN_READED, value = true) }
        if (Locale.getDefault().language.startsWith("zh").not() && context.prefs().get(LOCALE_WARN_READED).not())
            context.showDialog {
                title = "Notice of I18n Support"
                msg = "This Xposed Module is only for Chinese and the Chinese region.\n\n" +
                    "Currently, there will be no internationalization adaptation.\n\n" +
                    "There may be plans for internationalization adaptation in the future, so stay tuned."
                confirmButton(text = "Got It") { saveReaded() }
                noCancelable()
            }
    }
}