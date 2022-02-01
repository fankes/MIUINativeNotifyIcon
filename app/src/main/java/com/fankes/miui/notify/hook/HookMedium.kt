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
@file:Suppress("DEPRECATION", "SetWorldReadable")

package com.fankes.miui.notify.hook

import android.content.Context
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.annotation.Keep
import com.fankes.miui.notify.application.MNNApplication.Companion.appContext
import com.fankes.miui.notify.application.MNNApplication.Companion.isMineStarted
import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.utils.FileUtils
import com.fankes.miui.notify.utils.XPrefUtils
import java.io.File

@Keep
object HookMedium {

    const val ENABLE_MODULE = "_enable_module"
    const val ENABLE_MODULE_LOG = "_enable_module_log"
    const val ENABLE_HIDE_ICON = "_hide_icon"
    const val ENABLE_COLOR_ICON_HOOK = "_color_icon_hook"
    const val ENABLE_NOTIFY_ICON_HOOK = "_notify_icon_hook"

    const val SELF_PACKAGE_NAME = "com.fankes.miui.notify"
    const val SYSTEMUI_PACKAGE_NAME = "com.android.systemui"

    /**
     * 判断模块是否激活
     * 在 [HookMain] 中 Hook 掉此方法
     * @return [Boolean] 激活状态
     */
    fun isHooked(): Boolean {
        Log.d("MIUINativeNotifyIcon", "isHooked: true")
        return false
    }

    /**
     * 获取此 APP 的通知图标是否被 Hook
     * @param bean 图标 bean
     */
    fun isAppNotifyHookOf(bean: IconDataBean) = getBoolean(key = bean.toEnabledName(), default = bean.isEnabled)

    /**
     * 设置 Hook 此 APP 的通知图标
     * @param bean 图标 bean
     * @param isHook 是否 Hook
     */
    fun putAppNotifyHookOf(bean: IconDataBean, isHook: Boolean) = putBoolean(key = bean.toEnabledName(), bool = isHook)

    /**
     * 获取此 APP 的通知图标是否被全部 Hook
     * @param bean 图标 bean
     */
    fun isAppNotifyHookAllOf(bean: IconDataBean) = getBoolean(key = bean.toEnabledAllName(), default = bean.isEnabledAll)

    /**
     * 设置全部 Hook 此 APP 的通知图标
     * @param bean 图标 bean
     * @param isHook 是否 Hook
     */
    fun putAppNotifyHookAllOf(bean: IconDataBean, isHook: Boolean) = putBoolean(key = bean.toEnabledAllName(), bool = isHook)

    /**
     * 获取保存的值
     * @param key 名称
     * @param default 默认值
     * @return [Boolean] 保存的值
     */
    fun getBoolean(key: String, default: Boolean = false) =
        if (isMineStarted)
            appContext.getSharedPreferences(
                appContext.packageName + "_preferences",
                Context.MODE_PRIVATE
            ).getBoolean(key, default)
        else XPrefUtils.getBoolean(key, default)

    /**
     * 保存值
     * @param key 名称
     * @param bool 值
     */
    fun putBoolean(key: String, bool: Boolean) {
        appContext.getSharedPreferences(
            appContext.packageName + "_preferences",
            Context.MODE_PRIVATE
        ).edit().putBoolean(key, bool).apply()
        setWorldReadable(appContext)
        /** 延迟继续设置强制允许 SP 可读可写 */
        Handler().postDelayed({ setWorldReadable(appContext) }, 500)
        Handler().postDelayed({ setWorldReadable(appContext) }, 1000)
        Handler().postDelayed({ setWorldReadable(appContext) }, 1500)
    }

    /**
     * 强制设置 Sp 存储为全局可读可写
     * 以供模块使用
     * @param context 实例
     */
    fun setWorldReadable(context: Context) {
        try {
            if (FileUtils.getDefaultPrefFile(context).exists()) {
                for (file in arrayOf<File>(
                    FileUtils.getDataDir(context),
                    FileUtils.getPrefDir(context),
                    FileUtils.getDefaultPrefFile(context)
                )) {
                    file.setReadable(true, false)
                    file.setExecutable(true, false)
                }
            }
        } catch (_: Exception) {
            Toast.makeText(context, "无法写入模块设置，请检查权限\n如果此提示一直显示，请不要双开模块", Toast.LENGTH_SHORT).show()
        }
    }
}