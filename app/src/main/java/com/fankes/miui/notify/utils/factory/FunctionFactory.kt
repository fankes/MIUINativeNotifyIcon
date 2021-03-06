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
import android.app.Service
import android.app.WallpaperManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.provider.Settings
import android.util.Base64
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.google.android.material.snackbar.Snackbar
import com.highcapable.yukihookapi.hook.factory.classOf
import com.highcapable.yukihookapi.hook.factory.hasClass
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.type.java.StringType
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication.Companion.appContext
import com.topjohnwu.superuser.Shell
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


/**
 * ??????????????????????????????
 * @return [Boolean] ????????????
 */
val isSystemInDarkMode get() = appContext.isSystemInDarkMode

/**
 * ?????????????????????????????????
 * @return [Boolean] ????????????
 */
inline val isNotSystemInDarkMode get() = !isSystemInDarkMode

/**
 * ??????????????????????????????
 * @return [Boolean] ????????????
 */
val Context.isSystemInDarkMode get() = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

/**
 * ?????????????????????????????????
 * @return [Boolean] ????????????
 */
inline val Context.isNotSystemInDarkMode get() = !isSystemInDarkMode

/**
 * ????????????????????????????????? Android 12
 * @return [Boolean] ??????????????????
 */
inline val isUpperOfAndroidS get() = Build.VERSION.SDK_INT > Build.VERSION_CODES.R

/**
 * ???????????????????????? Android 9
 * @return [Boolean] ??????????????????
 */
inline val isLowerAndroidP get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.P

/**
 * ???????????????????????? Android 11
 * @return [Boolean] ??????????????????
 */
inline val isLowerAndroidR get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.R

/**
 * ????????????????????? MIUI ?????? Android ??????
 * @return [Boolean] ??????????????????
 */
val isMIUI by lazy { ("android.miui.R").hasClass }

/**
 * ???????????????????????? MIUI ?????? Android ??????
 * @return [Boolean] ??????????????????
 */
inline val isNotMIUI get() = !isMIUI

/**
 * ?????????????????? MIUI ??????
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
 * ????????????????????? MIUI ??????
 * @return [Boolean]
 */
inline val isNotSupportMiuiVersion get() = !isSupportMiuiVersion

/**
 * ?????? MIUI ??????
 * @return [String]
 */
