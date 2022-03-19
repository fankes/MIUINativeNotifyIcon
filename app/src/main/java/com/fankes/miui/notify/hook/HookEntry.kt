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
 * This file is Created by fankes on 2022/2/15.
 */
package com.fankes.miui.notify.hook

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.service.notification.StatusBarNotification
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.hook.HookConst.ENABLE_COLOR_ICON_COMPAT
import com.fankes.miui.notify.hook.HookConst.ENABLE_COLOR_ICON_HOOK
import com.fankes.miui.notify.hook.HookConst.ENABLE_HOOK_STATUS_ICON_COUNT
import com.fankes.miui.notify.hook.HookConst.ENABLE_MODULE
import com.fankes.miui.notify.hook.HookConst.ENABLE_MODULE_LOG
import com.fankes.miui.notify.hook.HookConst.ENABLE_NOTIFY_ICON_FIX
import com.fankes.miui.notify.hook.HookConst.HOOK_STATUS_ICON_COUNT
import com.fankes.miui.notify.hook.HookConst.SYSTEMUI_PACKAGE_NAME
import com.fankes.miui.notify.hook.factory.isAppNotifyHookAllOf
import com.fankes.miui.notify.hook.factory.isAppNotifyHookOf
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.utils.drawable.drawabletoolbox.DrawableBuilder
import com.fankes.miui.notify.utils.factory.*
import com.fankes.miui.notify.utils.tool.BitmapCompatTool
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.bean.VariousClass
import com.highcapable.yukihookapi.hook.factory.*
import com.highcapable.yukihookapi.hook.log.loggerD
import com.highcapable.yukihookapi.hook.log.loggerW
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.highcapable.yukihookapi.hook.type.android.ContextClass
import com.highcapable.yukihookapi.hook.type.android.DrawableClass
import com.highcapable.yukihookapi.hook.type.android.ImageViewClass
import com.highcapable.yukihookapi.hook.type.java.BooleanType
import com.highcapable.yukihookapi.hook.type.java.IntType
import com.highcapable.yukihookapi.hook.xposed.proxy.YukiHookXposedInitProxy

@InjectYukiHookWithXposed
class HookEntry : YukiHookXposedInitProxy {

    companion object {

        /** MIUI 新版本存在的类 */
        private const val SystemUIApplicationClass = "$SYSTEMUI_PACKAGE_NAME.SystemUIApplication"

        /** MIUI 新版本存在的类 */
        private const val NotificationHeaderViewWrapperInjectorClass =
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.row.wrapper.NotificationHeaderViewWrapperInjector"

        /** 原生存在的类 */
        private const val ContrastColorUtilClass = "com.android.internal.util.ContrastColorUtil"

        /** 原生存在的类 */
        private const val StatusBarIconViewClass = "$SYSTEMUI_PACKAGE_NAME.statusbar.StatusBarIconView"

        /** 原生存在的类 */
        private const val NotificationIconContainerClass = "$SYSTEMUI_PACKAGE_NAME.statusbar.phone.NotificationIconContainer"

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
    }

    /** 缓存的通知优化图标数组 */
    private var iconDatas = ArrayList<IconDataBean>()

    /** 是否显示通知图标 - 跟随 Hook 保存 */
    private var isShowNotificationIcons = true

    /**
     * - 这个是修复彩色图标的关键核心代码判断
     *
     * 判断是否为灰度图标 - 反射执行系统方法
     * @param context 实例
     * @param drawable 要判断的图标
     * @return [Boolean]
     */
    private fun PackageParam.isGrayscaleIcon(context: Context, drawable: Drawable) =
        if (!prefs.getBoolean(ENABLE_COLOR_ICON_COMPAT)) safeOfFalse {
            ContrastColorUtilClass.clazz.let {
                it.method {
                    name = "isGrayscaleIcon"
                    param(DrawableClass)
                }.get(it.method {
                    name = "getInstance"
                    param(ContextClass)
                }.get().invoke(context)).invoke<Boolean>(drawable) ?: false
            }
        } else BitmapCompatTool.isGrayscaleDrawable(drawable)

    /**
     * 是否为旧版本 MIUI 方案
     *
     * 拥有 “handleHeaderViews” 方法
     * @return [Boolean]
     */
    private val PackageParam.hasHandleHeaderViews
        get() = safeOfFalse {
            NotificationHeaderViewWrapperClass.clazz.hasMethod(name = "handleHeaderViews")
        }

