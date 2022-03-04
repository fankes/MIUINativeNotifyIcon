/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2019-2022 Fankes Studio(qzmmcn@163.com)
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
 * This file is Created by fankes on 2022/1/24.
 */
package com.fankes.miui.notify.params

import android.content.Context
import android.graphics.Color
import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.hook.HookConst.NOTIFY_ICON_DATAS
import com.fankes.miui.notify.utils.bitmap
import com.fankes.miui.notify.utils.safeOf
import com.fankes.miui.notify.utils.safeOfNan
import com.highcapable.yukihookapi.hook.factory.modulePrefs
import com.highcapable.yukihookapi.hook.param.PackageParam
import org.json.JSONArray
import org.json.JSONObject

/**
 * 通知栏小图标适配类
 *
 * 国内 APP 不规范的图标将由这里完成其自定义单色小图标绘制
 * @param context 实例 - 二选一
 * @param param 实例 - 二选一
 */
class IconPackParams(private val context: Context? = null, private val param: PackageParam? = null) {

    /**
     * 已存储的 JSON 数据
     * @return [String]
     */
    private val storageDataJson get() = (context?.modulePrefs ?: param?.prefs)?.getString(NOTIFY_ICON_DATAS)

    /**
     * 获取图标数据
     * @return [Array] 通知栏小图标数组
     */
    val iconDatas
        get() = ArrayList<IconDataBean>().apply {
            storageDataJson?.also {
                if (it.isNotBlank()) runCatching {
                    JSONArray(it).also { array ->
                        for (i in 0 until array.length()) runCatching {
                            (array.get(i) as JSONObject).apply {
                                add(
                                    IconDataBean(
                                        appName = getString("appName"),
                                        packageName = getString("packageName"),
                                        isEnabled = getBoolean("isEnabled"),
                                        isEnabledAll = getBoolean("isEnabledAll"),
                                        iconBitmap = getString("iconBitmap").bitmap,
                                        iconColor = safeOfNan { Color.parseColor(getString("iconColor")) },
                                        contributorName = getString("contributorName")
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

    /**
     * 拼接图标数组数据
     * @param dataJson1 图标数据 JSON
     * @param dataJson2 图标数据 JSON
     * @return [String] 拼接后的数据
     */
    fun splicingJsonArray(dataJson1: String, dataJson2: String) = safeOf(default = "[]") {
        dataJson1.replace(oldValue = "]", newValue = "") + "," + dataJson2.replace(oldValue = "[", newValue = "")
    }

    /**
     * 是否不为合法 JSON
     * @param json 数据
     * @return [Boolean]
     */
    fun isNotVaildJson(json: String) = json.trim().let { !it.startsWith("[") || !it.endsWith("]") }

    /**
     * 是否为异常地址
     * @param json 数据
     * @return [Boolean]
     */
    fun isHackString(json: String) = json.contains(other = "Checking your browser before accessing")

    /**
     * 比较图标数据不相等
     * @param dataJson 图标数据 JSON
     * @return [Boolean] 是否不相等
     */
    fun isCompareDifferent(dataJson: String) = storageDataJson?.trim() != dataJson.trim()

    /**
     * 保存图标数据
     * @param dataJson 图标数据 JSON
     */
    fun save(dataJson: String) = context?.modulePrefs?.putString(NOTIFY_ICON_DATAS, dataJson)
}
