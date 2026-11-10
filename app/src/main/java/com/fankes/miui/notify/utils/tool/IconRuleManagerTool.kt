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

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.text.InputType
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.IconCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.fankes.miui.notify.R
import com.fankes.miui.notify.const.AdditionalSourceTemplate
import com.fankes.miui.notify.const.IconRuleSourceSyncType
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.databinding.DiaSourceFromBinding
import com.fankes.miui.notify.ui.activity.ConfigureActivity
import com.fankes.miui.notify.ui.window.DropdownMenu
import com.fankes.miui.notify.utils.factory.appNameOf
import com.fankes.miui.notify.utils.factory.showDialog
import com.fankes.miui.notify.utils.factory.snake
import com.fankes.miui.notify.wrapper.BuildConfigWrapper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.highcapable.anip.sdk.Anip
import com.highcapable.anip.sdk.config.AnipConfig
import com.highcapable.anip.sdk.config.RemoteSource
import com.highcapable.anip.sdk.entity.NotificationIcon
import com.highcapable.anip.sdk.entity.NotificationIconSnapshot
import com.highcapable.anip.sdk.type.SystemVariant
import com.highcapable.betterandroid.system.extension.utils.AndroidVersion
import com.highcapable.betterandroid.ui.component.notification.factory.NotificationChannel
import com.highcapable.betterandroid.ui.component.notification.factory.createNotification
import com.highcapable.betterandroid.ui.component.notification.factory.notificationManager
import com.highcapable.betterandroid.ui.component.notification.type.NotificationImportance
import com.highcapable.betterandroid.ui.extension.component.launch
import com.highcapable.betterandroid.ui.extension.view.layoutInflater
import com.highcapable.betterandroid.ui.extension.view.textToString
import com.highcapable.kavaref.extension.classOf
import com.highcapable.yukihookapi.YukiHook
import com.highcapable.yukihookapi.hook.xposed.file.YukiHookSharedFiles
import com.highcapable.yukihookapi.runtime.YukiHookRuntime
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.File

/**
 * ANIP 通知图标资源管理类
 *
 * 模块与 SystemUI 进程会各自创建实例，缓存保存在模块共享文件中，由模块负责获取与写入，SystemUI 只读
 *
 * legacy 运行时没有模块共享文件，回退为各进程使用私有缓存
 *
 * 快照仅在当前进程内复用
 */
object IconRuleManagerTool {

    /** ANIP 仓库地址 */
    const val ANIP_REPOSITORY_URL = "https://github.com/BetterAndroid/android-notification-icon-project"

    /** 取消旧版宿主内的后台同步 */
    const val ACTION_CANCEL_SYNC = "${BuildConfigWrapper.APPLICATION_ID}.CANCEL_ICON_RULE_SYNC"

    private const val SYNC_NOTIFY_CHANNEL = "iconRuleUpdatingId"

    /** 更新通知携带的仓库清单地址键 */
    const val EXTRA_ICON_RULE_SOURCE = "iconRuleSource"

    /** 更新通知携带的资源版本键 */
    const val EXTRA_ICON_RULE_VERSION = "iconRuleVersion"

    /** 两个来源成功加载后的快照，统一发布供页面和宿主读取 */
    private data class RuleState(
        val main: NotificationIconSnapshot,
        val extra: NotificationIconSnapshot?,
        val version: Pair<String, Long>
    )

    @Volatile
    private var state: RuleState? = null

    /** 当前两源的来源地址与 SDK 资源版本 */
    val snapshotVersion get() = state?.version

    private val operationMutex = Mutex()
    private const val BUNDLE_FILE_NAME = "anip-bundle"
    private const val EXTRA_BUNDLE_FILE_NAME = "anip-bundle-extra"

    private var mainAnip: Anip? = null
    private var extraAnip: Anip? = null

    private const val NOTIFY_CHANNEL = "notifyRuleUpdateId"
    private const val NOTIFY_COLOR = 0xFFE06818.toInt()

    private const val GITHUB_PROXY_1_URL = "https://cdn.gh-proxy.org"
    private const val GITHUB_PROXY_2_URL = "https://ghfast.top"

