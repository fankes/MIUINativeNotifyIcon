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
@file:Suppress("TrustAllX509TrustManager", "CustomX509TrustManager", "IMPLICIT_CAST_TO_ANY")

package com.fankes.miui.notify.utils.tool

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.fankes.miui.notify.R
import com.fankes.miui.notify.data.DataConst
import com.fankes.miui.notify.databinding.DiaSourceFromBinding
import com.fankes.miui.notify.databinding.DiaSourceFromStringBinding
import com.fankes.miui.notify.hook.HookConst.TYPE_SOURCE_SYNC_WAY_1
import com.fankes.miui.notify.hook.HookConst.TYPE_SOURCE_SYNC_WAY_2
import com.fankes.miui.notify.hook.HookConst.TYPE_SOURCE_SYNC_WAY_3
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.ui.activity.ConfigureActivity
import com.fankes.miui.notify.utils.factory.safeOfNull
import com.fankes.miui.notify.utils.factory.showDialog
import com.fankes.miui.notify.utils.factory.snake
import com.highcapable.yukihookapi.hook.factory.modulePrefs
import com.highcapable.yukihookapi.hook.log.loggerD
import okhttp3.*
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

/**
 * 通知图标在线规则管理类
 */
object IconRuleManagerTool {

    /** 推送通知的渠道名称 */
    private const val NOTIFY_CHANNEL = "notifyRuleUpdateId"

    /** 当前规则的系统名称 */
    private const val OS_TAG = "MIUI"

    /** 当前规则的通知图标颜色 */
    private const val OS_COLOR = 0xFFE06818

    /**
     * 从在线地址手动同步规则
     * @param context 实例
     * @param callback 成功后回调
     */
    fun syncByHand(context: Context, callback: () -> Unit) =
        context.showDialog<DiaSourceFromBinding> {
            title = "同步列表"
            var sourceType = context.modulePrefs.get(DataConst.SOURCE_SYNC_WAY)
            var customUrl = context.modulePrefs.get(DataConst.SOURCE_SYNC_WAY_CUSTOM_URL)
            binding.sourceUrlEdit.apply {
                if (customUrl.isNotBlank()) {
                    setText(customUrl)
                    setSelection(customUrl.length)
                }
                doOnTextChanged { text, _, _, _ -> customUrl = text.toString() }
            }
            binding.sourceFromTextLin.isVisible = sourceType == TYPE_SOURCE_SYNC_WAY_3
            binding.sourceRadio1.isChecked = sourceType == TYPE_SOURCE_SYNC_WAY_1
            binding.sourceRadio2.isChecked = sourceType == TYPE_SOURCE_SYNC_WAY_2
            binding.sourceRadio3.isChecked = sourceType == TYPE_SOURCE_SYNC_WAY_3
            binding.sourceRadio1.setOnClickListener {
                binding.sourceFromTextLin.isVisible = false
                sourceType = TYPE_SOURCE_SYNC_WAY_1
            }
            binding.sourceRadio2.setOnClickListener {
                binding.sourceFromTextLin.isVisible = false
                sourceType = TYPE_SOURCE_SYNC_WAY_2
            }
            binding.sourceRadio3.setOnClickListener {
                binding.sourceFromTextLin.isVisible = true
                sourceType = TYPE_SOURCE_SYNC_WAY_3
            }
            confirmButton {
                context.modulePrefs.put(DataConst.SOURCE_SYNC_WAY, sourceType)
                context.modulePrefs.put(DataConst.SOURCE_SYNC_WAY_CUSTOM_URL, customUrl)
                sync(context, sourceType, customUrl, callback)
            }
            cancelButton()
            neutralButton(text = "自定义规则") {
                context.showDialog<DiaSourceFromStringBinding> {
                    title = "自定义规则(调试)"
                    binding.jsonRuleEdit.apply {
                        requestFocus()
                        invalidate()
                    }
                    IconPackParams(context).also { params ->
                        confirmButton(text = "合并") {
                            binding.jsonRuleEdit.text.toString().also { jsonString ->
                                when {
                                    jsonString.isNotBlank() && params.isNotVaildJson(jsonString) -> context.snake(msg = "不是有效的 JSON 数据")
                                    jsonString.isNotBlank() -> {
                                        params.save(
                                            params.splicingJsonArray(
                                                dataJson1 = params.storageDataJson ?: "[]",
                                                dataJson2 = jsonString.takeIf { params.isJsonArray(it) } ?: "[$jsonString]"
                                            )
                                        )
                                        notifyRefresh(context)
                                        callback()
                                    }
                                    else -> context.snake(msg = "请输入有效内容")
                                }
                            }
                        }
                        cancelButton(text = "覆盖") {
                            binding.jsonRuleEdit.text.toString().also { jsonString ->
                                when {
                                    jsonString.isNotBlank() && params.isNotVaildJson(jsonString) -> context.snake(msg = "不是有效的 JSON 数据")
                                    jsonString.isNotBlank() -> {
                                        params.save(dataJson = jsonString.takeIf { params.isJsonArray(it) } ?: "[$jsonString]")
                                        notifyRefresh(context)
                                        callback()
                                    }
                                    else -> context.snake(msg = "请输入有效内容")
                                }
                            }
                        }
                    }
                    neutralButton(text = "取消")
                }
            }
        }

