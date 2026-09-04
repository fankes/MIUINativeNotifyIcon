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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * and eula along with this software.  If not, see
 * <https://www.gnu.org/licenses/>
 *
 * This file is created by fankes on 2022/2/25.
 */
package com.fankes.miui.notify.utils.tool

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.core.widget.doAfterTextChanged
import com.fankes.miui.notify.R
import com.fankes.miui.notify.const.IconRuleSourceSyncType
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.databinding.DiaSourceFromBinding
import com.fankes.miui.notify.ui.activity.ConfigureActivity
import com.fankes.miui.notify.utils.factory.openBrowser
import com.fankes.miui.notify.utils.factory.showDialog
import com.fankes.miui.notify.utils.factory.snake
import com.fankes.miui.notify.wrapper.BuildConfigWrapper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.highcapable.anip.sdk.Anip
import com.highcapable.anip.sdk.config.AnipConfig
import com.highcapable.anip.sdk.config.RemoteSource
import com.highcapable.anip.sdk.entity.NotificationIconSnapshot
import com.highcapable.anip.sdk.type.SystemVariant
import com.highcapable.betterandroid.ui.extension.component.launch
import com.highcapable.betterandroid.ui.extension.view.textToString
import com.highcapable.kavaref.extension.classOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ANIP 通知图标资源管理类
 *
 * 模块与 SystemUI 进程会各自创建实例和私有缓存，快照仅在当前进程内复用
 */
object IconRuleManagerTool {

    /** ANIP 仓库地址 */
    const val ANIP_REPOSITORY_URL = "https://github.com/BetterAndroid/android-notification-icon-project"

    /** 请求适配 ANIP 图标的文档地址 */
    const val RULES_FEEDBACK_URL = "https://betterandroid.github.io/android-notification-icon-project/zh-cn/contribute/request"

    /** 参与贡献 ANIP 图标的文档地址 */
    const val RULES_CONTRIBUTING_URL = "https://betterandroid.github.io/android-notification-icon-project/zh-cn/contribute/submit"

    /** 当前进程已发布的 ANIP 内存快照 */
    @Volatile
    var snapshot: NotificationIconSnapshot? = null
        private set

    private const val NOTIFY_CHANNEL = "notifyRuleUpdateId"
    private const val NOTIFY_COLOR = 0xFFE06818.toInt()

    private const val GITHUB_PROXY_1_URL = "https://cdn.gh-proxy.org"
    private const val GITHUB_PROXY_2_URL = "https://ghfast.top"

    private val githubProxy1UrlResolver = RemoteSource.UrlResolver { sourceUrl, _, _ -> "$GITHUB_PROXY_1_URL/$sourceUrl" }
    private val githubProxy2UrlResolver = RemoteSource.UrlResolver { sourceUrl, _, _ -> "$GITHUB_PROXY_2_URL/$sourceUrl" }

    @Volatile
    private var anip: Anip? = null

    /**
     * 从当前进程的私有缓存恢复 ANIP 快照
     * @param context 当前进程上下文
     * @return 是否恢复了非空快照
     */
    suspend fun reload(context: Context): Boolean {
        val currentAnip = obtainAnip(context)
        if (currentAnip.reload().not()) return false
        return publishSnapshot(currentAnip)
    }

    /**
     * 获取最新 ANIP 资源并原子替换当前进程快照
     *
     * 获取失败或新快照为空时保留上一份可用快照
     * @param context 当前进程上下文
     * @return SDK 获取结果
     */
    suspend fun fetch(context: Context): Anip.FetchResult {
        val currentAnip = obtainAnip(context)
        val result = currentAnip.fetch()
        if (result.isOk && publishSnapshot(currentAnip).not())
            return Anip.FetchResult("ANIP 资源中没有可用的通知图标", Anip.FetchResult.Status.FAILED)
        return result
    }

    /**
     * 当前进程是否存在已成功获取的 ANIP 缓存
     * @param context 当前进程上下文
     */
    fun hasCachedResources(context: Context) = obtainAnip(context).timestamp > 0L