val miuiVersion
    get() = if (isMIUI)
        findPropString(key = "ro.miui.ui.version.name", default = "V????????????").let {
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
 * ?????? MIUI ?????????
 * @return [Float]
 */
val miuiVersionCode get() = safeOf(default = 0f) { miuiVersion.toFloat() }

/**
 * ?????? MIUI ????????????
 * @return [String]
 */
val miuiIncrementalVersion get() = findPropString(key = "ro.system.build.version.incremental").trim()

/**
 * ?????? MIUI ????????????
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
        ) "$it ?????????" else "V$miuiVersion $it ?????????"
    } else "?????? MIUI ??????"

/**
 * ?????????????????????
 * @return [PackageInfo]
 */
val Context.packageInfo get() = packageManager?.getPackageInfo(packageName, 0) ?: PackageInfo()

/**
 * ????????????????????????
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
 * ??????????????????
 * @return [String]
 */
val Context.versionName get() = packageInfo.versionName ?: ""

/**
 * ???????????????
 * @return [Int]
 */
val Context.versionCode get() = packageInfo.versionCode

/**
 * ?????? APP ??????
 * @param name APP ??????
 * @return [String]
 */
fun Context.findAppName(name: String) =
    safeOfNothing { packageManager?.getPackageInfo(name, 0)?.applicationInfo?.loadLabel(packageManager).toString() }

/**
 * ?????? APP ??????
 * @param name APP ??????
 * @return [Drawable] or null
 */
fun Context.findAppIcon(name: String) =
    safeOfNull { packageManager?.getPackageInfo(name, 0)?.applicationInfo?.loadIcon(packageManager) }

/**
 * ?????? APP ????????? DEBUG ??????
 * @param name APP ??????
 * @return [Boolean]
 */
fun Context.isAppDebuggable(name: String) =
    safeOfFalse { (packageManager?.getPackageInfo(name, 0)?.applicationInfo?.flags?.and(ApplicationInfo.FLAG_DEBUGGABLE) ?: 0) != 0 }

/**
 * ?????????????????????
 * @return [String]
 */
val Int.autoZero: String get() = if (this < 10) "0$this" else toString()

/**
 * ????????????????????????
 * @return [Int]
 */
val String.hour
    get() = safeOfNan {
        Calendar.getInstance().also {
            it.time = SimpleDateFormat("HH:mm", Locale.CHINA).parse(this) as Date
        }.get(Calendar.HOUR_OF_DAY)
    }

/**
 * ????????????????????????
 * @return [Int]
 */
val String.minute
    get() = safeOfNan {
        Calendar.getInstance().also {
            it.time = SimpleDateFormat("HH:mm", Locale.CHINA).parse(this) as Date
        }.get(Calendar.MINUTE)
    }

/**
 * ???????????????????????????
 * @return [Boolean]
 */
val isNotNoificationEnabled get() = !NotificationManagerCompat.from(appContext).areNotificationsEnabled()

/**
 * ????????????????????????
 * @return [Boolean] ??????????????????
 */
val isNetWorkSuccess
    get() = safeOfFalse { appContext.getSystemService<ConnectivityManager>()?.activeNetworkInfo != null }

/**
 * dp ????????? pxInt
 * @param context ???????????????
 * @return [Int]
 */
fun Number.dp(context: Context) = dpFloat(context).toInt()

/**
 * dp ????????? pxFloat
 * @param context ???????????????
 * @return [Float]
 */
fun Number.dpFloat(context: Context) = toFloat() * context.resources.displayMetrics.density

/**
 * ?????????????????????
 * @return [Int] Android < 12 ?????? [wallpaperColor]
 */
val Context.systemAccentColor
    get() = safeOf(wallpaperColor) { if (isUpperOfAndroidS) resources.getColor(android.R.color.system_accent1_600) else wallpaperColor }

/**
 * ????????????????????????
 * @return [Int] ??????????????????????????????
 */
val Context.wallpaperColor
    get() = safeOfNan {
        WallpaperManager.getInstance(this).getWallpaperColors(WallpaperManager.FLAG_SYSTEM)?.primaryColor?.toArgb() ?: 0
    }

/**
 * ?????????????????????
 * @return [Int]
 */
val Int.brighter: Int
    get() {
        val hsv = FloatArray(3)
        Color.colorToHSV(this, hsv)
        hsv[1] = hsv[1] - 0.3f
        hsv[2] = hsv[2] + 0.3f
        return Color.HSVToColor(hsv)
    }

/**
 * ???????????????
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
 * Base64 ??????
 * @return [String]
 */
val Bitmap.base64
    get() = safeOfNothing {
        val baos = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.PNG, 100, baos)
        Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
    }

/**
 * Base64 ??????
 * @return [String]
 */
val String.base64: String get() = Base64.encodeToString(toByteArray(), Base64.DEFAULT)

/**
 * Base64 ??????????????????
 * @return [ByteArray]
 */
val String.unbase64 get() = Base64.decode(this, Base64.DEFAULT) ?: ByteArray(0)

/**
 * ????????????????????????
 * @return [Bitmap]
 */
val ByteArray.bitmap: Bitmap get() = BitmapFactory.decodeByteArray(this, 0, size)

/**
 * ????????????????????????
 * @return [Bitmap]
 */
val String.bitmap: Bitmap get() = unbase64.bitmap

/**
 * ????????????
 * @param radius ?????????
 * @return [Bitmap] ?????????????????? - ????????????????????????????????????
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
 * ???????????? Prop ???
 * @param key Key
 * @param default ?????????
 * @return [String]
 */