    /**
     * 从在线地址同步规则
     * @param context 实例
     * @param sourceType 同步地址类型 - 默认自动获取已存储的键值
     * @param customUrl 自定义同步地址 - 默认自动获取已存储的键值
     * @param callback 成功后回调
     */
    fun sync(
        context: Context,
        sourceType: Int = context.modulePrefs.get(DataConst.SOURCE_SYNC_WAY),
        customUrl: String = context.modulePrefs.get(DataConst.SOURCE_SYNC_WAY_CUSTOM_URL),
        callback: () -> Unit
    ) {
        when (sourceType) {
            TYPE_SOURCE_SYNC_WAY_1 ->
                onRefreshing(context, url = "https://raw.fastgit.org/fankes/AndroidNotifyIconAdapt/main", callback)
            TYPE_SOURCE_SYNC_WAY_2 ->
                onRefreshing(context, url = "https://raw.githubusercontent.com/fankes/AndroidNotifyIconAdapt/main", callback)
            TYPE_SOURCE_SYNC_WAY_3 ->
                if (customUrl.isNotBlank())
                    if (customUrl.startsWith("http://") || customUrl.startsWith("https://"))
                        onRefreshingCustom(context, customUrl, callback)
                    else context.snakeOrNotify(title = "同步失败", msg = "同步地址不是一个合法的 URL")
                else context.snakeOrNotify(title = "同步失败", msg = "同步地址不能为空")
            else -> context.snakeOrNotify(title = "同步异常", msg = "同步类型错误")
        }
    }

    /**
     * 开始更新数据
     * @param context 实例
     * @param url
     * @param callback 成功后回调
     */
    private fun onRefreshing(context: Context, url: String, callback: () -> Unit) = checkingInternetConnect(context) {
        fun doParse(result: (Boolean) -> Unit = {}) {
            wait(context, url = "$url/OS/$OS_TAG/NotifyIconsSupportConfig.json") { isDone1, ctOS ->
                result(true)
                wait(context, url = "$url/APP/NotifyIconsSupportConfig.json") { isDone2, ctAPP ->
                    result(false)
                    IconPackParams(context).also { params ->
                        when {
                            isDone1 && isDone2 -> params.splicingJsonArray(ctOS, ctAPP).also {
                                when {
                                    params.isHackString(it) ->
                                        context.snakeOrNotify(title = "同步错误", msg = "请求需要验证，请尝试魔法上网或关闭魔法")
                                    params.isNotVaildJson(it) ->
                                        context.snakeOrNotify(title = "同步错误", msg = "目标地址不是有效的 JSON 数据")
                                    params.isCompareDifferent(it) -> {
                                        params.save(it)
                                        pushNotify(context, title = "同步完成", msg = "已更新通知图标优化名单，点击查看")
                                        notifyRefresh(context)
                                        callback()
                                    }
                                    else -> (if (context is AppCompatActivity) context.snake(msg = "列表数据已是最新"))
                                }
                            }
                            context is AppCompatActivity ->
                                context.showDialog {
                                    title = "连接失败"
                                    msg = "连接失败，错误如下：\n${if (isDone1.not()) ctOS else ctAPP}"
                                    confirmButton(text = "再试一次") { syncByHand(context, callback) }
                                    cancelButton()
                                }
                            else -> pushNotify(context, title = "同步地址不可用", msg = if (isDone1.not()) ctOS else ctAPP)
                        }
                    }
                }
            }
        }
        if (context is AppCompatActivity)
            context.showDialog {
                title = "同步中"
                progressContent = "正在同步 OS 数据"
                noCancelable()
                doParse { if (it) progressContent = "正在同步 APP 数据" else cancel() }
            }
        else doParse()
    }

