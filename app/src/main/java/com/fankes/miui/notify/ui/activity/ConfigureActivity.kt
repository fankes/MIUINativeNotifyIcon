/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2017-2023 Fankes Studio(qzmmcn@163.com)
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
 * This file is Created by fankes on 2022/1/30.
 */
@file:Suppress("SetTextI18n", "InflateParams")

package com.fankes.miui.notify.ui.activity

import androidx.core.view.isVisible
import com.fankes.miui.notify.R
import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.databinding.ActivityConfigBinding
import com.fankes.miui.notify.databinding.AdapterConfigBinding
import com.fankes.miui.notify.databinding.DiaIconFilterBinding
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.params.factory.isAppNotifyHookAllOf
import com.fankes.miui.notify.params.factory.isAppNotifyHookOf
import com.fankes.miui.notify.params.factory.putAppNotifyHookAllOf
import com.fankes.miui.notify.params.factory.putAppNotifyHookOf
import com.fankes.miui.notify.ui.activity.base.BaseActivity
import com.fankes.miui.notify.utils.factory.*
import com.fankes.miui.notify.utils.tool.IconRuleManagerTool
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.highcapable.yukihookapi.YukiHookAPI

class ConfigureActivity : BaseActivity<ActivityConfigBinding>() {

    /** 当前筛选条件 */
    private var filterText = ""

    /** 回调适配器改变 */
    private var onChanged: (() -> Unit)? = null

    /** 回调滚动事件改变 */
    private var onScrollEvent: ((Boolean) -> Unit)? = null

    /** 全部的通知图标优化数据 */
    private var iconAllDatas = ArrayList<IconDataBean>()

