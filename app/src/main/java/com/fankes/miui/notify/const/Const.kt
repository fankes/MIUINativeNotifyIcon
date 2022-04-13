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
 * This file is Created by fankes on 2022/3/24.
 */
@file:Suppress("MemberVisibilityCanBePrivate")

package com.fankes.miui.notify.const

import com.fankes.miui.notify.BuildConfig

/**
 * 存储一些静态编译后的值
 */
object Const {

    /** 当前模块的包名 */
    const val MODULE_PACKAGE_NAME = BuildConfig.APPLICATION_ID

    /** 当前模块的版本名称 */
    const val MODULE_VERSION_NAME = BuildConfig.VERSION_NAME

    /** 当前模块的版本号 */
    const val MODULE_VERSION_CODE = BuildConfig.VERSION_CODE

    /** 当前模块的版本校验 */
    const val MODULE_VERSION_VERIFY = "${MODULE_VERSION_NAME}_${MODULE_VERSION_CODE}_202204140309"

    /** 当前模块的版本校验标签 */
    const val MODULE_VERSION_VERIFY_TAG = "module_version_verify"

    /** 发送通讯广播号标签 - 模块激活状态 */
    const val ACTION_MODULE_CHECKING_RECEIVER = "mnn_module_checking_action"

    /** 接收通讯广播号标签 - 模块激活状态 */
    const val ACTION_MODULE_HANDLER_RECEIVER = "mnn_module_handler_action"

    /** 发送通讯广播号标签 - 通知系统界面刷新 */
    const val ACTION_REMIND_CHECKING_RECEIVER = "mnn_remind_checking_action"

    /** 接收通讯广播号标签 - 通知系统界面刷新 */
    const val ACTION_REMIND_HANDLER_RECEIVER = "mnn_remind_handler_action"
}