fun findPropString(key: String, default: String = "") = safeOf(default) {
    (classOf(name = "android.os.SystemProperties").method {
        name = "get"
        param(StringType, StringType)
    }.get().invoke(key, default)) ?: default
}

/**
 * ????????? Root ??????
 * @return [Boolean]
 */
val isRootAccess get() = safeOfFalse { Shell.rootAccess() }

/**
 * ????????????
 * @param cmd ??????
 * @param isSu ???????????? Root ???????????? - ????????????
 * @return [String] ????????????
 */
fun execShell(cmd: String, isSu: Boolean = true) = safeOfNothing {
    (if (isSu) Shell.su(cmd) else Shell.sh(cmd)).exec().out.let {
        if (it.isNotEmpty()) it[0].trim() else ""
    }
}

/**
 * ?????? [Toast]
 * @param msg ????????????
 */
fun toast(msg: String) = Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show()

/**
 * ?????????????????????
 *
 * [T] ???????????? [Activity]
 */
inline fun <reified T : Activity> Context.navigate() = runInSafe {
    startActivity(Intent(if (this is Service) applicationContext else this, T::class.java).apply {
        if (this@navigate !is Activity) flags = Intent.FLAG_ACTIVITY_NEW_TASK
    })
}

/**
 * ?????? [Snackbar]
 * @param msg ????????????
 * @param actionText ???????????? - ????????????????????????
 * @param callback ??????????????????
 */
fun Context.snake(msg: String, actionText: String = "", callback: () -> Unit = {}) =
    Snackbar.make((this as Activity).findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
        .apply {
            if (actionText.isBlank()) return@apply
            setActionTextColor(if (isSystemInDarkMode) Color.BLACK else Color.WHITE)
            setAction(actionText) { callback() }
        }.show()

/**
 * ?????????????????????
 * @param url ??????
 * @param packageName ???????????? - ?????????
 */
fun Context.openBrowser(url: String, packageName: String = "") = runCatching {
    startActivity(Intent().apply {
        if (packageName.isNotBlank()) setPackage(packageName)
        action = Intent.ACTION_VIEW
        data = Uri.parse(url)
        /** ???????????????????????????????????? APP ??? */
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    })
}.onFailure {
    if (packageName.isNotBlank())
        snake(msg = "?????? $packageName ??????")
    else snake(msg = "???????????????????????????")
}

/**
 * ?????? APP ??????????????????
 * @param packageName ??????
 */
fun Context.openSelfSetting(packageName: String = appContext.packageName) = runCatching {
    if (packageName.isInstall)
        startActivity(Intent().apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", packageName, null)
        })
    else toast(msg = "????????????????????????")
}.onFailure { toast(msg = "?????? $packageName ??????????????????") }

/** ???????????????????????? */
fun Context.openNotifySetting() = runCatching {
    Intent().also { intent ->
        intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        intent.putExtra(Notification.EXTRA_CHANNEL_ID, applicationInfo.uid)
        startActivity(intent)
    }
}.onFailure { snake(msg = "????????????????????????") }

/**
 * ??????????????????
 * @param content ??????????????????
 */
fun Context.copyToClipboard(content: String) = runInSafe {
    (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).apply {
        setPrimaryClip(ClipData.newPlainText(null, content))
        (primaryClip?.getItemAt(0)?.text ?: "").also {
            if (it != content) snake(msg = "????????????") else snake(msg = "?????????")
        }
    }
}

/**
 * ????????? -> ??????
 * @param format ??????????????? - ?????????yyyy-MM-dd HH:mm:ss
 * @return [String] ?????????????????????
 */
fun Long.stampToDate(format: String = "yyyy-MM-dd HH:mm:ss") =
    safeOfNothing { SimpleDateFormat(format, Locale.CHINA).format(Date(this)) ?: "" }

/**
 * ????????????
 * @param ms ?????? - ?????????150
 * @param it ?????????
 */
fun Any?.delayedRun(ms: Long = 150, it: () -> Unit) = runInSafe { Handler().postDelayed({ it() }, ms) }