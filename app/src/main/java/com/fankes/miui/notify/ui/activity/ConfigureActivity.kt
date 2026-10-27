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

import androidx.core.view.isVisible
import com.fankes.miui.notify.R
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.databinding.ActivityConfigBinding
import com.fankes.miui.notify.databinding.AdapterConfigBinding
import com.fankes.miui.notify.databinding.DiaIconFilterBinding
import com.fankes.miui.notify.params.factory.isAppNotifyEnabledOf
import com.fankes.miui.notify.params.factory.isAppNotifyOverlayOf
import com.fankes.miui.notify.params.factory.putAppNotifyEnabledOf
import com.fankes.miui.notify.params.factory.putAppNotifyOverlayOf
import com.fankes.miui.notify.ui.activity.base.BaseActivity
import com.fankes.miui.notify.utils.factory.addOnBackPressedEvent
import com.fankes.miui.notify.utils.factory.callOnBackPressed
import com.fankes.miui.notify.utils.factory.copyToClipboard
import com.fankes.miui.notify.utils.factory.openBrowser
import com.fankes.miui.notify.utils.factory.showDialog
import com.fankes.miui.notify.utils.factory.snake
import com.fankes.miui.notify.utils.tool.IconRuleManagerTool
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.highcapable.anip.sdk.entity.NotificationIcon
import com.highcapable.betterandroid.ui.component.adapter.factory.bindAdapter
import com.highcapable.betterandroid.ui.component.adapter.recycler.cosmetic.RecyclerCosmetic
import com.highcapable.betterandroid.ui.extension.component.base.getColorCompat
import com.highcapable.betterandroid.ui.extension.component.launch
import com.highcapable.betterandroid.ui.extension.component.runDelayed
import com.highcapable.betterandroid.ui.extension.component.startActivity
import com.highcapable.betterandroid.ui.extension.view.textColor
import com.highcapable.betterandroid.ui.extension.view.textToString
import com.highcapable.betterandroid.ui.extension.view.toast
import com.highcapable.yukihookapi.YukiHook
import com.highcapable.yukihookapi.runtime.YukiHookRuntime
import kotlinx.coroutines.Job

class ConfigureActivity : BaseActivity<ActivityConfigBinding>() {

    /** 当前筛选条件 */
    private var filterText = ""

    /** 回调适配器改变 */
    private var onChanged: ((List<NotificationIcon>) -> Unit)? = null

    /** 回调滚动事件改变 */
    private var onScrollEvent: ((Boolean) -> Unit)? = null