    /**
     * 开始更新数据
     * @param context 实例
     * @param url
     * @param callback 成功后回调
     */
    private fun onRefreshingCustom(context: Context, url: String, callback: () -> Unit) = checkingInternetConnect(context) {
        fun doParse(result: () -> Unit = {}) {
            wait(context, url) { isDone, content ->
                result()
                IconPackParams(context).also { params ->
                    when {
                        isDone -> when {
                            params.isHackString(content) ->
                                context.snakeOrNotify(title = "同步错误", msg = "请求需要验证，请尝试魔法上网或关闭魔法")
                            params.isNotVaildJson(content) ->
                                context.snakeOrNotify(title = "同步错误", msg = "目标地址不是有效的 JSON 数据")
                            params.isCompareDifferent(content) -> {
                                params.save(content)
                                pushNotify(context, title = "同步完成", msg = "已更新通知图标优化名单，点击查看")
                                notifyRefresh(context)
                                callback()
                            }
                            else -> (if (context is AppCompatActivity) context.snake(msg = "列表数据已是最新"))
                        }
                        context is AppCompatActivity ->
                            context.showDialog {
                                title = "连接失败"
                                msg = "连接失败，错误如下：\n$content"
                                confirmButton(text = "我知道了")
                            }
                        else -> pushNotify(context, title = "同步地址不可用", msg = content)
                    }
                }
            }
        }
        if (context is AppCompatActivity)
            context.showDialog {
                title = "同步中"
                progressContent = "正在通过自定义地址同步数据"
                noCancelable()
                doParse { cancel() }
            }
        else doParse()
    }

    /**
     * 检查网络连接情况
     * @param context 实例
     * @param callback 已连接回调
     */
    private fun checkingInternetConnect(context: Context, callback: () -> Unit) =
        if (context is AppCompatActivity) context.showDialog {
            title = "准备中"
            progressContent = "正在检查网络连接情况"
            noCancelable()
            baseCheckingInternetConnect(context) { isDone ->
                cancel()
                if (isDone) callback() else
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
        } else baseCheckingInternetConnect(context) { isDone ->
            if (isDone) callback() else pushNotify(context, title = "网络不可用", msg = "无法连接到互联网，无法更新通知图标规则")
        }

    /**
     * 检查网络连接情况
     * @param context 实例
     * @param result 已连接回调
     */
    private fun baseCheckingInternetConnect(context: Context, result: (Boolean) -> Unit) =
        wait(context, url = "https://www.baidu.com") { isDone, _ -> result(isDone) }

    /**
     * 发送 GET 请求内容并等待
     * @param context 实例
     * @param url 请求地址
     * @param result 回调 - ([Boolean] 是否成功,[String] 成功的内容或失败消息)
     */
    private fun wait(context: Context, url: String, result: (Boolean, String) -> Unit) = runCatching {
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
                (context as? Activity?)?.runOnUiThread { result(false, e.toString()) } ?: result(false, e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val bodyString = response.body.string()
                (context as? Activity?)?.runOnUiThread { result(true, bodyString) } ?: result(true, bodyString)
            }
        })
    }.onFailure { result(false, "URL 无效") }

    /**
     * 推送通知图标更新通知
     * @param context 实例
     */
    private fun notifyRefresh(context: Context) {
        if (context !is AppCompatActivity) return
        SystemUITool.refreshSystemUI(context) { context.snake(msg = "通知图标优化名单已完成同步") }
    }

    /**
     * 根据实例类型决定推送通知还是弹出提示
     * @param title 标题 - 仅对通知生效
     * @param msg 消息内容
     */
    private fun Context.snakeOrNotify(title: String, msg: String) {
        if (this !is AppCompatActivity)
            pushNotify(context = this, title, msg)
        else snake(msg)
    }

    /**
     * 推送通知
     * @param context 实例 - 类型为 [AppCompatActivity] 时将不会推送通知
     * @param title 通知标题
     * @param msg 通知消息
     */
    private fun pushNotify(context: Context, title: String, msg: String) {
        if (context !is AppCompatActivity)
            context.getSystemService<NotificationManager>()?.apply {
                createNotificationChannel(
                    NotificationChannel(
                        NOTIFY_CHANNEL, "通知图标优化规则",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                )
                notify(0, NotificationCompat.Builder(context, NOTIFY_CHANNEL).apply {
                    setContentTitle(title)
                    setContentText(msg)
                    color = OS_COLOR.toInt()
                    setAutoCancel(true)
                    setSmallIcon(R.drawable.ic_nf_icon_update)
                    setSound(null)
                    setDefaults(NotificationCompat.DEFAULT_ALL)
                    setContentIntent(
                        PendingIntent.getActivity(
                            context, msg.hashCode(),
                            Intent(context, ConfigureActivity::class.java).apply { putExtra("isShowUpdDialog", false) },
                            if (Build.VERSION.SDK_INT < 31) PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_IMMUTABLE
                        )
                    )
                }.build())
            }
    }

    /**
     * 自动信任 SSL 证书
     *
     * 放行全部加密 SSL 请求
     */
    private object SSLSocketClient {

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