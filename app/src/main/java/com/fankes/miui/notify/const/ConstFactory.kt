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
 * This file is created by fankes on 2023/2/2.
 */
@file:Suppress("MemberVisibilityCanBePrivate")

package com.fankes.miui.notify.const

import com.fankes.miui.notify.generated.AppProperties
import com.fankes.miui.notify.wrapper.BuildConfigWrapper

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

    /** GitHub Raw (代理 - GitHub Proxy) */
    const val GITHUB_RAW_PROXY_1 = 500

    /** GitHub Raw (代理 - 7ED Services) */
    const val GITHUB_RAW_PROXY_2 = 1000

    /** GitHub Raw (直连) */
    const val GITHUB_RAW_DIRECT = 2000

    /** 自定义地址 */
    const val CUSTOM_URL = 3000
}

/**
 * 模块版本常量定义类
 */
object ModuleVersion {

    /** 当前 GitHub 提交的 ID (CI 自动构建) */
    const val GITHUB_COMMIT_ID = AppProperties.GITHUB_CI_COMMIT_ID

    /** 版本名称 */
    const val NAME = BuildConfigWrapper.VERSION_NAME

    /** 版本号 */
    const val CODE = BuildConfigWrapper.VERSION_CODE

    /** 是否为 CI 自动构建版本 */
    val isCiMode = GITHUB_COMMIT_ID.isNotBlank()

    /** 当前版本名称后缀 */
    val suffix = GITHUB_COMMIT_ID.let { if (it.isNotBlank()) "-$it" else "" }

    override fun toString() = "$NAME$suffix($CODE)"
}