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
 * This file is created by fankes on 2022/3/25.
 */
@file:Suppress("ConstPropertyName")

package com.fankes.miui.notify.hook.entity

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.os.SystemClock
import android.service.notification.StatusBarNotification
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.scale
import androidx.core.view.children
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.fankes.miui.notify.R
import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.const.PackageName
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.params.factory.isAppNotifyHookAllOf
import com.fankes.miui.notify.params.factory.isAppNotifyHookOf
import com.fankes.miui.notify.utils.factory.appIconOf
import com.fankes.miui.notify.utils.factory.appNameOf
import com.fankes.miui.notify.utils.factory.brighterColor
import com.fankes.miui.notify.utils.factory.delayedRun
import com.fankes.miui.notify.utils.factory.dp
import com.fankes.miui.notify.utils.factory.dpFloat
import com.fankes.miui.notify.utils.factory.drawableOf
import com.fankes.miui.notify.utils.factory.isMIOS
import com.fankes.miui.notify.utils.factory.isNotSystemInDarkMode
import com.fankes.miui.notify.utils.factory.isSystemInDarkMode
import com.fankes.miui.notify.utils.factory.isUpperOfAndroidS
import com.fankes.miui.notify.utils.factory.miosVersionCode
import com.fankes.miui.notify.utils.factory.miuiIncrementalVersion
import com.fankes.miui.notify.utils.factory.round
import com.fankes.miui.notify.utils.factory.runInSafe
import com.fankes.miui.notify.utils.factory.safeOf
import com.fankes.miui.notify.utils.factory.safeOfFalse
import com.fankes.miui.notify.utils.factory.systemAccentColor
import com.fankes.miui.notify.utils.tool.ActivationPromptTool
import com.fankes.miui.notify.utils.tool.BitmapCompatTool
import com.fankes.miui.notify.utils.tool.IconAdaptationTool
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.highcapable.kavaref.KavaRef.Companion.asResolver
import com.highcapable.kavaref.KavaRef.Companion.resolve
import com.highcapable.kavaref.condition.MethodCondition
import com.highcapable.kavaref.extension.VariousClass
import com.highcapable.kavaref.extension.classOf
import com.highcapable.kavaref.extension.isSubclassOf
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.injectModuleAppResources
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.HookParam
import com.highcapable.yukihookapi.hook.type.android.ContextClass
import com.highcapable.yukihookapi.hook.type.android.NotificationClass
import com.highcapable.yukihookapi.hook.type.android.RemoteViewsClass
import com.highcapable.yukihookapi.hook.type.java.BooleanClass
import top.defaults.drawabletoolbox.DrawableBuilder

/**
 * 系统界面核心 Hook 类
 */
object SystemUIHooker : YukiBaseHooker() {

    /** MIUI 新版本存在的类 */
    private val SystemUIApplicationClass by lazyClassOrNull("${PackageName.SYSTEMUI}.SystemUIApplication")

    /** MIUI 新版本存在的类 */
    private val MiuiNotificationViewWrapperClass
        by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.MiuiNotificationViewWrapper")

    /** MIUI 新版本存在的类 */
    private val MiuiNotificationChildrenContainerClass
        by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.stack.MiuiNotificationChildrenContainer")

    /** MIUI 新版本存在的类 */
    private val NotificationHeaderViewWrapperInjectorClass
        by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.NotificationHeaderViewWrapperInjector")

    /** MIUI 未确定版本存在的类 */
    private val NotificationContentInflaterInjectorClass
        by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.row.NotificationContentInflaterInjector")

    /** MIUI 未确定版本存在的类 */
    private val SettingsManagerClass by lazyClassOrNull("com.miui.systemui.SettingsManager")

    /** MIUI 未确定版本存在的类 */
    private val MiuiClockClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.views.MiuiClock")

    /** MIUI 新版本存在的类 */
    private val NotificationStatClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.analytics.NotificationStat")

    /** 原生存在的类 */
    private val NotificationChildrenContainerClass by lazyClass("${PackageName.SYSTEMUI}.statusbar.notification.stack.NotificationChildrenContainer")

    /** 原生存在的类 (A15 HyperOS 已变成 `interface`) */
    private val NotificationIconAreaControllerClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.phone.NotificationIconAreaController")

    /** 原生存在的类 */
    private val ContrastColorUtilClass by lazyClass("com.android.internal.util.ContrastColorUtil")

    /** 原生存在的类 */
    private val StatusBarIconViewClass by lazyClass("${PackageName.SYSTEMUI}.statusbar.StatusBarIconView")

    /** 原生存在的类 */
    private val NotificationIconContainerClass by lazyClass("${PackageName.SYSTEMUI}.statusbar.phone.NotificationIconContainer")

