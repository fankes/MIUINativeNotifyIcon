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
 * This file is created by fankes on 2022/1/7.
 */
@file:Suppress("unused", "ObsoleteSdkInt")

package com.fankes.miui.notify.utils.factory

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Notification
import android.app.Service
import android.app.WallpaperManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PackageInfoFlags
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.provider.Settings
import android.util.Base64
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.core.content.pm.PackageInfoCompat
import androidx.core.content.res.ResourcesCompat
import com.fankes.miui.notify.wrapper.BuildConfigWrapper
import com.google.android.material.snackbar.Snackbar
import com.highcapable.yukihookapi.hook.factory.hasClass
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.toClassOrNull
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.type.java.StringClass
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication.Companion.appContext
import com.topjohnwu.superuser.Shell
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * 系统深色模式是否开启
 * @return [Boolean] 是否开启
 */
val isSystemInDarkMode get() = appContext.isSystemInDarkMode

/**
 * 系统深色模式是否没开启
 * @return [Boolean] 是否开启
 */
inline val isNotSystemInDarkMode get() = !isSystemInDarkMode

/**
 * 系统深色模式是否开启
 * @return [Boolean] 是否开启
 */
val Context.isSystemInDarkMode get() = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

/**
 * 系统深色模式是否没开启
 * @return [Boolean] 是否开启
 */
inline val Context.isNotSystemInDarkMode get() = !isSystemInDarkMode

/**
 * 系统版本是否高于或等于 Android 14
 * @return [Boolean] 是否符合条件
 */
inline val isUpperOfAndroidU get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

/**
 * 系统版本是否高于或等于 Android 13
 * @return [Boolean] 是否符合条件
 */
inline val isUpperOfAndroidT get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

/**
 * 系统版本是否高于或等于 Android 12
 * @return [Boolean] 是否符合条件
 */
inline val isUpperOfAndroidS get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

/**
 * 系统版本是否低于 Android 9
 * @return [Boolean] 是否符合条件
 */
inline val isLowerAndroidP get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.P

/**
 * 系统版本是否低于 Android 11
 * @return [Boolean] 是否符合条件
 */
inline val isLowerAndroidR get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.R

/**
 * 当前设备是否是 MIUI、HyperOS 定制 Android 系统
 * @return [Boolean] 是否符合条件
 */
val isMiSystem get() = isMIUI || isMIOS

/**
 * 当前设备是否不是 MIUI、HyperOS 定制 Android 系统
 * @return [Boolean] 是否符合条件
 */
val isNotMiSystem get() = !isMiSystem

/**
 * 当前设备是否是 MIUI 定制 Android 系统
 * @return [Boolean] 是否符合条件
 */
val isMIUI by lazy { "android.miui.R".hasClass() }

/**
 * 当前设备是否是 HyperOS 定制 Android 系统
 * @return [Boolean] 是否符合条件
 */
val isMIOS get() = isMIUI && miuiVersion == "816"

/**
 * 当前设备是否不是 MIUI 定制 Android 系统
 * @return [Boolean] 是否符合条件
 */
inline val isNotMIUI get() = !isMIUI

/**
 * 当前设备是否不是 HyperOS 定制 Android 系统
 * @return [Boolean] 是否符合条件
 */
inline val isNotMIOS get() = !isMIOS

/**
 * 是否为支持的 MIUI、HyperOS 版本
 * @return [Boolean]
 */
val isSupportMiSystemVersion
    get() = when {
        isMIOS -> when (miosVersion) {
            "1.0", "1.1", "2.0" -> true
            else -> false
        }
        isMIUI -> when (miuiVersion) {
            "11", "12", "12.5", "13", "14" -> true
            else -> false
        }
        else -> false
    }

/**
 * 是否不为支持的 MIUI、HyperOS 版本
 * @return [Boolean]
 */
inline val isNotSupportMiSystemVersion get() = !isSupportMiSystemVersion

/**
 * 获取 Android 版本代号
 * @return [String]
 */