    /**
     * 显示同步来源并手动获取 ANIP 资源
     * @param context 当前页面上下文
     * @param callback 成功后回调
     */
    fun syncByHand(context: Context, callback: () -> Unit) {
        val binding = DiaSourceFromBinding.inflate(LayoutInflater.from(context))
        var sourceType = ConfigData.iconRuleSourceSyncType.normalizedSourceType
        var isSyncRequested = false
        binding.sourceRadio0.isChecked = sourceType == IconRuleSourceSyncType.GITHUB_PROXY_1
        binding.sourceRadio1.isChecked = sourceType == IconRuleSourceSyncType.GITHUB_PROXY_2
        binding.sourceRadio2.isChecked = sourceType == IconRuleSourceSyncType.GITHUB_DIRECT
        binding.sourceRadio0.setOnClickListener { sourceType = IconRuleSourceSyncType.GITHUB_PROXY_1 }
        binding.sourceRadio1.setOnClickListener { sourceType = IconRuleSourceSyncType.GITHUB_PROXY_2 }
        binding.sourceRadio2.setOnClickListener { sourceType = IconRuleSourceSyncType.GITHUB_DIRECT }
        binding.sourceRepositoryEdit.apply {
            setText(ConfigData.iconRuleRepository)
            doAfterTextChanged { binding.sourceRepositoryTextLin.error = null }
        }
        binding.sourceRepositoryTextLin.setEndIconOnClickListener {
            binding.sourceRepositoryEdit.setText(RemoteSource.GITHUB_OFFICIAL_REPO_SLUG)
        }
        val dialog = MaterialAlertDialogBuilder(context)
            .setTitle("同步列表")
            .setView(binding.root)
            .setPositiveButton("确定", null)
            .setNegativeButton("取消", null)
            .setNeutralButton("云端规则") { _, _ -> context.openBrowser(ANIP_REPOSITORY_URL) }
            .create()
        dialog.setOnDismissListener {
            if (isSyncRequested) sync(context, sourceType, callback)
        }
        dialog.setOnShowListener {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val repository = binding.sourceRepositoryEdit.textToString().trim()
                if (repository.isEmpty()) {
                    binding.sourceRepositoryTextLin.error = "请填写目标存储库"
                    return@setOnClickListener
                }
                if (runCatching { createSource(repository, sourceType) }.isFailure) {
                    binding.sourceRepositoryTextLin.error = "存储库格式应为 owner/repository"
                    return@setOnClickListener
                }
                ConfigData.iconRuleSourceSyncType = sourceType
                ConfigData.iconRuleRepository = repository
                isSyncRequested = true
                dialog.dismiss()
            }
            binding.sourceRepositoryEdit.post {
                binding.sourceRepositoryEdit.selectAll()
                binding.sourceRepositoryEdit.requestFocus()
            }
        }
        dialog.show()
    }

    /**
     * 根据已选来源获取 ANIP 资源
     * @param context 当前上下文
     * @param sourceType 同步来源
     * @param callback 成功后回调
     */
    fun sync(
        context: Context,
        sourceType: Int = ConfigData.iconRuleSourceSyncType,
        callback: () -> Unit
    ) {
        ConfigData.iconRuleSourceSyncType = sourceType.normalizedSourceType
        if (context is AppCompatActivity)
            context.showDialog refreshingDialog@{
                title = "同步中"
                progressContent = "正在获取 ANIP 图标资源"
                noCancelable()
                context.launch {
                    val result = runCatching { fetch(context) }.getOrElse {
                        Anip.FetchResult(it.message ?: "获取 ANIP 资源失败", Anip.FetchResult.Status.FAILED)
                    }
                    this@refreshingDialog.cancel()
                    handleFetchResult(context, result, callback)
                }
            }
        else CoroutineScope(Dispatchers.Main.immediate).launch {
            val result = runCatching { fetch(context) }.getOrElse {
                Anip.FetchResult(it.message ?: "获取 ANIP 资源失败", Anip.FetchResult.Status.FAILED)
            }
            handleFetchResult(context, result, callback)
        }
    }

    private val Int.normalizedSourceType
        get() = when (this) {
            IconRuleSourceSyncType.GITHUB_PROXY_2 -> IconRuleSourceSyncType.GITHUB_PROXY_2
            IconRuleSourceSyncType.GITHUB_DIRECT -> IconRuleSourceSyncType.GITHUB_DIRECT
            else -> IconRuleSourceSyncType.GITHUB_PROXY_1
        }

    private val Int.urlResolver
        get() = when (normalizedSourceType) {
            IconRuleSourceSyncType.GITHUB_PROXY_1 -> githubProxy1UrlResolver
            IconRuleSourceSyncType.GITHUB_PROXY_2 -> githubProxy2UrlResolver
            else -> null
        }

    private fun obtainAnip(context: Context): Anip {
        val sourceType = ConfigData.iconRuleSourceSyncType.normalizedSourceType
        val source = runCatching { createSource(ConfigData.iconRuleRepository, sourceType) }.getOrElse {
            if (context.packageName == BuildConfigWrapper.APPLICATION_ID)
                ConfigData.iconRuleRepository = RemoteSource.GITHUB_OFFICIAL_REPO_SLUG
            createSource(RemoteSource.GITHUB_OFFICIAL_REPO_SLUG, sourceType)
        }
        anip?.let {
            it.config.source = source
            return it
        }
        return synchronized(this) {
            anip?.apply { config.source = source } ?: Anip(
                context.applicationContext,
                AnipConfig(
                    systemVariant = SystemVariant.MIOS,
                    source = source
                )
            ).also { anip = it }
        }
    }

    private fun createSource(repository: String, sourceType: Int) = RemoteSource.GitHub(
        repository = repository,
        urlResolver = sourceType.urlResolver
    )

    private suspend fun publishSnapshot(anip: Anip): Boolean {
        val nextSnapshot = anip.createSnapshot()
        if (nextSnapshot.icons.isEmpty()) return false
        snapshot = nextSnapshot
        return true
    }

    private fun handleFetchResult(context: Context, result: Anip.FetchResult, callback: () -> Unit) {
        if (result.isOk && snapshot?.icons.isNullOrEmpty().not()) {
            callback()
            if (result.status == Anip.FetchResult.Status.SUCCESS) notifyRefresh(context)
            if (result.status == Anip.FetchResult.Status.UP_TO_DATE && context is AppCompatActivity)
                context.snake(msg = "通知图标优化适配名单已是最新")
            return
        }
        if (context is AppCompatActivity)
            context.showDialog {
                title = "同步失败"
                msg = result.message
                confirmButton(text = "再试一次") { syncByHand(context, callback) }
                cancelButton()
            }
        else pushNotify(context, title = "同步失败", msg = result.message, isRetry = true)
    }

    private fun notifyRefresh(context: Context) {
        if (context !is AppCompatActivity)
            pushNotify(context, title = "同步完成", msg = "通知图标优化适配名单已更新，点击查看")
        SystemUITool.refreshSystemUI(context, isRefreshIconData = true) {
            if (context is AppCompatActivity) context.snake(msg = "通知图标优化适配名单已更新")
        }
    }

    private fun pushNotify(context: Context, title: String, msg: String, isRetry: Boolean = false) {
        if (context is AppCompatActivity) return
        context.getSystemService<NotificationManager>()?.apply {
            createNotificationChannel(
                NotificationChannel(
                    NOTIFY_CHANNEL,
                    "通知图标优化适配名单",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
            notify(0, NotificationCompat.Builder(context, NOTIFY_CHANNEL).apply {
                setContentTitle(title)
                setContentText(msg)
                color = NOTIFY_COLOR
                setAutoCancel(true)
                setSmallIcon(R.drawable.ic_nf_icon_update)
                setSound(null)
                setDefaults(NotificationCompat.DEFAULT_ALL)
                setContentIntent(
                    PendingIntent.getActivity(
                        context,
                        msg.hashCode(),
                        Intent(context, classOf<ConfigureActivity>()).apply {
                            if (isRetry) putExtra("isDirectUpdate", true)
                            else putExtra("isShowUpdDialog", false)
                        },
                        if (Build.VERSION.SDK_INT < 31) PendingIntent.FLAG_UPDATE_CURRENT
                        else PendingIntent.FLAG_IMMUTABLE
                    )
                )
            }.build())
        }
    }
}