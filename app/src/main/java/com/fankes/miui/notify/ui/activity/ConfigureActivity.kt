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
 * This file is Created by fankes on 2022/1/30.
 */
@file:Suppress("SetTextI18n", "InflateParams", "DEPRECATION")

package com.fankes.miui.notify.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.view.isVisible
import com.fankes.miui.notify.R
import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.databinding.ActivityConfigBinding
import com.fankes.miui.notify.databinding.AdapterConfigBinding
import com.fankes.miui.notify.databinding.DiaIconFilterBinding
import com.fankes.miui.notify.hook.factory.isAppNotifyHookAllOf
import com.fankes.miui.notify.hook.factory.isAppNotifyHookOf
import com.fankes.miui.notify.hook.factory.putAppNotifyHookAllOf
import com.fankes.miui.notify.hook.factory.putAppNotifyHookOf
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.ui.activity.base.BaseActivity
import com.fankes.miui.notify.utils.factory.*
import com.fankes.miui.notify.utils.tool.IconRuleManagerTool
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.highcapable.yukihookapi.hook.factory.isXposedModuleActive

class ConfigureActivity : BaseActivity<ActivityConfigBinding>() {

    /** 当前筛选条件 */
    private var filterText = ""

    /** 回调适配器改变 */
    private var onChanged: (() -> Unit)? = null

    /** 回调滚动事件改变 */
    private var onScrollEvent: ((Boolean) -> Unit)? = null

    /** 全部的通知优化图标数据 */
    private var iconAllDatas = ArrayList<IconDataBean>()

    override fun onCreate() {
        /** 检查激活状态 */
        if (!isXposedModuleActive) {
            showDialog {
                title = "模块没有激活"
                msg = "模块没有激活，你无法使用这里的功能，请先激活模块。"
                confirmButton(text = "我知道了") { finish() }
                noCancelable()
            }
            return
        }
        /** 返回按钮点击事件 */
        binding.titleBackIcon.setOnClickListener { onBackPressed() }
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
            showDialog {
                title = "按条件过滤"
                val editText = bind<DiaIconFilterBinding>().diaIconFilterInputEdit.apply {
                    requestFocus()
                    invalidate()
                    if (filterText.isNotBlank()) {
                        setText(filterText)
                        setSelection(filterText.length)
                    }
                }
                confirmButton {
                    if (editText.text.toString().isNotBlank()) {
                        filterText = editText.text.toString().trim()
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
            adapter = object : BaseAdapter() {

                override fun getCount() = iconDatas.size

                override fun getItem(position: Int) = iconDatas[position]

                override fun getItemId(position: Int) = position.toLong()

                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                    var cView = convertView
                    val holder: AdapterConfigBinding
                    if (convertView == null) {
                        holder = AdapterConfigBinding.inflate(LayoutInflater.from(context))
                        cView = holder.root
                        cView.tag = holder
                    } else holder = convertView.tag as AdapterConfigBinding
                    getItem(position).also {
                        holder.adpAppIcon.setImageBitmap(it.iconBitmap)
                        (if (it.iconColor != 0) it.iconColor else resources.getColor(R.color.colorTextGray)).also { color ->
                            holder.adpAppIcon.setColorFilter(color)
                            holder.adpAppName.setTextColor(color)
                        }
                        holder.adpAppName.text = it.appName
                        holder.adpAppPkgName.text = it.packageName
                        holder.adpCbrName.text = "贡献者：" + it.contributorName
                        isAppNotifyHookOf(it).also { e ->
                            holder.adpAppOpenSwitch.isChecked = e
                            holder.adpAppAllSwitch.isEnabled = e
                        }
                        holder.adpAppOpenSwitch.setOnCheckedChangeListener { btn, b ->
                            if (!btn.isPressed) return@setOnCheckedChangeListener
                            putAppNotifyHookOf(it, b)
                            holder.adpAppAllSwitch.isEnabled = b
                            SystemUITool.showNeedRestartSnake(context = this@ConfigureActivity)
                        }
                        holder.adpAppAllSwitch.isChecked = isAppNotifyHookAllOf(it)
                        holder.adpAppAllSwitch.setOnCheckedChangeListener { btn, b ->
                            if (!btn.isPressed) return@setOnCheckedChangeListener
                            putAppNotifyHookAllOf(it, b)
                            SystemUITool.showNeedRestartSnake(context = this@ConfigureActivity)
                        }
                    }
                    return cView!!
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
            openBrowser(url = "https://github.com/fankes/AndroidNotifyIconAdapt/blob/main/CONTRIBUTING.md")
        }
        /** 装载数据 */
        mockLocalData()
        /** 更新数据 */
        when {
            intent?.getBooleanExtra("isShowNeedRestart", false) == true ->
                showDialog {
                    title = "规则列表已同步至最新"
                    msg = "同步完成，部分通知图标可能需要重新启动系统界面才能生效。"
                    confirmButton(text = "重新启动") { SystemUITool.restartSystemUI(context) }
                    cancelButton()
                    noCancelable()
                }
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
            intent?.getBooleanExtra("isShowUpdDialog", true) == true -> onStartRefresh()
        }
        /** 清除数据 */
        intent?.apply {
            removeExtra("isShowNeedRestart")
            removeExtra("isNewAppSupport")
            removeExtra("isShowUpdDialog")
        }
    }

    /** 开始手动同步 */
    private fun onStartRefresh() =
        IconRuleManagerTool.syncByHand(context = this) {
            filterText = ""
            mockLocalData()
            SystemUITool.showNeedUpdateApplySnake(context = this)
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