val androidVersionCodeName
    get() = when (Build.VERSION.SDK_INT) {
        35 -> "V"
        34 -> "U"
        33 -> "T"
        32 -> "S_V2"
        31 -> "S"
        30 -> "R"
        29 -> "Q"
        28 -> "P"
        27 -> "O_MR1"
        26 -> "O"
        25 -> "N_MR1"
        24 -> "N"
        23 -> "M"
        22 -> "L_MR1"
        21 -> "L"
        else -> "?"
    }

/**
 * 获取 MIUI、HyperOS 版本
 * @return [String]
 */
val miSystemVersion
    get() = when {
        isMIOS -> miosVersion
        isMIUI -> miuiVersion
        else -> ""
    }

/**
 * 获取 MIUI、HyperOS 版本号
 * @return [Float]
 */
val miSystemVersionCode
    get() = when {
        isMIOS -> miosVersionCode
        isMIUI -> miuiVersionCode
        else -> 0f
    }

/**
 * 获取 MIUI 版本
 * @return [String]
 */
val miuiVersion
    get() = if (isMIUI)
        findPropString("ro.miui.ui.version.name").let {
            when (it) {
                "V11", "V110" -> "11"
                "V12", "V120" -> "12"
                "V125" -> "12.5"
                "V13", "V130" -> "13"
                "V14", "V140" -> "14"
                else -> it.replace("V", "")
            }
        }.trim()
    else ""

/**
 * 获取 HyperOS 版本
 * @return [String]
 */
val miosVersion
    get() = if (isMIOS)
        findPropString("ro.mi.os.version.name").let {
            if (it.startsWith("OS")) it.replaceFirst("OS", "") else it
        }.trim()
    else ""

/**
 * 获取 MIUI 版本号
 * @return [Float]
 */
val miuiVersionCode get() = miuiVersion.toFloatOrNull() ?: 0f

/**
 * 获取 HyperOS 版本号
 * @return [Float]
 */
val miosVersionCode get() = findPropString("ro.mi.os.version.code").toFloatOrNull() ?: 0f

/**
 * 获取 MIUI 次版本号
 * @return [String]
 */
val miuiIncrementalVersion get() = findPropString("ro.system.build.version.incremental").trim()

/**
 * 获取 HyperOS 次版本号
 * @return [String]
 */
val miosIncrementalVersion get() = findPropString("ro.mi.os.version.incremental").trim()

/**
 * 获取 MIUI、HyperOS 完全版本
 * @return [String]
 */
val systemFullVersion
    get() = when {
        isMIOS -> "HyperOS " + miosIncrementalVersion.let {
            if (it.lowercase().endsWith(".dev").not() && it.lowercase().any { e -> e.code in 97..122 })
                "${it.replaceFirst("OS", "")} 稳定版"
            else when {
                it.lowercase().endsWith(".dev") -> "${it.replaceFirst("OS", "")} 开发版"
                else -> "$miosVersion $it 开发版"
            }
        }
        isMIUI -> miuiIncrementalVersion.let {
            if (it.lowercase().endsWith(".dev").not() && it.lowercase().any { e -> e.code in 97..122 })
                "$it 稳定版"
            else when {
                it.lowercase().endsWith(".dev") -> "$it 开发版"
                else -> "V$miuiVersion $it 开发版"
            }
        }
        else -> "不是 MIUI 或 HyperOS 系统"
    }

/**
 * 获取 [Drawable]
 * @param resId 属性资源 ID
 * @return [Drawable]
 */
fun Resources.drawableOf(@DrawableRes resId: Int) = ResourcesCompat.getDrawable(this, resId, null) ?: error("Invalid resources")

/**
 * 获取颜色
 * @param resId 属性资源 ID
 * @return [Int]
 */
fun Resources.colorOf(@ColorRes resId: Int) = ResourcesCompat.getColor(this, resId, null)

/**
 * 得到 APP 安装包信息 (兼容)
 * @param packageName APP 包名
 * @param flag [PackageInfoFlags]
 * @return [PackageInfo] or null
 */
private fun Context.getPackageInfoCompat(packageName: String, flag: Number = 0) = runCatching {
    @Suppress("DEPRECATION", "KotlinRedundantDiagnosticSuppress")
    if (Build.VERSION.SDK_INT >= 33)
        packageManager?.getPackageInfo(packageName, PackageInfoFlags.of(flag.toLong()))
    else packageManager?.getPackageInfo(packageName, flag.toInt())
}.getOrNull()

