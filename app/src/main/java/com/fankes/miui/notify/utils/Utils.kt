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
 * This file is Created by fankes on 2022/1/7.
 */
@file:Suppress("DEPRECATION", "PrivateApi", "unused")

package com.fankes.miui.notify.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.*
import android.graphics.Bitmap.createBitmap
import android.os.Build
import android.service.notification.StatusBarNotification
import android.util.Base64
import com.fankes.miui.notify.application.MNNApplication.Companion.appContext
import java.io.DataInputStream
import java.io.DataOutputStream

/**
 * 系统深色模式是否开启
 * @return [Boolean] 是否开启
 */
val isSystemInDarkMode
    get() = (appContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

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
val isMIUI by lazy {
    try {
        Class.forName("android.miui.R")
        true
    } catch (_: Exception) {
        false
    }
}

/**
 * 当前设备是否不是 MIUI 定制 Android 系统
 * @return [Boolean] 是否符合条件
 */
inline val isNotMIUI get() = !isMIUI

/**
 * 获取 MIUI 版本
 * @return [String]
 */
val miuiVersion
    get() =
        if (isMIUI)
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
            } + " " + findPropString(key = "ro.system.build.version.incremental")
        else "不是 MIUI 系统"

/**
 * 获取推送通知的包名
 * @return [String]
 */
val StatusBarNotification.opPkgName get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) opPkg else packageName ?: ""

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
    get() =
        try {
            appContext.packageManager.getPackageInfo(
                this,
                PackageManager.GET_UNINSTALLED_PACKAGES
            )
            true
        } catch (e: Exception) {
            false
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
 * dp 转换为 px
 * @return [Int]
 */
val Number.dp get() = (toFloat() * appContext.resources.displayMetrics.density).toInt()

/**
 * dp 转换为 px
 * @param context 使用的实例
 * @return [Float]
 */
fun Number.dp(context: Context) = toFloat() * context.resources.displayMetrics.density

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
fun Bitmap.round(radius: Float): Bitmap =
    createBitmap(width, height, Bitmap.Config.ARGB_8888).also { out ->
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

/**
 * 获取系统 Prop 值
 * @param key Key
 * @param default 默认值
 * @return [String]
 */
fun findPropString(key: String, default: String = "") =
    try {
        (Class.forName("android.os.SystemProperties").getDeclaredMethod(
            "get",
            String::class.java,
            String::class.java
        ).apply { isAccessible = true }.invoke(null, key, default)) as? String? ?: default
    } catch (_: Exception) {
        default
    }

/**
 * 执行命令 - su
 * @param cmd 命令
 * @return [String] 执行结果
 */
fun execShellCmd(cmd: String): String {
    var result = ""
    var dos: DataOutputStream? = null
    var dis: DataInputStream? = null
    try {
        val p = Runtime.getRuntime().exec("su")
        dos = DataOutputStream(p.outputStream)
        dis = DataInputStream(p.inputStream)
        dos.writeBytes("$cmd\n")
        dos.flush()
        dos.writeBytes("exit\n")
        dos.flush()
        var line: String
        while (dis.readLine().also { line = it } != null) result += line
        p.waitFor()
    } catch (_: Exception) {
    } finally {
        try {
            dos?.close()
            dis?.close()
        } catch (_: Exception) {
        }
    }
    return result.trim()
}