    private val githubProxy1UrlResolver = RemoteSource.UrlResolver { sourceUrl, _, _ -> "$GITHUB_PROXY_1_URL/$sourceUrl" }
    private val githubProxy2UrlResolver = RemoteSource.UrlResolver { sourceUrl, _, _ -> "$GITHUB_PROXY_2_URL/$sourceUrl" }

    /** SystemUI 绑定的共享文件，模块进程中为空 */
    @Volatile
    private var hostSharedFiles: YukiHookSharedFiles? = null

    /**
     * 在 SystemUI 中绑定只读的模块共享文件，需要在首次装载资源之前调用
     * @param sharedFiles 宿主中的共享文件实例
     */
    fun attachHost(sharedFiles: YukiHookSharedFiles) {
        hostSharedFiles = sharedFiles
    }

    /** 宿主任务结束后释放当前模块实例的规则快照和 SDK 引用，不删除磁盘缓存 */
    fun release() {
        state = null
        mainAnip = null
        extraAnip = null
        hostSharedFiles = null
    }

    /**
     * 从当前进程的私有缓存恢复 ANIP 快照
     * @param context 当前进程上下文
     * @return 是否恢复了非空快照
     */
    suspend fun reload(context: Context): Boolean = operationMutex.withLock {
        val main = obtainMainAnip(context)
        val extra = if (ConfigData.isEnableAdditionalSource) obtainExtraAnip(context) else null
        if (extra != null && !extra.reload()) return@withLock false
        if (!main.reload()) return@withLock false
        val next = createRuleState(main, extra) ?: return@withLock false
        state = next
        true
    }

    /**
     * 先同步附加来源，成功后同步主来源；两者成功后统一发布快照
     * 缓存的读取、校验和写入均交给各自的 SDK 实例
     * @param context 当前上下文
     * @param resources 用于读取同步提示的资源
     */
    suspend fun fetch(context: Context, resources: Resources = context.resources): Anip.FetchResult = operationMutex.withLock {
        val extra = if (ConfigData.isEnableAdditionalSource) obtainExtraAnip(context) else null
        val extraResult = extra?.fetch()
        if (extraResult != null && !extraResult.isOk) return@withLock extraResult
        val main = obtainMainAnip(context)
        val mainResult = main.fetch()
        if (!mainResult.isOk) return@withLock mainResult
        val next = createRuleState(main, extra)
            ?: return@withLock Anip.FetchResult(resources.getString(R.string.anip_no_icons), Anip.FetchResult.Status.FAILED)
        val changed = mainResult.status == Anip.FetchResult.Status.SUCCESS ||
            extraResult?.status == Anip.FetchResult.Status.SUCCESS || state?.version != next.version
        if (changed) state = next
        // 记录模块本地名单的成功同步，已是最新也计入；legacy 宿主使用自己的独立缓存
        if (context.packageName == BuildConfigWrapper.APPLICATION_ID) ConfigData.lastIconRuleSyncTime = System.currentTimeMillis()
        Anip.FetchResult(mainResult.message, if (changed) Anip.FetchResult.Status.SUCCESS else Anip.FetchResult.Status.UP_TO_DATE)
    }

    private suspend fun createRuleState(main: Anip, extra: Anip?): RuleState? {
        val mainSnapshot = main.createSnapshot()
        if (mainSnapshot.icons.isEmpty()) return null
        val extraSnapshot = extra?.createSnapshot()
        if (extraSnapshot != null && extraSnapshot.icons.isEmpty()) return null
        return RuleState(mainSnapshot, extraSnapshot, resourceVersion(main, extra))
    }

    private suspend fun resourceVersion(main: Anip, extra: Anip?) = withContext(Dispatchers.IO) {
        val mainVersion = main.timestamp
        val extraVersion = extra?.timestamp ?: 0L
        val identity = "${main.config.source.manifestUrl}@$mainVersion|${extra?.config?.source?.manifestUrl.orEmpty()}@$extraVersion"
        identity to maxOf(mainVersion, extraVersion)
    }