/**
 * 得到 APP 版本号 (兼容 [PackageInfo.getLongVersionCode])
 * @return [Int]
 */
private val PackageInfo.versionCodeCompat get() = PackageInfoCompat.getLongVersionCode(this)

/**
 * 判断 APP 是否安装
 * @param packageName APP 包名
 * @return [Boolean]
 */
fun Context.isInstall(packageName: String) = getPackageInfoCompat(packageName)?.let { true } ?: false

/**
 * 得到 APP 版本信息
 * @return [String]
 */
val Context.appVersionName get() = getPackageInfoCompat(packageName)?.versionName ?: ""

/**
 * 得到 APP 版本号
 * @return [Int]
 */
val Context.appVersionCode get() = getPackageInfoCompat(packageName)?.versionCodeCompat

/**
 * 得到 APP 名称
 * @param packageName APP 包名 - 默认为当前 APP
 * @return [String]
 */
fun Context.appNameOf(packageName: String = getPackageName()) =
    getPackageInfoCompat(packageName)?.applicationInfo?.loadLabel(packageManager)?.toString() ?: ""

/**
 * 得到 APP 图标
 * @param packageName APP 包名 - 默认为当前 APP
 * @return [Drawable] or null
 */
fun Context.appIconOf(packageName: String = getPackageName()) = getPackageInfoCompat(packageName)?.applicationInfo?.loadIcon(packageManager)

/**
 * 获取 APP 是否为 DEBUG 版本
 * @param packageName APP 包名
 * @return [Boolean]
 */
fun Context.isDebugApp(packageName: String) =
    safeOfFalse { (getPackageInfoCompat(packageName)?.applicationInfo?.flags?.and(ApplicationInfo.FLAG_DEBUGGABLE) ?: 0) != 0 }

/**
 * 获取 APP 是否为系统 APP
 * @param packageName APP 包名
 * @return [Boolean]
 */
fun Context.isSystemApp(packageName: String) =
    safeOfFalse { (getPackageInfoCompat(packageName)?.applicationInfo?.flags?.and(ApplicationInfo.FLAG_SYSTEM) ?: 0) != 0 }

/**
 * 对数值自动补零
 * @return [String]
 */
val Int.autoZero: String get() = if (this < 10) "0$this" else toString()

/**
 * 从字符串获取小时
 * @return [Int]
 */
val String.hour
    get() = safeOfNan {
        Calendar.getInstance().also {
            it.time = SimpleDateFormat("HH:mm", Locale.CHINA).parse(this) as Date
        }.get(Calendar.HOUR_OF_DAY)
    }

/**
 * 从字符串获取分钟
 * @return [Int]
 */
val String.minute
    get() = safeOfNan {
        Calendar.getInstance().also {
            it.time = SimpleDateFormat("HH:mm", Locale.CHINA).parse(this) as Date
        }.get(Calendar.MINUTE)
    }

/**
 * 是否关闭了通知权限
 * @return [Boolean]
 */
val isNotNoificationEnabled get() = !NotificationManagerCompat.from(appContext).areNotificationsEnabled()

/**
 * 网络连接是否正常
 * @return [Boolean] 网络是否连接
 */
val Context.isNetWorkSuccess
    get() = safeOfFalse {
        @Suppress("DEPRECATION")
        getSystemService<ConnectivityManager>()?.activeNetworkInfo != null
    }

/**
 * dp 转换为 pxInt
 * @param context 使用的实例
 * @return [Int]
 */
fun Number.dp(context: Context) = dpFloat(context).toInt()

/**
 * dp 转换为 pxFloat
 * @param context 使用的实例
 * @return [Float]
 */
fun Number.dpFloat(context: Context) = toFloat() * context.resources.displayMetrics.density

/**
 * 获取系统主题色
 * @return [Int] Android < 12 返回 [wallpaperColor]
 */
val Context.systemAccentColor
    @SuppressLint("InlinedApi")
    get() = safeOf(wallpaperColor) {
        if (isUpperOfAndroidS) resources.colorOf(android.R.color.system_accent1_600) else wallpaperColor
    }

