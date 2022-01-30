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
 * This file is Created by fankes on 2022/1/24.
 */
package com.fankes.miui.notify.hook

import android.util.Log
import androidx.annotation.Keep

@Keep
object HookMedium {

    const val ENABLE_MODULE = "_enable_module"
    const val ENABLE_HIDE_ICON = "_hide_icon"
    const val ENABLE_COLOR_ICON_HOOK = "_color_icon_hook"
    const val ENABLE_CHAT_ICON_HOOK = "_chat_icon_hook"

    const val SELF_PACKAGE_NAME = "com.fankes.miui.notify"
    const val SYSTEMUI_PACKAGE_NAME = "com.android.systemui"
    const val QQ_PACKAGE_NAME = "com.tencent.mobileqq"

    /**
     * 判断模块是否激活
     * 在 [HookMain] 中 Hook 掉此方法
     * @return [Boolean] 激活状态
     */
    fun isHooked(): Boolean {
        Log.d("MIUINativeNotifyIcon", "isHooked: true")
        return false
    }
}