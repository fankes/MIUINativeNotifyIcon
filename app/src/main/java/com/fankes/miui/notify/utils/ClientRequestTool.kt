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
 * This file is Created by fankes on 2022/2/25.
 */
@file:Suppress("TrustAllX509TrustManager", "CustomX509TrustManager", "DEPRECATION")

package com.fankes.miui.notify.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.highcapable.yukihookapi.hook.log.loggerD
import okhttp3.*
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

/**
 * 网络请求管理类
 */
object ClientRequestTool {

    /**
     * 检查网络连接情况
     * @param context 实例
     * @param it 已连接回调
     */
    fun checkingInternetConnect(context: Activity, it: () -> Unit) =
        ProgressDialog(context).apply {
            setDefaultStyle(context)
            setCancelable(false)
            setTitle("准备中")
            setMessage("正在检查网络连接情况")
        }.apply {
            wait(context, url = "https://www.baidu.com") { isDone, _ ->
                cancel()
                if (isDone) it() else
                    context.showDialog {
                        title = "网络不可用"
                        msg = "无法连接到互联网，请检查你当前的设备是否可以上网，且没有在手机管家中禁用本模块的联网权限。"
                        confirmButton(text = "检查设置") {
                            runCatching {
                                context.startActivity(Intent().apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                    data = Uri.fromParts("package", context.packageName, null)
                                })
                            }.onFailure { context.snake(msg = "启动应用信息页面失败") }
                        }
                        cancelButton()
                    }
            }
        }.show()

    /**
     * 发送 GET 请求内容并等待
     * @param context 实例
     * @param url 请求地址
     * @param it 回调 - ([Boolean] 是否成功,[String] 成功的内容或失败消息)
     */
    fun wait(context: Activity, url: String, it: (Boolean, String) -> Unit) = runCatching {
        OkHttpClient().newBuilder().apply {
            SSLSocketClient.sSLSocketFactory?.let { sslSocketFactory(it, SSLSocketClient.trustManager) }
            hostnameVerifier(SSLSocketClient.hostnameVerifier)
        }.build().newCall(
            Request.Builder()
                .url(url)
                .get()
                .build()
        ).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                context.runOnUiThread { it(false, e.toString()) }
            }

            override fun onResponse(call: Call, response: Response) {
                val bodyString = response.body?.string() ?: ""
                context.runOnUiThread { it(true, bodyString) }
            }
        })
    }.onFailure { it(false, "URL 无效") }

    /**
     * 自动信任 SSL 证书
     *
     * 放行全部加密 SSL 请求
     */
    object SSLSocketClient {

        /**
         * 格式化实例
         * @return [SSLSocketFactory] or null
         */
        val sSLSocketFactory
            get() = safeOfNull {
                SSLContext.getInstance("TLS").let {
                    it.init(null, arrayOf<TrustManager>(trustManager), SecureRandom())
                    it.socketFactory
                }
            }

        /**
         * 使用的实例
         * @return [HostnameVerifier]
         */
        val hostnameVerifier get() = HostnameVerifier { _, _ -> true }

        /**
         * 信任管理者
         * @return [X509TrustManager]
         */
        val trustManager
            get() = object : X509TrustManager {

                override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                    loggerD(msg = "TrustX509 --> $authType")
                }

                override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                    loggerD(msg = "TrustX509 --> $authType")
                }

                override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
            }
    }
}