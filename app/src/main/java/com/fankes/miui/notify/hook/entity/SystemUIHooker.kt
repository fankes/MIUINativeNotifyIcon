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
 * This file is Created by fankes on 2022/3/25.
 */
@file:Suppress("StaticFieldLeak")

package com.fankes.miui.notify.hook.entity

import android.app.NotificationManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.os.SystemClock
import android.service.notification.StatusBarNotification
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.children
import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.data.DataConst
import com.fankes.miui.notify.hook.HookConst.SYSTEMUI_PACKAGE_NAME
import com.fankes.miui.notify.hook.factory.isAppNotifyHookAllOf
import com.fankes.miui.notify.hook.factory.isAppNotifyHookOf
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.utils.drawable.drawabletoolbox.DrawableBuilder
import com.fankes.miui.notify.utils.factory.*
import com.fankes.miui.notify.utils.tool.BitmapCompatTool
import com.fankes.miui.notify.utils.tool.IconAdaptationTool
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.highcapable.yukihookapi.hook.bean.VariousClass
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.*
import com.highcapable.yukihookapi.hook.log.loggerD
import com.highcapable.yukihookapi.hook.log.loggerW
import com.highcapable.yukihookapi.hook.type.android.*
import com.highcapable.yukihookapi.hook.type.java.BooleanType
import com.highcapable.yukihookapi.hook.type.java.IntType

/**
 * 系统界面核心 Hook 类
 */
object SystemUIHooker : YukiBaseHooker() {

    /** MIUI 新版本存在的类 */
    private const val SystemUIApplicationClass = "$SYSTEMUI_PACKAGE_NAME.SystemUIApplication"

    /** MIUI 新版本存在的类 */
    private const val NotificationHeaderViewWrapperInjectorClass =
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.row.wrapper.NotificationHeaderViewWrapperInjector"

    /** MIUI 新版本存在的类 */
    private const val MiuiNotificationViewWrapperClass =
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.row.wrapper.MiuiNotificationViewWrapper"

    /** MIUI 新版本存在的类 */
    private const val MiuiNotificationChildrenContainerClass =
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.stack.MiuiNotificationChildrenContainer"

    /** 原生存在的类 */
    private const val NotificationChildrenContainerClass =
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.stack.NotificationChildrenContainer"

    /** 原生存在的类 */
    private const val ContrastColorUtilClass = "com.android.internal.util.ContrastColorUtil"

    /** 原生存在的类 */
    private const val StatusBarIconViewClass = "$SYSTEMUI_PACKAGE_NAME.statusbar.StatusBarIconView"

    /** 原生存在的类 */
    private const val NotificationIconContainerClass = "$SYSTEMUI_PACKAGE_NAME.statusbar.phone.NotificationIconContainer"

    /** 原生存在的类 */
    private const val PluginManagerImplClass = "$SYSTEMUI_PACKAGE_NAME.shared.plugins.PluginManagerImpl"

