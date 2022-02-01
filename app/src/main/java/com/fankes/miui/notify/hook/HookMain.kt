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
@file:Suppress("SameParameterValue")

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

        private const val NotificationUtilClass =
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.NotificationUtil"

        private const val NotificationHeaderViewWrapperInjectorClass =
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.row.wrapper.NotificationHeaderViewWrapperInjector"

        private const val ExpandedNotificationClass =
            "$SYSTEMUI_PACKAGE_NAME.statusbar.notification.ExpandedNotification"

        private const val SystemUIApplicationClass = "$SYSTEMUI_PACKAGE_NAME.SystemUIApplication"

        private const val ContrastColorUtilClass = "com.android.internal.util.ContrastColorUtil"
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

    /** 仅作用于替换的 Hook 方法体 */
    private val replaceToFalse = object : XC_MethodReplacement() {
        override fun replaceHookedMethod(param: MethodHookParam?): Any {
            return false
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
        if (!HookMedium.getBoolean(HookMedium.ENABLE_MODULE_LOG, default = true)) return
        XposedBridge.log("[MIUINativeNotifyIcon][D]>$content")
        Log.d("MIUINativeNotifyIcon", content)
        it()
    }

    /**
     * Print the log
     * @param content
     * @param it 继续执行的方法
     */
    private fun logW(content: String, it: () -> Unit = {}) {
        if (!HookMedium.getBoolean(HookMedium.ENABLE_MODULE_LOG, default = true)) return
        XposedBridge.log("[MIUINativeNotifyIcon][W]>$content")
        Log.d("MIUINativeNotifyIcon", content)
        it()
    }

    /**
     * Print the log
     * @param content
     * @param e 异常
     * @param it 继续执行的方法
     */
    private fun logE(content: String, e: Throwable? = null, it: () -> Unit = {}) {
        XposedBridge.log("[MIUINativeNotifyIcon][E]>$content")
        XposedBridge.log(e)
        Log.e("MIUINativeNotifyIcon", content, e)
        it()
    }

    /**
     * 查找目标类
     * @param name 类名
     * @return [Class]
     */
    private fun XC_LoadPackage.LoadPackageParam.findClass(name: String) =
        classLoader.loadClass(name)

    /**
     * ⚠️ 这个是修复彩色图标的关键核心代码判断
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
     * 获取 [ExpandedNotificationClass] 的应用名称
     * @param instance 通知实例
     * @return [String]
     */
    private fun XC_LoadPackage.LoadPackageParam.findAppName(instance: Any?) = try {
        findClass(ExpandedNotificationClass).getDeclaredMethod("getAppName").let {
            it.isAccessible = true
            it.invoke(instance) as? String ?: ""
        }
    } catch (_: Throwable) {
        "unknown"
    }

    /**
     * 获取全局上下文
     * @return [Context]
     */
    private val XC_LoadPackage.LoadPackageParam.globalContext
        get() = findClass(SystemUIApplicationClass)
            .getDeclaredMethod("getContext").apply { isAccessible = true }
            .invoke(null) as Context

    /**
     * Hook 状态栏小图标
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param param Hook Param
     */
    private fun XC_LoadPackage.LoadPackageParam.hookSmallIconOnSet(param: XC_MethodHook.MethodHookParam) =
        runWithoutError(error = "GetSmallIconOnSet") {
            /** 获取通知小图标 */
            val iconDrawable = (param.result as Icon).loadDrawable(globalContext)

            /** 判断是否不是灰度图标 */
            val isNotGrayscaleIcon = !isGrayscaleIcon(globalContext, iconDrawable)
            /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
            (param.args?.get(0) as? StatusBarNotification?)?.also { notifyInstance ->
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
                                iconDrawable.toBitmap().round(15.dp(globalContext))
                            )
                        }
                }
            } ?: logW(content = "GetSmallIconOnSet -> StatusBarNotification got null")
        }

    /**
     * Hook 通知栏小图标
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param param Hook Param
     * @param isNew 是否为新版方式
     */
    private fun XC_LoadPackage.LoadPackageParam.hookNotifyIconOnSet(param: XC_MethodHook.MethodHookParam, isNew: Boolean) =
        runWithoutError(error = "AutoSetAppIconOnSet") {
            /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
            (param.args?.get(if (isNew) 2 else 1) as? StatusBarNotification?)?.let { notifyInstance ->
                /** 是否 Hook 彩色通知图标 */
                val isHookColorIcon = HookMedium.getBoolean(HookMedium.ENABLE_COLOR_ICON_HOOK, default = true)

                /** 获取 [Context] */
                val context = if (isNew) param.args[0] as Context else globalContext

                /** 新版风格反色 */
                val newStyle = if (context.isSystemInDarkMode) 0xFF2D2D2D.toInt() else Color.WHITE

                /** 旧版风格反色 */
                val oldStyle = if (context.isNotSystemInDarkMode) 0xFF515151.toInt() else Color.WHITE

                /** 获取图标框 */
                val iconImageView = param.args[if (isNew) 1 else 0] as ImageView

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
                                            0,
                                            0,
                                            view.width,
                                            view.height,
                                            5.dp(context)
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
                    /** Hook 被手动关闭停止 Hook */
                    !HookMedium.getBoolean(HookMedium.ENABLE_MODULE, default = true) ->
                        logW(content = "Aborted Hook -> Hook Closed")
                    else -> {
                        /** 强制回写系统的状态栏图标样式为原生 */
                        runWithoutError(error = "SubstituteSmallIcon") {
                            XposedHelpers.findAndHookMethod(
                                NotificationUtilClass,
                                lpparam.classLoader,
                                "shouldSubstituteSmallIcon",
                                lpparam.findClass(ExpandedNotificationClass),
                                replaceToFalse
                            )
                        }
                        /** 修复通知图标为彩色 */
                        runWithoutError(error = "IgnoreStatusBarIconColor") {
                            XposedHelpers.findAndHookMethod(
                                NotificationUtilClass,
                                lpparam.classLoader,
                                "ignoreStatusBarIconColor",
                                lpparam.findClass(ExpandedNotificationClass),
                                object : XC_MethodReplacement() {
                                    override fun replaceHookedMethod(param: MethodHookParam) =
                                        if (HookMedium.getBoolean(HookMedium.ENABLE_COLOR_ICON_HOOK, default = true))
                                            try {
                                                /** 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的 */
                                                (param.args?.get(0) as? StatusBarNotification?)?.let { notifyInstance ->
                                                    /** 获取通知小图标 */
                                                    val iconDrawable =
                                                        notifyInstance.notification.smallIcon.loadDrawable(lpparam.globalContext)

                                                    /** 判断是否不是灰度图标 */
                                                    val isNotGrayscaleIcon =
                                                        !lpparam.isGrayscaleIcon(lpparam.globalContext, iconDrawable)

                                                    /** 获取目标修复彩色图标的 APP */
                                                    var isTargetApp = false
                                                    run {
                                                        IconPackParams.iconDatas.forEach {
                                                            if ((notifyInstance.opPkgName == it.packageName ||
                                                                        lpparam.findAppName(notifyInstance) == it.appName) &&
                                                                HookMedium.isAppNotifyHookOf(it)
                                                            ) {
                                                                if (isNotGrayscaleIcon || HookMedium.isAppNotifyHookAllOf(it))
                                                                    isTargetApp = true
                                                                return@run
                                                            }
                                                        }
                                                    }
                                                    /** 如果开启了修复 APP 的彩色图标 */
                                                    if (isTargetApp &&
                                                        HookMedium.getBoolean(
                                                            HookMedium.ENABLE_NOTIFY_ICON_HOOK,
                                                            default = true
                                                        )
                                                    ) let {
                                                        logD(
                                                            content = "IgnoreStatusBarIconColor -> " +
                                                                    "hook Color AppIcon [pkgName] ${notifyInstance.opPkgName} " +
                                                                    "[appName] ${lpparam.findAppName(notifyInstance)}"
                                                        )
                                                        false
                                                    }
                                                    else let {
                                                        logD(
                                                            content = "IgnoreStatusBarIconColor -> " +
                                                                    "hook Grayscale[${!isNotGrayscaleIcon}] AppIcon " +
                                                                    "[pkgName] ${notifyInstance.opPkgName} " +
                                                                    "[appName] ${lpparam.findAppName(notifyInstance)}"
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
                                }
                            )
                        }
                        /** 强制回写系统的状态栏图标样式为原生 */
                        runWithoutError(error = "GetSmallIcon") {
                            try {
                                /** 新版方法 */
                                lpparam.findClass(NotificationUtilClass)
                                    .getDeclaredMethod(
                                        "getSmallIcon",
                                        lpparam.findClass(ExpandedNotificationClass),
                                        Int::class.java
                                    )
                                    .apply { isAccessible = true }
                            } catch (_: Throwable) {
                                /** 旧版方法 */
                                lpparam.findClass(NotificationUtilClass)
                                    .getDeclaredMethod("getSmallIcon", lpparam.findClass(ExpandedNotificationClass))
                                    .apply { isAccessible = true }
                            }.also {
                                XposedBridge.hookMethod(it, object : XC_MethodHook() {

                                    override fun afterHookedMethod(param: MethodHookParam) {
                                        lpparam.hookSmallIconOnSet(param)
                                    }
                                })
                            }
                        }
                        /** 修复下拉通知图标自动设置回 APP 图标的方法 */
                        runWithoutError(error = "AutoSetAppIcon") {
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
                                        lpparam.hookNotifyIconOnSet(param, isNew = true)
                                        return null
                                    }
                                })
                            }
                        }
                        /** 干掉下拉通知图标自动设置回 APP 图标的方法 - Android 12 */
                        if (isUpperOfAndroidS)
                            runWithoutError(error = "ResetIconBgAndPaddings") {
                                XposedHelpers.findAndHookMethod(
                                    NotificationHeaderViewWrapperInjectorClass,
                                    lpparam.classLoader,
                                    "resetIconBgAndPaddings",
                                    ImageView::class.java,
                                    lpparam.findClass(ExpandedNotificationClass),
                                    replaceToNull
                                )
                            }
                        logD("hook Completed!")
                    }
                }
        }
    }
}