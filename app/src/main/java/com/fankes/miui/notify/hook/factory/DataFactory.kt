/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2019-2022 Fankes Studio(qzmmcn@163.com)
 * https://github.com/fankes/MIUINativeNotifyIcon
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version and our eula as published
 * by ferredoxin.
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
 * This file is Created by fankes on 2022/2/15.
 */
package com.fankes.miui.notify.hook.factory

import android.content.Context
import com.fankes.miui.notify.bean.IconDataBean
import com.highcapable.yukihookapi.hook.factory.modulePrefs
import com.highcapable.yukihookapi.hook.param.PackageParam

/**
 * 获取此 APP 的通知图标是否被 Hook
 * @param bean 图标 bean
 */
fun PackageParam.isAppNotifyHookOf(bean: IconDataBean) = prefs.getBoolean(key = bean.toEnabledName(), default = bean.isEnabled)

/**
 * 获取此 APP 的通知图标是否被 Hook
 * @param bean 图标 bean
 */
fun Context.isAppNotifyHookOf(bean: IconDataBean) = modulePrefs.getBoolean(key = bean.toEnabledName(), default = bean.isEnabled)

/**
 * 设置 Hook 此 APP 的通知图标
 * @param bean 图标 bean
 * @param isHook 是否 Hook
 */
fun Context.putAppNotifyHookOf(bean: IconDataBean, isHook: Boolean) =
    modulePrefs.putBoolean(key = bean.toEnabledName(), value = isHook)

/**
 * 获取此 APP 的通知图标是否被全部 Hook
 * @param bean 图标 bean
 */
fun PackageParam.isAppNotifyHookAllOf(bean: IconDataBean) =
    prefs.getBoolean(key = bean.toEnabledAllName(), default = bean.isEnabledAll)

/**
 * 获取此 APP 的通知图标是否被全部 Hook
 * @param bean 图标 bean
 */
fun Context.isAppNotifyHookAllOf(bean: IconDataBean) =
    modulePrefs.getBoolean(key = bean.toEnabledAllName(), default = bean.isEnabledAll)

/**
 * 设置全部 Hook 此 APP 的通知图标
 * @param bean 图标 bean
 * @param isHook 是否 Hook
 */
fun Context.putAppNotifyHookAllOf(bean: IconDataBean, isHook: Boolean) =
    modulePrefs.putBoolean(key = bean.toEnabledAllName(), value = isHook)