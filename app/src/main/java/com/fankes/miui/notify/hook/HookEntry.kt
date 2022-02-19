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
package com.fankes.miui.notify.hook

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.service.notification.StatusBarNotification
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.fankes.miui.notify.hook.HookConst.ENABLE_COLOR_ICON_HOOK
import com.fankes.miui.notify.hook.HookConst.ENABLE_MODULE
import com.fankes.miui.notify.hook.HookConst.ENABLE_MODULE_LOG
import com.fankes.miui.notify.hook.HookConst.ENABLE_NOTIFY_ICON_FIX
import com.fankes.miui.notify.hook.HookConst.SYSTEMUI_PACKAGE_NAME
import com.fankes.miui.notify.hook.factory.isAppNotifyHookAllOf
import com.fankes.miui.notify.hook.factory.isAppNotifyHookOf
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.utils.*
import com.fankes.miui.notify.utils.drawable.drawabletoolbox.DrawableBuilder
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.bean.VariousClass
import com.highcapable.yukihookapi.hook.factory.*
import com.highcapable.yukihookapi.hook.log.loggerD
import com.highcapable.yukihookapi.hook.log.loggerW
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.highcapable.yukihookapi.hook.type.android.ContextClass
import com.highcapable.yukihookapi.hook.type.android.ImageViewClass
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

        /** MIUI 新版本存在的类 */
        private const val NotificationHeaderViewWrapperClass =
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.NotificationHeaderViewWrapper"

        /** MIUI 新版本存在的类 */
        private const val NotificationViewWrapperClass =
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.NotificationViewWrapper"

        /** 未确定是否只有旧版本存在的类 */
        private const val ExpandableNotificationRowClass = "$SYSTEMUI_PACKAGE_NAME.statusbar.ExpandableNotificationRow"

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

    /**
     * - 这个是修复彩色图标的关键核心代码判断
     *
     * 判断是否为灰度图标 - [isXmsf] 直接标记为非灰度图标防止 💩 MIUI 乱改
     * @param drawable 要判断的图标
     * @return [Boolean]
     */
    private fun StatusBarNotification.isGrayscaleIcon(drawable: Drawable) = !isXmsf && BitmapCompatTool.isGrayscaleDrawable(drawable)

    /**
     * 是否为新版本 MIUI 方案
     *
     * 拥有状态栏图标颜色检查功能
     * @return [Boolean]
     */
    private val PackageParam.hasIgnoreStatusBarIconColor
        get() = NotificationUtilClass.clazz.hasMethod(name = "ignoreStatusBarIconColor", ExpandedNotificationClass.clazz)

    /**
     * 获取当前通知栏的样式
     * @return [Boolean]
     */
    private fun PackageParam.isShowMiuiStyle() = safeOfFalse {
        NotificationUtilClass.clazz.method { name = "showMiuiStyle" }.get().invoke() ?: false
    }

    /**
     * 获取 [ExpandedNotificationClass] 的应用名称
     * @param instance 通知实例
     * @return [String]
     */
    private fun PackageParam.findAppName(instance: Any?) = safeOf(default = "<unknown>") {
        ExpandedNotificationClass.clazz.method { name = "getAppName" }.get(instance).invoke() ?: "<empty>"
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
            context.packageManager.getPackageInfo(opPkgName, 0).applicationInfo.loadIcon(context.packageManager)
        else iconDrawable
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
            if (SystemUIApplicationClass.clazz.hasMethod(name = "getContext"))
                SystemUIApplicationClass.clazz.method { name = "getContext" }.get().invoke<Context>()
            else null
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
    ) = safeRun(msg = "GetSmallIconOnSet") {
        if (iconDrawable == null) return@safeRun
        /** 如果没开启修复 APP 的彩色图标 */
        if (!prefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)) return@safeRun
        /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
        expandedNf?.also { notifyInstance ->
            /** 判断是否不是灰度图标 */
            val isNotGrayscaleIcon = !notifyInstance.isGrayscaleIcon(iconDrawable)

            /** 目标彩色通知 APP 图标 */
            var customIcon: Bitmap? = null
            if (prefs.getBoolean(ENABLE_NOTIFY_ICON_FIX, default = true))
                run {
                    IconPackParams.iconDatas.forEach {
                        if ((notifyInstance.opPkgName == it.packageName ||
                                    findAppName(notifyInstance) == it.appName) &&
                            isAppNotifyHookOf(it)
                        ) {
                            if (isNotGrayscaleIcon || isAppNotifyHookAllOf(it))
                                customIcon = it.iconBitmap
                            return@run
                        }
                    }
                }
            /** 打印日志 */
            if (prefs.getBoolean(ENABLE_MODULE_LOG))
                loggerD(msg = "Icon --> [${findAppName(notifyInstance)}][${notifyInstance.opPkgName}] custom [${customIcon != null}] grayscale [${!isNotGrayscaleIcon}] xmsf [${notifyInstance.isXmsf}]")
            when {
                /** 处理自定义通知图标优化 */
                customIcon != null -> it(customIcon!!)
                /** 若不是灰度图标自动处理为圆角 */
                isNotGrayscaleIcon -> it(notifyInstance.compatNotifyIcon(context, iconDrawable).toBitmap().round(15.dp(context)))
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
     */
    private fun PackageParam.hookNotifyIconOnSet(context: Context, expandedNf: StatusBarNotification?, iconImageView: ImageView) =
        safeRun(msg = "AutoSetAppIconOnSet") {
            /** 如果没开启修复 APP 的彩色图标 */
            if (!prefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)) return@safeRun
            /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
            expandedNf?.let { notifyInstance ->
                /** 是否开启修复 APP 的彩色图标 */
                val isNotifyIconFix = prefs.getBoolean(ENABLE_NOTIFY_ICON_FIX, default = true)

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
                        it == 0 -> oldStyle
                        else -> it
                    }
                }

                /** 获取通知小图标 */
                val iconDrawable = notifyInstance.notification.smallIcon.loadDrawable(context)

                /** 判断图标风格 */
                val isGrayscaleIcon = notifyInstance.isGrayscaleIcon(iconDrawable)

                /** 自定义默认小图标 */
                var customIcon: Bitmap? = null

                /** 自定义默认小图标颜色 */
                var customIconColor = 0

                if (isNotifyIconFix) run {
                    IconPackParams.iconDatas.forEach {
                        if ((notifyInstance.opPkgName == it.packageName ||
                                    findAppName(notifyInstance) == it.appName) &&
                            isAppNotifyHookOf(it)
                        ) {
                            if (!isGrayscaleIcon || isAppNotifyHookAllOf(it)) {
                                customIcon = it.iconBitmap
                                customIconColor = it.iconColor
                                return@run
                            }
                        }
                    }
                }
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
                                    view.width, view.height, 5.dp(context)
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
     * Hook 通知栏小图标颜色
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param expandedNf 状态栏实例
     * @return [Boolean] 是否忽略通知图标颜色
     */
    private fun PackageParam.hookIgnoreStatusBarIconColor(context: Context, expandedNf: StatusBarNotification?) =
        if (prefs.getBoolean(ENABLE_COLOR_ICON_HOOK, default = true)) safeOfFalse {
            /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
            expandedNf?.let { notifyInstance ->
                /** 获取通知小图标 */
                val iconDrawable =
                    notifyInstance.notification.smallIcon.loadDrawable(context)

                /** 判断是否不是灰度图标 */
                val isNotGrayscaleIcon = !notifyInstance.isGrayscaleIcon(iconDrawable)

                /** 获取目标修复彩色图标的 APP */
                var isTargetFixApp = false
                /** 如果开启了自定义通知图标优化 */
                if (prefs.getBoolean(ENABLE_NOTIFY_ICON_FIX, default = true))
                    run {
                        IconPackParams.iconDatas.forEach {
                            if ((notifyInstance.opPkgName == it.packageName ||
                                        findAppName(notifyInstance) == it.appName) &&
                                isAppNotifyHookOf(it)
                            ) {
                                if (isNotGrayscaleIcon || isAppNotifyHookAllOf(it)) isTargetFixApp = true
                                return@run
                            }
                        }
                    }
                /**
                 * 只要不是灰度就返回彩色图标
                 * 否则不对颜色进行反色处理防止一些系统图标出现异常
                 */
                if (isTargetFixApp) false else isNotGrayscaleIcon
            } ?: true
        } else false

    override fun onHook() = encase {
        configs {
            debugTag = "MIUINativeNotifyIcon"
            isDebug = false
        }
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
                    NotificationUtilClass.hook {
                        /** 强制回写系统的状态栏图标样式为原生 */
                        injectMember {
                            method {
                                name = "shouldSubstituteSmallIcon"
                                param(ExpandedNotificationClass.clazz)
                            }
                            /**
                             * 因为之前的 MIUI 版本的状态栏图标颜色会全部设置为白色的 - 找不到修复的地方就直接判断版本了
                             * 对于之前没有通知图标色彩判断功能的版本判断是 MIUI 样式就停止 Hook
                             */
                            replaceAny { if (hasIgnoreStatusBarIconColor) false else isShowMiuiStyle() }
                        }
                        if (hasIgnoreStatusBarIconColor)
                            injectMember {
                                method {
                                    name = "ignoreStatusBarIconColor"
                                    param(ExpandedNotificationClass.clazz)
                                }
                                replaceAny {
                                    hookIgnoreStatusBarIconColor(
                                        context = globalContext ?: error("GlobalContext got null"),
                                        expandedNf = args[0] as? StatusBarNotification?
                                    )
                                }
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
                                /** 对于之前没有通知图标色彩判断功能的版本判断是 MIUI 样式就停止 Hook */
                                if (hasIgnoreStatusBarIconColor || !isShowMiuiStyle())
                                    (globalContext ?: args[0] as Context).also { context ->
                                        hookSmallIconOnSet(
                                            context = context,
                                            args[if (isUseLegacy) 1 else 0] as? StatusBarNotification?,
                                            (result as Icon).loadDrawable(context)
                                        ) { icon -> result = Icon.createWithBitmap(icon) }
                                    }
                            }
                        }
                    }
                    if (NotificationHeaderViewWrapperInjectorClass.hasClass)
                        NotificationHeaderViewWrapperInjectorClass.hook {
                            /** 修复下拉通知图标自动设置回 APP 图标的方法 */
                            injectMember {
                                var isUseLegacy = false
                                method {
                                    name = "setAppIcon"
                                    param(ContextClass, ImageViewClass, ExpandedNotificationClass.clazz)
                                }.remedys {
                                    method {
                                        name = "setAppIcon"
                                        param(ImageViewClass, ExpandedNotificationClass.clazz)
                                    }.onFind { isUseLegacy = true }
                                }
                                replaceUnit {
                                    if (isUseLegacy)
                                        hookNotifyIconOnSet(
                                            context = globalContext ?: error("GlobalContext got null"),
                                            args[1] as? StatusBarNotification?,
                                            args[0] as ImageView
                                        )
                                    else
                                        hookNotifyIconOnSet(
                                            context = args[0] as? Context ?: globalContext ?: error("GlobalContext got null"),
                                            args[2] as? StatusBarNotification?,
                                            args[1] as ImageView
                                        )

                                }
                            }
                            /** 干掉下拉通知图标自动设置回 APP 图标的方法 - Android 12 */
                            if (isUpperOfAndroidS)
                                injectMember {
                                    method {
                                        name = "resetIconBgAndPaddings"
                                        param(ImageViewClass, ExpandedNotificationClass.clazz)
                                    }
                                    intercept()
                                }
                        }
                    else
                        NotificationHeaderViewWrapperClass.hook {
                            /** 之前的版本解决方案 */
                            injectMember {
                                method { name = "handleHeaderViews" }
                                afterHook {
                                    /** 对于之前没有通知图标色彩判断功能的版本判断是 MIUI 样式就停止 Hook */
                                    if (!hasIgnoreStatusBarIconColor && isShowMiuiStyle()) return@afterHook

                                    /** 获取小图标 */
                                    val iconImageView =
                                        NotificationHeaderViewWrapperClass.clazz
                                            .field { name = "mIcon" }.of<ImageView>(instance) ?: return@afterHook

                                    /**
                                     * 从父类中得到 mRow 变量 - [ExpandableNotificationRowClass]
                                     * 获取其中的得到通知方法
                                     */
                                    val expandedNf =
                                        ExpandableNotificationRowClass.clazz
                                            .method { name = "getStatusBarNotification" }
                                            .get(NotificationViewWrapperClass.clazz.field { name = "mRow" }.get(instance).self)
                                            .invoke<StatusBarNotification>()
                                    /** 执行 Hook */
                                    hookNotifyIconOnSet(iconImageView.context, expandedNf, iconImageView)
                                }
                            }
                        }
                }
            }
        }
    }
}