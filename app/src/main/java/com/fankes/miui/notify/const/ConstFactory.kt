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
 * This file is Created by fankes on 2023/2/2.
 */
package com.fankes.miui.notify.const

/**
 * 包名常量定义类
 */
object PackageName {

    /** 系统界面 (系统 UI) */
    const val SYSTEMUI = "com.android.systemui"
}

/**
 * 通知图标优化名单同步方式定义类
 */
object IconRuleSourceSyncType {

    /** Github Raw (代理) */
    const val GITHUB_RAW_PROXY = 1000

    /** Github Raw (直连) */
    const val GITHUB_RAW_DIRECT = 2000

    /** 自定义地址 */
    const val CUSTOM_URL = 3000
}