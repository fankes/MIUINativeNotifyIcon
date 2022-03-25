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
 * This file is Created by fankes on 2022/1/7.
 */
@file:Suppress("DEPRECATION", "PrivateApi", "unused", "ObsoleteSdkInt")

package com.fankes.miui.notify.utils.factory

import android.app.Activity
import android.app.Notification
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Base64
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.fankes.miui.notify.application.MNNApplication.Companion.appContext
import com.google.android.material.snackbar.Snackbar
import com.highcapable.yukihookapi.hook.factory.classOf
import com.highcapable.yukihookapi.hook.factory.hasClass
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.type.java.StringType
import com.topjohnwu.superuser.Shell
import java.io.ByteArrayOutputStream

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
 * 通知栏是否为 MIUI 样式
 * @return [Boolean] 是否符合条件
 */
val Context.isMiuiNotifyStyle get() = safeOfFalse { Settings.System.getInt(contentResolver, "status_bar_notification_style") == 0 }

/**
 * 系统深色模式是否没开启
 * @return [Boolean] 是否开启
 */
inline val Context.isNotSystemInDarkMode get() = !isSystemInDarkMode

/**
 * 系统版本是否高于或等于 Android 12
 * @return [Boolean] 是否符合条件
 */
inline val isUpperOfAndroidS get() = Build.VERSION.SDK_INT > Build.VERSION_CODES.R

/**
 * 系统版本是否低于 Android 9
 * @return [Boolean] 是否符合条件
 */
inline val isLowerAndroidP get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.P

/**
 * 当前设备是否是 MIUI 定制 Android 系统
 * @return [Boolean] 是否符合条件
 */
val isMIUI by lazy { ("android.miui.R").hasClass }

/**
 * 当前设备是否不是 MIUI 定制 Android 系统
 * @return [Boolean] 是否符合条件
 */
inline val isNotMIUI get() = !isMIUI

/**
 * 是否为支持的 MIUI 版本
 * @return [Boolean]
 */
val isSupportMiuiVersion
    get() = when (miuiVersion) {
        "12" -> true
        "12.5" -> true
        "13" -> true
        else -> false
    }

/**
 * 是否不为支持的 MIUI 版本
 * @return [Boolean]
 */
inline val isNotSupportMiuiVersion get() = !isSupportMiuiVersion

/**
 * 获取 MIUI 版本
 * @return [String]
 */
val miuiVersion
    get() = if (isMIUI)
        findPropString(key = "ro.miui.ui.version.name", default = "V无法获取").let {
            when (it) {
                "V110" -> "11"
                "V11" -> "11"
                "V120" -> "12"
                "V12" -> "12"
                "V125" -> "12.5"
                "V130" -> "13"
                "V13" -> "13"
                else -> it.replace(oldValue = "V", newValue = "")
            }
        }.trim()
    else "NULL"

/**
 * 获取 MIUI 版本号
 * @return [Float]
 */
val miuiVersionCode get() = safeOf(default = 0f) { miuiVersion.toFloat() }

/**
 * 获取 MIUI 次版本号
 * @return [String]
 */
val miuiIncrementalVersion get() = findPropString(key = "ro.system.build.version.incremental").trim()

/**
 * 获取 MIUI 完全版本
 * @return [String]
 */