/**
 * 获取系统壁纸颜色
 * @return [Int] 无法获取时返回默认颜色
 */
val Context.wallpaperColor
    get() = runCatching {
        WallpaperManager.getInstance(this).getWallpaperColors(WallpaperManager.FLAG_SYSTEM)?.primaryColor?.toArgb()
    }.getOrNull() ?: (if (isSystemInDarkMode) 0xFFD8D8D8.toInt() else 0xFF707173.toInt())

/**
 * 获取较浅的颜色
 * @return [Int]
 */
val Int.brighterColor: Int
    get() {
        val hsv = FloatArray(3)
        Color.colorToHSV(this, hsv)
        hsv[1] = hsv[1] - 0.3f
        hsv[2] = hsv[2] + 0.3f
        return Color.HSVToColor(hsv)
    }

/**
 * 是否为白色
 * @return [Boolean]
 */
val Int.isWhiteColor
    get() = safeOfTrue {
        val r = this and 0xff0000 shr 16
        val g = this and 0x00ff00 shr 8
        val b = this and 0x0000ff
        (0.2126 * r + 0.7152 * g + 0.0722 * b) >= 128
    }

/**
 * Base64 加密
 * @return [String]
 */
val Bitmap.base64
    get() = safeOfNothing {
        val baos = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.PNG, 100, baos)
        Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
    }

/**
 * Base64 加密
 * @return [String]
 */
val String.base64: String get() = Base64.encodeToString(toByteArray(), Base64.DEFAULT)

/**
 * Base64 解密为字节流
 * @return [ByteArray]
 */
val String.unbase64 get() = Base64.decode(this, Base64.DEFAULT) ?: ByteArray(0)

/**
 * 字节流解析为位图
 * @return [Bitmap]
 */
val ByteArray.bitmap: Bitmap get() = BitmapFactory.decodeByteArray(this, 0, size)

/**
 * 字符串解析为位图
 * @return [Bitmap]
 */
val String.bitmap: Bitmap get() = unbase64.bitmap

/**
 * 圆角图片
 * @param radius 圆角度
 * @return [Bitmap] 圆角后的位图 - 失败会返回处理之前的位图
 */
fun Bitmap.round(radius: Float): Bitmap = safeOf(default = this) {
    Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).also { out ->
        Canvas(out).also { canvas ->
            Paint().also { paint ->
                paint.isAntiAlias = true
                canvas.drawARGB(0, 0, 0, 0)
                paint.color = Color.WHITE
                canvas.drawRoundRect(RectF(Rect(0, 0, width, height)), radius, radius, paint)
                paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
                canvas.drawBitmap(this, Rect(0, 0, width, height), Rect(0, 0, width, height), paint)
            }
        }
    }
}

/**
 * 获取系统 Prop 值
 * @param key Key
 * @param default 默认值
 * @return [String]
 */
fun findPropString(key: String, default: String = "") = safeOf(default) {
    "android.os.SystemProperties".toClassOrNull()?.method {
        name = "get"
        param(StringClass, StringClass)
    }?.get()?.invoke(key, default) ?: default
}

/**
 * 是否有 Root 权限
 * @return [Boolean]
 */
val isRootAccess get() = safeOfFalse {
    @Suppress("DEPRECATION")
    Shell.rootAccess()
}

/**
 * 执行命令
 * @param cmd 命令
 * @param isSu 是否使用 Root 权限执行 - 默认：是
 * @return [String] 执行结果
 */
fun execShell(cmd: String, isSu: Boolean = true) = safeOfNothing {
    @Suppress("DEPRECATION")
    (if (isSu) Shell.su(cmd) else Shell.sh(cmd)).exec().out.let {
        if (it.isNotEmpty()) it[0].trim() else ""
    }
}

/**
 * 弹出 [Toast]
 * @param msg 提示内容
 */
fun toast(msg: String) {
    runCatching {
        Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show()
    }.onFailure { YLog.warn(msg) }
}

/**
 * 跳转到指定页面
 *
 * [T] 为指定的 [Activity]
 */