    /**
     * 获取当前通知栏的样式
     *
     *  - ❗新版本可能不存在这个方法
     * @return [Boolean]
     */
    private val PackageParam.isShowMiuiStyle
        get() = safeOfFalse {
            NotificationUtilClass.clazz.method { name = "showMiuiStyle" }.get().invoke() ?: false
        }

    /**
     * 适配通知栏、状态栏图标
     *
     * 适配第三方图标包对系统包管理器更换图标后的彩色图标
     *
     * 自动识别 MIPUSH 图标
     * @param context 实例
     * @param iconDrawable 原始图标
     * @return [Drawable] 适配的图标
     */
    private fun StatusBarNotification.compatNotifyIcon(context: Context, iconDrawable: Drawable) = safeOf(iconDrawable) {
        /** 给 MIPUSH 设置 APP 自己的图标 */
        if (isXmsf && opPkgName.isNotBlank())
            findAppIcon(context)
        else iconDrawable
    }

    /**
     * 获取推送通知的应用名称
     * @param context 实例
     * @return [String]
     */
    private fun StatusBarNotification.findAppName(context: Context) = safeOf(default = "<unknown>") {
        context.packageManager.getPackageInfo(opPkgName, 0).applicationInfo.loadLabel(context.packageManager)
    }

    /**
     * 获取通知栏、状态栏 APP 图标
     * @param context 实例
     * @return [Drawable] 适配的图标
     */
    private fun StatusBarNotification.findAppIcon(context: Context) = safeOf(notification.smallIcon.loadDrawable(context)) {
        context.packageManager.getPackageInfo(opPkgName, 0).applicationInfo.loadIcon(context.packageManager)
    }