    override fun onCreate() {
        /** 检查激活和启用状态 */
        if (YukiHookAPI.Status.isXposedModuleActive.not() || ConfigData.isEnableModule.not()) {
            showDialog {
                title = "模块不可用"
                msg = "模块没有激活或已被停用，你无法使用这里的功能，请先激活或启用模块。"
                confirmButton(text = "我知道了") { finish() }
                noCancelable()
            }
            return
        }
        /** 返回按钮点击事件 */
        binding.titleBackIcon.setOnClickListener { callOnBackPressed() }
        /** 刷新适配器结果相关 */
        refreshAdapterResult()
        /** 设置上下按钮点击事件 */
        binding.configTitleUp.setOnClickListener {
            snake(msg = "滚动到顶部")
            onScrollEvent?.invoke(false)
        }
        binding.configTitleDown.setOnClickListener {
            snake(msg = "滚动到底部")
            onScrollEvent?.invoke(true)
        }
        /** 设置过滤按钮点击事件 */
        binding.configTitleFilter.setOnClickListener {
            showDialog<DiaIconFilterBinding> {
                title = "按条件过滤"
                binding.iconFiltersEdit.apply {
                    requestFocus()
                    invalidate()
                    if (filterText.isNotBlank()) {
                        setText(filterText)
                        setSelection(filterText.length)
                    }
                }
                confirmButton {
                    if (binding.iconFiltersEdit.text.toString().isNotBlank()) {
                        filterText = binding.iconFiltersEdit.text.toString().trim()
                        refreshAdapterResult()
                    } else {
                        toast(msg = "条件不能为空")
                        it.performClick()
                    }
                }
                cancelButton()
                if (filterText.isNotBlank())
                    neutralButton(text = "清除条件") {
                        filterText = ""
                        refreshAdapterResult()
                    }
            }
        }
        /** 设置同步列表按钮点击事件 */
        binding.configTitleSync.setOnClickListener { onStartRefresh() }
        /** 设置列表元素和 Adapter */
        binding.configListView.apply {
            bindAdapter {
                onBindDatas { iconDatas }
                onBindViews<AdapterConfigBinding> { binding, position ->
                    iconDatas[position].also { bean ->
                        binding.adpAppIcon.setImageBitmap(bean.iconBitmap)
                        (if (bean.iconColor != 0) bean.iconColor else resources.colorOf(R.color.colorTextGray)).also { color ->
                            binding.adpAppIcon.setColorFilter(color)
                            binding.adpAppName.setTextColor(color)
                        }
                        binding.adpAppName.text = bean.appName
                        binding.adpAppPkgName.text = bean.packageName
                        binding.adpCbrName.text = "贡献者：" + bean.contributorName
                        isAppNotifyHookOf(bean).also { e ->
                            binding.adpAppOpenSwitch.isChecked = e
                            binding.adpAppAllSwitch.isEnabled = e
                        }
                        binding.adpAppOpenSwitch.setOnCheckedChangeListener { btn, b ->
                            if (btn.isPressed.not()) return@setOnCheckedChangeListener
                            putAppNotifyHookOf(bean, b)
                            binding.adpAppAllSwitch.isEnabled = b
                            SystemUITool.refreshSystemUI(context = this@ConfigureActivity)
                        }
                        binding.adpAppAllSwitch.isChecked = isAppNotifyHookAllOf(bean)
                        binding.adpAppAllSwitch.setOnCheckedChangeListener { btn, b ->
                            if (btn.isPressed.not()) return@setOnCheckedChangeListener
                            fun saveState() {
                                putAppNotifyHookAllOf(bean, b)
                                SystemUITool.refreshSystemUI(context = this@ConfigureActivity)
                            }
                            if (b) showDialog {
                                title = "全部替换"
                                msg = "此功能仅针对严重不遵守规范的 APP 通知图标才需要开启，例如：APP 推送通知后无法识别出现的黑白块图标。\n\n" +
                                        "此功能在一般情况下请保持关闭并跟随在线规则的配置，并不要随意改变此配置，" +
                                        "开启后 APP 的通知图标可能会被规则破坏，你确定还要开启吗？"
                                confirmButton { saveState() }
                                cancelButton { btn.isChecked = btn.isChecked.not() }
                                noCancelable()
                            } else saveState()
                        }
                    }
                }
            }.apply {
                setOnItemLongClickListener { _, _, p, _ ->
                    showDialog {
                        title = "复制“${iconDatas[p].appName}”的规则"
                        msg = "是否复制单条规则到剪贴板？"
                        confirmButton { copyToClipboard(iconDatas[p].toString()) }
                        cancelButton()
                    }
                    true
                }
                onChanged = { notifyDataSetChanged() }
            }
            onScrollEvent = { post { setSelection(if (it) iconDatas.lastIndex else 0) } }
        }
        /** 设置点击事件 */
        binding.configCbrButton.setOnClickListener {
            showDialog {
                title = "感谢你的贡献"
                msg = "通知图标优化名单需要大家的共同维护才能得以完善，请选择你的贡献方式。"
                confirmButton(text = "贡献规则") { openBrowser(IconRuleManagerTool.RULES_CONTRIBUTING_URL) }
                cancelButton(text = "请求适配") { openBrowser(IconRuleManagerTool.RULES_FEEDBACK_URL) }
                neutralButton(text = "暂时不用")
            }
        }
        /** 装载数据 */
        mockLocalData()
        /** 更新数据 */
        when {
            intent?.getBooleanExtra("isNewAppSupport", false) == true ->
                showDialog {
                    val appName = intent?.getStringExtra("appName") ?: ""
                    val pkgName = intent?.getStringExtra("pkgName") ?: ""
                    title = "新安装应用通知图标适配"
                    msg = "你已安装 $appName($pkgName)\n\n" +
                            "此应用未在通知优化名单中发现适配数据，若此应用发送的通知为彩色图标，" +
                            "可随时点击本页面下方的“贡献通知图标优化名单”按钮提交贡献或请求适配。\n\n" +
                            "若你已知晓此应用会遵守原生通知图标规范，可忽略此提示。\n\n" +
                            "你可以现在立即同步适配列表，以获取最新的适配数据。"
                    confirmButton(text = "同步列表") { onStartRefresh() }
                    cancelButton(text = "复制名称+包名") { copyToClipboard(content = "$appName($pkgName)") }
                    neutralButton(text = "取消")
                    noCancelable()
                }
            intent?.getBooleanExtra("isDirectUpdate", false) == true -> onStartRefresh(isByHand = false)
            intent?.getBooleanExtra("isShowUpdDialog", true) == true -> onStartRefresh()
        }
        /** 清除数据 */
        intent?.apply {
            removeExtra("isNewAppSupport")
            removeExtra("isDirectUpdate")
            removeExtra("isShowUpdDialog")
        }
        /** 设置返回监听事件 */
        addOnBackPressedEvent {
            if (MainActivity.isActivityLive.not())
                showDialog {
                    title = "提示"
                    msg = "要返回模块主页吗？"
                    confirmButton {
                        releaseEventAndBack()
                        navigate<MainActivity>()
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
        iconAllDatas = IconPackParams(context = this).iconDatas
        refreshAdapterResult()
    }

    /** 刷新适配器结果相关 */
    private fun refreshAdapterResult() {
        onChanged?.invoke()
        binding.configTitleCountText.text =
            if (filterText.isBlank()) "已适配 ${iconDatas.size} 个 APP 的通知图标"
            else "“${filterText}” 匹配到 ${iconDatas.size} 个结果"
        binding.configListNoDataView.apply {
            text = if (iconAllDatas.isEmpty()) "噫，竟然什么都没有~\n请点击右上角同步按钮获取云端数据" else "噫，竟然什么都没找到~"
            isVisible = iconDatas.isEmpty()
        }
    }

    /**
     * 当前结果下的图标数组
     * @return [Array]
     */
    private val iconDatas
        get() = if (filterText.isBlank()) iconAllDatas
        else iconAllDatas.filter {
            it.appName.lowercase().contains(filterText.lowercase()) || it.packageName.lowercase().contains(filterText.lowercase())
        }
}