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
 * This file is created by fankes on 2022/1/30.
 */
@file:Suppress("InflateParams")

package com.fankes.miui.notify.ui.activity

import android.graphics.Bitmap
import android.graphics.Typeface
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import com.fankes.miui.notify.R
import com.fankes.miui.notify.bean.InstalledAppBean
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.data.factory.bind
import com.fankes.miui.notify.databinding.ActivityConfigBinding
import com.fankes.miui.notify.databinding.AdapterConfigBinding
import com.fankes.miui.notify.databinding.AdapterUncoveredAppBinding
import com.fankes.miui.notify.databinding.DiaIconFilterBinding
import com.fankes.miui.notify.databinding.DiaOptionsBinding
import com.fankes.miui.notify.databinding.DiaUncoveredAppsBinding
import com.fankes.miui.notify.params.factory.isAppNotifyEnabledOf
import com.fankes.miui.notify.params.factory.isAppNotifyOverlayOf
import com.fankes.miui.notify.params.factory.putAppNotifyEnabledOf
import com.fankes.miui.notify.params.factory.putAppNotifyOverlayOf
import com.fankes.miui.notify.params.factory.resetAppNotifyOverlayRules
import com.fankes.miui.notify.ui.activity.base.BaseActivity
import com.fankes.miui.notify.utils.factory.addOnBackPressedEvent
import com.fankes.miui.notify.utils.factory.callOnBackPressed
import com.fankes.miui.notify.utils.factory.copyToClipboard
import com.fankes.miui.notify.utils.factory.openBrowser
import com.fankes.miui.notify.utils.factory.showDialog
import com.fankes.miui.notify.utils.factory.showTimePicker
import com.fankes.miui.notify.utils.factory.snake
import com.fankes.miui.notify.utils.tool.IconRuleManagerTool
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.google.android.material.appbar.AppBarLayout
import com.highcapable.anip.sdk.entity.NotificationIcon
import com.highcapable.betterandroid.ui.component.adapter.factory.bindAdapter
import com.highcapable.betterandroid.ui.component.adapter.recycler.cosmetic.RecyclerCosmetic
import com.highcapable.betterandroid.ui.component.adapter.recycler.factory.scrollToFirstPosition
import com.highcapable.betterandroid.ui.component.adapter.recycler.factory.smoothScrollToFirstPosition
import com.highcapable.betterandroid.ui.extension.component.base.getColorCompat
import com.highcapable.betterandroid.ui.extension.component.launch
import com.highcapable.betterandroid.ui.extension.component.runDelayed
import com.highcapable.betterandroid.ui.extension.component.startActivity
import com.highcapable.betterandroid.ui.extension.view.textColor
import com.highcapable.betterandroid.ui.extension.view.textToString
import com.highcapable.betterandroid.ui.extension.view.toast
import com.highcapable.yukihookapi.YukiHook
import com.highcapable.yukihookapi.runtime.YukiHookRuntime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class ConfigureActivity : BaseActivity<ActivityConfigBinding>() {

    /** 图标及当前本地开关状态，用于比较列表项变化 */
    private data class IconItem(val icon: NotificationIcon, val enabled: Boolean, val overlay: Boolean)

    /** 名单的查看范围 */
    private enum class ViewMode { ALL, INSTALLED, DISABLED, OVERRIDDEN }

    /** 当前查看范围 */
    private var viewMode = ViewMode.ALL

    /** 已安装的应用 - 系统界面没有返回时为空 */
    private var installedApps = emptyList<InstalledAppBean>()

    /** 查看范围按钮 */
    private val viewPills get() = mapOf(
        binding.configViewAll to ViewMode.ALL,
        binding.configViewInstalled to ViewMode.INSTALLED,
        binding.configViewDisabled to ViewMode.DISABLED,
        binding.configViewOverridden to ViewMode.OVERRIDDEN
    )

    /** 当前筛选条件 */
    private var filterText = ""

    /** 回调适配器改变 */
    private var onChanged: ((List<IconItem>, Runnable?) -> Unit)? = null

    /** 回调滚动事件改变 */
    private var onScrollEvent: ((Boolean) -> Unit)? = null

    /** 全部的通知图标优化数据 */
    private var iconAllDatas = emptyList<NotificationIcon>()

    /** 当前名单状态加载任务 */
    private var iconLoadJob: Job? = null

    /** 首次名单加载完成前不显示空内容提示 */
    private var isLoadingIcons = true

    /** 当前名单的开关状态，切换时只更新对应应用，避免全量重读偏好 */
    private var iconItems = mutableMapOf<String, IconItem>()

    override fun onCreate() {
        if (YukiHook.Status.runtime == YukiHookRuntime.LIBXPOSED) {
            var unavailableJob: Job? = null
            YukiHook.Status.observeFrameworkService(lifecycle) { available ->
                // 第一次回调可能是 false，稍后连上会再回调 true，所以 false 先等一会
                unavailableJob?.cancel()
                if (available) onCreate(available = true)
                else unavailableJob = runDelayed(500) { onCreate(available = false) }
            }
        } else onCreate(available = true)
    }

    private fun onCreate(available: Boolean) {
        if (!available || !YukiHook.Status.isModuleActive) {
            binding.configTitleCountText.text = getString(R.string.config_module_unavailable)
            showDialog {
                title = getString(R.string.config_module_unavailable)
                msg = getString(R.string.config_module_unavailable_msg)
                confirmButton(text = getString(R.string.common_got_it)) { finish() }
                noCancelable()
            }
        } else continueOnCreate()
    }

    private fun continueOnCreate() {
        // 开启优化时允许直接拖动 AppBar，关闭时锁定展开状态
        (binding.configAppBar.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.Behavior().apply {
            setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
                override fun canDrag(appBarLayout: AppBarLayout) = binding.notifyIconFixSwitch.isChecked
            })
        }
        // 返回按钮点击事件
        binding.titleBackIcon.setOnClickListener { callOnBackPressed() }
        // 通知图标优化开关，关闭后隐藏名单相关内容
        binding.notifyIconFixSwitch.bind(ConfigData.ENABLE_NOTIFY_ICON_FIX) {
            onInitialize {
                binding.configTip.layoutParams = (binding.configTip.layoutParams as AppBarLayout.LayoutParams).apply {
                    scrollFlags = if (it) AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL else 0
                }
                if (!it) {
                    binding.configAppBar.setExpanded(true, false)
                    binding.configListView.scrollToFirstPosition()
                }
                binding.configContent.isVisible = it
                binding.configBottomBar.isVisible = it
                binding.configListControls.isVisible = it
                binding.configDisabledView.isVisible = it.not()
                binding.configTitleSync.isVisible = it
                binding.configTitleOptions.isVisible = it
                updateRestoreButton()
            }
            onChanged {
                reinitialize()
                SystemUITool.refreshSystemUI(context = this@ConfigureActivity)
            }
        }
        // 刷新适配器结果相关
        refreshAdapterResult()
        // 设置上下按钮点击事件
        binding.configTitleUp.setOnClickListener {
            snake(msg = getString(R.string.config_scroll_top))
            onScrollEvent?.invoke(false)
        }
        binding.configTitleDown.setOnClickListener {
            snake(msg = getString(R.string.config_scroll_bottom))
            onScrollEvent?.invoke(true)
        }
        // 设置过滤按钮点击事件
        binding.configTitleFilter.setOnClickListener {
            showDialog<DiaIconFilterBinding> {
                title = getString(R.string.config_filter)
                binding.iconFiltersEdit.apply {
                    requestFocus()
                    invalidate()
                    if (filterText.isNotBlank()) {
                        setText(filterText)
                        setSelection(filterText.length)
                    }
                }
                confirmButton {
                    if (binding.iconFiltersEdit.textToString().isNotBlank()) {
                        filterText = binding.iconFiltersEdit.textToString().trim()
                        refreshAdapterResult(isScrollToTop = true)
                    } else {
                        toast(getString(R.string.config_filter_empty))
                        it.performClick()
                    }
                }
                cancelButton()
                if (filterText.isNotBlank())
                    neutralButton(text = getString(R.string.config_filter_clear)) {
                        filterText = ""
                        refreshAdapterResult(isScrollToTop = true)
                    }
            }
        }
        binding.configTitleRestore.setOnClickListener {
            showDialog {
                title = getString(R.string.config_restore_rules)
                msg = getString(R.string.config_restore_rules_confirm)
                confirmButton {
                    resetAppNotifyOverlayRules(iconAllDatas)
                    iconItems.replaceAll { _, item -> item.copy(overlay = item.icon.overlay) }
                    refreshAdapterResult()
                    SystemUITool.refreshSystemUI(context = this@ConfigureActivity)
                }
                cancelButton()
            }
        }
        // 设置同步列表按钮点击事件
        binding.configTitleSync.setOnClickListener { onStartRefresh() }
        // 设置选项按钮点击事件
        binding.configTitleOptions.setOnClickListener { showOptions() }
        // 设置查看范围按钮点击事件
        viewPills.forEach { (pill, mode) ->
            pill.setOnClickListener {
                // 再次点击当前选项时数据不变，直接平滑滚动到顶部
                if (mode == viewMode) return@setOnClickListener binding.configListView.smoothScrollToFirstPosition()
                viewMode = mode
                refreshAdapterResult(isScrollToTop = true)
            }
        }
        // 设置列表元素和 Adapter
        binding.configListView.apply {
            bindAdapter<IconItem>(RecyclerCosmetic.fromLinearVertical(context, rowSpacing = 10.dp)) {
                onBindDiffer(
                    areItemsTheSame = { oldItem, newItem -> oldItem.icon.packageName == newItem.icon.packageName },
                    // 同一快照的图标不可变，重新装载后的新实例也需要刷新位图
                    areContentsTheSame = { oldItem, newItem ->
                        oldItem.icon === newItem.icon && oldItem.enabled == newItem.enabled && oldItem.overlay == newItem.overlay
                    }
                )
                onBindItemView<AdapterConfigBinding> { binding, item, _ ->
                    val icon = item.icon
                    binding.adpAppIcon.setImageBitmap(IconRuleManagerTool.snapshot?.getBitmap(icon))
                    (icon.color ?: resources.getColorCompat(R.color.colorTextGray)).also { color ->
                        binding.adpAppIcon.setColorFilter(color)
                        binding.adpAppName.textColor = color
                    }
                    binding.adpAppName.text = icon.label
                    binding.adpAppPkgName.text = icon.packageName
                    binding.adpCbrName.text = getString(R.string.config_contributors, icon.contributors.joinToString())
                    item.enabled.also { e ->
                        binding.adpAppOpenSwitch.isChecked = e
                        binding.adpAppAllSwitch.isEnabled = e
                        binding.adpAppOverlayRow.alpha = if (e) 1f else 0.5f
                    }
                    binding.adpAppOpenSwitch.setOnCheckedChangeListener { btn, b ->
                        if (btn.isPressed.not()) return@setOnCheckedChangeListener
                        putAppNotifyEnabledOf(icon, b)
                        iconItems[icon.packageName] = iconItems.getValue(icon.packageName).copy(enabled = b)
                        binding.adpAppAllSwitch.isEnabled = b
                        binding.adpAppOverlayRow.alpha = if (b) 1f else 0.5f
                        refreshAdapterResult()
                        SystemUITool.refreshSystemUI(context = this@ConfigureActivity)
                    }
                    val isOverlayChanged = item.overlay != icon.overlay
                    binding.adpAppOverlayText.textColor = resources.getColorCompat(
                        if (isOverlayChanged) R.color.colorRuleChanged else R.color.colorTextGray
                    )
                    binding.adpAppOverlayText.setTypeface(null, if (isOverlayChanged) Typeface.BOLD else Typeface.NORMAL)
                    binding.adpAppAllSwitch.isChecked = item.overlay
                    binding.adpAppAllSwitch.setOnCheckedChangeListener { btn, b ->
                        if (btn.isPressed.not()) return@setOnCheckedChangeListener
                        fun saveState() {
                            putAppNotifyOverlayOf(icon, b)
                            iconItems[icon.packageName] = iconItems.getValue(icon.packageName).copy(overlay = b)
                            refreshAdapterResult()
                            SystemUITool.refreshSystemUI(context = this@ConfigureActivity)
                        }
                        if (b) showDialog {
                            title = getString(R.string.config_override)
                            msg = getString(R.string.config_override_msg)
                            confirmButton { saveState() }
                            cancelButton { btn.isChecked = btn.isChecked.not() }
                            noCancelable()
                        } else saveState()
                    }
                }
            }.apply { onChanged = { icons, commitCallback -> submitList(icons, commitCallback) } }
            onScrollEvent = { toBottom ->
                post {
                    val count = adapter?.itemCount ?: 0
                    if (count > 0) {
                        binding.configAppBar.setExpanded(!toBottom, true)
                        scrollToPosition(if (toBottom) count - 1 else 0)
                    }
                }
            }
        }
        // 装载数据
        mockLocalData()
        loadInstalledApps()
        /** 更新通知对应的仓库和版本，仅在此次通知点击中使用 */
        val requestedSource = intent?.getStringExtra(IconRuleManagerTool.EXTRA_ICON_RULE_SOURCE).orEmpty()
        val requestedVersion = intent?.getLongExtra(IconRuleManagerTool.EXTRA_ICON_RULE_VERSION, 0L) ?: 0L
        // 更新数据
        when {
            intent?.getBooleanExtra("isNewAppSupport", false) == true ->
                showDialog {
                    val appName = intent?.getStringExtra("appName") ?: ""
                    val pkgName = intent?.getStringExtra("pkgName") ?: ""
                    title = getString(R.string.config_new_app_title)
                    msg = getString(R.string.config_new_app_msg, appName, pkgName)
                    confirmButton(text = getString(R.string.config_sync_list)) { onStartRefresh() }
                    cancelButton(text = getString(R.string.config_copy_name_package)) { copyToClipboard(content = "$appName($pkgName)") }
                    neutralButton(text = getString(R.string.common_cancel))
                    noCancelable()
                }
            intent?.getBooleanExtra("isDirectUpdate", false) == true -> onStartRefresh(isByHand = false)
            requestedSource.isNotBlank() && requestedVersion > 0L -> launch {
                if (IconRuleManagerTool.isCachedVersionBehind(this@ConfigureActivity, requestedSource to requestedVersion))
                    onStartRefresh(isByHand = false)
            }
            intent?.getBooleanExtra("isShowUpdDialog", true) == true && ConfigData.isEnableNotifyIconFix &&
                IconRuleManagerTool.hasCachedResources(this).not() -> onStartRefresh()
        }
        // 清除数据
        intent?.apply {
            removeExtra("isNewAppSupport")
            removeExtra("isDirectUpdate")
            removeExtra("isShowUpdDialog")
            removeExtra(IconRuleManagerTool.EXTRA_ICON_RULE_SOURCE)
            removeExtra(IconRuleManagerTool.EXTRA_ICON_RULE_VERSION)
        }
        // 设置返回监听事件
        addOnBackPressedEvent {
            if (MainActivity.isActivityLive.not())
                showDialog {
                    title = getString(R.string.common_prompt)
                    msg = getString(R.string.config_back_to_main)
                    confirmButton {
                        releaseEventAndBack()
                        startActivity<MainActivity>()
                    }
                    cancelButton { releaseEventAndBack() }
                }
            else releaseEventAndBack()
        }
    }

    /**
     * 开始同步
     * @param isByHand 是否手动同步 - 默认是
     */
    private fun onStartRefresh(isByHand: Boolean = true) {
        if (isByHand)
            IconRuleManagerTool.syncByHand(context = this) {
                filterText = ""
                mockLocalData()
            }
        else
            IconRuleManagerTool.sync(context = this) {
                filterText = ""
                mockLocalData()
            }
    }

    /** 获取系统界面中已安装的应用 */
    private fun loadInstalledApps() = SystemUITool.obtainInstalledApps(context = this) { apps ->
        if (apps.isEmpty()) return@obtainInstalledApps
        installedApps = apps
        refreshAdapterResult()
    }

    /** 显示选项对话框 */
    private fun showOptions() =
        showDialog<DiaOptionsBinding> {
            title = getString(R.string.config_options)
            // 嵌套对话框中的 binding 会指向其自身，这里先保存
            val dialogBinding = binding
            dialogBinding.notifyIconFixPlaceholderSwitch.bind(ConfigData.ENABLE_NOTIFY_ICON_FIX_PLACEHOLDER) {
                isAutoApplyChanges = false
                onChanged {
                    /** 应用更改并刷新系统界面 */
                    fun applyChangesAndRefresh() {
                        applyChanges()
                        SystemUITool.refreshSystemUI(context = this@ConfigureActivity)
                    }
                    if (it) showDialog {
                        title = getString(R.string.common_notice)
                        msg = getString(R.string.main_placeholder_icon_msg)
                        confirmButton { applyChangesAndRefresh() }
                        cancelButton { cancelChanges() }
                        noCancelable()
                    } else applyChangesAndRefresh()
                }
            }
            dialogBinding.notifyIconFixNotifySwitch.bind(ConfigData.ENABLE_NOTIFY_ICON_FIX_NOTIFY) {
                onChanged { SystemUITool.refreshSystemUI(context = this@ConfigureActivity, isRefreshCacheOnly = true) }
            }
            dialogBinding.notifyIconAutoSyncText.text = ConfigData.notifyIconFixAutoTime
            dialogBinding.notifyIconAutoSyncSwitch.bind(ConfigData.ENABLE_NOTIFY_ICON_FIX_AUTO) {
                onInitialize { dialogBinding.notifyIconAutoSyncChildItem.isVisible = it }
                onChanged {
                    reinitialize()
                    SystemUITool.refreshSystemUI(context = this@ConfigureActivity, isRefreshCacheOnly = true)
                }
            }
            dialogBinding.notifyIconAutoSyncButton.setOnClickListener {
                showTimePicker(ConfigData.notifyIconFixAutoTime) { time ->
                    showDialog {
                        title = getString(R.string.main_auto_update_title, time)
                        msg = getString(R.string.main_auto_update_msg, time)
                        confirmButton(text = getString(R.string.main_save_settings)) {
                            ConfigData.notifyIconFixAutoTime = time
                            dialogBinding.notifyIconAutoSyncText.text = time
                            SystemUITool.refreshSystemUI(context, isRefreshCacheOnly = true)
                        }
                        cancelButton()
                        noCancelable()
                    }
                }
            }
            confirmButton(text = getString(R.string.common_close))
        }

    /** 显示为 ANIP 做出贡献的对话框 */
    private fun showContributeDialog() =
        showDialog {
            title = getString(R.string.config_contribute_anip)
            msg = getString(R.string.config_contribute_anip_msg)
            confirmButton(text = getString(R.string.config_contribute)) { openBrowser(getString(R.string.anip_contribute_url)) }
            cancelButton(text = getString(R.string.config_request_adaptation)) { openBrowser(getString(R.string.anip_request_adaptation_url)) }
            neutralButton(text = getString(R.string.config_not_now))
        }

    /**
     * 显示未收录的应用对话框
     * @param apps 未收录的应用
     */
    private fun showUncoveredApps(apps: List<InstalledAppBean>) =
        showDialog<DiaUncoveredAppsBinding> {
            title = getString(R.string.config_uncovered_title, apps.size)
            // 嵌套回调中的 binding 同样需要先保存
            val dialogBinding = binding

            /** 系统界面推送回来的应用图标 */
            val appIcons = mutableMapOf<String, Bitmap>()
            dialogBinding.uncoveredListView.bindAdapter<InstalledAppBean>(RecyclerCosmetic.fromLinearVertical(context)) {
                onBindData { apps }
                onBindItemView<AdapterUncoveredAppBinding> { itemBinding, app, _ ->
                    appIcons[app.packageName]?.also {
                        itemBinding.adpUncoveredIcon.clearColorFilter()
                        itemBinding.adpUncoveredIcon.setImageBitmap(it)
                    } ?: itemBinding.adpUncoveredIcon.apply {
                        setImageResource(R.drawable.ic_unsupported)
                        setColorFilter(resources.getColorCompat(R.color.colorTextDark))
                    }
                    itemBinding.adpUncoveredName.text = app.name
                    itemBinding.adpUncoveredPkgName.text = app.packageName
                    itemBinding.root.setOnClickListener { copyToClipboard(content = "${app.name}(${app.packageName})") }
                }
            }
            SystemUITool.obtainAppIcons(context = this@ConfigureActivity, apps.map { it.packageName }) { packageName, icon ->
                appIcons[packageName] = icon
                dialogBinding.uncoveredListView.adapter?.notifyItemChanged(apps.indexOfFirst { it.packageName == packageName })
            }
            confirmButton(text = getString(R.string.config_request_adaptation)) { showContributeDialog() }
            cancelButton(text = getString(R.string.common_close))
        }

    /** 根据当前名单与云端规则的差异更新恢复按钮 */
    private fun updateRestoreButton() {
        binding.configTitleRestore.isVisible = binding.notifyIconFixSwitch.isChecked && iconItems.values.any {
            it.overlay != it.icon.overlay
        }
    }

    /** 装载或刷新本地数据 */
    private fun mockLocalData() {
        iconLoadJob?.cancel()
        isLoadingIcons = true
        refreshAdapterResult()
        iconLoadJob = launch {
            val (icons, items) = withContext(Dispatchers.IO) {
                var icons = IconRuleManagerTool.snapshot?.icons.orEmpty()
                if (icons.isEmpty() && IconRuleManagerTool.reload(this@ConfigureActivity))
                    icons = IconRuleManagerTool.snapshot?.icons.orEmpty()
                val items = icons.associate { icon ->
                    icon.packageName to IconItem(icon, isAppNotifyEnabledOf(icon), isAppNotifyOverlayOf(icon))
                }.toMutableMap()
                icons to items
            }
            iconAllDatas = icons
            iconItems = items
            isLoadingIcons = false
            refreshAdapterResult()
        }
    }

    /**
     * 刷新适配器结果相关
     * @param isScrollToTop 是否滚动到列表顶部
     */
    private fun refreshAdapterResult(isScrollToTop: Boolean = false) {
        val icons = iconDatas.toList()
        binding.configListProgress.isVisible = isLoadingIcons && iconAllDatas.isEmpty()
        updateRestoreButton()
        // 列表数据提交完成后再滚动，否则列表变化会打断滚动
        onChanged?.invoke(icons.map { iconItems.getValue(it.packageName) }, if (isScrollToTop) Runnable { binding.configListView.scrollToFirstPosition() } else null)
        binding.configTitleCountText.text =
            if (filterText.isBlank()) getString(R.string.config_included_count, icons.size)
            else getString(R.string.config_filter_result, filterText, icons.size)
        binding.configListNoDataView.apply {
            text = if (iconAllDatas.isEmpty()) getString(R.string.config_empty_no_data) else getString(R.string.config_empty_no_match)
            isVisible = !isLoadingIcons && icons.isEmpty()
        }
        // 列表为空时没有可滚动的内容
        binding.configTitleUp.isVisible = icons.isNotEmpty()
        binding.configTitleDown.isVisible = icons.isNotEmpty()
        // 没有名单数据时查看范围和数量栏没有意义
        binding.configViewPills.isVisible = iconAllDatas.isNotEmpty()
        binding.configCountBar.isVisible = iconAllDatas.isNotEmpty()
        binding.configViewInstalled.isVisible = installedApps.isNotEmpty()
        viewPills.forEach { (pill, mode) -> pill.isSelected = mode == viewMode }
        /** 已安装但名单中没有的应用，名单为空时无法判断 */
        val uncoveredApps = if (iconAllDatas.isEmpty()) emptyList() else {
            val includedPackages = iconAllDatas.map { it.packageName }.toSet()
            installedApps.filter { it.isCandidate && it.packageName !in includedPackages }.sortedBy { it.name.lowercase() }
        }
        binding.configCbrButton.apply {
            text = if (uncoveredApps.isNotEmpty()) getString(R.string.config_uncovered_count, uncoveredApps.size)
            else getString(R.string.config_contribute_anip)
            setOnClickListener {
                if (uncoveredApps.isNotEmpty()) showUncoveredApps(uncoveredApps)
                else showContributeDialog()
            }
        }
    }

    /**
     * 当前结果下的图标数组
     * @return [List]
     */
    private val iconDatas
        get() = iconAllDatas.filter { icon ->
            when (viewMode) {
                ViewMode.INSTALLED -> installedApps.any { it.packageName == icon.packageName }
                ViewMode.DISABLED -> !iconItems.getValue(icon.packageName).enabled
                ViewMode.OVERRIDDEN -> iconItems.getValue(icon.packageName).overlay
                else -> true
            } && (filterText.isBlank() ||
                icon.label.lowercase().contains(filterText.lowercase()) ||
                icon.packageName.lowercase().contains(filterText.lowercase()) ||
                icon.contributors.any { contributor -> contributor.lowercase().contains(filterText.lowercase()) })
        }
}