inline fun <reified T : Activity> Context.navigate() = runInSafe {
    startActivity(Intent(if (this is Service) applicationContext else this, T::class.java).apply {
        if (this@navigate !is Activity) flags = Intent.FLAG_ACTIVITY_NEW_TASK
    })
}

/**
 * 弹出 [Snackbar]
 * @param msg 提示内容
 * @param actionText 按钮文本 - 不写默认取消按钮
 * @param callback 按钮事件回调
 */
fun Context.snake(msg: String, actionText: String = "", callback: () -> Unit = {}) =
    Snackbar.make((this as Activity).findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
        .apply {
            if (actionText.isBlank()) return@apply
            setActionTextColor(if (isSystemInDarkMode) Color.BLACK else Color.WHITE)
            setAction(actionText) { callback() }
        }.show()

/**
 * 启动系统浏览器
 * @param url 网址
 * @param packageName 指定包名 - 可不填
 */
fun Context.openBrowser(url: String, packageName: String = "") = runCatching {
    startActivity(Intent().apply {
        if (packageName.isNotBlank()) setPackage(packageName)
        action = Intent.ACTION_VIEW
        data = Uri.parse(url)
        /** 防止顶栈一样重叠在自己的 APP 中 */
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    })
}.onFailure {
    if (packageName.isNotBlank())
        snake(msg = "启动 $packageName 失败")
    else snake(msg = "启动系统浏览器失败")
}

/**
 * 跳转 APP 自身设置界面
 * @param packageName 包名
 */
fun Context.openSelfSetting(packageName: String = appContext.packageName) = runCatching {
    if (isInstall(packageName))
        startActivity(Intent().apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", packageName, null)
        })
    else toast(msg = "你没有安装此应用")
}.onFailure { toast(msg = "启动 $packageName 应用信息失败") }

/** 跳转通知设置界面 */
fun Context.openNotifySetting() = runCatching {
    Intent().also { intent ->
        intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        intent.putExtra(Notification.EXTRA_CHANNEL_ID, applicationInfo.uid)
        startActivity(intent)
    }
}.onFailure { snake(msg = "跳转通知设置失败") }

/**
 * 复制到剪贴板
 * @param content 要复制的文本
 */
fun Context.copyToClipboard(content: String) = runInSafe {
    (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).apply {
        setPrimaryClip(ClipData.newPlainText(null, content))
        (primaryClip?.getItemAt(0)?.text ?: "").also {
            if (it != content) snake(msg = "复制失败") else snake(msg = "已复制")
        }
    }
}

/**
 * 时间戳 -> 时间
 * @param format 格式化方法 - 默认：yyyy-MM-dd HH:mm:ss
 * @return [String] 目标字符串时间
 */
fun Long.stampToDate(format: String = "yyyy-MM-dd HH:mm:ss") =
    safeOfNothing { SimpleDateFormat(format, Locale.CHINA).format(Date(this)) ?: "" }

/**
 * 延迟执行
 * @param ms 毫秒 - 默认：150
 * @param it 方法体
 */
fun Any?.delayedRun(ms: Long = 150, it: () -> Unit) = runInSafe {
    @Suppress("DEPRECATION")
    Handler().postDelayed({ it() }, ms)
}

/**
 * 隐藏或显示启动器图标
 *
 * - 你可能需要 LSPosed 的最新版本以开启高版本系统中隐藏 APP 桌面图标功能
 * @param isShow 是否显示
 */
fun Context.hideOrShowLauncherIcon(isShow: Boolean) {
    packageManager?.setComponentEnabledSetting(
        ComponentName(packageName, "${BuildConfigWrapper.APPLICATION_ID}.Home"),
        if (isShow) PackageManager.COMPONENT_ENABLED_STATE_DISABLED else PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP
    )
}

/**
 * 获取启动器图标状态
 * @return [Boolean] 是否显示
 */
val Context.isLauncherIconShowing
    get() = packageManager?.getComponentEnabledSetting(
        ComponentName(packageName, "${BuildConfigWrapper.APPLICATION_ID}.Home")
    ) != PackageManager.COMPONENT_ENABLED_STATE_DISABLED