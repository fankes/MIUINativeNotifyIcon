/**
 * Copyright (C) 2022. Fankes Studio(qzmmcn@163.com)
 *
 * This file is part of MIUINativeNotifyIcon.
 *
 * MIUINativeNotifyIcon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MIUINativeNotifyIcon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * This file is Created by fankes on 2022/01/24.
 */
@file:Suppress("SameParameterValue", "DEPRECATION")

package com.fankes.miui.notify.hook

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.service.notification.StatusBarNotification
import android.util.Log
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.annotation.Keep
import androidx.core.graphics.drawable.toBitmap
import com.fankes.miui.notify.hook.HookMedium.SELF_PACKAGE_NAME
import com.fankes.miui.notify.hook.HookMedium.SYSTEMUI_PACKAGE_NAME
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.utils.*
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage

@Keep
class HookMain : IXposedHookLoadPackage {

    companion object {

        /** 一直存在的类 */
        private const val SystemUIApplicationClass = "$SYSTEMUI_PACKAGE_NAME.SystemUIApplication"

        /** MIUI 新版本存在的类 */
        private const val NotificationHeaderViewWrapperInjectorClass =
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.row.wrapper.NotificationHeaderViewWrapperInjector"

        /** 一直存在的类 */
        private const val NotificationHeaderViewWrapperClass =
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.NotificationHeaderViewWrapper"

        /** 一直存在的类 */
        private const val NotificationViewWrapperClass =
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.NotificationViewWrapper"

        /** 原生存在的类 */
        private const val StatusBarIconViewClass = "$SYSTEMUI_PACKAGE_NAME.statusbar.StatusBarIconView"

        /** 原生存在的类 */
        private const val ContrastColorUtilClass = "com.android.internal.util.ContrastColorUtil"

        /** 根据多个版本存在不同的包名相同的类 */
        private val NotificationUtilClass = Pair(
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.NotificationUtil",
            "$SYSTEMUI_PACKAGE_NAME.miui.statusbar.notification.NotificationUtil"
        )

        /** 根据多个版本存在不同的包名相同的类 */
        private val ExpandedNotificationClass = Pair(
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.ExpandedNotification",
            "$SYSTEMUI_PACKAGE_NAME.miui.statusbar.ExpandedNotification"
        )
    }

    /** 仅作用于替换的 Hook 方法体 */
    private val replaceToNull = object : XC_MethodReplacement() {
        override fun replaceHookedMethod(param: MethodHookParam?): Any? {
            return null
        }
    }

    /** 仅作用于替换的 Hook 方法体 */
    private val replaceToTrue = object : XC_MethodReplacement() {
        override fun replaceHookedMethod(param: MethodHookParam?): Any {
            return true
        }
    }

    /**
     * 忽略异常运行
     * @param error 错误信息
     * @param it 正常回调
     */
    private fun runWithoutError(error: String = "", it: () -> Unit) {
        try {
            it()
        } catch (e: Throwable) {
            logE(content = "hookFailed: $error", e)
        }
    }

    /**
     * Print the log
     * @param content
     * @param it 继续执行的方法
     */
    private fun logD(content: String, it: () -> Unit = {}) {
        it()
        if (!HookMedium.getBoolean(HookMedium.ENABLE_MODULE_LOG, default = false)) return
        XposedBridge.log("[MIUINativeNotifyIcon][D]>$content")
        Log.d("MIUINativeNotifyIcon", content)
    }

    /**
     * Print the log
     * @param content
     * @param it 继续执行的方法
     */
    private fun logW(content: String, it: () -> Unit = {}) {
        it()
        if (!HookMedium.getBoolean(HookMedium.ENABLE_MODULE_LOG, default = false)) return
        XposedBridge.log("[MIUINativeNotifyIcon][W]>$content")
        Log.d("MIUINativeNotifyIcon", content)
    }

    /**
     * Print the log
     * @param content
     * @param e 异常
     * @param it 继续执行的方法
     */
    private fun logE(content: String, e: Throwable? = null, it: () -> Unit = {}) {
        it()
        XposedBridge.log("[MIUINativeNotifyIcon][E]>$content")
        XposedBridge.log(e)
        Log.e("MIUINativeNotifyIcon", content, e)
    }