    /** 全部的通知图标优化数据 */
    private var iconAllDatas = emptyList<NotificationIcon>()

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
        if (!available || !YukiHook.Status.isModuleActive || !ConfigData.isEnableModule)
            showDialog {
                title = getString(R.string.config_module_unavailable)
                msg = getString(R.string.config_module_unavailable_msg)
                confirmButton(text = getString(R.string.common_got_it)) { finish() }
                noCancelable()
            }
        else continueOnCreate()
    }

    private fun continueOnCreate() {
        // 返回按钮点击事件
        binding.titleBackIcon.setOnClickListener { callOnBackPressed() }
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
                        refreshAdapterResult()
                    } else {
                        toast(getString(R.string.config_filter_empty))
                        it.performClick()
                    }
                }
                cancelButton()
                if (filterText.isNotBlank())
                    neutralButton(text = getString(R.string.config_filter_clear)) {
                        filterText = ""
                        refreshAdapterResult()
                    }
            }
        }
        // 设置同步列表按钮点击事件
        binding.configTitleSync.setOnClickListener { onStartRefresh() }
        // 设置列表元素和 Adapter
        binding.configListView.apply {
            bindAdapter<NotificationIcon>(RecyclerCosmetic.fromLinearVertical(context, rowSpacing = 15.dp)) {
                onBindDiffer(
                    areItemsTheSame = { oldItem, newItem -> oldItem.packageName == newItem.packageName },
                    // 同一快照的图标不可变，重新装载后的新实例也需要刷新位图。
                    areContentsTheSame = { oldItem, newItem -> oldItem === newItem }
                )
                onBindItemView<AdapterConfigBinding> { binding, icon, _ ->
                    binding.adpAppIcon.setImageBitmap(IconRuleManagerTool.snapshot?.getBitmap(icon))
                    (icon.color ?: resources.getColorCompat(R.color.colorTextGray)).also { color ->
                        binding.adpAppIcon.setColorFilter(color)
                        binding.adpAppName.textColor = color
                    }
                    binding.adpAppName.text = icon.label
                    binding.adpAppPkgName.text = icon.packageName
                    binding.adpCbrName.text = getString(R.string.config_contributors, icon.contributors.joinToString())
                    isAppNotifyEnabledOf(icon).also { e ->
                        binding.adpAppOpenSwitch.isChecked = e
                        binding.adpAppAllSwitch.isEnabled = e
                    }
                    binding.adpAppOpenSwitch.setOnCheckedChangeListener { btn, b ->
                        if (btn.isPressed.not()) return@setOnCheckedChangeListener
                        putAppNotifyEnabledOf(icon, b)
                        binding.adpAppAllSwitch.isEnabled = b
                        SystemUITool.refreshSystemUI(context = this@ConfigureActivity)
                    }
                    binding.adpAppAllSwitch.isChecked = isAppNotifyOverlayOf(icon)
                    binding.adpAppAllSwitch.setOnCheckedChangeListener { btn, b ->
                        if (btn.isPressed.not()) return@setOnCheckedChangeListener
                        fun saveState() {
                            putAppNotifyOverlayOf(icon, b)
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
            }.apply { onChanged = { submitList(it) } }
            onScrollEvent = { toBottom ->
                post {
                    val count = adapter?.itemCount ?: 0
                    if (count > 0) scrollToPosition(if (toBottom) count - 1 else 0)
                }
            }
        }
        // 设置点击事件
        binding.configCbrButton.setOnClickListener {
            showDialog {
                title = getString(R.string.config_contribute_anip)
                msg = getString(R.string.config_contribute_anip_msg)
                confirmButton(text = getString(R.string.config_contribute)) { openBrowser(getString(R.string.anip_contribute_url)) }
                cancelButton(text = getString(R.string.config_request_adaptation)) { openBrowser(getString(R.string.anip_request_adaptation_url)) }
                neutralButton(text = getString(R.string.config_not_now))
            }
        }
        // 装载数据
        mockLocalData()
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
            intent?.getBooleanExtra("isShowUpdDialog", true) == true -> onStartRefresh()
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

    /** 装载或刷新本地数据 */
    private fun mockLocalData() {
        iconAllDatas = IconRuleManagerTool.snapshot?.icons.orEmpty()
        refreshAdapterResult()
        if (iconAllDatas.isEmpty()) launch {
            if (IconRuleManagerTool.reload(this@ConfigureActivity)) {
                iconAllDatas = IconRuleManagerTool.snapshot?.icons.orEmpty()
                refreshAdapterResult()
            }
        }
    }

    /** 刷新适配器结果相关 */
    private fun refreshAdapterResult() {
        val icons = iconDatas.toList()
        onChanged?.invoke(icons)
        binding.configTitleCountText.text =
            if (filterText.isBlank()) getString(R.string.config_included_count, icons.size)
            else getString(R.string.config_filter_result, filterText, icons.size)
        binding.configListNoDataView.apply {
            text = if (iconAllDatas.isEmpty()) getString(R.string.config_empty_no_data) else getString(R.string.config_empty_no_match)
            isVisible = icons.isEmpty()
        }
    }

    /**
     * 当前结果下的图标数组
     * @return [List]
     */
    private val iconDatas
        get() = if (filterText.isBlank()) iconAllDatas
        else iconAllDatas.filter {
            it.label.lowercase().contains(filterText.lowercase()) ||
                it.packageName.lowercase().contains(filterText.lowercase()) ||
                it.contributors.any { contributor -> contributor.lowercase().contains(filterText.lowercase()) }
        }
}