/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2017-2023 Fankes Studio(qzmmcn@163.com)
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

import android.app.Notification
import android.app.NotificationManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.children
import androidx.core.view.isVisible
import com.fankes.miui.notify.R
import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.const.PackageName
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.params.factory.isAppNotifyHookAllOf
import com.fankes.miui.notify.params.factory.isAppNotifyHookOf
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
import top.defaults.drawabletoolbox.DrawableBuilder

/**
 * 系统界面核心 Hook 类
 */
object SystemUIHooker : YukiBaseHooker() {

    /** MIUI 新版本存在的类 */
    private const val SystemUIApplicationClass = "${PackageName.SYSTEMUI}.SystemUIApplication"

    /** MIUI 新版本存在的类 */
    private const val MiuiNotificationViewWrapperClass = "${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.MiuiNotificationViewWrapper"

    /** MIUI 新版本存在的类 */
    private const val MiuiNotificationChildrenContainerClass =
        "${PackageName.SYSTEMUI}.statusbar.notification.stack.MiuiNotificationChildrenContainer"

    /** MIUI 新版本存在的类 */
    private const val NotificationHeaderViewWrapperInjectorClass =
        "${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.NotificationHeaderViewWrapperInjector"

    /** 原生存在的类 */
    private const val NotificationChildrenContainerClass = "${PackageName.SYSTEMUI}.statusbar.notification.stack.NotificationChildrenContainer"

    /** 原生存在的类 */
    private const val NotificationIconAreaControllerClass = "${PackageName.SYSTEMUI}.statusbar.phone.NotificationIconAreaController"

    /** 原生存在的类 */
    private const val ContrastColorUtilClass = "com.android.internal.util.ContrastColorUtil"

    /** 原生存在的类 */
    private const val StatusBarIconViewClass = "${PackageName.SYSTEMUI}.statusbar.StatusBarIconView"

    /** 原生存在的类 */
    private const val NotificationIconContainerClass = "${PackageName.SYSTEMUI}.statusbar.phone.NotificationIconContainer"