val miuiFullVersion
    get() = if (isMIUI) miuiIncrementalVersion.let {
        if (it.lowercase().contains("a") ||
            it.lowercase().contains("b") ||
            it.lowercase().contains("c") ||
            it.lowercase().contains("d") ||
            it.lowercase().contains("e") ||
            it.lowercase().contains("f") ||
            it.lowercase().contains("g") ||
            it.lowercase().contains("h") ||
            it.lowercase().contains("i") ||
            it.lowercase().contains("j") ||
            it.lowercase().contains("k") ||
            it.lowercase().contains("l") ||
            it.lowercase().contains("m") ||
            it.lowercase().contains("n") ||
            it.lowercase().contains("o") ||
            it.lowercase().contains("p") ||
            it.lowercase().contains("q") ||
            it.lowercase().contains("r") ||
            it.lowercase().contains("s") ||
            it.lowercase().contains("t") ||
            it.lowercase().contains("u") ||
            it.lowercase().contains("v") ||
            it.lowercase().contains("w") ||
            it.lowercase().contains("x") ||
            it.lowercase().contains("y") ||
            it.lowercase().contains("z")
        ) "$it 稳定版" else "V$miuiVersion $it 开发版"
    } else "不是 MIUI 系统"

/**
 * 得到安装包信息
 * @return [PackageInfo]
 */
val Context.packageInfo get() = packageManager?.getPackageInfo(packageName, 0) ?: PackageInfo()

/**
 * 判断应用是否安装
 * @return [Boolean]
 */
val String.isInstall
    get() = safeOfFalse {
        appContext.packageManager.getPackageInfo(
            this,
            PackageManager.GET_UNINSTALLED_PACKAGES
        )
        true
    }

/**
 * 得到版本信息
 * @return [String]
 */
val Context.versionName get() = packageInfo.versionName ?: ""

/**
 * 得到版本号
 * @return [Int]
 */
val Context.versionCode get() = packageInfo.versionCode

/**
 * 得到 APP 名称
 * @param name APP 包名
 * @return [String]
 */
fun Context.findAppName(name: String) =
    safeOfNothing { packageManager?.getPackageInfo(name, 0)?.applicationInfo?.loadLabel(packageManager).toString() }

/**
 * 得到 APP 图标
 * @param name APP 包名
 * @return [Drawable] or null
 */
fun Context.findAppIcon(name: String) =
    safeOfNull { packageManager?.getPackageInfo(name, 0)?.applicationInfo?.loadIcon(packageManager) }

/**
 * 是否关闭了通知权限
 * @return [Boolean]
 */
val isNotNoificationEnabled get() = !NotificationManagerCompat.from(appContext).areNotificationsEnabled()

/**
 * 网络连接是否正常
 * @return [Boolean] 网络是否连接
 */
val isNetWorkSuccess
    get() = safeOfFalse { appContext.getSystemService<ConnectivityManager>()?.activeNetworkInfo != null }

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
 * @return [Int] Android < 12 返回透明色
 */
val Context.systemAccentColor
    get() = safeOfNan { if (isUpperOfAndroidS) resources.getColor(android.R.color.system_accent1_600) else 0 }

/**
 * 是否为白色
 * @return [Boolean]
 */
val Int.isWhite
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
    (classOf(name = "android.os.SystemProperties").method {
        name = "get"
        param(StringType, StringType)
    }.get().invoke(key, default)) ?: default
}

/**
 * 执行命令 - su
 * @param cmd 命令
 * @return [String] 执行结果
 */
fun execShellSu(cmd: String) = safeOfNothing {
    Shell.su(cmd).exec().out.let {
        if (it.isNotEmpty()) it[0].trim() else ""
    }
}

/**
 * 弹出 [Toast]
 * @param msg 提示内容
 */
fun toast(msg: String) = Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show()

/**
 * 跳转到指定页面
 *
 * [T] 为指定的 [Activity]
 */
inline fun <reified T : Activity> Context.navigate() = startActivity(Intent(this, T::class.java))

/**
 * 弹出 [Snackbar]
 * @param msg 提示内容
 * @param actionText 按钮文本 - 不写默认取消按钮
 * @param it 按钮事件回调
 */
fun Context.snake(msg: String, actionText: String = "", it: () -> Unit = {}) =
    Snackbar.make((this as Activity).findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
        .apply {
            if (actionText.isBlank()) return@apply
            setActionTextColor(Color.WHITE)
            setAction(actionText) { it() }
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
    if (packageName.isInstall)
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