    /**
     * 判断当前快照是否已包含指定仓库的资源版本
     * @param version 仓库清单地址与资源版本
     */
    fun hasSnapshotVersion(version: Pair<String, Long>) = snapshotVersion?.let {
        version.first == it.first && version.second > 0L && it.second >= version.second
    } == true

    /**
     * 当前进程是否存在已成功获取的 ANIP 缓存
     * @param context 当前进程上下文
     */
    fun hasCachedResources(context: Context) = obtainMainAnip(context).timestamp > 0L

    /** 判断缓存中的两源版本是否与通知一致 */
    suspend fun isCachedVersionBehind(context: Context, version: Pair<String, Long>): Boolean {
        val main = obtainMainAnip(context)
        val extra = if (ConfigData.isEnableAdditionalSource) obtainExtraAnip(context) else null
        return resourceVersion(main, extra) != version
    }

    /** 按包名查找，附加来源优先 */
    fun getIcon(packageName: String) = state?.let { it.extra?.getIcon(packageName) ?: it.main.getIcon(packageName) }

    /** 判断图标是否来自当前已生效的附加来源 */
    fun isAdditionalIcon(packageName: String) = state?.extra?.getIcon(packageName) != null

    /** 按包名读取对应来源的位图 */
    fun getBitmap(packageName: String) = state?.let { it.extra?.getBitmap(packageName) ?: it.main.getBitmap(packageName) }

    /** 读取图标位图 */
    fun getBitmap(icon: NotificationIcon) = getBitmap(icon.packageName)

    /** 按包名合并，附加来源覆盖主来源 */
    val icons: List<NotificationIcon>
        get() = state?.let { current ->
            val merged = current.main.icons.associateByTo(linkedMapOf()) { it.packageName }
            current.extra?.icons?.forEach { merged[it.packageName] = it }
            merged.values.toList()
        }.orEmpty()

