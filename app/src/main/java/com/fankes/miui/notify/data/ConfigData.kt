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

package com.fankes.miui.notify.data

import android.content.Context
import com.fankes.miui.notify.const.IconRuleSourceSyncType
import com.highcapable.anip.sdk.config.RemoteSource
import com.highcapable.yukihookapi.hook.factory.preferences
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.highcapable.yukihookapi.hook.xposed.preference.data.PreferenceData

/**
 * 全局配置存储控制类
 */
object ConfigData {

    /** 启动时展示项目推广 */
    val SHOW_PROJECT_PROMOTION = PreferenceData("_show_project_promotion", true)

    /** 启用模块日志 */
    val ENABLE_MODULE_LOG = PreferenceData("_enable_module_log", false)

    /** 解除状态栏通知图标个数限制 */
    val ENABLE_LIFTED_STATUS_ICON_COUNT = PreferenceData("_enable_hook_status_icon_count", true)

    /** 解除后的状态栏通知图标最大个数 */
    val LIFTED_STATUS_ICON_COUNT = PreferenceData("_hook_status_icon_count", 5)

    /** 启用通知图标兼容模式 */
    val ENABLE_COLOR_ICON_COMPAT = PreferenceData("_color_icon_compat", false)

    /** 状态栏中的通知图标暗色透明度 */
    val STATUS_ICON_DARK_ALPHA_LEVEL = PreferenceData("_status_icon_dark_alpha", 95)

    /** 状态栏中的通知图标亮色透明度 */
    val STATUS_ICON_LIGHT_ALPHA_LEVEL = PreferenceData("_status_icon_light_alpha", 95)

    /** 通知栏中的通知图标圆角程度 */
    val NOTIFY_ICON_CORNER_SIZE = PreferenceData("_notify_icon_corner", 15)

    /** 替换 MIUI 样式通知栏的通知图标 */
    val ENABLE_REPLACE_MIUI_STYLE_NOTIFY_ICON = PreferenceData("_replace_miui_style_notify_icon", true)

    /** 强制通知栏中的通知图标使用系统着色 */
    val ENABLE_NOTIFY_ICON_FORCE_SYSTEM_COLOR = PreferenceData("_notify_icon_force_system_color", false)

    /** 强制通知栏中的通知图标为 APP 图标 */
    val ENABLE_NOTIFY_ICON_FORCE_APP_ICON = PreferenceData("_notify_icon_force_app_icon", false)

    /** 启用通知图标优化 */
    val ENABLE_NOTIFY_ICON_FIX = PreferenceData("_notify_icon_fix", true)

    /** 强制启用焦点通知反色 */
    val ENABLE_FOCUS_NOTIFICATION_FIX = PreferenceData("_enable_focus_notification_fix", false)

    /** 使用占位符修补未适配的通知图标 */
    val ENABLE_NOTIFY_ICON_FIX_PLACEHOLDER = PreferenceData("_notify_icon_fix_placeholder", false)

    /** 提醒未适配通知图标的新安装应用 */
    val ENABLE_NOTIFY_ICON_FIX_NOTIFY = PreferenceData("_notify_icon_fix_notify", true)

    /** 启用通知图标优化名单自动更新 */
    val ENABLE_NOTIFY_ICON_FIX_AUTO = PreferenceData("_enable_notify_icon_fix_auto", true)

    /** 通知图标优化名单自动更新时间 */
    val NOTIFY_ICON_FIX_AUTO_TIME = PreferenceData("_notify_icon_fix_auto_time", "07:00")

    /** 通知图标优化名单同步方式 */
    val ICON_RULE_SOURCE_SYNC_TYPE = PreferenceData("_rule_source_sync_way", IconRuleSourceSyncType.GITHUB_PROXY_1)

    /** ANIP GitHub 存储库 */
    val ICON_RULE_REPOSITORY = PreferenceData("_rule_repository", RemoteSource.GITHUB_OFFICIAL_REPO_SLUG)

    /** 忽略 Android 版本过低提示 */
    val IGNORED_ANDROID_VERSION_TO_LOW = PreferenceData("_ignored_android_version_to_low", false)

    /** 当前实例 - [Context] or [PackageParam] */
    private var instance: Any? = null

    /**
     * 初始化存储控制类
     * @param instance 实例 - 只能是 [Context] or [PackageParam]
     * @throws IllegalStateException 如果类型错误
     */
    fun init(instance: Any) {
        when (instance) {
            is Context, is PackageParam -> this.instance = instance
            else -> error("Unknown type for init ConfigData")
        }
    }