    /** 根据多个版本存在不同的包名相同的类 */
    private val MiuiClockClass = VariousClass(
        "$SYSTEMUI_PACKAGE_NAME.statusbar.views.MiuiClock",
        "$SYSTEMUI_PACKAGE_NAME.statusbar.policy.MiuiClock"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val StatusBarNotificationPresenterClass = VariousClass(
        "$SYSTEMUI_PACKAGE_NAME.statusbar.phone.StatusBarNotificationPresenter",
        "$SYSTEMUI_PACKAGE_NAME.statusbar.phone.StatusBar"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val ExpandableNotificationRowClass = VariousClass(
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.row.ExpandableNotificationRow",
        "$SYSTEMUI_PACKAGE_NAME.statusbar.ExpandableNotificationRow"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationViewWrapperClass = VariousClass(
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.row.wrapper.NotificationViewWrapper",
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.NotificationViewWrapper"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationHeaderViewWrapperClass = VariousClass(
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper",
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.NotificationHeaderViewWrapper"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationUtilClass = VariousClass(
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.NotificationUtil",
        "$SYSTEMUI_PACKAGE_NAME.miui.statusbar.notification.NotificationUtil"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val ExpandedNotificationClass = VariousClass(
        "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.ExpandedNotification",
        "$SYSTEMUI_PACKAGE_NAME.miui.statusbar.ExpandedNotification"
    )

    /** 缓存的通知图标优化数组 */
    private var iconDatas = ArrayList<IconDataBean>()

    /** 是否显示通知图标 - 跟随 Hook 保存 */
    private var isShowNotificationIcons = true

    /** 是否已经使用过缓存刷新功能 */
    private var isUsingCachingMethod = false

    /** 状态栏通知图标容器 */
    private var notificationIconContainer: ViewGroup? = null

    /** 通知栏通知控制器 */
    private var notificationPresenter: Any? = null

    /** 仅监听一次主题壁纸颜色变化 */
    private var isWallpaperColorListenerSetUp = false

    /**
     * 是否启用通知图标优化功能
     * @param isHooking 是否判断启用通知功能 - 默认：是
     * @return [Boolean]
     */
    private fun isEnableHookColorNotifyIcon(isHooking: Boolean = true) =
        prefs.get(DataConst.ENABLE_NOTIFY_ICON_FIX) && (if (isHooking) prefs.get(DataConst.ENABLE_NOTIFY_ICON_FIX_NOTIFY) else true)

    /**
     * - 这个是修复彩色图标的关键核心代码判断
     *
     * 判断是否为灰度图标 - 反射执行系统方法
     * @param context 实例
     * @param drawable 要判断的图标
     * @return [Boolean]
     */
    private fun isGrayscaleIcon(context: Context, drawable: Drawable) =
        if (prefs.get(DataConst.ENABLE_COLOR_ICON_COMPAT).not()) safeOfFalse {
            ContrastColorUtilClass.clazz.let {
                it.method {
                    name = "isGrayscaleIcon"
                    param(DrawableClass)
                }.get(it.method {
                    name = "getInstance"
                    param(ContextClass)
                }.get().invoke(context)).boolean(drawable)
            }
        } else BitmapCompatTool.isGrayscaleDrawable(drawable)

    /**
     * 是否为旧版本 MIUI 方案
     *
     * 拥有 “handleHeaderViews” 方法
     * @return [Boolean]
     */
    private val hasHandleHeaderViews
        get() = safeOfFalse { NotificationHeaderViewWrapperClass.clazz.hasMethod { name = "handleHeaderViews" } }

    /**
     * 获取是否存在忽略图标色彩处理的方法
     * @return [Boolean]
     */
    private val hasIgnoreStatusBarIconColor
        get() = safeOfFalse {
            NotificationUtilClass.clazz.hasMethod {
                name = "ignoreStatusBarIconColor"
                param(ExpandedNotificationClass)
            }
        }

    /**
     * 处理为圆角图标
     * @return [Drawable]
     */
    private fun Drawable.rounded(context: Context) =
        safeOf(default = this) { BitmapDrawable(context.resources, toBitmap().round(10.dpFloat(context))) }

    /**
     * 适配通知栏、状态栏来自系统推送的彩色 APP 图标
     *
     * 适配第三方图标包对系统包管理器更换图标后的彩色图标
     * @param context 实例
     * @param iconDrawable 原始图标
     * @return [Drawable] 适配的图标
     */
    private fun StatusBarNotification.compatPushingIcon(context: Context, iconDrawable: Drawable) = safeOf(iconDrawable) {
        /** 给 MIPUSH 设置 APP 自己的图标 */
        if (isXmsf && nfPkgName.isNotBlank())
            context.findAppIcon(xmsfPkgName) ?: iconDrawable
        else iconDrawable
    }

    /**
     * 打印日志
     * @param tag 标识
     * @param context 实例
     * @param expandedNf 通知实例
     * @param isCustom 是否为通知优化生效图标
     * @param isGrayscale 是否为灰度图标
     */
    private fun printLogcat(
        tag: String,
        context: Context,
        expandedNf: StatusBarNotification?,
        isCustom: Boolean,
        isGrayscale: Boolean
    ) {
        if (prefs.get(DataConst.ENABLE_MODULE_LOG)) loggerD(
            msg = "$tag --> [${context.findAppName(name = expandedNf?.nfPkgName ?: "")}][${expandedNf?.nfPkgName}] " +
                    "custom [$isCustom] " +
                    "grayscale [$isGrayscale] " +
                    "xmsf [${expandedNf?.isXmsf}]"
        )
    }

    /**
     * 获取推送通知的包名
     *
     * 自动兼容旧版本系统
     * @return [String]
     */
    private val StatusBarNotification.compatOpPkgName
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) opPkg else packageName ?: ""

    /**
     * 判断通知是否来自 MIPUSH
     * @return [Boolean]
     */
    private val StatusBarNotification.isXmsf get() = compatOpPkgName == "com.xiaomi.xmsf"

    /**
     * 获取推送通知的包名
     *
     * 自动判断 MIPUSH
     * @return [String]
     */
    private val StatusBarNotification.nfPkgName get() = if (isXmsf) xmsfPkgName else packageName

    /**
     * 获取 MIPUSH 通知真实包名
     * @return [String]
     */
    private val StatusBarNotification.xmsfPkgName: String
        get() {
            val xmsfPkg = notification.extras.getString("xmsf_target_package") ?: ""
            val targetPkg = notification.extras.getString("target_package") ?: ""
            return xmsfPkg.ifBlank { targetPkg.ifBlank { packageName } }
        }

    /**
     * 获取全局上下文
     * @return [Context] or null
     */
    private val globalContext
        get() = safeOfNull {
            SystemUIApplicationClass.clazz.method { name = "getContext" }.ignoredError().get().invoke<Context?>() ?: appContext
        }

    /**
     * 注册主题壁纸改变颜色监听
     *
     *  - 仅限在 Android 12 以下注册
     * @param view 实例
     */
    private fun registerWallpaperColorChanged(view: View) = runInSafe {
        if (isWallpaperColorListenerSetUp.not() && isUpperOfAndroidS.not()) view.apply {
            WallpaperManager.getInstance(context).addOnColorsChangedListener({ _, _ -> refreshNotificationIcons() }, handler)
        }
        isWallpaperColorListenerSetUp = true
    }

    /** 刷新状态栏小图标 */
    private fun refreshStatusBarIcons() = runInSafe {
        StatusBarIconViewClass.clazz.field { name = "mNotification" }.also { result ->
            notificationIconContainer?.children?.forEach {
                /** 得到通知实例 */
                val nf = result.get(it).cast<StatusBarNotification>() ?: return
                /** 刷新状态栏图标 */
                compatStatusIcon(it.context, nf, nf.notification.smallIcon.loadDrawable(it.context)).also { pair ->
                    pair.first.let { e -> (it as? ImageView?)?.setImageDrawable(e) }
                }
            }
        }
    }

    /** 刷新通知小图标 */
    private fun refreshNotificationIcons() = runInSafe {
        notificationPresenter?.current {
            method {
                name = "updateNotificationsOnDensityOrFontScaleChanged"
                emptyParam()
            }.call()
        }
    }

    /**
     * 自动适配状态栏、通知栏自定义小图标
     * @param isGrayscaleIcon 是否为灰度图标
     * @param packageName APP 包名
     * @return [Pair] - ([Bitmap] 位图,[Int] 颜色)
     */
    private fun compatCustomIcon(isGrayscaleIcon: Boolean, packageName: String): Pair<Bitmap?, Int> {
        var customPair: Pair<Bitmap?, Int>? = null
        if (prefs.get(DataConst.ENABLE_NOTIFY_ICON_FIX)) run {
            iconDatas.takeIf { it.isNotEmpty() }?.forEach {
                if (packageName == it.packageName && isAppNotifyHookOf(it)) {
                    if (isGrayscaleIcon.not() || isAppNotifyHookAllOf(it))
                        customPair = Pair(it.iconBitmap, it.iconColor)
                    return@run
                }
            }
        }
        return customPair ?: Pair(null, 0)
    }

    /**
     * Hook 状态栏小图标
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param expandedNf 通知实例
     * @param iconDrawable 小图标 [Drawable]
     * @return [Pair] 回调小图标 - ([Drawable] 小图标,[Boolean] 是否替换)
     */
    private fun compatStatusIcon(context: Context, expandedNf: StatusBarNotification?, iconDrawable: Drawable?) =
        expandedNf?.let { notifyInstance ->
            if (iconDrawable == null) return@let Pair(null, false)
            /** 判断是否不是灰度图标 */
            val isNotGrayscaleIcon = notifyInstance.isXmsf || isGrayscaleIcon(context, iconDrawable).not()

            /** 目标彩色通知 APP 图标 */
            val customIcon = compatCustomIcon(isNotGrayscaleIcon.not(), notifyInstance.nfPkgName).first
            /** 打印日志 */
            printLogcat(tag = "StatusIcon", context, notifyInstance, isCustom = customIcon != null, isNotGrayscaleIcon.not())
            when {
                /** 处理自定义通知图标优化 */
                customIcon != null -> Pair(BitmapDrawable(context.resources, customIcon), true)
                /** 若不是灰度图标自动处理为圆角 */
                isNotGrayscaleIcon -> Pair(notifyInstance.compatPushingIcon(context, iconDrawable).rounded(context), true)
                /** 否则返回原始小图标 */
                else -> Pair(notifyInstance.notification.smallIcon.loadDrawable(context), false)
            }
        } ?: Pair(null, false)

    /**
     * Hook 通知栏小图标
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param expandedNf 通知实例
     * @param iconImageView 通知图标实例
     * @param isExpanded 通知是否展开 - 可做最小化通知处理 - 默认：是
     * @param isUseAndroid12Style 是否使用 Android 12 通知图标风格 - 默认跟随系统版本决定
     */
    private fun compatNotifyIcon(
        context: Context,
        expandedNf: StatusBarNotification?,
        iconImageView: ImageView,
        isExpanded: Boolean = true,
        isUseAndroid12Style: Boolean = isUpperOfAndroidS,
    ) = runInSafe(msg = "compatNotifyIcon") {
        /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
        expandedNf?.let { notifyInstance ->

            /** 新版风格反色 */
            val newStyle = if (context.isSystemInDarkMode) 0xFF2D2D2D.toInt() else Color.WHITE

            /** 旧版风格反色 */
            val oldStyle = if (context.isNotSystemInDarkMode) 0xFF707070.toInt() else Color.WHITE

            /** 通知图标原始颜色 */
            val iconColor = notifyInstance.notification.color

            /** 是否有通知栏图标颜色 */
            val hasIconColor = iconColor != 0

            /** 通知图标适配颜色 */
            val supportColor = iconColor.let {
                when {
                    isUseAndroid12Style -> newStyle
                    it == 0 || isExpanded.not() -> oldStyle
                    else -> it
                }
            }

            /** 获取通知小图标 */
            val iconDrawable = notifyInstance.notification.smallIcon.loadDrawable(context)

            /** 判断图标风格 */
            val isGrayscaleIcon = notifyInstance.isXmsf.not() && isGrayscaleIcon(context, iconDrawable)

            /** 自定义默认小图标 */
            var customIcon: Bitmap?

            /** 自定义默认小图标颜色 */
            var customIconColor: Int
            compatCustomIcon(isGrayscaleIcon, notifyInstance.nfPkgName).also {
                customIcon = it.first
                customIconColor = if (isUseAndroid12Style || isExpanded)
                    (it.second.takeIf { e -> e != 0 } ?: (if (isUseAndroid12Style) context.systemAccentColor else 0)) else 0
            }
            /** 打印日志 */
            printLogcat(tag = "NotifyIcon", context, notifyInstance, isCustom = customIcon != null, isGrayscaleIcon)
            /** 处理自定义通知图标优化 */
            if (customIcon != null) iconImageView.apply {
                /** 设置不要裁切到边界 */
                clipToOutline = false
                /** 设置自定义小图标 */
                setImageBitmap(customIcon)
                /** 上色 */
                setColorFilter(if (isUseAndroid12Style || customIconColor == 0) supportColor else customIconColor)
                /** Android 12 设置图标外圈颜色 */
                if (isUseAndroid12Style && customIconColor != 0)
                    background = DrawableBuilder()
                        .rounded()
                        .solidColor(if (context.isSystemInDarkMode) customIconColor.brighter else customIconColor)
                        .build()
                /** 设置原生的背景边距 */
                if (isUseAndroid12Style) setPadding(4.dp(context), 4.dp(context), 4.dp(context), 4.dp(context))
            } else {
                /** 重新设置图标 - 防止系统更改它 */
                iconImageView.setImageDrawable(iconDrawable)
                /** 判断如果是灰度图标就给他设置一个白色颜色遮罩 */
                if (isGrayscaleIcon) iconImageView.apply {
                    /** 设置不要裁切到边界 */
                    clipToOutline = false
                    /** 设置图标着色 */
                    setColorFilter(supportColor)
                    /** Android 12 设置图标外圈颜色 */
                    (if (hasIconColor) iconColor else context.systemAccentColor).also {
                        if (isUseAndroid12Style)
                            background = DrawableBuilder()
                                .rounded()
                                .solidColor(if (context.isSystemInDarkMode) it.brighter else it)
                                .build()
                    }
                    /** 设置原生的背景边距 */
                    if (isUseAndroid12Style) setPadding(4.dp(context), 4.dp(context), 4.dp(context), 4.dp(context))
                } else iconImageView.apply {
                    /** 重新设置图标 */
                    setImageDrawable(notifyInstance.compatPushingIcon(context, iconDrawable))
                    /** 设置裁切到边界 */
                    clipToOutline = true
                    /** 设置一个圆角轮廓裁切 */
                    outlineProvider = object : ViewOutlineProvider() {
                        override fun getOutline(view: View, out: Outline) {
                            out.setRoundRect(
                                0, 0,
                                view.width, view.height, 5.dpFloat(context)
                            )
                        }
                    }
                    if (isUseAndroid12Style) {
                        /** 清除原生的背景边距 */
                        setPadding(0, 0, 0, 0)
                        /** 清除原生的主题色背景圆圈颜色 */
                        background = null
                    }
                    /** 清除遮罩颜色 */
                    colorFilter = null
                }
            }
        }
    }

    /**
     * 判断状态栏小图标颜色以及反射的核心方法
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param expandedNf 状态栏实例
     * @return [Boolean] 是否忽略通知图标颜色
     */
    private fun hasIgnoreStatusBarIconColor(context: Context, expandedNf: StatusBarNotification?) = safeOfFalse {
        /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
        expandedNf?.let { notifyInstance ->
            /** 获取通知小图标 */
            val iconDrawable = notifyInstance.notification.smallIcon.loadDrawable(context)

            /** 判断是否不是灰度图标 */
            val isNotGrayscaleIcon = notifyInstance.isXmsf || isGrayscaleIcon(context, iconDrawable).not()

            /** 获取目标修复彩色图标的 APP */
            val isTargetFixApp = compatCustomIcon(isNotGrayscaleIcon.not(), notifyInstance.nfPkgName).first != null
            /**
             * 只要不是灰度就返回彩色图标
             * 否则不对颜色进行反色处理防止一些系统图标出现异常
             */
            (if (isTargetFixApp) false else isNotGrayscaleIcon).also {
                printLogcat(tag = "IconColor", context, expandedNf, isTargetFixApp, isNotGrayscaleIcon.not())
            }
        } ?: true.also { printLogcat(tag = "IconColor", context, expandedNf = null, isCustom = false, isGrayscale = false) }
    }

    /**
     * 从 [NotificationViewWrapperClass] 中获取 [ExpandableNotificationRowClass]
     * @return [Pair] - ([Boolean] 通知是否展开,[Any] 通知 Row 实例)
     */
    private fun Any.getRowPair(): Pair<Boolean, Any?> {
        /** 通知是否展开 */
        var isExpanded = false

        /**
         * 从父类中得到 mRow 变量 - [ExpandableNotificationRowClass]
         * 获取其中的得到通知方法
         */
        val row = NotificationViewWrapperClass.clazz.field {
            name = "mRow"
        }.get(this).self?.also {
            isExpanded = ExpandableNotificationRowClass.clazz.method {
                name = "isExpanded"
                returnType = BooleanType
            }.get(it).boolean()
        }
        return Pair(isExpanded, row)
    }

    /**
     * 从 [ExpandableNotificationRowClass] 中获取 [StatusBarNotification]
     * @return [StatusBarNotification] or null
     */
    private fun Any?.getSbn() =
        ExpandableNotificationRowClass.clazz
            .method { name = "getEntry" }
            .get(this).call()?.let {
                it.javaClass.method {
                    name = "getSbn"
                }.get(it).invoke<StatusBarNotification>()
            } ?: ExpandableNotificationRowClass.clazz
            .method { name = "getStatusBarNotification" }
            .get(NotificationViewWrapperClass.clazz.field { name = "mRow" }.get(this).self)
            .invoke<StatusBarNotification>()

    /** 注册 */
    private fun register() {
        /** 解锁后重新刷新状态栏图标防止系统重新设置它 */
        onAppLifecycle { registerReceiver(Intent.ACTION_USER_PRESENT) { _, _ -> if (isUsingCachingMethod) refreshStatusBarIcons() } }
        /** 刷新图标缓存 */
        SystemUITool.Host.onRefreshSystemUI(param = this) { recachingPrefs(it) }
    }

    /** 缓存图标数据 */
    private fun cachingIconDatas() {
        iconDatas.clear()
        IconPackParams(param = this).iconDatas.apply {
            when {
                isNotEmpty() -> forEach { iconDatas.add(it) }
                isEmpty() && isEnableHookColorNotifyIcon(isHooking = false) -> loggerW(msg = "NotifyIconSupportData is empty!")
            }
        }
    }

    /**
     * 刷新缓存数据
     * @param isRefreshCacheOnly 仅刷新缓存不刷新图标和通知改变 - 默认：否
     * @return [Boolean] 是否成功
     */
    private fun recachingPrefs(isRefreshCacheOnly: Boolean = false): Boolean {
        /** 必要的延迟防止 Sp 存储不刷新 */
        SystemClock.sleep(100)
        /** 获取可读写状态 */
        return prefs.isXSharePrefsReadable.also {
            isUsingCachingMethod = true
            prefs.clearCache()
            cachingIconDatas()
            if (isRefreshCacheOnly) return@also
            refreshStatusBarIcons()
            refreshNotificationIcons()
        }
    }

    override fun onHook() {
        /** 注册 */
        register()
        /** 缓存图标数据 */
        cachingIconDatas()
        /** 注入 MIUI 自己增加的一个工具类 */
        NotificationUtilClass.hook {
            /** 强制回写系统的状态栏图标样式为原生 */
            injectMember {
                method {
                    name = "shouldSubstituteSmallIcon"
                    param(ExpandedNotificationClass)
                }
                replaceToFalse()
            }
            /** 强制回写系统的状态栏图标样式为原生 */
            injectMember {
                var isUseLegacy = false
                method {
                    name = "getSmallIcon"
                    param(ExpandedNotificationClass, IntType)
                }.remedys {
                    method {
                        name = "getSmallIcon"
                        param(ExpandedNotificationClass)
                    }
                    method {
                        name = "getSmallIcon"
                        param(ContextClass, ExpandedNotificationClass)
                    }.onFind { isUseLegacy = true }
                }
                afterHook {
                    (globalContext ?: args().first().cast())?.also { context ->
                        val expandedNf = args(if (isUseLegacy) 1 else 0).cast<StatusBarNotification?>()
                        /** Hook 状态栏小图标 */
                        compatStatusIcon(
                            context = context,
                            expandedNf,
                            result<Icon>()?.loadDrawable(context)
                        ).also { pair -> if (pair.second) result = Icon.createWithBitmap(pair.first?.toBitmap()) }
                    }
                }
            }
        }
        /** 注入状态栏通知图标实例 */
        StatusBarIconViewClass.hook {
            /** Hook 状态栏通知图标的颜色 */
            injectMember {
                method { name = "updateIconColor" }
                afterHook {
                    instance<ImageView>().also {
                        if (hasIgnoreStatusBarIconColor(it.context, field { name = "mNotification" }
                                .get(instance).cast<StatusBarNotification>())) it.apply {
                            alpha = 1f
                            colorFilter = null
                        } else it.apply {
                            /**
                             * 防止图标不是纯黑的问题
                             * 图标在任何场景下跟随状态栏其它图标保持半透明
                             * MIUI 12 进行单独判断
                             */
                            field { name = "mCurrentSetColor" }.get(instance).int().also { color ->
                                if (hasIgnoreStatusBarIconColor) {
                                    alpha = if (color.isWhite) 0.95f else 0.8f
                                    setColorFilter(if (color.isWhite) color else Color.BLACK)
                                } else setColorFilter(color)
                            }
                        }
                    }
                }
            }
            /** 注册广播 */
            injectMember {
                method {
                    name = "setNotification"
                    param(StatusBarNotificationClass)
                }.remedys {
                    method {
                        name = "setNotification"
                        param(ExpandedNotificationClass)
                    }
                }
                afterHook {
                    /** 注册壁纸颜色监听 */
                    if (args().first().any() != null) instance<ImageView>().also { registerWallpaperColorChanged(it) }
                }
            }
        }
        /** 注入通知控制器实例 */
        StatusBarNotificationPresenterClass.hook {
            injectMember {
                allConstructors()
                afterHook { notificationPresenter = instance }
            }
        }
        /** 注入状态栏通知图标容器实例 */
        NotificationIconContainerClass.hook {
            injectMember {
                method { name = "calculateIconTranslations" }
                afterHook {
                    /** 缓存实例 */
                    notificationIconContainer = instance<ViewGroup>()
                    /** 修复部分开发版状态栏图标只能显示一个的问题 */
                    when (miuiIncrementalVersion.lowercase()) {
                        "22.3.14", "22.3.15", "22.3.16", "v13.0.1.1.16.dev", "22.3.18" ->
                            instance<ViewGroup>().layoutParams.width = 9999
                    }
                }
            }
            injectMember {
                method { name = "updateState" }
                beforeHook {
                    /** 解除状态栏通知图标个数限制 */
                    if (isShowNotificationIcons && prefs.get(DataConst.ENABLE_HOOK_STATUS_ICON_COUNT))
                        field { name = "MAX_STATIC_ICONS" }
                            .get(instance).set(prefs.get(DataConst.HOOK_STATUS_ICON_COUNT)
                                .let { if (it in 0..100) it else 5 })
                }
            }.by { NotificationIconContainerClass.clazz.hasField { name = "MAX_STATIC_ICONS" } }
            /** 旧版方法 - 新版不存在 */
            injectMember {
                method {
                    name = "setMaxStaticIcons"
                    param(IntType)
                }
                beforeHook { isShowNotificationIcons = args().first().int() > 0 }
            }.ignoredNoSuchMemberFailure()
            /** 新版方法 - 旧版不存在 */
            injectMember {
                method {
                    name = "miuiShowNotificationIcons"
                    param(BooleanType)
                }
                beforeHook { isShowNotificationIcons = args().first().boolean() }
            }.ignoredNoSuchMemberFailure()
        }
        /** 注入原生通知包装纸实例 */
        NotificationHeaderViewWrapperClass.hook {
            /** 修复下拉通知图标自动设置回 APP 图标的方法 */
            injectMember {
                if (hasHandleHeaderViews)
                    method { name = "handleHeaderViews" }
                else method { name = "resolveHeaderViews" }
                afterHook {
                    /** 获取小图标 */
                    val iconImageView =
                        NotificationHeaderViewWrapperClass.clazz
                            .field { name = "mIcon" }.get(instance).cast<ImageView>() ?: return@afterHook

                    /** 获取 [ExpandableNotificationRowClass] */
                    val rowPair = instance.getRowPair()

                    /** 获取 [StatusBarNotification] */
                    val expandedNf = rowPair.second.getSbn()

                    /** 通知是否展开 */
                    var isExpanded = rowPair.first

                    /** 获取优先级 */
                    val importance =
                        (iconImageView.context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager?)
                            ?.getNotificationChannel(expandedNf?.notification?.channelId)?.importance ?: 0
                    /** 非最小化优先级的通知全部设置为展开状态 */
                    if (importance != 1) isExpanded = true
                    /** 执行 Hook */
                    compatNotifyIcon(iconImageView.context, expandedNf, iconImageView, isExpanded)
                }
            }
        }
        /** 修改 MIUI 风格通知栏的通知图标 */
        MiuiNotificationViewWrapperClass.hook {
            /** 替换通知小图标 */
            injectMember {
                method { name = "handleAppIcon" }
                replaceUnit {
                    field { name = "mAppIcon" }.get(instance).cast<ImageView>()?.apply {
                        compatNotifyIcon(
                            context = context,
                            expandedNf = instance.getRowPair().second.getSbn(),
                            iconImageView = this,
                            isUseAndroid12Style = true
                        )
                    }
                }
            }
        }.ignoredHookClassNotFoundFailure()
        /** 修改 MIUI 风格通知栏的通知图标 - 折叠通知 */
        MiuiNotificationChildrenContainerClass.hook {
            /** 替换通知小图标 */
            injectMember {
                method {
                    name = "updateAppIcon"
                    param(BooleanType)
                }
                afterHook {
                    val expandedNf = NotificationChildrenContainerClass.clazz.field {
                        name = "mContainingNotification"
                    }.get(instance).self.getSbn()
                    field { name = "mAppIcon" }.get(instance).cast<ImageView>()?.apply {
                        compatNotifyIcon(context, expandedNf, iconImageView = this, isUseAndroid12Style = true)
                    }
                }
            }
        }.ignoredHookClassNotFoundFailure()
        /** 干掉下拉通知图标自动设置回 APP 图标的方法 */
        NotificationHeaderViewWrapperInjectorClass.hook {
            injectMember {
                method {
                    name = "setAppIcon"
                    param(ContextClass, ImageViewClass, ExpandedNotificationClass)
                }.remedys {
                    method {
                        name = "setAppIcon"
                        param(ImageViewClass, ExpandedNotificationClass)
                    }
                }
                intercept()
            }.ignoredNoSuchMemberFailure()
            injectMember {
                method {
                    name = "resetIconBgAndPaddings"
                    param(ImageViewClass, ExpandedNotificationClass)
                }
                intercept()
            }.ignoredNoSuchMemberFailure()
        }.ignoredHookClassNotFoundFailure()
        /** 发送适配新的 APP 图标通知 */
        PluginManagerImplClass.hook {
            injectMember {
                method {
                    name = "onReceive"
                    param(ContextClass, IntentClass)
                }
                afterHook {
                    if (isEnableHookColorNotifyIcon()) args().last().cast<Intent>()?.also {
                        if (it.action.equals(Intent.ACTION_PACKAGE_REPLACED).not() &&
                            it.getBooleanExtra(Intent.EXTRA_REPLACING, false)
                        ) return@also
                        when (it.action) {
                            Intent.ACTION_PACKAGE_ADDED ->
                                it.data?.schemeSpecificPart?.also { newPkgName ->
                                    if (iconDatas.takeIf { e -> e.isNotEmpty() }
                                            ?.filter { e -> e.packageName == newPkgName }
                                            .isNullOrEmpty()
                                    ) IconAdaptationTool.pushNewAppSupportNotify(args().first().cast()!!, newPkgName)
                                }
                            Intent.ACTION_PACKAGE_REMOVED ->
                                IconAdaptationTool.removeNewAppSupportNotify(
                                    context = args().first().cast()!!,
                                    packageName = it.data?.schemeSpecificPart ?: ""
                                )
                        }
                    }
                }
            }
        }
        /** 自动检查通知图标优化更新的注入监听 */
        MiuiClockClass.hook {
            injectMember {
                method { name = "updateTime" }
                afterHook {
                    instance<View>().context.also {
                        /** 注册定时监听 */
                        if (isEnableHookColorNotifyIcon() && prefs.get(DataConst.ENABLE_NOTIFY_ICON_FIX_AUTO))
                            IconAdaptationTool.prepareAutoUpdateIconRule(
                                context = it,
                                timeSet = prefs.get(DataConst.NOTIFY_ICON_FIX_AUTO_TIME)
                            )
                    }
                }
            }
        }.ignoredHookClassNotFoundFailure()
    }
}