    /**
     * 显示旧版宿主内的后台同步通知，取消操作交给宿主接收
     * @param context 发布通知的宿主上下文
     * @param resources 模块资源
     */
    fun notifySyncing(context: Context, resources: Resources) {
        @Suppress("MissingPermission")
        context.createNotification(
            channel = NotificationChannel(SYNC_NOTIFY_CHANNEL, importance = NotificationImportance.LOW) {
                name = resources.getString(R.string.sync_service_channel)
            }
        ) {
            @Suppress("RestrictedApi")
            smallIcon = IconCompat.createWithResource(resources, BuildConfigWrapper.APPLICATION_ID, R.drawable.ic_nf_icon_update)
            contentTitle = resources.getString(R.string.sync_syncing)
            contentText = resources.getString(R.string.sync_fetching_anip)
            isOngoing = true
            addAction(
                icon = 0,
                title = resources.getString(R.string.common_cancel),
                intent = PendingIntent.getBroadcast(
                    context, 0, Intent(ACTION_CANCEL_SYNC).setPackage(context.packageName),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
        }.post(id = 0, tag = SYNC_NOTIFY_CHANNEL)
    }

    /**
     * 移除旧版宿主内的后台同步通知
     * @param context 发布通知的宿主上下文
     */
    fun dismissSyncing(context: Context) = context.notificationManager.cancel(SYNC_NOTIFY_CHANNEL, 0)

    /**
     * 发送后台同步结果提醒；资源已是最新时不发送通知
     * @param context 当前进程上下文
     * @param result 获取结果
     * @param resources 用于读取文案的模块资源，SystemUI 中需要传入模块资源 - 默认：当前上下文资源
     */
    fun notifyFetchResult(context: Context, result: Anip.FetchResult, resources: Resources = context.resources) {
        when (result.status) {
            Anip.FetchResult.Status.SUCCESS ->
                pushNotify(context, resources, title = resources.getString(R.string.sync_done), msg = resources.getString(R.string.sync_done_msg))
            Anip.FetchResult.Status.FAILED -> pushNotify(context, resources, title = resources.getString(R.string.sync_failed), msg = result.message, isRetry = true)
            Anip.FetchResult.Status.UP_TO_DATE -> Unit
        }
    }

    /**
     * 显示同步设置对话框，仅保存设置，不主动触发同步
     * @param context 当前页面上下文
     * @param syncOnConfirm 是否在确定保存设置后触发一次同步 - 仅首次配置时使用
     * @param onSync 触发同步的回调
     */
    fun showSyncSettings(context: Context, syncOnConfirm: Boolean = false, onSync: () -> Unit = {}) {
        val binding = DiaSourceFromBinding.inflate(context.layoutInflater)
        var sourceType = ConfigData.iconRuleSourceSyncType.normalizedSourceType
        var isAdditionalEnabled = ConfigData.isEnableAdditionalSource
        var template = ConfigData.additionalSourceTemplate
        val sourceValues = AdditionalSourceTemplate.entries.associateWith { ConfigData.getAdditionalSourceValue(it) }.toMutableMap()
        var isSyncRequested = false

        val sourceTypeLabels = mapOf(
            IconRuleSourceSyncType.GITHUB_PROXY_1 to R.string.sync_source_cdn_1,
            IconRuleSourceSyncType.GITHUB_PROXY_2 to R.string.sync_source_cdn_2,
            IconRuleSourceSyncType.GITHUB_DIRECT to R.string.sync_source_github
        )
        val templateLabels = mapOf(
            AdditionalSourceTemplate.GITHUB to R.string.sync_additional_github,
            AdditionalSourceTemplate.GITLAB to R.string.sync_additional_gitlab,
            AdditionalSourceTemplate.STATIC to R.string.sync_additional_static,
            AdditionalSourceTemplate.CUSTOM to R.string.sync_additional_custom
        )

        fun updateAdditionalValueHint() {
            binding.additionalSourceValueLin.hint = context.getString(template.hintResource())
        }
        fun showSourceTypeMenu() = DropdownMenu(
            context = context,
            items = sourceTypeLabels.map { it.value to it.key },
            selectedItem = sourceType,
            widthMatchedToAnchor = true
        ) {
            sourceType = it
            binding.sourceTypeEdit.setText(context.getString(sourceTypeLabels.getValue(it)))
        }.show(binding.sourceTypeEdit)

        fun showTemplateMenu() = DropdownMenu(
            context = context,
            items = templateLabels.map { it.value to it.key },
            selectedItem = template,
            widthMatchedToAnchor = true
        ) {
            sourceValues[template] = binding.additionalSourceValueEdit.textToString()
            template = it
            binding.additionalSourceValueEdit.setText(sourceValues.getValue(template))
            binding.additionalTemplateEdit.setText(context.getString(templateLabels.getValue(it)))
            binding.additionalSourceValueLin.error = null
            updateAdditionalValueHint()
        }.show(binding.additionalTemplateEdit)

        fun setupDropdown(edit: TextInputEditText, layout: TextInputLayout, label: String, onClick: () -> Unit) {
            fun showMenu() {
                edit.requestFocus()
                edit.post { onClick() }
            }
            edit.apply {
                inputType = InputType.TYPE_NULL
                isSingleLine = true
                setText(label)
                setOnClickListener { showMenu() }
                setOnTouchListener { view, event ->
                    if (event.actionMasked != MotionEvent.ACTION_UP) return@setOnTouchListener false
                    view.performClick()
                    true
                }
            }
            layout.setEndIconOnClickListener { showMenu() }
        }
        setupDropdown(binding.sourceTypeEdit, binding.sourceTypeLin, context.getString(sourceTypeLabels.getValue(sourceType))) { showSourceTypeMenu() }

        binding.additionalSourcePanel.isVisible = isAdditionalEnabled
        setupDropdown(binding.additionalTemplateEdit, binding.additionalTemplateLin, context.getString(templateLabels.getValue(template))) { showTemplateMenu() }

        binding.additionalSourceValueEdit.apply {
            setText(sourceValues.getValue(template))
            doAfterTextChanged { binding.additionalSourceValueLin.error = null }
        }
        updateAdditionalValueHint()
        val dialog = MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(R.string.sync_settings))
            .setView(binding.root)
            .setPositiveButton(context.getString(if (syncOnConfirm) R.string.common_ok else R.string.common_save), null)
            .setNegativeButton(context.getString(R.string.common_cancel), null)
            .setNeutralButton(context.getString(R.string.sync_additional), null)
            .create()
        dialog.setOnDismissListener {
            if (isSyncRequested) onSync()
        }
        dialog.setOnShowListener {
            dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener {
                isAdditionalEnabled = isAdditionalEnabled.not()
                binding.additionalSourcePanel.isVisible = isAdditionalEnabled
            }
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                sourceValues[template] = binding.additionalSourceValueEdit.textToString().trim()
                if (isAdditionalEnabled) {
                    val value = binding.additionalSourceValueEdit.textToString().trim()
                    if (runCatching { createAdditionalSource(template, value) }.isFailure) {
                        binding.additionalSourceValueLin.error = context.getString(R.string.sync_additional_invalid)
                        return@setOnClickListener
                    }
                }
                ConfigData.iconRuleSourceSyncType = sourceType
                ConfigData.additionalSourceTemplate = template
                sourceValues.forEach { (sourceTemplate, value) -> ConfigData.putAdditionalSourceValue(sourceTemplate, value.trim()) }
                ConfigData.isEnableAdditionalSource = isAdditionalEnabled
                isSyncRequested = syncOnConfirm
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    /**
     * 根据已选来源获取 ANIP 资源
     * @param context 当前上下文
     * @param sourceType 同步来源
     * @param isAuto 是否自动触发 - 自动触发时仅在存在更新时提示
     * @param onSyncStateChange 同步状态变化回调 - 用于转圈提示
     * @param callback 成功后回调
     */
    fun sync(
        context: AppCompatActivity,
        sourceType: Int = ConfigData.iconRuleSourceSyncType,
        isAuto: Boolean = false,
        onSyncStateChange: ((Boolean) -> Unit)? = null,
        callback: () -> Unit = {}
    ) {
        ConfigData.iconRuleSourceSyncType = sourceType.normalizedSourceType
        onSyncStateChange?.invoke(true)
        context.launch {
            val result = runCatching { fetch(context) }.getOrElse {
                if (it is CancellationException) throw it
                Anip.FetchResult(it.message ?: context.getString(R.string.anip_fetch_failed), Anip.FetchResult.Status.FAILED)
            }
            onSyncStateChange?.invoke(false)
            handleFetchResult(context, result, isAuto, onSyncStateChange, callback)
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

    private fun obtainMainAnip(context: Context): Anip {
        val source = createMainSource(ConfigData.iconRuleSourceSyncType.normalizedSourceType)
        return mainAnip?.apply { config.source = source }
            ?: createAnip(context, source, BUNDLE_FILE_NAME).also { mainAnip = it }
    }

    private fun obtainExtraAnip(context: Context): Anip {
        val source = createAdditionalSource()
        return extraAnip?.apply { config.source = source }
            ?: createAnip(context, source, EXTRA_BUNDLE_FILE_NAME).also { extraAnip = it }
    }

    private fun createAnip(context: Context, source: RemoteSource, fileName: String): Anip {
        val config = if (YukiHook.Status.runtime == YukiHookRuntime.LIBXPOSED)
            AnipConfig(
                systemVariant = SystemVariant.MIOS,
                source = source,
                cacheDescriptor = hostSharedFiles?.let { files ->
                    AnipConfig.CacheDescriptor(isReadOnly = true) { files.open(fileName) }
                } ?: AnipConfig.CacheDescriptor { YukiHook.Module.sharedFiles.open(fileName) }
            )
        else AnipConfig(
            systemVariant = SystemVariant.MIOS,
            source = source,
            // SDK 默认缓存文件同名，附加实例使用独立目录避免覆盖主源缓存
            cacheDirectory = if (fileName == EXTRA_BUNDLE_FILE_NAME) File(context.cacheDir, EXTRA_BUNDLE_FILE_NAME) else null
        )
        return Anip(context.applicationContext, config)
    }

    private fun createMainSource(sourceType: Int) = RemoteSource.GitHub(
        repository = RemoteSource.GITHUB_OFFICIAL_REPO_SLUG,
        urlResolver = sourceType.urlResolver
    )

    private fun createAdditionalSource(
        template: AdditionalSourceTemplate = ConfigData.additionalSourceTemplate,
        value: String = ConfigData.additionalSourceValue
    ) = when (template) {
        AdditionalSourceTemplate.GITHUB -> RemoteSource.GitHub(value)
        AdditionalSourceTemplate.GITLAB -> RemoteSource.GitLab(value)
        AdditionalSourceTemplate.STATIC -> RemoteSource.Static(value)
        AdditionalSourceTemplate.CUSTOM -> RemoteSource.Custom(value)
    }

    private fun AdditionalSourceTemplate.hintResource() = when (this) {
        AdditionalSourceTemplate.GITHUB -> R.string.sync_additional_github_hint
        AdditionalSourceTemplate.GITLAB -> R.string.sync_additional_gitlab_hint
        AdditionalSourceTemplate.STATIC -> R.string.sync_additional_static_hint
        AdditionalSourceTemplate.CUSTOM -> R.string.sync_additional_custom_hint
    }

    private fun handleFetchResult(
        context: Context,
        result: Anip.FetchResult,
        isAuto: Boolean,
        onSyncStateChange: ((Boolean) -> Unit)?,
        callback: () -> Unit
    ) {
        if (context !is AppCompatActivity) notifyFetchResult(context, result)
        if (result.isOk && icons.isNotEmpty()) {
            if (result.status == Anip.FetchResult.Status.SUCCESS) {
                callback()
                notifyRefresh(context)
            } else if (result.status == Anip.FetchResult.Status.UP_TO_DATE && context is AppCompatActivity && !isAuto) {
                context.snake(msg = context.getString(R.string.sync_up_to_date))
            }
            return
        }
        if (context is AppCompatActivity && !isAuto)
            context.showDialog {
                title = context.getString(R.string.sync_failed)
                msg = result.message
                confirmButton(text = context.getString(R.string.sync_retry)) {
                    sync(context, isAuto = isAuto, onSyncStateChange = onSyncStateChange, callback = callback)
                }
                cancelButton()
            }
    }

    private fun notifyRefresh(context: Context) {
        SystemUITool.refreshSystemUI(context, isRefreshIconData = true) {
            if (context is AppCompatActivity) context.snake(msg = context.getString(R.string.sync_updated))
        }
    }

    private fun pushNotify(context: Context, resources: Resources, title: String, msg: String, isRetry: Boolean = false) {
        if (context is AppCompatActivity) return
        @Suppress("MissingPermission")
        context.createNotification(
            channel = NotificationChannel(NOTIFY_CHANNEL) {
                name = resources.getString(R.string.sync_notify_channel)
            }
        ) {
            subText = context.appNameOf(BuildConfigWrapper.APPLICATION_ID)
            contentTitle = title
            contentText = msg
            color = NOTIFY_COLOR
            isAutoCancel = true
            @Suppress("RestrictedApi")
            smallIcon = IconCompat.createWithResource(resources, BuildConfigWrapper.APPLICATION_ID, R.drawable.ic_nf_icon_update)
            contentIntent = PendingIntent.getActivity(
                context,
                msg.hashCode(),
                Intent().apply {
                    component = ComponentName(BuildConfigWrapper.APPLICATION_ID, classOf<ConfigureActivity>().name)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    if (isRetry) putExtra("isDirectUpdate", true)
                    else {
                        putExtra("isShowUpdDialog", false)
                        snapshotVersion?.let { version ->
                            putExtra(EXTRA_ICON_RULE_SOURCE, version.first)
                            putExtra(EXTRA_ICON_RULE_VERSION, version.second)
                        }
                    }
                },
                PendingIntent.FLAG_UPDATE_CURRENT or
                    if (AndroidVersion.isLessThan(AndroidVersion.S)) 0 else PendingIntent.FLAG_IMMUTABLE
            )
        }.post(id = 0, tag = NOTIFY_CHANNEL)
    }
}