    /**
     * 读取 [String] 数据
     * @param data 键值数据模板
     * @return [String]
     */
    private fun getString(data: PreferenceData<String>) = when (instance) {
        is Context -> (instance as Context).preferences().get(data)
        is PackageParam -> (instance as PackageParam).preferences.get(data)
        else -> error("Unknown type for get preferences data")
    }

    /**
     * 存入 [String] 数据
     * @param data 键值数据模板
     * @param value 键值内容
     */
    private fun putString(data: PreferenceData<String>, value: String) {
        when (instance) {
            is Context -> (instance as Context).preferences().edit { put(data, value) }
            is PackageParam -> YLog.warn("Not support for this method")
            else -> error("Unknown type for put preferences data")
        }
    }

    /**
     * 读取 [Int] 数据
     * @param data 键值数据模板
     * @return [Int]
     */
    internal fun getInt(data: PreferenceData<Int>) = when (instance) {
        is Context -> (instance as Context).preferences().get(data)
        is PackageParam -> (instance as PackageParam).preferences.get(data)
        else -> error("Unknown type for get preferences data")
    }

    /**
     * 存入 [Int] 数据
     * @param data 键值数据模板
     * @param value 键值内容
     */
    internal fun putInt(data: PreferenceData<Int>, value: Int) {
        when (instance) {
            is Context -> (instance as Context).preferences().edit { put(data, value) }
            is PackageParam -> YLog.warn("Not support for this method")
            else -> error("Unknown type for put preferences data")
        }
    }

    /**
     * 读取 [Boolean] 数据
     * @param data 键值数据模板
     * @return [Boolean]
     */
    internal fun getBoolean(data: PreferenceData<Boolean>) = when (instance) {
        is Context -> (instance as Context).preferences().get(data)
        is PackageParam -> (instance as PackageParam).preferences.get(data)
        else -> error("Unknown type for get preferences data")
    }

    /**
     * 存入 [Boolean] 数据
     * @param data 键值数据模板
     * @param value 键值内容
     */
    internal fun putBoolean(data: PreferenceData<Boolean>, value: Boolean) {
        when (instance) {
            is Context -> (instance as Context).preferences().edit { put(data, value) }
            is PackageParam -> YLog.warn("Not support for this method")
            else -> error("Unknown type for put preferences data")
        }
    }

    /**
     * 是否在启动时展示项目推广
     * @return [Boolean]
     */
    var isShowProjectPromotion
        get() = getBoolean(SHOW_PROJECT_PROMOTION)
        set(value) {
            putBoolean(SHOW_PROJECT_PROMOTION, value)
        }

    /**
     * 是否启用模块日志
     * @return [Boolean]
     */
    var isEnableModuleLog
        get() = getBoolean(ENABLE_MODULE_LOG)
        set(value) {
            putBoolean(ENABLE_MODULE_LOG, value)
        }

    /**
     * 是否解除状态栏通知图标个数限制
     * @return [Boolean]
     */
    var isEnableLiftedStatusIconCount
        get() = getBoolean(ENABLE_LIFTED_STATUS_ICON_COUNT)
        set(value) {
            putBoolean(ENABLE_LIFTED_STATUS_ICON_COUNT, value)
        }

    /**
     * 解除后的状态栏通知图标最大个数
     * @return [Int]
     */
    var liftedStatusIconCount
        get() = getInt(LIFTED_STATUS_ICON_COUNT)
        set(value) {
            putInt(LIFTED_STATUS_ICON_COUNT, value)
        }

    /**
     * 是否启用通知图标兼容模式
     * @return [Boolean]
     */
    var isEnableColorIconCompat
        get() = getBoolean(ENABLE_COLOR_ICON_COMPAT)
        set(value) {
            putBoolean(ENABLE_COLOR_ICON_COMPAT, value)
        }

    /**
     * 状态栏中的通知图标暗色透明度
     * @return [Int]
     */
    var statusIconDarkAlphaLevel
        get() = getInt(STATUS_ICON_DARK_ALPHA_LEVEL)
        set(value) {
            putInt(STATUS_ICON_DARK_ALPHA_LEVEL, value)
        }

    /**
     * 状态栏中的通知图标亮色透明度
     * @return [Int]
     */
    var statusIconLightAlphaLevel
        get() = getInt(STATUS_ICON_LIGHT_ALPHA_LEVEL)
        set(value) {
            putInt(STATUS_ICON_LIGHT_ALPHA_LEVEL, value)
        }