    /**
     * 目标类是否存在
     * @param name 类名
     * @return [Boolean]
     */
    private fun XC_LoadPackage.LoadPackageParam.isClassExist(name: String) = try {
        classLoader.loadClass(name)
        true
    } catch (_: Throwable) {
        false
    }

    /**
     * 目标方法是否存在
     * @param classPair 类数组
     * @param name 方法名
     * @param param 方法参数类型数组
     * @return [Boolean]
     */
    private fun XC_LoadPackage.LoadPackageParam.isMethodExist(
        classPair: Pair<String, String>,
        name: String, vararg param: Class<*>
    ) = try {
        (try {
            classLoader.loadClass(classPair.first)
        } catch (_: Throwable) {
            try {
                classLoader.loadClass(classPair.second)
            } catch (_: Throwable) {
                null
            }
        })?.getDeclaredMethod(name, *param)
        true
    } catch (_: Throwable) {
        false
    }

    /**
     * 目标方法是否存在
     * @param className 类名
     * @param name 方法名
     * @param param 方法参数类型数组
     * @return [Boolean]
     */
    private fun XC_LoadPackage.LoadPackageParam.isMethodExist(className: String, name: String, vararg param: Class<*>) =
        try {
            (try {
                classLoader.loadClass(className)
            } catch (_: Throwable) {
                null
            })?.getDeclaredMethod(name, *param)
            true
        } catch (_: Throwable) {
            false
        }

    /**
     * 查找目标类
     * @param name 类名
     * @return [Class]
     */
    private fun XC_LoadPackage.LoadPackageParam.findClass(name: String) =
        classLoader.loadClass(name)

    /**
     * 查找目标类 - 两个类都没找到才会报错
     * @param pair 类名数组
     * @return [Class]
     */
    private fun XC_LoadPackage.LoadPackageParam.findClass(pair: Pair<String, String>) = try {
        classLoader.loadClass(pair.first)
    } catch (_: Throwable) {
        try {
            classLoader.loadClass(pair.second)
        } catch (e: Throwable) {
            logE(content = "Cannot find Class ${pair.first} and ${pair.second}", e)
            error("[Throwable] Cannot find Class ${pair.first} and ${pair.second}")
        }
    }

    /**
     * 存在目标类的类名 - 两个类都没找到会抛出异常
     * @param pair 类名数组
     * @return [String] 目标类名
     */
    private fun XC_LoadPackage.LoadPackageParam.existClass(pair: Pair<String, String>) = try {
        classLoader.loadClass(pair.first)
        pair.first
    } catch (_: Throwable) {
        try {
            classLoader.loadClass(pair.second)
            pair.second
        } catch (_: Throwable) {
            logE(content = "Cannot find Class ${pair.first} and ${pair.second}")
            error("[Throwable] Cannot find Class ${pair.first} and ${pair.second}")
        }
    }

    /**
     * - 这个是修复彩色图标的关键核心代码判断
     *
     * 判断是否为灰度图标 - 反射执行系统方法
     * @param context 实例
     * @param icon 要判断的图标
     * @return [Boolean]
     */
    private fun XC_LoadPackage.LoadPackageParam.isGrayscaleIcon(context: Context, icon: Drawable) =
        findClass(ContrastColorUtilClass).let {
            val instance = it.getDeclaredMethod("getInstance", Context::class.java)
                .apply { isAccessible = true }.invoke(null, context)
            it.getDeclaredMethod("isGrayscaleIcon", Drawable::class.java)
                .apply { isAccessible = true }.invoke(instance, icon) as Boolean
        }

    /**
     * 获取当前通知栏的样式
     *
     * 判断是否为灰度图标 - 反射执行系统方法
     * @return [Boolean]
     */
    private fun XC_LoadPackage.LoadPackageParam.isShowMiuiStyle() = try {
        findClass(NotificationUtilClass).let {
            it.getDeclaredMethod("showMiuiStyle")
                .apply { isAccessible = true }.invoke(null) as Boolean
        }
    } catch (_: Throwable) {
        false
    }