    /** 根据多个版本存在不同的包名相同的类 */
    private val StatusBarNotificationPresenterClass = VariousClass(
        "${PackageName.SYSTEMUI}.statusbar.phone.StatusBarNotificationPresenter",
        "${PackageName.SYSTEMUI}.statusbar.phone.StatusBar"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val ExpandableNotificationRowClass = VariousClass(
        "${PackageName.SYSTEMUI}.statusbar.notification.row.ExpandableNotificationRow",
        "${PackageName.SYSTEMUI}.statusbar.ExpandableNotificationRow"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationViewWrapperClass = VariousClass(
        "${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.NotificationViewWrapper",
        "${PackageName.SYSTEMUI}.statusbar.notification.NotificationViewWrapper"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationHeaderViewWrapperClass = VariousClass(
        "${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper",
        "${PackageName.SYSTEMUI}.statusbar.notification.NotificationHeaderViewWrapper"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationUtilClass = VariousClass(
        "${PackageName.SYSTEMUI}.statusbar.notification.NotificationUtil",
        "${PackageName.SYSTEMUI}.miui.statusbar.notification.NotificationUtil"
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val ExpandedNotificationClass = VariousClass(
        "${PackageName.SYSTEMUI}.statusbar.notification.ExpandedNotification",
        "${PackageName.SYSTEMUI}.miui.statusbar.ExpandedNotification"
    )

    /** 缓存的通知图标优化数组 */
    private var iconDatas = ArrayList<IconDataBean>()

    /** 当前是否处于深色图标模式 - 跟随 Hook 保存 */
    private var isDarkIconMode = false

    /** 是否显示通知图标 - 跟随 Hook 保存 */
    private var isShowNotificationIcons = true

    /** 状态栏原始通知图标最大个数限制 */
    private var statusBarMaxStaticIcons = -1

    /** 是否已经使用过缓存刷新功能 */
    private var isUsingCachingMethod = false

    /** 状态栏通知图标容器 */
    private var notificationIconContainer: ViewGroup? = null

    /** 通知栏通知控制器 */
    private var notificationPresenter: Any? = null

    /** 仅监听一次主题壁纸颜色变化 */
    private var isWallpaperColorListenerSetUp = false

    /**
     * 获取全局上下文
     * @return [Context] or null
     */
    private val globalContext
        get() = SystemUIApplicationClass.toClassOrNull()?.method { name = "getContext" }?.ignored()?.get()?.invoke<Context?>() ?: appContext

    /**
     * 是否为 MIUI 样式通知栏 - 旧版 - 新版一律返回 false
     * @return [Boolean]
     */
    private val isShowMiuiStyle
        get() = NotificationUtilClass.toClassOrNull()?.method { name = "showMiuiStyle" }?.ignored()?.get()?.boolean() ?: false

    /**
     * 是否没有单独的 MIUI 通知栏样式
     * @return [Boolean]
     */
    private val isNotHasAbsoluteMiuiStyle get() = MiuiNotificationViewWrapperClass.hasClass().not()

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
     * 打印日志
     * @param tag 标识
     * @param context 实例
     * @param nf 通知实例
     * @param isCustom 是否为通知优化生效图标
     * @param isGrayscale 是否为灰度图标
     */
    private fun loggerDebug(tag: String, context: Context, nf: StatusBarNotification?, isCustom: Boolean, isGrayscale: Boolean) {
        if (ConfigData.isEnableModuleLog) loggerD(
            msg = "(Processing $tag) ↓\n" +
                    "[Title]: ${nf?.notification?.extras?.getString(Notification.EXTRA_TITLE)}\n" +
                    "[Content]: ${nf?.notification?.extras?.getString(Notification.EXTRA_TEXT)}\n" +
                    "[App Name]: ${context.appNameOf(packageName = nf?.packageName ?: "")}\n" +
                    "[Package Name]: ${nf?.packageName}\n" +
                    "[Sender Package Name]: ${nf?.compatOpPkgName}\n" +
                    "[Custom Icon]: $isCustom\n" +
                    "[Grayscale Icon]: $isGrayscale\n" +
                    "[From Xmsf]: ${nf?.isXmsf}\n" +
                    "[String]: ${nf?.notification}"
        )
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
            ContrastColorUtilClass.toClass().let {
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
        StatusBarIconViewClass.toClassOrNull()?.field { name = "mNotification" }?.also { result ->
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
        notificationPresenter?.current()?.method {
            name = "updateNotificationsOnDensityOrFontScaleChanged"
            emptyParam()
        }?.call()
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

        /** 目标彩色通知 APP 图标 */
        val customTriple = compatCustomIcon(context, isGrayscaleIcon, notifyInstance.nfPkgName)

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
     */
    private fun compatNotifyIcon(
        context: Context,
        nf: StatusBarNotification?,
        iconView: ImageView,
        isExpanded: Boolean = true,
        isUseMaterial3Style: Boolean = isUpperOfAndroidS
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
            val hasIconColor = iconColor != 0

            /** 通知图标适配颜色 */
            val supportColor = iconColor.let {
                when {
                    isUseMaterial3Style -> newStyle
                    it == 0 || isExpanded.not() -> oldStyle
                    else -> it
                }
            }

            /** 获取通知小图标 */
            val iconDrawable = notifyInstance.notification.smallIcon.loadDrawable(context)
                ?: return@let loggerW(msg = "compatNotifyIcon got null smallIcon")

            /** 判断图标风格 */
            val isGrayscaleIcon = notifyInstance.isXmsf.not() && isGrayscaleIcon(context, iconDrawable)

            /** 自定义默认小图标 */
            var customIcon: Drawable? = null

            /** 自定义默认小图标颜色 */
            var customIconColor = 0
            compatCustomIcon(context, isGrayscaleIcon, notifyInstance.nfPkgName).also {
                /** 不处理占位符图标 */
                if (it.third) return@also
                customIcon = it.first
                customIconColor = if (isUseMaterial3Style || isExpanded)
                    (it.second.takeIf { e -> e != 0 } ?: (if (isUseMaterial3Style) context.systemAccentColor else 0)) else 0
            }
            /** 打印日志 */
            loggerDebug(tag = "Notification Panel Icon", context, notifyInstance, isCustom = customIcon != null, isGrayscaleIcon)
            /** 处理自定义通知图标优化 */
            when {
                ConfigData.isEnableNotifyIconForceAppIcon -> {
                    @Suppress("DEPRECATION")
                    val miuiAppIcon = notifyInstance.notification?.extras?.getParcelable<Icon?>("miui.appIcon")
                    setDefaultNotifyIcon(drawable = miuiAppIcon?.loadDrawable(context) ?: context.appIconOf(notifyInstance.nfPkgName))
                }
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
                    /** 设置原生的背景边距 */
                    if (isUseMaterial3Style) setPadding(4.dp(context), 4.dp(context), 4.dp(context), 4.dp(context))
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
                        /** 设置原生的背景边距 */
                        if (isUseMaterial3Style) setPadding(4.dp(context), 4.dp(context), 4.dp(context), 4.dp(context))
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
        val notifyInstance = current().field { name = "mNotification" }.cast<StatusBarNotification>()
            ?: return loggerW(msg = "isGrayscaleIcon got null mNotification").let { false }

        /** 获取通知小图标 */
        val iconDrawable = notifyInstance.notification?.smallIcon?.loadDrawable(context)
            ?: return loggerW(msg = "isGrayscaleIcon got null smallIcon").let { false }

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
    private fun updateStatusBarIconColor(container: ViewGroup, isDarkIconMode: Boolean = this.isDarkIconMode, animColor: Int? = null) {
        if (container.childCount > 0) container.children.forEach { iconView ->
            if (iconView !is ImageView) return@forEach
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
    }

    /**
     * 更新状态栏通知图标透明度
     * @param container 当前 [NotificationIconContainerClass] 的实例
     */
    private fun updateStatusBarIconAlpha(container: ViewGroup) {
        val iconStateMethod = container.current().method { name = "getIconState"; param(StatusBarIconViewClass) }
        if (container.childCount > 0) container.children.forEach { iconView ->
            if (iconView !is ImageView) return@forEach
            val iconAlpha = if (iconView.isGrayscaleIcon()) statusBarIconAlpha else 1f
            iconView.alpha = iconAlpha
            iconStateMethod.call(iconView)?.current()?.field { name = "alpha"; superClass() }?.set(iconAlpha)
        }
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
        val row = NotificationViewWrapperClass.toClassOrNull()?.field {
            name = "mRow"
        }?.get(this)?.any()?.also {
            isExpanded = ExpandableNotificationRowClass.toClassOrNull()?.method {
                name = "isExpanded"
                returnType = BooleanType
            }?.get(it)?.boolean() ?: false
        }
        return Pair(isExpanded, row)
    }

    /**
     * 从 [ExpandableNotificationRowClass] 中获取 [StatusBarNotification]
     * @return [StatusBarNotification] or null
     */
    private fun Any?.getSbn() =
        ExpandableNotificationRowClass.toClassOrNull()
            ?.method { name = "getEntry" }
            ?.get(this)?.call()?.let {
                it.javaClass.method {
                    name = "getSbn"
                }.ignored().get(it).invoke<StatusBarNotification>()
            } ?: ExpandableNotificationRowClass.toClassOrNull()
            ?.method { name = "getStatusBarNotification" }
            ?.get(this)?.invoke<StatusBarNotification>()

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
            registerReceiver(Intent.ACTION_USER_PRESENT) { _, _ -> if (isUsingCachingMethod) refreshStatusBarIcons() }
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
                if (intent.action.equals(Intent.ACTION_PACKAGE_REPLACED).not() &&
                    intent.getBooleanExtra(Intent.EXTRA_REPLACING, false)
                ) return@registerReceiver
                if (ConfigData.isEnableNotifyIconFix && ConfigData.isEnableNotifyIconFixNotify)
                    intent.data?.schemeSpecificPart?.also { packageName ->
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
            prefs.clearCache()
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
        /** 注入状态栏通知图标容器管理实例 */
        NotificationIconAreaControllerClass.hook {
            /** Hook 深色图标模式改变 */
            injectMember {
                method {
                    name = "onDarkChanged"
                    paramCount { it > 0 }
                }
                afterHook {
                    field { name = "mNotificationIcons" }.get(instance).cast<ViewGroup>()?.also {
                        /** 重新设置通知图标容器实例 */
                        notificationIconContainer = it
                        when (args(index = 1).float()) {
                            1.0f -> {
                                isDarkIconMode = true
                                updateStatusBarIconColor(it, isDarkIconMode = true)
                            }
                            0.0f -> {
                                isDarkIconMode = false
                                updateStatusBarIconColor(it, isDarkIconMode = false)
                            }
                            else -> updateStatusBarIconColor(it, isDarkIconMode = false, args(index = 2).int())
                        }
                    }
                }
            }
            /** Hook 更新通知图标事件 */
            injectMember {
                method { name { it == "updateNotificationIcons" || it == "onChanged" } }.all()
                afterHook {
                    field { name = "mNotificationIcons" }.get(instance).cast<ViewGroup>()?.also {
                        /** 重新设置通知图标容器实例 */
                        notificationIconContainer = it
                        updateStatusBarIconColor(it)
                        /** 延迟防止新添加的通知图标不刷新 */
                        delayedRun { updateStatusBarIconColor(it) }
                    }
                }
            }
        }
        /** 注入状态栏通知图标实例 */
        StatusBarIconViewClass.hook {
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
                allMembers(MembersType.CONSTRUCTOR)
                afterHook { notificationPresenter = instance }
            }
        }
        /** 注入状态栏通知图标容器实例 */
        NotificationIconContainerClass.hook {
            injectMember {
                method { name = "applyIconStates" }
                afterHook { updateStatusBarIconAlpha(instance()) }
            }
            injectMember {
                method { name = "resetViewStates" }
                afterHook { updateStatusBarIconAlpha(instance()) }
            }
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
                    val maxStaticIconsField = field { name = "MAX_STATIC_ICONS" }.get(instance)
                    if (statusBarMaxStaticIcons == -1) statusBarMaxStaticIcons = maxStaticIconsField.int()
                    /** 解除状态栏通知图标个数限制 */
                    if (isShowNotificationIcons && ConfigData.isEnableLiftedStatusIconCount)
                        maxStaticIconsField.set(ConfigData.liftedStatusIconCount.let { if (it in 0..100) it else 5 })
                    else maxStaticIconsField.set(if (isShowNotificationIcons) statusBarMaxStaticIcons else 0)
                }
            }.by { NotificationIconContainerClass.toClassOrNull()?.hasField { name = "MAX_STATIC_ICONS" } ?: false }
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
                method { name { it == "resolveHeaderViews" || it == "handleHeaderViews" || it == "resolveViews" } }
                afterHook {
                    /** 忽略较旧版本 - 在没有 MIUI 通知栏样式的时候可能出现奇怪的问题 */
                    if (isNotHasAbsoluteMiuiStyle && isShowMiuiStyle) return@afterHook

                    /** 获取小图标 */
                    val iconImageView = NotificationHeaderViewWrapperClass.toClassOrNull()
                        ?.field { name = "mIcon" }?.get(instance)?.cast<ImageView>() ?: return@afterHook

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
                    field { name = "mAppIcon" }.get(instance).cast<ImageView>()?.clone {
                        compatNotifyIcon(
                            context = context,
                            nf = instance.getRowPair().second.getSbn(),
                            iconView = this,
                            isUseMaterial3Style = true
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
                    field { name = "mAppIcon" }.get(instance).cast<ImageView>()?.apply {
                        compatNotifyIcon(context, NotificationChildrenContainerClass.toClassOrNull()?.field {
                            name = "mContainingNotification"
                        }?.get(instance)?.any()?.getSbn(), iconView = this, isUseMaterial3Style = true)
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
    }
}