    /**
     * 通知栏中的通知图标圆角程度
     * @return [Int]
     */
    var notifyIconCornerSize
        get() = getInt(NOTIFY_ICON_CORNER_SIZE)
        set(value) {
            putInt(NOTIFY_ICON_CORNER_SIZE, value)
        }

    /**
     * 是否替换 MIUI 样式通知栏的通知图标
     * @return [Boolean]
     */
    var isEnableReplaceMiuiStyleNotifyIcon
        get() = getBoolean(ENABLE_REPLACE_MIUI_STYLE_NOTIFY_ICON)
        set(value) {
            putBoolean(ENABLE_REPLACE_MIUI_STYLE_NOTIFY_ICON, value)
        }

    /**
     * 是否强制通知栏中的通知图标使用系统着色
     * @return [Boolean]
     */
    var isEnableNotifyIconForceSystemColor
        get() = getBoolean(ENABLE_NOTIFY_ICON_FORCE_SYSTEM_COLOR)
        set(value) {
            putBoolean(ENABLE_NOTIFY_ICON_FORCE_SYSTEM_COLOR, value)
        }

    /**
     * 是否强制通知栏中的通知图标为 APP 图标
     * @return [Boolean]
     */
    var isEnableNotifyIconForceAppIcon
        get() = getBoolean(ENABLE_NOTIFY_ICON_FORCE_APP_ICON)
        set(value) {
            putBoolean(ENABLE_NOTIFY_ICON_FORCE_APP_ICON, value)
        }

    /**
     * 是否启用通知图标优化
     * @return [Boolean]
     */
    var isEnableNotifyIconFix
        get() = getBoolean(ENABLE_NOTIFY_ICON_FIX)
        set(value) {
            putBoolean(ENABLE_NOTIFY_ICON_FIX, value)
        }

    /**
     * 是否强制启用焦点通知反色
     * @return [Boolean]*/
    var isEnableFocusNotificationFix
        get() = getBoolean(ENABLE_FOCUS_NOTIFICATION_FIX)
        set(value) {
            putBoolean(ENABLE_FOCUS_NOTIFICATION_FIX, value)
        }

    /**
     * 是否使用占位符修补未适配的通知图标
     * @return [Boolean]
     */
    var isEnableNotifyIconFixPlaceholder
        get() = getBoolean(ENABLE_NOTIFY_ICON_FIX_PLACEHOLDER)
        set(value) {
            putBoolean(ENABLE_NOTIFY_ICON_FIX_PLACEHOLDER, value)
        }

    /**
     * 是否提醒未适配通知图标的新安装应用
     * @return [Boolean]
     */
    var isEnableNotifyIconFixNotify
        get() = getBoolean(ENABLE_NOTIFY_ICON_FIX_NOTIFY)
        set(value) {
            putBoolean(ENABLE_NOTIFY_ICON_FIX_NOTIFY, value)
        }

    /**
     * 是否启用通知图标优化名单自动更新
     * @return [Boolean]
     */
    var isEnableNotifyIconFixAuto
        get() = getBoolean(ENABLE_NOTIFY_ICON_FIX_AUTO)
        set(value) {
            putBoolean(ENABLE_NOTIFY_ICON_FIX_AUTO, value)
        }

    /**
     * 通知图标优化名单自动更新时间
     * @return [String]
     */
    var notifyIconFixAutoTime
        get() = getString(NOTIFY_ICON_FIX_AUTO_TIME)
        set(value) {
            putString(NOTIFY_ICON_FIX_AUTO_TIME, value)
        }

    /**
     * 通知图标优化名单同步方式
     * @return [Int]
     */
    var iconRuleSourceSyncType
        get() = getInt(ICON_RULE_SOURCE_SYNC_TYPE)
        set(value) {
            putInt(ICON_RULE_SOURCE_SYNC_TYPE, value)
        }

    /**
     * ANIP GitHub 存储库
     * @return [String]
     */
    var iconRuleRepository
        get() = getString(ICON_RULE_REPOSITORY).ifBlank { RemoteSource.GITHUB_OFFICIAL_REPO_SLUG }
        set(value) {
            putString(ICON_RULE_REPOSITORY, value)
        }

    /**
     * 是否忽略 Android 版本过低提示
     * @return [Boolean]
     */
    var isIgnoredAndroidVersionToLow
        get() = getBoolean(IGNORED_ANDROID_VERSION_TO_LOW)
        set(value) {
            putBoolean(IGNORED_ANDROID_VERSION_TO_LOW, value)
        }
}