    /** 根据多个版本存在不同的包名相同的类 */
    private val StatusBarNotificationPresenterClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.phone.StatusBarNotificationPresenter",
            "${PackageName.SYSTEMUI}.statusbar.phone.StatusBar"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val ExpandableNotificationRowClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.row.ExpandableNotificationRow",
            "${PackageName.SYSTEMUI}.statusbar.ExpandableNotificationRow"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationViewWrapperClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.NotificationViewWrapper",
            "${PackageName.SYSTEMUI}.statusbar.notification.NotificationViewWrapper"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationHeaderViewWrapperClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper",
            "${PackageName.SYSTEMUI}.statusbar.notification.NotificationHeaderViewWrapper"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationUtilClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.NotificationUtil",
            "${PackageName.SYSTEMUI}.miui.statusbar.notification.NotificationUtil",
            "${PackageName.SYSTEMUI}.statusbar.notification.utils.NotifImageUtil"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val ExpandedNotificationClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.ExpandedNotification",
            "${PackageName.SYSTEMUI}.miui.statusbar.ExpandedNotification"
        )
    )

    /** HyperOS 焦点通知的用到的 API */
    private val FocusUtilsClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.utils.FocusUtils")

    /** HyperOS 焦点通知的用到的 API */
    private val FocusedNotifPromptViewClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.phone.FocusedNotifPromptView")

    /** 缓存的通知图标优化数组 */
    private var iconDatas = ArrayList<IconDataBean>()

    /** 当前是否处于深色图标模式 - 跟随 Hook 保存 */
    private var isDarkIconMode = false

    /** 是否显示通知图标 - 跟随 Hook 保存 */
    private var isShowNotificationIcons = true

    /** 状态栏原始通知图标最大个数限制 */
    private var statusBarMaxStaticIcons = -1

    /** 模块上次设置的通知图标最大个数限制 */
    private var moduleLastSetStatusBarMaxStaticIcons = -1

    /** 是否已经使用过缓存刷新功能 */
    private var isUsingCachingMethod = false

    /** 状态栏通知图标容器 */
    @SuppressLint("StaticFieldLeak")
    private var notificationIconContainer: ViewGroup? = null

    /** 通知栏通知控制器 */
    private var notificationPresenter: Any? = null

    /** 设置管理器 */
    private var settingsManager: Any? = null

    /** 仅监听一次主题壁纸颜色变化 */
    private var isWallpaperColorListenerSetUp = false

    /** 用来同步是否需要焦点图标染色 */
    private var focusedIcon = false

    /**
     * 获取全局上下文
     * @return [Context] or null
     */
    private val globalContext
        get() = SystemUIApplicationClass?.resolve()?.optional(silent = true)
            ?.firstMethodOrNull { name = "getContext" }
            ?.invoke<Context?>() ?: appContext

    /**
     * 是否为 MIUI 样式通知栏 - 旧版 - 新版一律返回 false
     * @return [Boolean]
     */
    private val isShowMiuiStyle
        get() = NotificationUtilClass.resolve().optional(silent = true)
            .firstMethodOrNull { name = "showMiuiStyle" }
            ?.invoke<Boolean>() == true

    /**
     * 是否没有单独的 MIUI 通知栏样式
     * @return [Boolean]
     */
    private val isNotHasAbsoluteMiuiStyle get() = MiuiNotificationViewWrapperClass == null

    /**
     * 获取状态栏通知图标透明度
     * @return [Float]
     */
    private val statusBarIconAlpha
        get() = (if (isDarkIconMode) ConfigData.statusIconDarkAlphaLevel else ConfigData.statusIconLightAlphaLevel) / 100f

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
     * 获取 MIUI 自己设置的通知图标
     * @return [Icon] or null
     */
    @Suppress("DEPRECATION")
    private val StatusBarNotification.miuiAppIcon get() = notification?.extras?.getParcelable<Icon?>("miui.appIcon")

    /**
     * 打印日志
     * @param tag 标识
     * @param context 实例
     * @param nf 通知实例
     * @param isCustom 是否为通知优化生效图标
     * @param isGrayscale 是否为灰度图标
     */
    @Suppress("DEPRECATION")
    private fun loggerDebug(tag: String, context: Context, nf: StatusBarNotification?, isCustom: Boolean, isGrayscale: Boolean) {
        if (ConfigData.isEnableModuleLog) {
            val focusTicker = nf?.notification?.extras?.getString("miui.focus.ticker")
            val focusIconId = nf?.notification?.extras?.getInt("miui.focus.iconId", -1)
            val focusRv = nf?.notification?.extras?.getParcelable<RemoteViews?>("miui.focus.rv")
            val focusParam = nf?.notification?.extras?.getString("miui.focus.param")
            if (focusRv != null)
                YLog.debug(
                    msg = "(Processing $tag with Focus Notification) ↓\n" +
                        "[Title]: ${nf.notification?.extras?.getString(Notification.EXTRA_TITLE)}\n" +
                        "[Content]: ${nf.notification?.extras?.getString(Notification.EXTRA_TEXT)}\n" +
                        "[App Name]: ${context.appNameOf(packageName = nf.packageName ?: "")}\n" +
                        "[Package Name]: ${nf.packageName}\n" +
                        "[Sender Package Name]: ${nf.compatOpPkgName}\n" +
                        "[Custom Icon]: $isCustom\n" +
                        "[Grayscale Icon]: $isGrayscale\n" +
                        "[From Xmsf]: ${nf.isXmsf}\n" +
                        "[String]: ${nf.notification}\n" +
                        "[Focus IconId]: $focusIconId\n" +
                        "[Focus String]: ${focusParam}\n" +
                        "[Focus rv String]: ${focusRv}\n" +
                        "[Focus Ticker String]: ${focusTicker}\n" +
                        "[Dark Icon Mode]: $isDarkIconMode"
                )
            else
                YLog.debug(
                    msg = "(Processing $tag) ↓\n" +
                        "[Title]: ${nf?.notification?.extras?.getString(Notification.EXTRA_TITLE)}\n" +
                        "[Content]: ${nf?.notification?.extras?.getString(Notification.EXTRA_TEXT)}\n" +
                        "[App Name]: ${context.appNameOf(packageName = nf?.packageName ?: "")}\n" +
                        "[Package Name]: ${nf?.packageName}\n" +
                        "[Sender Package Name]: ${nf?.compatOpPkgName}\n" +
                        "[Custom Icon]: $isCustom\n" +
                        "[Grayscale Icon]: $isGrayscale\n" +
                        "[From Xmsf]: ${nf?.isXmsf}\n" +
                        "[String]: ${nf?.notification}\n" +
                        "[Dark Icon Mode]: $isDarkIconMode"
                )
        }
    }

    /**
     * - 这个是修复彩色图标的关键核心代码判断
     *
     * 判断是否为灰度图标 - 反射执行系统方法
     * @param context 实例
     * @param drawable 要判断的图标
     * @return [Boolean]
     */
    private fun isGrayscaleIcon(context: Context, drawable: Drawable) =
        if (ConfigData.isEnableColorIconCompat.not()) safeOfFalse {
            ContrastColorUtilClass.resolve()
                .optional(silent = true)
                .let {
                    it.firstMethodOrNull {
                        name = "isGrayscaleIcon"
                        parameters(Drawable::class)
                    }?.of(
                        it.firstMethodOrNull {
                            name = "getInstance"
                            parameters(Context::class)
                        }?.invoke(context)
                    )?.invokeQuietly<Boolean>(drawable) == true
                }
        } else BitmapCompatTool.isGrayscaleDrawable(drawable)

    /**
     * 处理为圆角图标
     * @return [Drawable]
     */
    private fun Drawable.rounded(context: Context) =
        safeOf(default = this) { toBitmap().round(10.dpFloat(context)).toDrawable(context.resources) }

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
            context.appIconOf(xmsfPkgName) ?: iconDrawable
        else iconDrawable
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
        StatusBarIconViewClass.resolve().optional(silent = true).firstFieldOrNull { name = "mNotification" }?.also { result ->
            notificationIconContainer?.children?.forEach {
                /** 得到通知实例 */
                val nf = result.of(it).get<StatusBarNotification>() ?: return
                /** 刷新状态栏图标 */
                compatStatusIcon(it.context, nf, nf.notification.smallIcon.loadDrawable(it.context)).also { pair ->
                    pair.first.let { e -> (it as? ImageView?)?.setImageDrawable(e) }
                }
            }
        }
    }

    /** 刷新通知小图标 */
    private fun refreshNotificationIcons() = runInSafe {
        val updateNotificationMethodName = "updateNotificationsOnDensityOrFontScaleChanged"
        val result = notificationPresenter?.asResolver()?.optional(silent = true)?.firstMethodOrNull {
            name = updateNotificationMethodName
            emptyParameters()
        }?.invoke()
        if (result == null) settingsManager?.asResolver()?.optional(silent = true)?.apply {
            firstFieldOrNull { name = "notifStyle" }?.set(-100)
            firstMethodOrNull { name = "onNotifStyleChanged" }?.invoke()
        }
    }

    /**
     * 自动适配状态栏、通知栏自定义小图标
     * @param context 实例
     * @param isGrayscaleIcon 是否为灰度图标
     * @param packageName APP 包名
     * @return [Triple] - ([Drawable] 小图标,[Int] 颜色,[Boolean] 是否为占位符图标)
     */
    private fun compatCustomIcon(context: Context, isGrayscaleIcon: Boolean, packageName: String): Triple<Drawable?, Int, Boolean> {
        /** 防止模块资源注入失败重新注入 */
        context.injectModuleAppResources()
        var customPair: Triple<Drawable?, Int, Boolean>? = null
        if (ConfigData.isEnableNotifyIconFix) run {
            iconDatas.takeIf { it.isNotEmpty() }?.forEach {
                if (packageName == it.packageName && isAppNotifyHookOf(it)) {
                    if (isGrayscaleIcon.not() || isAppNotifyHookAllOf(it))
                        customPair = Triple(it.iconBitmap.toDrawable(context.resources), it.iconColor, false)
                    return@run
                }
            }
            if (isGrayscaleIcon.not() && ConfigData.isEnableNotifyIconFixPlaceholder)
                customPair = Triple(context.resources.drawableOf(R.drawable.ic_message), 0, true)
        }
        return customPair ?: Triple(null, 0, false)
    }

    /**
     * Hook 状态栏小图标
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param nf 通知实例
     * @param iconDrawable 小图标 [Drawable]
     * @return [Pair] 回调小图标 - ([Drawable] 小图标,[Boolean] 是否替换)
     */
    private fun compatStatusIcon(context: Context, nf: StatusBarNotification?, iconDrawable: Drawable?) = nf?.let { notifyInstance ->
        if (iconDrawable == null) return@let Pair(null, false)
        /** 判断是否不是灰度图标 */
        val isGrayscaleIcon = notifyInstance.isXmsf.not() && isGrayscaleIcon(context, iconDrawable)

        /** 读取通知是否附加包名，如果没有则使用通知包名 */
        val extras = notifyInstance.notification.extras
        val pkgname = extras.getString("app_package")?.takeIf { it.isNotBlank() } ?: notifyInstance.nfPkgName

        /** 目标彩色通知 APP 图标 */
        val customTriple = compatCustomIcon(context, isGrayscaleIcon, pkgname)

        /** 是否为通知优化生效图标 */
        val isCustom = customTriple.first != null && customTriple.third.not()
        /** 打印日志 */
        loggerDebug(tag = "Status Bar Icon", context, notifyInstance, isCustom = isCustom, isGrayscaleIcon)
        when {
            /** 处理自定义通知图标优化 */
            customTriple.first != null -> Pair(customTriple.first, true)
            /** 若不是灰度图标自动处理为圆角 */
            isGrayscaleIcon.not() -> Pair(notifyInstance.compatPushingIcon(context, iconDrawable).rounded(context), true)
            /** 否则返回原始小图标 */
            else -> Pair(notifyInstance.notification.smallIcon.loadDrawable(context), false)
        }
    } ?: Pair(null, false)

    /**
     * Hook 通知栏小图标
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param nf 通知实例
     * @param iconView 通知图标实例
     * @param isExpanded 通知是否展开 - 可做最小化通知处理 - 默认：是
     * @param isUseMaterial3Style 是否使用 Material 3 通知图标风格 - 默认跟随系统版本决定
     * @param isMiuiPanel 是否来自 MIUI 自己的通知面板
     */
    private fun compatNotifyIcon(
        context: Context,
        nf: StatusBarNotification?,
        iconView: ImageView,
        isExpanded: Boolean = true,
        isUseMaterial3Style: Boolean = isUpperOfAndroidS,
        isMiuiPanel: Boolean = false
    ) = runInSafe(msg = "compatNotifyIcon") {
        /**
         * 设置默认通知图标
         * @param drawable 通知图标
         */
        fun setDefaultNotifyIcon(drawable: Drawable?) {
            iconView.apply {
                /** 重新设置图标 */
                setImageDrawable(drawable)
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
                if (isUseMaterial3Style) {
                    /** 清除原生的背景边距 */
                    setPadding(0, 0, 0, 0)
                    /** 清除原生的主题色背景圆圈颜色 */
                    background = null
                }
                /** 清除遮罩颜色 */
                colorFilter = null
            }
        }
        /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
        nf?.let { notifyInstance ->

            /** 新版风格反色 */
            val newStyle = if (context.isSystemInDarkMode) 0xFF2D2D2D.toInt() else Color.WHITE

            /** 旧版风格反色 */
            val oldStyle = if (context.isNotSystemInDarkMode) 0xFF707070.toInt() else Color.WHITE

            /** 通知图标原始颜色 */
            val iconColor = notifyInstance.notification.color

            /** 是否有通知栏图标颜色 */
            val hasIconColor = iconColor != 0 && ConfigData.isEnableNotifyIconForceSystemColor.not()

            /** 通知图标适配颜色 */
            val supportColor = (iconColor.takeIf { ConfigData.isEnableNotifyIconForceSystemColor.not() } ?: 0).let {
                when {
                    isUseMaterial3Style -> newStyle
                    it == 0 || isExpanded.not() -> oldStyle
                    else -> it
                }
            }

            /** 获取通知小图标 */
            val iconDrawable = notifyInstance.notification.smallIcon.loadDrawable(context)
                ?: return@let YLog.warn("compatNotifyIcon got null smallIcon")

            /** 判断图标风格 */
            val isGrayscaleIcon = notifyInstance.isXmsf.not() && isGrayscaleIcon(context, iconDrawable)

            /** 自定义默认小图标 */
            var customIcon: Drawable? = null

            /** 读取通知是否附加包名，如果没有则使用通知包名 */
            val extras = notifyInstance.notification.extras
            val pkgname = extras.getString("app_package")?.takeIf { it.isNotBlank() } ?: notifyInstance.nfPkgName

            /** 自定义默认小图标颜色 */
            var customIconColor = 0
            compatCustomIcon(context, isGrayscaleIcon, pkgname).also {
                /** 不处理占位符图标 */
                if (it.third) return@also
                customIcon = it.first
                customIconColor = if (isUseMaterial3Style || isExpanded)
                    (it.second.takeIf { e -> e != 0 && ConfigData.isEnableNotifyIconForceSystemColor.not() }
                        ?: (if (isUseMaterial3Style) context.systemAccentColor else 0)) else 0
            }
            /** 打印日志 */
            loggerDebug(tag = "Notification Panel Icon", context, notifyInstance, isCustom = customIcon != null, isGrayscaleIcon)
            /** 处理自定义通知图标优化 */
            when {
                ConfigData.isEnableNotifyIconForceAppIcon ->
                    setDefaultNotifyIcon(drawable = notifyInstance.miuiAppIcon?.loadDrawable(context) ?: context.appIconOf(notifyInstance.nfPkgName))
                customIcon != null -> iconView.apply {
                    /** 设置不要裁切到边界 */
                    clipToOutline = false
                    /** 设置自定义小图标 */
                    setImageDrawable(customIcon)
                    /** 上色 */
                    setColorFilter(if (isUseMaterial3Style || customIconColor == 0) supportColor else customIconColor)
                    /** 设置图标外圈颜色 */
                    if (isUseMaterial3Style && customIconColor != 0)
                        background = DrawableBuilder()
                            .rectangle()
                            .cornerRadius(ConfigData.notifyIconCornerSize.dp(context))
                            .solidColor(if (context.isSystemInDarkMode) customIconColor.brighterColor else customIconColor)
                            .build()
                    when {
                        /** 缩小 HyperOS 的通知图标 */
                        isMIOS && isMiuiPanel -> setPadding(7.dp(context))
                        /** 设置原生的背景边距 */
                        isUseMaterial3Style -> setPadding(4.dp(context))
                    }
                }
                else -> {
                    /** 重新设置图标 - 防止系统更改它 */
                    iconView.setImageDrawable(iconDrawable)
                    /** 判断如果是灰度图标就给他设置一个白色颜色遮罩 */
                    if (isGrayscaleIcon) iconView.apply {
                        /** 设置不要裁切到边界 */
                        clipToOutline = false
                        /** 设置图标着色 */
                        setColorFilter(supportColor)
                        /** 设置图标外圈颜色 */
                        (if (hasIconColor) iconColor else context.systemAccentColor).also {
                            if (isUseMaterial3Style)
                                background = DrawableBuilder()
                                    .rectangle()
                                    .cornerRadius(ConfigData.notifyIconCornerSize.dp(context))
                                    .solidColor(if (context.isSystemInDarkMode) it.brighterColor else it)
                                    .build()
                        }
                        when {
                            /** 缩小 HyperOS 的通知图标 */
                            isMIOS && isMiuiPanel -> setPadding(7.dp(context))
                            /** 设置原生的背景边距 */
                            isUseMaterial3Style -> setPadding(4.dp(context))
                        }
                    } else setDefaultNotifyIcon(notifyInstance.compatPushingIcon(context, iconDrawable))
                }
            }
        }
    }

    /**
     * 获取 [StatusBarIconViewClass] 实例是否为灰度图标 (单色图标)
     * @return [Boolean]
     */
    private fun ImageView.isGrayscaleIcon(): Boolean {
        /** 获取 [StatusBarNotification] 实例 */
        val notifyInstance = asResolver().optional().firstFieldOrNull { name = "mNotification" }?.get<StatusBarNotification>() ?: return false

        val appContext = context.createPackageContext(notifyInstance.nfPkgName, Context.CONTEXT_IGNORE_SECURITY)

        /** 获取通知小图标 */
        val iconDrawable = notifyInstance.notification?.smallIcon?.loadDrawable(appContext) ?: return false

        /** 判断是否不是灰度图标 */
        val isGrayscaleIcon = notifyInstance.isXmsf.not() && isGrayscaleIcon(context, iconDrawable)

        /** 获取目标修复彩色图标的 APP */
        val isTargetFixApp = compatCustomIcon(context, isGrayscaleIcon, notifyInstance.nfPkgName).first != null
        /**
         * 只要不是灰度就返回彩色图标
         * 否则不对颜色进行反色处理防止一些系统图标出现异常
         */
        return isTargetFixApp || isGrayscaleIcon
    }

    /**
     * 更新状态栏通知图标颜色
     * @param container 当前 [NotificationIconAreaControllerClass] 的实例
     * @param isDarkIconMode 是否为深色图标模式
     * @param animColor 动画过渡颜色
     */
    private fun updateStatusBarIconsColor(container: ViewGroup, isDarkIconMode: Boolean = this.isDarkIconMode, animColor: Int? = null) {
        if (container.isNotEmpty()) container.children.forEach { iconView ->
            if (iconView !is ImageView) return@forEach
            updateStatusBarIconColor(iconView, isDarkIconMode, animColor)
        }
    }

    /**
     * 更新状态栏每个通知图标的颜色
     * @param isDarkIconMode 是否为深色图标模式
     * @param animColor 动画过渡颜色
     */
    private fun updateStatusBarIconColor(iconView: ImageView, isDarkIconMode: Boolean = this.isDarkIconMode, animColor: Int? = null) {
        if (miosVersionCode >= 3) return
        if (iconView.isGrayscaleIcon()) {
            /**
             * 防止图标不是纯黑的问题
             * 图标在任何场景下跟随状态栏其它图标保持半透明
             */
            iconView.alpha = if (animColor != null) 1f else statusBarIconAlpha
            iconView.setColorFilter(animColor ?: (if (isDarkIconMode) Color.BLACK else Color.WHITE))
        } else {
            iconView.alpha = 1f
            iconView.colorFilter = null
        }
    }

    /**
     * 更新状态栏通知图标透明度
     * @param container 当前 [NotificationIconContainerClass] 的实例
     */
    private fun updateStatusBarIconsAlpha(container: ViewGroup) {
        val iconStatesMap = container.asResolver().optional().firstFieldOrNull { name = "mIconStates" }?.get<HashMap<View, Any>>()
        if (container.isNotEmpty()) container.children.forEach { iconView ->
            if (iconView !is ImageView) return@forEach
            val iconAlpha = if (iconView.isGrayscaleIcon()) statusBarIconAlpha else 1f
            iconView.alpha = iconAlpha
            iconStatesMap?.get(iconView)?.asResolver()?.optional()?.firstFieldOrNull {
                name { it == "alpha" || it == "mAlpha" }
                superclass()
            }?.set(iconAlpha)
        }
    }

    /**
     * Hook 状态栏通知图标最大数量
     * @param instance 被 Hook 的 Method 的实例
     */
    private fun hookStatusBarMaxStaticIcons(instance: Any) {
        val maxStaticIconsResolver = NotificationIconContainerClass.resolve().optional().firstFieldOrNull {
            name {
                /** 旧版名称 */
                val oldVersion = it == "mMaxStaticIcons"

                /** 旧版名称 */
                val oldVersion2 = it == "MAX_STATIC_ICONS"

                /** 新版本名称 */
                val newVersion = it == "mMaxIcons"
                oldVersion || oldVersion2 || newVersion
            }
        }?.of(instance) ?: return
        if (statusBarMaxStaticIcons == -1 ||
            /** 系统设置内修改，模块同步更新 */
            moduleLastSetStatusBarMaxStaticIcons != maxStaticIconsResolver.get<Int>()) {
            statusBarMaxStaticIcons = maxStaticIconsResolver.get<Int>() ?: return
        }
        if (!ConfigData.isEnableLiftedStatusIconCount) {
            maxStaticIconsResolver.set(statusBarMaxStaticIcons)
            return
        }
        /** 解除状态栏通知图标个数限制 */
        if (isShowNotificationIcons) {
            moduleLastSetStatusBarMaxStaticIcons = ConfigData.liftedStatusIconCount.let { if (it in 0..100) it else 5 }
            maxStaticIconsResolver.set(moduleLastSetStatusBarMaxStaticIcons)
        } else {
            maxStaticIconsResolver.set(0)
            moduleLastSetStatusBarMaxStaticIcons = 0
        }
    }

    /**
     * Hook 原生通知包装纸实例内容
     * @param wrapper 通知包装纸实例
     */
    private fun hookNotificationViewWrapper(wrapper: Any) {
        /** 忽略较旧版本 - 在没有 MIUI 通知栏样式的时候可能出现奇怪的问题 */
        if (isNotHasAbsoluteMiuiStyle && isShowMiuiStyle) return

        /** 获取小图标 */
        val iconImageView = NotificationHeaderViewWrapperClass.resolve().optional()
            .firstFieldOrNull { name = "mIcon" }?.of(wrapper)?.get<ImageView>() ?: return

        /** 获取 [ExpandableNotificationRowClass] */
        val rowPair = wrapper.getRowPair()

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
        val row = NotificationViewWrapperClass.resolve().optional().firstFieldOrNull {
            name = "mRow"
        }?.of(this)?.get()?.also {
            isExpanded = ExpandableNotificationRowClass.resolve().optional().firstMethodOrNull {
                name = "isExpanded"
                parameters(Boolean::class)
                returnType = Boolean::class
            }?.of(it)?.invoke<Boolean>(false) == true
        }; return isExpanded to row
    }

    /**
     * 从 [ExpandableNotificationRowClass] 中获取 [StatusBarNotification]
     * @return [StatusBarNotification] or null
     */
    private fun Any?.getSbn() =
        ExpandableNotificationRowClass.resolve()
            .optional(silent = true)
            .firstFieldOrNull {
                name = "mEntry"
            }?.of(this)?.get()?.asResolver()
            ?.optional(silent = true)
            ?.firstFieldOrNull { name = "mSbn" }
            ?.get<StatusBarNotification>()
            ?: ExpandableNotificationRowClass.resolve()
                .optional(silent = true)
                .firstMethodOrNull { name = "getStatusBarNotification" }
                ?.of(this)?.invoke<StatusBarNotification>()

    /**
     * 根据当前 [ImageView] 的父布局克隆一个新的 [ImageView]
     * @param initiate 新的 [ImageView] 方法体 - 失败会返回当前 [ImageView]
     */
    private fun ImageView.clone(initiate: ImageView.() -> Unit) {
        (parent as? ViewGroup?)?.also { parent ->
            /** 将之前的 [ImageView] 调为全透明且隐藏 */
            alpha = 0f
            isVisible = false
            /** 将内容清空 */
            setImageDrawable(null)
            setBackgroundColor(Color.TRANSPARENT)
            /** 创建一个傀儡 */
            parent.findViewWithTag<ImageView?>("new_view").also { base ->
                if (base == null) parent.addView(ImageView(context).also { new ->
                    new.tag = "new_view"
                    /** 克隆它的 [ViewGroup.LayoutParams] */
                    new.layoutParams = layoutParams
                    initiate(new)
                }) else initiate(base)
            }
        } ?: initiate(this)
    }

    /** 注册生命周期 */
    private fun registerLifecycle() {
        onAppLifecycle {
            /** 解锁后重新刷新状态栏图标防止系统重新设置它 */
            registerReceiver(Intent.ACTION_USER_PRESENT) { _, _ ->
                if (isUsingCachingMethod) refreshStatusBarIcons()
                /** 每次解锁时都对通知栏的图标进行刷新以响应 [RemoteViews] 阶段的图标改变 */
                refreshNotificationIcons()
            }
            /** 注册定时监听 */
            registerReceiver(Intent.ACTION_TIME_TICK) { context, _ ->
                if (ConfigData.isEnableNotifyIconFix && ConfigData.isEnableNotifyIconFixNotify && ConfigData.isEnableNotifyIconFixAuto)
                    IconAdaptationTool.prepareAutoUpdateIconRule(context, ConfigData.notifyIconFixAutoTime)
            }
            /** 注册发送适配新的 APP 图标通知监听 */
            registerReceiver(IntentFilter().apply {
                addDataScheme("package")
                addAction(Intent.ACTION_PACKAGE_ADDED)
                addAction(Intent.ACTION_PACKAGE_REPLACED)
                addAction(Intent.ACTION_PACKAGE_REMOVED)
            }) { context, intent ->
                intent.data?.schemeSpecificPart?.also { packageName ->
                    if (intent.action.equals(Intent.ACTION_PACKAGE_REPLACED)) ActivationPromptTool.prompt(context, packageName)
                    if (intent.action.equals(Intent.ACTION_PACKAGE_REPLACED).not() &&
                        intent.getBooleanExtra(Intent.EXTRA_REPLACING, false)
                    ) return@registerReceiver
                    if (ConfigData.isEnableNotifyIconFix && ConfigData.isEnableNotifyIconFixNotify)
                        when (intent.action) {
                            Intent.ACTION_PACKAGE_ADDED -> {
                                if (iconDatas.takeIf { e -> e.isNotEmpty() }
                                        ?.filter { e -> e.packageName == packageName }
                                        .isNullOrEmpty()
                                ) IconAdaptationTool.pushNewAppSupportNotify(context, packageName)
                            }
                            Intent.ACTION_PACKAGE_REMOVED -> IconAdaptationTool.removeNewAppSupportNotify(context, packageName)
                        }
                }
            }
            /** 注入模块资源 */
            onCreate { injectModuleAppResources() }
        }
        /** 刷新图标缓存 */
        SystemUITool.Host.onRefreshSystemUI(param = this) { recachingPrefs(it) }
    }

    /** 缓存图标数据 */
    private fun cachingIconDatas() {
        iconDatas.clear()
        IconPackParams(param = this).iconDatas.apply { if (isNotEmpty()) forEach { iconDatas.add(it) } }
    }

    /**
     * 刷新缓存数据
     * @param isRefreshCacheOnly 仅刷新缓存不刷新图标和通知改变 - 默认：否
     * @return [Boolean] 是否成功
     */
    private fun recachingPrefs(isRefreshCacheOnly: Boolean = false): Boolean {
        /** 必要的延迟防止 Sp 存储不刷新 */
        SystemClock.sleep(300)
        /** 获取可读写状态 */
        return prefs.isPreferencesAvailable.also {
            isUsingCachingMethod = true
            cachingIconDatas()
            if (isRefreshCacheOnly) return@also
            refreshStatusBarIcons()
            refreshNotificationIcons()
        }
    }

    override fun onHook() {
        /** 注册生命周期 */
        registerLifecycle()
        /** 缓存图标数据 */
        cachingIconDatas()
        /** 注入 MIUI 自己增加的一个工具类 */
        NotificationUtilClass.apply {
            /** 强制回写系统的状态栏图标样式为原生 */
            resolve().optional().method {
                name { it == "shouldSubstituteSmallIcon" || it == "shouldSubstituteSmallIconForStatusBarNotification" }
                parameters { it.first() isSubclassOf StatusBarNotification::class }
            }.hookAll().replaceToFalse()
            var isUseLegacy = false
            /**
             * 强制修改 getCustomAppIcon 获取的图标为 smallIcon
             * 部分系统没有 "getCustomAppIcon" 这个方法 - 所以直接忽略
             */
            resolve().optional(silent = true).firstMethodOrNull {
                name = "getCustomAppIcon"
                parameters(Notification::class, Context::class)
            }?.hook()?.after {
                val nf = args().first().cast<Notification>()
                val appname = nf?.extras?.getString("miui.opPkg")
                val context = args(index = 1).cast<Context>()
                if (appname != null) {
                    val appContext = context?.createPackageContext(appname, Context.CONTEXT_IGNORE_SECURITY)
                    val iconBitmap = nf.smallIcon?.loadDrawable(appContext)?.toBitmap()
                    result = if (context != null && iconBitmap != null && !iconBitmap.isRecycled)
                        iconBitmap.toDrawable(context.resources)
                    else null
                }
            }
            /**
             * 强制回写系统的状态栏图标样式为原生
             * 部分系统没有 "getSmallIcon" 这个方法 - 所以直接忽略
             */
            val getSmallIconCondition1 = MethodCondition<Any>().apply {
                name = "getSmallIcon"
                parameters { it[0] isSubclassOf StatusBarNotification::class && it[1] == classOf<Int>() }
            }
            val getSmallIconCondition2 = MethodCondition<Any>().apply {
                name = "getSmallIcon"
                parameters(ExpandedNotificationClass)
            }
            val getSmallIconCondition3 = MethodCondition<Any>().apply {
                name = "getSmallIcon"
                parameters { it[0] == classOf<Context>() && it[1] isSubclassOf StatusBarNotification::class }
            }
            val getSmallIconResolver = resolve().optional(silent = true)
            val getSmallIcon = getSmallIconResolver.firstMethodOrNull(getSmallIconCondition1)
                ?: getSmallIconResolver.firstMethodOrNull(getSmallIconCondition2)
                ?: getSmallIconResolver.firstMethodOrNull(getSmallIconCondition3)
                    ?.also { isUseLegacy = true }
            getSmallIcon?.hook()?.after {
                (globalContext ?: args().first().cast())?.also { context ->
                    val expandedNf = args(if (isUseLegacy) 1 else 0).cast<StatusBarNotification?>()
                    /** Hook 状态栏小图标 */
                    compatStatusIcon(
                        context = context,
                        nf = expandedNf,
                        iconDrawable = result<Icon>()?.loadDrawable(context)
                    ).also { pair -> if (pair.second) result = Icon.createWithBitmap(pair.first?.toBitmap()) }
                }
            }
        }
        if (miosVersionCode < 3) {
            /** 焦点通知深色模式切换点 */
            FocusedNotifPromptViewClass?.resolve()?.optional()?.apply {
                firstMethodOrNull {
                    name = "onDarkChanged"
                    parameters(ArrayList::class, Float::class, Int::class, Int::class, Int::class, Boolean::class)
                }?.hook()?.after {
                    val isDark = args(index = 1).float()
                    val mIcon = firstFieldOrNull { name = "mIcon" }?.of(instance)?.get()
                    if (ConfigData.isEnableModuleLog)
                        YLog.debug("FocusedNotifPromptView DEBUG $isDark $mIcon")
                    if (focusedIcon || ConfigData.isEnableFocusNotificationFix)
                        mIcon?.asResolver()?.optional()?.firstMethodOrNull {
                            name = "setColorFilter"
                            superclass()
                        }?.invoke(if (isDark <= 0.5f) Color.WHITE else Color.BLACK)
                }
            }
            /** 去他妈的焦点通知彩色图标 */
            FocusUtilsClass?.resolve()?.optional()?.apply {
                fun HookParam.hookTickerDarkIcon(isDark: Boolean) {
                    (globalContext ?: args().first().cast())?.also { context ->
                        val expandedNf = args().first().cast<StatusBarNotification?>()
                        val small = expandedNf?.notification?.smallIcon
                        /** Hook 状态栏小图标 */
                        compatStatusIcon(
                            context = context,
                            nf = expandedNf,
                            iconDrawable = small?.loadDrawable(context)
                        ).also { pair ->
                            focusedIcon = pair.second
                            val originalBitmap = pair.first?.toBitmap()
                            val bitmap = originalBitmap?.scale(50, 50)
                            result = Icon.createWithBitmap(bitmap).apply {
                                if (pair.second || ConfigData.isEnableFocusNotificationFix)
                                    setTint(if (isDark) Color.BLACK else Color.WHITE)
                            }
                        }
                    }
                }
                firstMethodOrNull {
                    name = "getStatusBarTickerDarkIcon"
                    parameters {
                        (it.first() == classOf<StatusBarNotification>() ||
                            it.first() == ExpandedNotificationClass) && it.size == 1
                    }
                }?.hook()?.after { hookTickerDarkIcon(isDark = true) }
                firstMethodOrNull {
                    name = "getStatusBarTickerIcon"
                    parameters {
                        (it.first() == classOf<StatusBarNotification>() ||
                            it.first() == ExpandedNotificationClass) && it.size == 1
                    }
                }?.hook()?.after { hookTickerDarkIcon(isDark = false) }
            }
        }
        /** 注入状态栏通知图标实例 */
        StatusBarIconViewClass.resolve().optional().firstMethodOrNull {
            name = "updateIconColor"
            emptyParameters()
        }?.hook()?.after {
            val iconView = instance<ImageView>()
            val expandedNf = iconView.asResolver().optional().firstFieldOrNull { name = "mNotification" }?.get<StatusBarNotification>()
            /** Hook 状态栏小图标 */
            compatStatusIcon(
                context = iconView.context,
                nf = expandedNf,
                iconDrawable = expandedNf?.notification?.smallIcon?.loadDrawable(iconView.context)
            ).also { pair ->
                if (pair.second)
                    result = iconView.setImageDrawable(pair.first?.toBitmap()?.toDrawable(iconView.resources))
            }
            updateStatusBarIconColor(iconView)
        }
        /**
         * 注入状态栏通知图标容器管理实例
         * 在 Android 15 中，这个类被移除变成了 `interface`，所以判断并跳过 Hook 行为
         */
        val isPlaceholder = NotificationIconAreaControllerClass?.isInterface == true
        if (!isPlaceholder) NotificationIconAreaControllerClass?.resolve()?.optional()?.apply {
            /** Hook 深色图标模式改变 */
            firstMethodOrNull {
                name = "onDarkChanged"
                parameterCount { it > 0 }
            }?.hook()?.after {
                firstFieldOrNull { name = "mNotificationIcons" }?.of(instance)?.get<ViewGroup>()?.also {
                    /** 重新设置通知图标容器实例 */
                    notificationIconContainer = it
                    when (args(index = 1).float()) {
                        1.0f -> {
                            isDarkIconMode = true
                            updateStatusBarIconsColor(it, isDarkIconMode = true)
                        }
                        0.0f -> {
                            isDarkIconMode = false
                            updateStatusBarIconsColor(it, isDarkIconMode = false)
                        }
                        else -> updateStatusBarIconsColor(it, isDarkIconMode = false, args(index = 2).int())
                    }
                }
            }
            /** Hook 更新通知图标事件 */
            method {
                name { it == "updateNotificationIcons" || it.startsWith("onChanged") }
            }.hookAll().after {
                firstFieldOrNull { name = "mNotificationIcons" }?.of(instance)?.get<ViewGroup>()?.also {
                    /** 重新设置通知图标容器实例 */
                    notificationIconContainer = it
                    updateStatusBarIconsColor(it)
                    /** 延迟防止新添加的通知图标不刷新 */
                    delayedRun { updateStatusBarIconsColor(it) }
                }
            }
        } else MiuiClockClass?.resolve()?.optional()?.apply {
            firstMethodOrNull {
                name = "onDarkChanged"
                parameterCount { it > 4 }
            }?.hook()?.after {
                notificationIconContainer?.let {
                    when (args(index = 1).float()) {
                        1.0f -> {
                            isDarkIconMode = true
                            updateStatusBarIconsColor(it, isDarkIconMode = true)
                        }
                        0.0f -> {
                            isDarkIconMode = false
                            updateStatusBarIconsColor(it, isDarkIconMode = false)
                        }
                        else -> updateStatusBarIconsColor(it, isDarkIconMode = false, args(index = 2).int())
                    }
                }
            }
        }
        /** 注入状态栏通知图标实例 */
        StatusBarIconViewClass.resolve().optional().apply {
            firstMethodOrNull {
                name = "setNotification"
                parameters {
                    (it.first() == classOf<StatusBarNotification>() ||
                        it.first() == ExpandedNotificationClass) && it.size == 1
                }
            }?.hook()?.after {
                /** 注册壁纸颜色监听 */
                if (args().first().any() != null) instance<ImageView>().also { registerWallpaperColorChanged(it) }
            }
            /** Hook 深色图标模式改变 */
            if (isPlaceholder) firstMethodOrNull {
                name = "onDarkChanged"
                parameterCount { it > 4 }
            }?.hook()?.after {
                val self = instance<ImageView>()
                when (args(index = 1).float()) {
                    1.0f -> {
                        isDarkIconMode = true
                        updateStatusBarIconColor(self, isDarkIconMode = true)
                    }
                    0.0f -> {
                        isDarkIconMode = false
                        updateStatusBarIconColor(self, isDarkIconMode = false)
                    }
                    else -> updateStatusBarIconColor(self, isDarkIconMode = false, args(index = 2).int())
                }
            }
        }
        /** 注入设置管理器实例 */
        SettingsManagerClass?.resolve()?.optional()?.constructor {}?.hookAll()?.after { settingsManager = instance }
        /** 注入通知控制器实例 */
        StatusBarNotificationPresenterClass.resolve().optional().constructor {}.hookAll().after { notificationPresenter = instance }
        /** 注入状态栏通知图标容器实例 */
        NotificationIconContainerClass.apply {
            resolve().optional().firstMethodOrNull {
                name = "applyIconStates"
            }?.hook()?.after { updateStatusBarIconsAlpha(instance()) }
            resolve().optional().firstMethodOrNull {
                name = "resetViewStates"
            }?.hook()?.after {
                updateStatusBarIconsAlpha(instance())
                /** HyperOS 系统设置修改通知图标个数触发此方法 */
                hookStatusBarMaxStaticIcons(instance)
            }
            resolve().optional().firstMethodOrNull {
                name { it == "calculateIconTranslations" || it == "calculateIconXTranslations" }
            }?.hook()?.after {
                /** 缓存实例 */
                notificationIconContainer = instance<ViewGroup>()
                /** 修复部分开发版状态栏图标只能显示一个的问题 */
                when (miuiIncrementalVersion.lowercase()) {
                    "22.3.14", "22.3.15", "22.3.16", "v13.0.1.1.16.dev", "22.3.18" ->
                        instance<ViewGroup>().layoutParams.width = 9999
                }
            }
            /** 旧版方法 (A13 MIUI) - 新版不存在 */
            resolve().optional(silent = true).firstMethodOrNull {
                name = "updateState"
            }?.hook()?.before { hookStatusBarMaxStaticIcons(instance) }
            /** 新版方法 (A14 MIUI14 / A14 HyperOS) - 旧版不存在 */
            resolve().optional(silent = true).firstMethodOrNull {
                name = "onMeasure"
            }?.hook()?.before { hookStatusBarMaxStaticIcons(instance) }
            /** 旧版方法 - 新版不存在 */
            resolve().optional(silent = true).firstMethodOrNull {
                name = "setMaxStaticIcons"
                parameters(Int::class)
            }?.hook()?.before { isShowNotificationIcons = args().first().int() > 0 }
            /** 旧版方法 - 新版 (A15 HyperOS) 不存在 */
            resolve().optional(silent = true).firstMethodOrNull {
                name = "miuiShowNotificationIcons"
                parameters(Boolean::class)
            }?.hook()?.before { isShowNotificationIcons = args().first().boolean() }
        }
        /** 注入原生通知包装纸实例 */
        NotificationHeaderViewWrapperClass.resolve().optional().apply {
            firstMethodOrNull {
                name { it == "resolveHeaderViews" || it == "handleHeaderViews" || it == "resolveViews" }
            }?.hook()?.after { hookNotificationViewWrapper(instance) }
            firstMethodOrNull {
                name = "onContentUpdated"
            }?.hook()?.after { hookNotificationViewWrapper(instance) }
        }
        /** 修改 MIUI 风格通知栏的通知图标 */
        MiuiNotificationViewWrapperClass?.resolve()?.optional()?.apply {
            constructor {}.hookAll().after {
                val nf = instance.getRowPair().second.getSbn() ?: return@after
                firstFieldOrNull { name = "mAppIcon" }?.of(instance)?.get<ImageView>()?.clone {
                    if (ConfigData.isEnableReplaceMiuiStyleNotifyIcon || ConfigData.isEnableNotifyIconForceAppIcon)
                        compatNotifyIcon(context, nf, iconView = this, isUseMaterial3Style = true, isMiuiPanel = true)
                    else setImageDrawable(nf.miuiAppIcon?.loadDrawable(context) ?: context.appIconOf(nf.packageName))
                }
            }
        }
        /** 修改 MIUI 风格通知栏的通知图标 - 折叠通知 */
        MiuiNotificationChildrenContainerClass?.resolve()?.optional()?.apply {
            /** 替换通知小图标 */
            firstMethodOrNull {
                name = "updateAppIcon"
                parameters(Boolean::class)
            }?.hook()?.after {
                firstFieldOrNull { name = "mAppIcon" }?.of(instance)?.get<ImageView>()?.apply {
                    val nf = NotificationChildrenContainerClass.resolve().optional().firstFieldOrNull {
                        name = "mContainingNotification"
                    }?.of(instance)?.get()?.getSbn() ?: return@after
                    if (ConfigData.isEnableReplaceMiuiStyleNotifyIcon || ConfigData.isEnableNotifyIconForceAppIcon)
                        compatNotifyIcon(context, nf, iconView = this, isUseMaterial3Style = true, isMiuiPanel = true)
                    else setImageDrawable(nf.miuiAppIcon?.loadDrawable(context) ?: context.appIconOf(nf.packageName))
                }
            }
        }
        /** 干掉下拉通知图标自动设置回 APP 图标的方法 */
        NotificationHeaderViewWrapperInjectorClass?.resolve()?.optional(silent = true)?.apply {
            firstMethodOrNull {
                name = "setAppIcon"
                parameters(Context::class, ImageView::class, ExpandedNotificationClass)
            }?.hook()?.intercept()
            firstMethodOrNull {
                name = "setAppIcon"
                parameters(ImageView::class, ExpandedNotificationClass)
            }?.hook()?.intercept()
            firstMethodOrNull {
                name = "resetIconBgAndPaddings"
                parameters(ImageView::class, ExpandedNotificationClass)
            }?.hook()?.intercept()
        }
        NotificationContentInflaterInjectorClass?.resolve()?.optional()?.firstMethodOrNull {
            name = "handleAppIcon"
            /**
             * MIUI 14 ([RemoteViewsClass], [NotificationClass])
             * HyperOS ([RemoteViewsClass], [NotificationClass], [ContextClass])
             * HyperOS 2.0 ([RemoteViewsClass], [NotificationClass], [ContextClass], [BooleanClass])
             */
            parameterCount { it in 2..4 }
        }?.hook()?.intercept()
        /**
         * 尝试修复从 MIUI 14 开始出现的一个崩溃问题
         * 由于模块注入推送的通知没有对 [StatusBarNotification] 设置 TAG 会导致其空指针
         * 直接替换掉它自己的实现方法 - 使用自己的方式实现这个功能
         * ```java
         * public final boolean isUnimportantEntry(NotificationEntry notificationEntry) {
         *     return notificationEntry.getSbn().getPackageName().equals("com.android.systemui") &&
         *         notificationEntry.getSbn().getTag().equals("UNIMPORTANT");
         * }
         * ```
         */
        NotificationStatClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "isUnimportantEntry"
            parameterCount = 1
        }?.hook()?.replaceAny {
            args().first().any()?.asResolver()?.optional()?.firstMethodOrNull {
                name = "getSbn"
                superclass()
            }?.invoke<StatusBarNotification>()?.let { sbn ->
                sbn.packageName == PackageName.SYSTEMUI && sbn.tag == "UNIMPORTANT"
            } ?: false
        }
    }
}