    /**
     * 打印日志
     * @param tag 标识
     * @param context 实例
     * @param expandedNf 通知实例
     * @param isCustom 是否为通知优化生效图标
     * @param isGrayscale 是否为灰度图标
     */
    private fun PackageParam.printLogcat(
        tag: String,
        context: Context,
        expandedNf: StatusBarNotification?,
        isCustom: Boolean,
        isGrayscale: Boolean
    ) {
        if (prefs.getBoolean(ENABLE_MODULE_LOG)) loggerD(
            msg = "$tag --> [${expandedNf?.findAppName(context)}][${expandedNf?.opPkgName}] " +
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
    private val StatusBarNotification.opPkgName get() = if (isXmsf) xmsfPkgName else compatOpPkgName

    /**
     * 获取 MIPUSH 通知真实包名
     * @return [String]
     */
    private val StatusBarNotification.xmsfPkgName: String
        get() {
            val xmsfPkg = notification.extras.getString("xmsf_target_package") ?: ""
            val targetPkg = notification.extras.getString("target_package") ?: ""
            return xmsfPkg.ifBlank { targetPkg.ifBlank { compatOpPkgName } }
        }

    /**
     * 获取全局上下文
     * @return [Context] or null
     */
    private val PackageParam.globalContext
        get() = safeOfNull {
            SystemUIApplicationClass.clazz.method { name = "getContext" }.ignoredError().get().invoke<Context>()
        }

    /**
     * 自动适配状态栏、通知栏自定义小图标
     * @param isGrayscaleIcon 是否为灰度图标
     * @param packageName APP 包名
     * @return [Pair] - ([Bitmap] 位图,[Int] 颜色)
     */
    private fun PackageParam.compatCustomIcon(isGrayscaleIcon: Boolean, packageName: String): Pair<Bitmap?, Int> {
        var customPair: Pair<Bitmap?, Int>? = null
        if (prefs.getBoolean(ENABLE_NOTIFY_ICON_FIX, default = true))
            run {
                if (iconDatas.isNotEmpty())
                    iconDatas.forEach {
                        if (packageName == it.packageName && isAppNotifyHookOf(it)) {
                            if (!isGrayscaleIcon || isAppNotifyHookAllOf(it))
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
     * @param it 回调小图标 - ([Bitmap] 小图标)
     */
    private fun PackageParam.hookSmallIconOnSet(
        context: Context,
        expandedNf: StatusBarNotification?,
        iconDrawable: Drawable?,
        it: (Bitmap) -> Unit
    ) = runSafe(msg = "GetSmallIconOnSet") {
        if (iconDrawable == null) return@runSafe
        /** 如果没开启修复 APP 的彩色图标 */
        if (!prefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)) return@runSafe
        /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
        expandedNf?.also { notifyInstance ->
            /** 判断是 MIUI 样式就停止 Hook */
            if (context.isMiuiNotifyStyle) {
                it(notifyInstance.findAppIcon(context).toBitmap())
                return@runSafe
            }
            /** 判断是否不是灰度图标 */
            val isNotGrayscaleIcon = notifyInstance.isXmsf || !isGrayscaleIcon(context, iconDrawable)

            /** 目标彩色通知 APP 图标 */
            val customIcon = compatCustomIcon(!isNotGrayscaleIcon, notifyInstance.opPkgName).first
            /** 打印日志 */
            printLogcat(tag = "StatusIcon", context, notifyInstance, isCustom = customIcon != null, !isNotGrayscaleIcon)
            when {
                /** 处理自定义通知图标优化 */
                customIcon != null -> it(customIcon)
                /** 若不是灰度图标自动处理为圆角 */
                isNotGrayscaleIcon -> it(notifyInstance.compatNotifyIcon(context, iconDrawable).toBitmap())
            }
        }
    }

    /**
     * Hook 通知栏小图标
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param expandedNf 通知实例
     * @param iconImageView 通知图标实例
     * @param isExpanded 通知是否展开 - 可做最小化通知处理
     */
    private fun PackageParam.hookNotifyIconOnSet(
        context: Context,
        expandedNf: StatusBarNotification?,
        iconImageView: ImageView,
        isExpanded: Boolean
    ) = runSafe(msg = "AutoSetAppIconOnSet") {
        /** 判断是 MIUI 样式就停止 Hook */
        if (context.isMiuiNotifyStyle) return@runSafe
        /** 如果没开启修复 APP 的彩色图标 */
        if (!prefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)) return@runSafe
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
                    isUpperOfAndroidS -> newStyle
                    it == 0 || !isExpanded -> oldStyle
                    else -> it
                }
            }

            /** 获取通知小图标 */
            val iconDrawable = notifyInstance.notification.smallIcon.loadDrawable(context)

            /** 判断图标风格 */
            val isGrayscaleIcon = !notifyInstance.isXmsf && isGrayscaleIcon(context, iconDrawable)

            /** 自定义默认小图标 */
            var customIcon: Bitmap?

            /** 自定义默认小图标颜色 */
            var customIconColor: Int
            compatCustomIcon(isGrayscaleIcon, notifyInstance.opPkgName).also {
                customIcon = it.first
                customIconColor = if (isUpperOfAndroidS || isExpanded) it.second else 0
            }
            /** 打印日志 */
            printLogcat(tag = "NotifyIcon", context, notifyInstance, isCustom = customIcon != null, isGrayscaleIcon)
            /** 处理自定义通知图标优化 */
            if (customIcon != null)
                iconImageView.apply {
                    /** 设置自定义小图标 */
                    setImageBitmap(customIcon)
                    /** 上色 */
                    setColorFilter(if (isUpperOfAndroidS || customIconColor == 0) supportColor else customIconColor)
                    /** Android 12 设置图标外圈颜色 */
                    if (isUpperOfAndroidS && customIconColor != 0)
                        background = DrawableBuilder().rounded().solidColor(customIconColor).build()
                }
            else {
                /** 重新设置图标 - 防止系统更改它 */
                iconImageView.setImageDrawable(iconDrawable)
                /** 判断如果是灰度图标就给他设置一个白色颜色遮罩 */
                if (isGrayscaleIcon) iconImageView.apply {
                    /** 设置图标着色 */
                    setColorFilter(supportColor)
                    /** Android 12 设置图标外圈颜色 */
                    if (isUpperOfAndroidS && hasIconColor)
                        background = DrawableBuilder().rounded().solidColor(iconColor).build()
                } else iconImageView.apply {
                    /** 重新设置图标 */
                    setImageDrawable(notifyInstance.compatNotifyIcon(context, iconDrawable))
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
                    /** 清除原生的背景边距设置 */
                    if (isUpperOfAndroidS) setPadding(0, 0, 0, 0)
                    /** 清除原生的主题色背景圆圈颜色 */
                    if (isUpperOfAndroidS) background = null
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
    private fun PackageParam.hasIgnoreStatusBarIconColor(context: Context, expandedNf: StatusBarNotification?) =
        if (!context.isMiuiNotifyStyle)
            if (prefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)) safeOfFalse {
                /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
                expandedNf?.let { notifyInstance ->
                    /** 获取通知小图标 */
                    val iconDrawable = notifyInstance.notification.smallIcon.loadDrawable(context)

                    /** 判断是否不是灰度图标 */
                    val isNotGrayscaleIcon = notifyInstance.isXmsf || !isGrayscaleIcon(context, iconDrawable)

                    /** 获取目标修复彩色图标的 APP */
                    val isTargetFixApp = compatCustomIcon(!isNotGrayscaleIcon, notifyInstance.opPkgName).first != null
                    /**
                     * 只要不是灰度就返回彩色图标
                     * 否则不对颜色进行反色处理防止一些系统图标出现异常
                     */
                    (if (isTargetFixApp) false else isNotGrayscaleIcon).also {
                        printLogcat(tag = "IconColor", context, expandedNf, isTargetFixApp, !isNotGrayscaleIcon)
                    }
                } ?: true.also { printLogcat(tag = "IconColor", context, expandedNf = null, isCustom = false, isGrayscale = false) }
            } else false.also { printLogcat(tag = "IconColor", context, expandedNf, isCustom = false, isGrayscale = true) }
        else true.also { printLogcat(tag = "IconColor", context, expandedNf, isCustom = false, isGrayscale = false) }

    override fun onInit() = configs {
        debugTag = "MIUINativeNotifyIcon"
        isDebug = false
    }

    override fun onHook() = encase {
        loadApp(SYSTEMUI_PACKAGE_NAME) {
            when {
                /** 不是 MIUI 系统停止 Hook */
                isNotMIUI -> loggerW(msg = "Aborted Hook -> This System is not MIUI")
                /** 系统版本低于 Android P 停止 Hook */
                isLowerAndroidP -> loggerW(msg = "Aborted Hook -> This System is lower than Android P")
                /** 不是支持的 MIUI 系统停止 Hook */
                isNotSupportMiuiVersion -> loggerW(msg = "Aborted Hook -> This MIUI Version $miuiVersion not supported")
                /** Hook 被手动关闭停止 Hook */
                !prefs.getBoolean(ENABLE_MODULE, default = true) -> loggerW(msg = "Aborted Hook -> Hook Closed")
                /** 开始 Hook */
                else -> {
                    /** 缓存图标数据 */
                    iconDatas = IconPackParams(param = this).iconDatas
                    /** 执行 Hook */
                    NotificationUtilClass.hook {
                        /** 强制回写系统的状态栏图标样式为原生 */
                        injectMember {
                            method {
                                name = "shouldSubstituteSmallIcon"
                                param(ExpandedNotificationClass.clazz)
                            }
                            /**
                             * 为了防止 MIUI 自身的版本不同造成的各种 BUG
                             * 判断是 MIUI 样式就停止 Hook
                             */
                            replaceAny { globalContext?.isMiuiNotifyStyle ?: isShowMiuiStyle }
                        }
                        /** 强制回写系统的状态栏图标样式为原生 */
                        injectMember {
                            var isUseLegacy = false
                            method {
                                name = "getSmallIcon"
                                param(ExpandedNotificationClass.clazz, IntType)
                            }.remedys {
                                method {
                                    name = "getSmallIcon"
                                    param(ExpandedNotificationClass.clazz)
                                }
                                method {
                                    name = "getSmallIcon"
                                    param(ContextClass, ExpandedNotificationClass.clazz)
                                }.onFind { isUseLegacy = true }
                            }
                            afterHook {
                                (globalContext ?: firstArgs as Context).also { context ->
                                    hookSmallIconOnSet(
                                        context = context,
                                        args[if (isUseLegacy) 1 else 0] as? StatusBarNotification?,
                                        (result as Icon).loadDrawable(context)
                                    ) { icon -> result = Icon.createWithBitmap(icon) }
                                }
                            }
                        }
                    }
                    StatusBarIconViewClass.hook {
                        /** Hook 状态栏图标的颜色 */
                        injectMember {
                            method { name = "updateIconColor" }
                            afterHook {
                                instance<ImageView>().also {
                                    if (hasIgnoreStatusBarIconColor(it.context, field { name = "mNotification" }
                                            .of<StatusBarNotification>(instance))) it.apply {
                                        alpha = 1f
                                        colorFilter = null
                                    } else it.apply {
                                        /**
                                         * 防止图标不是纯黑的问题
                                         * 图标在任何场景下跟随状态栏其它图标保持半透明
                                         * MIUI 12 进行单独判断
                                         */
                                        field { name = "mCurrentSetColor" }.of<Int>(instance)?.also { color ->
                                            if (safeOfFalse {
                                                    NotificationUtilClass.clazz.hasMethod(
                                                        name = "ignoreStatusBarIconColor",
                                                        ExpandedNotificationClass.clazz
                                                    )
                                                }) {
                                                alpha = if (color.isWhite) 0.95f else 0.8f
                                                setColorFilter(if (color.isWhite) color else Color.BLACK)
                                            } else setColorFilter(color)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    NotificationIconContainerClass.hook {
                        injectMember {
                            method { name = "calculateIconTranslations" }
                            afterHook {
                                /** 修复最新开发版状态栏图标只能显示一个的问题 */
                                instance<ViewGroup>().layoutParams.width = 9999
                            }
                        }
                        injectMember {
                            method { name = "updateState" }
                            beforeHook {
                                /** 解除状态栏通知图标个数限制 */
                                if (isShowNotificationIcons && prefs.getBoolean(ENABLE_HOOK_STATUS_ICON_COUNT, default = true))
                                    field { name = "MAX_STATIC_ICONS" }
                                        .get(instance).set(prefs.getInt(HOOK_STATUS_ICON_COUNT, default = 5)
                                            .let { if (it in 0..100) it else 5 })
                            }
                        }
                        /** 旧版方法 - 新版不存在 */
                        injectMember {
                            method {
                                name = "setMaxStaticIcons"
                                param(IntType)
                                beforeHook { isShowNotificationIcons = firstArgs as Int > 0 }
                            }
                        }.ignoredNoSuchMemberFailure()
                        /** 新版方法 - 旧版不存在 */
                        injectMember {
                            method {
                                name = "miuiShowNotificationIcons"
                                param(BooleanType)
                            }
                            beforeHook { isShowNotificationIcons = firstArgs as Boolean }
                        }.ignoredNoSuchMemberFailure()
                    }.by { NotificationIconContainerClass.clazz.hasField(name = "MAX_STATIC_ICONS") }
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
                                        .field { name = "mIcon" }.of<ImageView>(instance) ?: return@afterHook

                                /** 通知是否展开 */
                                var isExpanded = false

                                /**
                                 * 从父类中得到 mRow 变量 - [ExpandableNotificationRowClass]
                                 * 获取其中的得到通知方法
                                 */
                                val expandedNf = ExpandableNotificationRowClass.clazz
                                    .method { name = "getEntry" }
                                    .get(NotificationViewWrapperClass.clazz.field {
                                        name = "mRow"
                                    }.get(instance).self?.also {
                                        isExpanded = ExpandableNotificationRowClass.clazz.method {
                                            name = "isExpanded"
                                            returnType = BooleanType
                                        }.get(it).invoke<Boolean>() == true
                                    }).call()?.let {
                                        it.javaClass.method {
                                            name = "getSbn"
                                        }.get(it).invoke<StatusBarNotification>()
                                    } ?: ExpandableNotificationRowClass.clazz
                                    .method { name = "getStatusBarNotification" }
                                    .get(NotificationViewWrapperClass.clazz.field { name = "mRow" }.get(instance).self)
                                    .invoke<StatusBarNotification>()

                                /** 获取优先级 */
                                val importance =
                                    (iconImageView.context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager?)
                                        ?.getNotificationChannel(expandedNf?.notification?.channelId)?.importance ?: 0
                                /** 非最小化优先级的通知全部设置为展开状态 */
                                if (importance != 1) isExpanded = true
                                /** 执行 Hook */
                                hookNotifyIconOnSet(iconImageView.context, expandedNf, iconImageView, isExpanded)
                            }
                        }
                    }
                    /** 干掉下拉通知图标自动设置回 APP 图标的方法 */
                    NotificationHeaderViewWrapperInjectorClass.hook {
                        injectMember {
                            method {
                                name = "setAppIcon"
                                param(ContextClass, ImageViewClass, ExpandedNotificationClass.clazz)
                            }.remedys {
                                method {
                                    name = "setAppIcon"
                                    param(ImageViewClass, ExpandedNotificationClass.clazz)
                                }
                            }
                            intercept()
                        }.ignoredNoSuchMemberFailure()
                        injectMember {
                            method {
                                name = "resetIconBgAndPaddings"
                                param(ImageViewClass, ExpandedNotificationClass.clazz)
                            }
                            intercept()
                        }.ignoredNoSuchMemberFailure()
                    }.ignoredHookClassNotFoundFailure()
                }
            }
        }
    }
}