    /**
     * 是否为新版本 MIUI 方案
     *
     * 拥有状态栏图标颜色检查功能
     * @return [Boolean]
     */
    private fun XC_LoadPackage.LoadPackageParam.hasIgnoreStatusBarIconColor() =
        isMethodExist(NotificationUtilClass, name = "ignoreStatusBarIconColor")

    /**
     * 获取 [ExpandedNotificationClass] 的应用名称
     * @param instance 通知实例
     * @return [String]
     */
    private fun XC_LoadPackage.LoadPackageParam.findAppName(instance: Any?) = try {
        findClass(ExpandedNotificationClass).getDeclaredMethod("getAppName").let {
            it.isAccessible = true
            it.invoke(instance) as? String ?: "unknown"
        }
    } catch (_: Throwable) {
        "unknown"
    }

    /**
     * 获取全局上下文
     * @return [Context] or null
     */
    private val XC_LoadPackage.LoadPackageParam.globalContext
        get() = try {
            findClass(SystemUIApplicationClass)
                .getDeclaredMethod("getContext").apply { isAccessible = true }
                .invoke(null) as? Context?
        } catch (_: Throwable) {
            null
        }

    /**
     * Hook 状态栏小图标
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param expandedNf 通知实例
     * @param param Hook Param
     */
    private fun XC_LoadPackage.LoadPackageParam.hookSmallIconOnSet(
        context: Context,
        expandedNf: StatusBarNotification?,
        param: XC_MethodHook.MethodHookParam
    ) {
        runWithoutError(error = "GetSmallIconOnSet") {
            /** 获取通知小图标 */
            val iconDrawable = (param.result as Icon).loadDrawable(context)

            /** 判断是否不是灰度图标 */
            val isNotGrayscaleIcon = !isGrayscaleIcon(context, iconDrawable)
            /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
            expandedNf?.also { notifyInstance ->
                /** 目标彩色通知 APP 图标 */
                var customIcon: Icon? = null
                if (HookMedium.getBoolean(HookMedium.ENABLE_COLOR_ICON_HOOK, default = true))
                    run {
                        IconPackParams.iconDatas.forEach {
                            if ((notifyInstance.opPkgName == it.packageName ||
                                        findAppName(notifyInstance) == it.appName) &&
                                HookMedium.isAppNotifyHookOf(it)
                            ) {
                                if (isNotGrayscaleIcon || HookMedium.isAppNotifyHookAllOf(it))
                                    customIcon = Icon.createWithBitmap(it.iconBitmap)
                                return@run
                            }
                        }
                    }
                when {
                    /** 如果开启了修复 APP 的彩色图标 */
                    customIcon != null && HookMedium.getBoolean(HookMedium.ENABLE_NOTIFY_ICON_HOOK, default = true) ->
                        logD(
                            content = "GetSmallIconOnSet -> " +
                                    "hook Custom AppIcon [pkgName] ${notifyInstance.opPkgName} " +
                                    "[appName] ${findAppName(notifyInstance)}"
                        ) { param.result = customIcon }
                    /** 若不是灰度图标自动处理为圆角 */
                    isNotGrayscaleIcon ->
                        logD(
                            content = "GetSmallIconOnSet -> " +
                                    "hook Color AppIcon [pkgName] ${notifyInstance.opPkgName} " +
                                    "[appName] ${findAppName(notifyInstance)}"
                        ) {
                            param.result = Icon.createWithBitmap(
                                iconDrawable.toBitmap().round(15.dp(context))
                            )
                        }
                }
            } ?: logW(content = "GetSmallIconOnSet -> StatusBarNotification got null")
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
    private fun XC_LoadPackage.LoadPackageParam.hookNotifyIconOnSet(
        context: Context,
        expandedNf: StatusBarNotification?,
        iconImageView: ImageView
    ) {
        runWithoutError(error = "AutoSetAppIconOnSet") {
            /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
            expandedNf?.let { notifyInstance ->
                /** 是否 Hook 彩色通知图标 */
                val isHookColorIcon = HookMedium.getBoolean(HookMedium.ENABLE_COLOR_ICON_HOOK, default = true)

                /** 新版风格反色 */
                val newStyle = if (context.isSystemInDarkMode) 0xFF2D2D2D.toInt() else Color.WHITE

                /** 旧版风格反色 */
                val oldStyle = if (context.isNotSystemInDarkMode) 0xFF707070.toInt() else Color.WHITE

                /** 获取通知小图标 */
                val iconDrawable = notifyInstance.notification.smallIcon.loadDrawable(context)

                /** 判断图标风格 */
                val isGrayscaleIcon = isGrayscaleIcon(context, iconDrawable)

                /** 自定义默认小图标 */
                var customIcon: Bitmap? = null
                if (isHookColorIcon)
                    run {
                        IconPackParams.iconDatas.forEach {
                            if ((notifyInstance.opPkgName == it.packageName ||
                                        findAppName(notifyInstance) == it.appName) &&
                                HookMedium.isAppNotifyHookOf(it)
                            ) {
                                if (!isGrayscaleIcon || HookMedium.isAppNotifyHookAllOf(it))
                                    customIcon = it.iconBitmap
                                return@run
                            }
                        }
                    }
                /** 如果开启了修复 APP 的彩色图标 */
                if (customIcon != null && HookMedium.getBoolean(HookMedium.ENABLE_NOTIFY_ICON_HOOK, default = true))
                    iconImageView.apply {
                        /** 设置自定义小图标 */
                        setImageBitmap(customIcon)
                        /** 上色 */
                        setColorFilter(if (isUpperOfAndroidS) newStyle else oldStyle)
                        /** 输出调试日志 */
                        logD(
                            content = "AutoSetAppIconOnSet -> " +
                                    "hook Custom AppIcon [pkgName] ${notifyInstance.opPkgName} " +
                                    "[appName] ${findAppName(notifyInstance)}"
                        )
                    }
                else {
                    /** 重新设置图标 - 防止系统更改它 */
                    iconImageView.setImageDrawable(iconDrawable)
                    /** 判断是否开启 Hook 彩色图标 */
                    if (isHookColorIcon) {
                        /** 判断如果是灰度图标就给他设置一个白色颜色遮罩 */
                        if (isGrayscaleIcon)
                            logD(
                                content = "AutoSetAppIconOnSet -> " +
                                        "hook Grayscale AppIcon [pkgName] ${notifyInstance.opPkgName} " +
                                        "[appName] ${findAppName(notifyInstance)}"
                            ) { iconImageView.setColorFilter(if (isUpperOfAndroidS) newStyle else oldStyle) }
                        else
                            iconImageView.apply {
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
                                /** 输出调试日志 */
                                logD(
                                    content = "AutoSetAppIconOnSet -> " +
                                            "hook Color AppIcon [pkgName] ${notifyInstance.opPkgName} " +
                                            "[appName] ${findAppName(notifyInstance)}"
                                )
                            }
                        /** 否则一律设置灰度图标 */
                    } else
                        logD(
                            content = "AutoSetAppIconOnSet -> " +
                                    "hook NonColor AppIcon [pkgName] ${notifyInstance.opPkgName} " +
                                    "[appName] ${findAppName(notifyInstance)}"
                        ) { iconImageView.setColorFilter(if (isUpperOfAndroidS) newStyle else oldStyle) }
                }
            } ?: logW(content = "AutoSetAppIconOnSet -> StatusBarNotification got null")
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
    private fun XC_LoadPackage.LoadPackageParam.hookIgnoreStatusBarIconColor(
        context: Context,
        expandedNf: StatusBarNotification?
    ) = if (HookMedium.getBoolean(HookMedium.ENABLE_COLOR_ICON_HOOK, default = true))
        try {
            /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
            expandedNf?.let { notifyInstance ->
                /** 获取通知小图标 */
                val iconDrawable =
                    notifyInstance.notification.smallIcon.loadDrawable(context)

                /** 判断是否不是灰度图标 */
                val isNotGrayscaleIcon = !isGrayscaleIcon(context, iconDrawable)

                /** 获取目标修复彩色图标的 APP */
                var isTargetApp = false
                run {
                    IconPackParams.iconDatas.forEach {
                        if ((notifyInstance.opPkgName == it.packageName ||
                                    findAppName(notifyInstance) == it.appName) &&
                            HookMedium.isAppNotifyHookOf(it)
                        ) {
                            if (isNotGrayscaleIcon || HookMedium.isAppNotifyHookAllOf(it)) isTargetApp = true
                            return@run
                        }
                    }
                }
                /** 如果开启了修复 APP 的彩色图标 */
                if (isTargetApp && HookMedium.getBoolean(HookMedium.ENABLE_NOTIFY_ICON_HOOK, default = true)) let {
                    logD(
                        content = "IgnoreStatusBarIconColor -> " +
                                "hook Color AppIcon [pkgName] ${notifyInstance.opPkgName} " +
                                "[appName] ${findAppName(notifyInstance)}"
                    )
                    false
                }
                else let {
                    logD(
                        content = "IgnoreStatusBarIconColor -> " +
                                "hook Grayscale[${!isNotGrayscaleIcon}] AppIcon " +
                                "[pkgName] ${notifyInstance.opPkgName} " +
                                "[appName] ${findAppName(notifyInstance)}"
                    )
                    /** 只要不是灰度就返回彩色图标 */
                    isNotGrayscaleIcon
                }
            } ?: let {
                logW(content = "IgnoreStatusBarIconColor -> StatusBarNotification got null")
                /** 否则不对颜色进行反色处理防止一些系统图标出现异常 */
                true
            }
        } catch (e: Exception) {
            logE("Failed to hook ignoreStatusBarIconColor", e)
            false
        }
    else let {
        logD(content = "IgnoreStatusBarIconColor -> hook NonColor AppIcon")
        false
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (lpparam == null) return
        when (lpparam.packageName) {
            /** Hook 自身 */
            SELF_PACKAGE_NAME ->
                runWithoutError(error = "HookModuleSelf") {
                    XposedHelpers.findAndHookMethod(
                        "$SELF_PACKAGE_NAME.hook.HookMedium",
                        lpparam.classLoader,
                        "isHooked",
                        replaceToTrue
                    )
                }
            /** Hook 系统 UI */
            SYSTEMUI_PACKAGE_NAME ->
                when {
                    /** 不是 MIUI 系统停止 Hook */
                    isNotMIUI ->
                        logW(content = "Aborted Hook -> This System is not MIUI")
                    /** 系统版本低于 Android P 停止 Hook */
                    isLowerAndroidP ->
                        logW(content = "Aborted Hook -> This System is lower than Android P")
                    /** 不是支持的 MIUI 系统停止 Hook */
                    isNotSupportMiuiVersion ->
                        logW(content = "Aborted Hook -> This MIUI Version $miuiVersion not supported")
                    /** Hook 被手动关闭停止 Hook */
                    !HookMedium.getBoolean(HookMedium.ENABLE_MODULE, default = true) ->
                        logW(content = "Aborted Hook -> Hook Closed")
                    else -> {
                        /** 强制回写系统的状态栏图标样式为原生 */
                        runWithoutError(error = "SubstituteSmallIcon") {
                            XposedHelpers.findAndHookMethod(
                                lpparam.existClass(NotificationUtilClass),
                                lpparam.classLoader,
                                "shouldSubstituteSmallIcon",
                                lpparam.findClass(ExpandedNotificationClass),
                                object : XC_MethodReplacement() {
                                    override fun replaceHookedMethod(param: MethodHookParam) =
                                        /**
                                         * 因为之前的 MIUI 版本的状态栏图标颜色会全部设置为白色的 - 找不到修复的地方就直接判断版本了
                                         * 对于之前没有通知图标色彩判断功能的版本判断是 MIUI 样式就停止 Hook
                                         */
                                        if (lpparam.hasIgnoreStatusBarIconColor()) false else lpparam.isShowMiuiStyle()
                                }
                            )
                        }
                        /** 修复通知图标为彩色 */
                        if (lpparam.hasIgnoreStatusBarIconColor())
                            runWithoutError(error = "IgnoreStatusBarIconColor") {
                                XposedHelpers.findAndHookMethod(
                                    lpparam.existClass(NotificationUtilClass),
                                    lpparam.classLoader,
                                    "ignoreStatusBarIconColor",
                                    lpparam.findClass(ExpandedNotificationClass),
                                    object : XC_MethodReplacement() {
                                        override fun replaceHookedMethod(param: MethodHookParam) =
                                            lpparam.hookIgnoreStatusBarIconColor(
                                                context = lpparam.globalContext ?: error("GlobalContext got null"),
                                                param.args?.get(0) as? StatusBarNotification?
                                            )
                                    }
                                )
                            }
                        /** 之前的版本解决方案 */
                        else runWithoutError(error = "UpdateIconColor") {
                            XposedHelpers.findAndHookMethod(
                                StatusBarIconViewClass,
                                lpparam.classLoader, "updateIconColor",
                                object : XC_MethodHook() {
                                    override fun afterHookedMethod(param: MethodHookParam) =
                                        runWithoutError(error = "UpdateIconColorOnSet") hook@{
                                            /** 对于之前没有通知图标色彩判断功能的版本判断是 MIUI 样式就停止 Hook */
                                            if (lpparam.isShowMiuiStyle()) return@hook
                                            /** 获取自身 */
                                            val iconImageView = param.thisObject as ImageView

                                            /** 是否忽略图标颜色 */
                                            val isIgnoredColor = lpparam.hookIgnoreStatusBarIconColor(
                                                iconImageView.context,
                                                param.thisObject.javaClass.getDeclaredField("mNotification").apply {
                                                    isAccessible = true
                                                }[param.thisObject] as? StatusBarNotification?
                                            )

                                            /** 当前着色颜色 */
                                            val currentColor =
                                                param.thisObject.javaClass.getDeclaredField("mCurrentSetColor").apply {
                                                    isAccessible = true
                                                }[param.thisObject] as? Int ?: Color.WHITE
                                            /** 判断并设置颜色 */
                                            if (isIgnoredColor)
                                                iconImageView.colorFilter = null
                                            else iconImageView.setColorFilter(currentColor)
                                            logD(content = "IgnoreStatusBarIconColor[UseOldWay] -> isIgnored[$isIgnoredColor]")
                                        }
                                }
                            )
                        }
                        /** 强制回写系统的状态栏图标样式为原生 */
                        runWithoutError(error = "GetSmallIcon") {
                            var isTooOld: Boolean
                            try {
                                isTooOld = false
                                /** 新版方法 */
                                lpparam.findClass(NotificationUtilClass)
                                    .getDeclaredMethod(
                                        "getSmallIcon",
                                        lpparam.findClass(ExpandedNotificationClass),
                                        Int::class.java
                                    ).apply { isAccessible = true }
                            } catch (_: Throwable) {
                                try {
                                    isTooOld = false
                                    /** 旧版方法 */
                                    lpparam.findClass(NotificationUtilClass)
                                        .getDeclaredMethod("getSmallIcon", lpparam.findClass(ExpandedNotificationClass))
                                        .apply { isAccessible = true }
                                } catch (_: Throwable) {
                                    isTooOld = true
                                    /** 超旧版方法 */
                                    lpparam.findClass(NotificationUtilClass)
                                        .getDeclaredMethod(
                                            "getSmallIcon",
                                            Context::class.java,
                                            lpparam.findClass(ExpandedNotificationClass)
                                        ).apply { isAccessible = true }
                                }
                            }.also {
                                XposedBridge.hookMethod(it, object : XC_MethodHook() {
                                    override fun afterHookedMethod(param: MethodHookParam) {
                                        /** 对于之前没有通知图标色彩判断功能的版本判断是 MIUI 样式就停止 Hook */
                                        if (!lpparam.hasIgnoreStatusBarIconColor() && lpparam.isShowMiuiStyle()) return
                                        lpparam.hookSmallIconOnSet(
                                            context = lpparam.globalContext ?: param.args[0] as Context,
                                            param.args?.get(if (isTooOld) 1 else 0) as? StatusBarNotification?, param
                                        )
                                    }
                                })
                            }
                        }
                        /** 修复下拉通知图标自动设置回 APP 图标的方法 */
                        if (lpparam.isClassExist(NotificationHeaderViewWrapperInjectorClass))
                            runWithoutError(error = "AutoSetAppIcon") {
                                var isNewWay = true
                                try {
                                    /** 新版方法 */
                                    lpparam.findClass(NotificationHeaderViewWrapperInjectorClass)
                                        .getDeclaredMethod(
                                            "setAppIcon",
                                            Context::class.java,
                                            ImageView::class.java,
                                            lpparam.findClass(ExpandedNotificationClass)
                                        ).apply { isAccessible = true }
                                } catch (_: Throwable) {
                                    isNewWay = false
                                    /** 旧版方法 */
                                    lpparam.findClass(NotificationHeaderViewWrapperInjectorClass)
                                        .getDeclaredMethod(
                                            "setAppIcon",
                                            ImageView::class.java,
                                            lpparam.findClass(ExpandedNotificationClass)
                                        ).apply { isAccessible = true }
                                }.also {
                                    XposedBridge.hookMethod(it, object : XC_MethodReplacement() {
                                        override fun replaceHookedMethod(param: MethodHookParam): Any? {
                                            if (isNewWay)
                                                lpparam.hookNotifyIconOnSet(
                                                    context = param.args?.get(0) as? Context ?: lpparam.globalContext
                                                    ?: error("GlobalContext got null"),
                                                    param.args?.get(2) as? StatusBarNotification?,
                                                    param.args?.get(1) as ImageView
                                                )
                                            else
                                                lpparam.hookNotifyIconOnSet(
                                                    context = lpparam.globalContext ?: error("GlobalContext got null"),
                                                    param.args?.get(1) as? StatusBarNotification?,
                                                    param.args?.get(0) as ImageView
                                                )
                                            return null
                                        }
                                    })
                                }
                            }
                        /** 之前的版本解决方案 */
                        else runWithoutError(error = "AutoSetAppIconOldWay") {
                            XposedHelpers.findAndHookMethod(
                                NotificationHeaderViewWrapperClass,
                                lpparam.classLoader, "handleHeaderViews",
                                object : XC_MethodHook() {
                                    override fun afterHookedMethod(param: MethodHookParam) {
                                        runWithoutError(error = "AutoSetAppIconOldWayOnSet") hook@{
                                            /** 对于之前没有通知图标色彩判断功能的版本判断是 MIUI 样式就停止 Hook */
                                            if (!lpparam.hasIgnoreStatusBarIconColor() && lpparam.isShowMiuiStyle()) return@hook
                                            /** 从父类中得到 mRow 变量 */
                                            lpparam.findClass(NotificationViewWrapperClass).getDeclaredField("mRow")
                                                .apply {
                                                    isAccessible = true
                                                }[param.thisObject].apply {
                                                /** 获取小图标 */
                                                val iconImageView = lpparam.findClass(NotificationHeaderViewWrapperClass)
                                                    .getDeclaredField("mIcon")
                                                    .apply {
                                                        isAccessible = true
                                                    }[param.thisObject] as ImageView

                                                /** 获取其中的得到通知方法 */
                                                val expandedNf =
                                                    javaClass.getDeclaredMethod("getStatusBarNotification").apply {
                                                        isAccessible = true
                                                    }.invoke(this) as? StatusBarNotification?
                                                /** 执行 Hook */
                                                lpparam.hookNotifyIconOnSet(
                                                    iconImageView.context,
                                                    expandedNf, iconImageView
                                                )
                                            }
                                        }
                                    }
                                }
                            )
                        }
                        /** 干掉下拉通知图标自动设置回 APP 图标的方法 - Android 12 */
                        if (isUpperOfAndroidS &&
                            lpparam.isMethodExist(
                                NotificationHeaderViewWrapperInjectorClass,
                                name = "resetIconBgAndPaddings"
                            )
                        ) runWithoutError(error = "ResetIconBgAndPaddings") {
                            XposedHelpers.findAndHookMethod(
                                NotificationHeaderViewWrapperInjectorClass,
                                lpparam.classLoader,
                                "resetIconBgAndPaddings",
                                ImageView::class.java,
                                lpparam.findClass(ExpandedNotificationClass),
                                replaceToNull
                            )
                        }
                        logD(content = "hook Completed!")
                    }
                }
        }
    }
}