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

package com.fankes.miui.notify.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.fankes.miui.notify.R
import com.fankes.miui.notify.bean.IconDataBean
import com.fankes.miui.notify.hook.HookConst.SOURCE_SYNC_WAY
import com.fankes.miui.notify.hook.HookConst.SOURCE_SYNC_WAY_CUSTOM_URL
import com.fankes.miui.notify.hook.HookConst.TYPE_SOURCE_SYNC_WAY_1
import com.fankes.miui.notify.hook.HookConst.TYPE_SOURCE_SYNC_WAY_2
import com.fankes.miui.notify.hook.HookConst.TYPE_SOURCE_SYNC_WAY_3
import com.fankes.miui.notify.hook.factory.isAppNotifyHookAllOf
import com.fankes.miui.notify.hook.factory.isAppNotifyHookOf
import com.fankes.miui.notify.hook.factory.putAppNotifyHookAllOf
import com.fankes.miui.notify.hook.factory.putAppNotifyHookOf
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.ui.base.BaseActivity
import com.fankes.miui.notify.utils.*
import com.fankes.miui.notify.view.MaterialSwitch
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textfield.TextInputEditText
import com.highcapable.yukihookapi.hook.factory.modulePrefs
import com.highcapable.yukihookapi.hook.xposed.YukiHookModuleStatus

class ConfigureActivity : BaseActivity() {

    /** 当前筛选条件 */
    private var filterText = ""

    /** 回调适配器改变 */
    private var onChanged: (() -> Unit)? = null

    /** 回调滚动事件改变 */
    private var onScrollEvent: ((Boolean) -> Unit)? = null

    /** 全部的通知优化图标数据 */
    private var iconAllDatas = ArrayList<IconDataBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        /** 检查激活状态 */
        if (!YukiHookModuleStatus.isActive()) {
            showDialog {
                title = "模块没有激活"
                msg = "模块没有激活，你无法使用这里的功能，请先激活模块。"
                confirmButton(text = "我知道了") { finish() }
                noCancelable()
            }
            return
        }
        /** 返回按钮点击事件 */
        findViewById<View>(R.id.title_back_icon).setOnClickListener { onBackPressed() }
        /** 刷新适配器结果相关 */
        refreshAdapterResult()
        /** 设置上下按钮点击事件 */
        findViewById<View>(R.id.config_title_up).setOnClickListener {
            snake(msg = "滚动到顶部")
            onScrollEvent?.invoke(false)
        }
        findViewById<View>(R.id.config_title_down).setOnClickListener {
            snake(msg = "滚动到底部")
            onScrollEvent?.invoke(true)
        }
        /** 设置过滤按钮点击事件 */
        findViewById<View>(R.id.config_title_filter).setOnClickListener {
            showDialog {
                title = "按条件过滤"
                var editText: TextInputEditText
                addView(R.layout.dia_icon_filter).apply {
                    editText = findViewById<TextInputEditText>(R.id.dia_icon_filter_input_edit).apply {
                        requestFocus()
                        invalidate()
                        if (filterText.isNotBlank()) {
                            setText(filterText)
                            setSelection(filterText.length)
                        }
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
        findViewById<View>(R.id.config_title_sync).setOnClickListener { onStartRefresh() }
        /** 设置列表元素和 Adapter */
        findViewById<ListView>(R.id.config_list_view).apply {
            adapter = object : BaseAdapter() {

                private val inflater = LayoutInflater.from(context)

                override fun getCount() = iconDatas.size

                override fun getItem(position: Int) = iconDatas[position]

                override fun getItemId(position: Int) = position.toLong()

                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                    var cView = convertView
                    val holder: ViewHolder
                    if (convertView == null) {
                        holder = ViewHolder()
                        cView = inflater.inflate(R.layout.adapter_config, null).also {
                            holder.appIcon = it.findViewById(R.id.adp_app_icon)
                            holder.appName = it.findViewById(R.id.adp_app_name)
                            holder.pkgName = it.findViewById(R.id.adp_app_pkg_name)
                            holder.cbrName = it.findViewById(R.id.adp_cbr_name)
                            holder.switchOpen = it.findViewById(R.id.adp_app_open_switch)
                            holder.switchAll = it.findViewById(R.id.adp_app_all_switch)
                        }
                        cView.tag = holder
                    } else holder = convertView.tag as ViewHolder
                    getItem(position).also {
                        holder.appIcon.setImageBitmap(it.iconBitmap)
                        (if (it.iconColor != 0) it.iconColor else resources.getColor(R.color.colorTextGray)).also { color ->
                            holder.appIcon.setColorFilter(color)
                            holder.appName.setTextColor(color)
                        }
                        holder.appName.text = it.appName
                        holder.pkgName.text = it.packageName
                        holder.cbrName.text = "贡献者：" + it.contributorName
                        isAppNotifyHookOf(it).also { e ->
                            holder.switchOpen.isChecked = e
                            holder.switchAll.isEnabled = e
                        }
                        holder.switchOpen.setOnCheckedChangeListener { btn, b ->
                            if (!btn.isPressed) return@setOnCheckedChangeListener
                            putAppNotifyHookOf(it, b)
                            holder.switchAll.isEnabled = b
                            SystemUITool.showNeedRestartSnake(context = this@ConfigureActivity)
                        }
                        holder.switchAll.isChecked = isAppNotifyHookAllOf(it)
                        holder.switchAll.setOnCheckedChangeListener { btn, b ->
                            if (!btn.isPressed) return@setOnCheckedChangeListener
                            putAppNotifyHookAllOf(it, b)
                            SystemUITool.showNeedRestartSnake(context = this@ConfigureActivity)
                        }
                    }
                    return cView!!
                }

                inner class ViewHolder {
                    lateinit var appIcon: ImageFilterView
                    lateinit var appName: TextView
                    lateinit var pkgName: TextView
                    lateinit var cbrName: TextView
                    lateinit var switchOpen: MaterialSwitch
                    lateinit var switchAll: MaterialSwitch
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
        findViewById<View>(R.id.config_cbr_button).setOnClickListener {
            openBrowser(url = "https://github.com/fankes/AndroidNotifyIconAdapt/blob/main/CONTRIBUTING.md")
        }
        /** 装载数据 */
        mockLocalData()
        /** 更新数据 */
        onStartRefresh()
    }

    /** 装载或刷新本地数据 */
    private fun mockLocalData() {
        iconAllDatas = IconPackParams(context = this).iconDatas
        refreshAdapterResult()
    }

    /** 首次进入或更新数据 */
    private fun onStartRefresh() =
        showDialog {
            title = "同步列表"
            var sourceType = modulePrefs.getInt(SOURCE_SYNC_WAY, TYPE_SOURCE_SYNC_WAY_1)
            var customUrl = modulePrefs.getString(SOURCE_SYNC_WAY_CUSTOM_URL)
            addView(R.layout.dia_source_from).apply {
                val radio1 = findViewById<MaterialRadioButton>(R.id.dia_sf_rd1)
                val radio2 = findViewById<MaterialRadioButton>(R.id.dia_sf_rd2)
                val radio3 = findViewById<MaterialRadioButton>(R.id.dia_sf_rd3)
                val edLin = findViewById<View>(R.id.dia_sf_text_lin)
                findViewById<TextInputEditText>(R.id.dia_sf_text).apply {
                    if (customUrl.isNotBlank()) {
                        setText(customUrl)
                        setSelection(customUrl.length)
                    }
                    doOnTextChanged { text, _, _, _ ->
                        customUrl = text.toString()
                        modulePrefs.putString(SOURCE_SYNC_WAY_CUSTOM_URL, text.toString())
                    }
                }
                edLin.isVisible = sourceType == TYPE_SOURCE_SYNC_WAY_3
                radio1.isChecked = sourceType == TYPE_SOURCE_SYNC_WAY_1
                radio2.isChecked = sourceType == TYPE_SOURCE_SYNC_WAY_2
                radio3.isChecked = sourceType == TYPE_SOURCE_SYNC_WAY_3
                radio1.setOnClickListener {
                    radio2.isChecked = false
                    radio3.isChecked = false
                    edLin.isVisible = false
                    sourceType = TYPE_SOURCE_SYNC_WAY_1
                    modulePrefs.putInt(SOURCE_SYNC_WAY, TYPE_SOURCE_SYNC_WAY_1)
                }
                radio2.setOnClickListener {
                    radio1.isChecked = false
                    radio3.isChecked = false
                    edLin.isVisible = false
                    sourceType = TYPE_SOURCE_SYNC_WAY_2
                    modulePrefs.putInt(SOURCE_SYNC_WAY, TYPE_SOURCE_SYNC_WAY_2)
                }
                radio3.setOnClickListener {
                    radio1.isChecked = false
                    radio2.isChecked = false
                    edLin.isVisible = true
                    sourceType = TYPE_SOURCE_SYNC_WAY_3
                    modulePrefs.putInt(SOURCE_SYNC_WAY, TYPE_SOURCE_SYNC_WAY_3)
                }
            }
            confirmButton {
                when (sourceType) {
                    TYPE_SOURCE_SYNC_WAY_1 -> onRefreshing(url = "https://raw.fastgit.org/fankes/AndroidNotifyIconAdapt/main")
                    TYPE_SOURCE_SYNC_WAY_2 -> onRefreshing(url = "https://raw.githubusercontent.com/fankes/AndroidNotifyIconAdapt/main")
                    TYPE_SOURCE_SYNC_WAY_3 ->
                        if (customUrl.isNotBlank())
                            if (customUrl.startsWith("http://") || customUrl.startsWith("https://"))
                                onRefreshingCustom(customUrl)
                            else snake(msg = "同步地址不是一个合法的 URL")
                        else snake(msg = "同步地址不能为空")
                    else -> snake(msg = "同步类型错误")
                }
            }
            cancelButton()
            neutralButton(text = "自定义规则") {
                showDialog {
                    title = "自定义规则"
                    var editText: TextInputEditText
                    addView(R.layout.dia_source_from_string).apply {
                        editText = findViewById<TextInputEditText>(R.id.dia_sfs_input_edit).apply {
                            requestFocus()
                            invalidate()
                        }
                    }
                    IconPackParams(context = this@ConfigureActivity).also { params ->
                        confirmButton(text = "合并") {
                            editText.text.toString().also { jsonString ->
                                when {
                                    jsonString.isNotBlank() && params.isNotVaildJson(jsonString) -> snake(msg = "不是有效的 JSON 数据")
                                    jsonString.isNotBlank() -> {
                                        params.save(
                                            params.splicingJsonArray(
                                                dataJson1 = params.storageDataJson ?: "[]",
                                                dataJson2 = jsonString.takeIf { params.isJsonArray(it) } ?: "[$jsonString]"
                                            )
                                        )
                                        filterText = ""
                                        mockLocalData()
                                        SystemUITool.showNeedUpdateApplySnake(context = this@ConfigureActivity)
                                    }
                                    else -> snake(msg = "请输入有效内容")
                                }
                            }
                        }
                        cancelButton(text = "覆盖") {
                            editText.text.toString().also { jsonString ->
                                when {
                                    jsonString.isNotBlank() && params.isNotVaildJson(jsonString) -> snake(msg = "不是有效的 JSON 数据")
                                    jsonString.isNotBlank() -> {
                                        params.save(dataJson = jsonString.takeIf { params.isJsonArray(it) } ?: "[$jsonString]")
                                        filterText = ""
                                        mockLocalData()
                                        SystemUITool.showNeedUpdateApplySnake(context = this@ConfigureActivity)
                                    }
                                    else -> snake(msg = "请输入有效内容")
                                }
                            }
                        }
                    }
                    neutralButton(text = "取消")
                }
            }
        }

    /**
     * 开始更新数据
     * @param url
     */
    private fun onRefreshing(url: String) = ClientRequestTool.checkingInternetConnect(context = this) {
        ProgressDialog(this).apply {
            setDefaultStyle(context = this@ConfigureActivity)
            setCancelable(false)
            setTitle("同步中")
            setMessage("正在同步 OS 数据")
            show()
        }.also {
            ClientRequestTool.wait(
                context = this,
                url = "$url/OS/MIUI/NotifyIconsSupportConfig.json"
            ) { isDone1, ctOS ->
                it.setMessage("正在同步 APP 数据")
                ClientRequestTool.wait(
                    context = this,
                    url = "$url/APP/NotifyIconsSupportConfig.json"
                ) { isDone2, ctAPP ->
                    it.cancel()
                    IconPackParams(context = this).also { params ->
                        if (isDone1 && isDone2) params.splicingJsonArray(ctOS, ctAPP).also {
                            when {
                                params.isHackString(it) -> snake(msg = "请求需要验证，请尝试魔法上网或关闭魔法")
                                params.isNotVaildJson(it) -> snake(msg = "在线规则发生问题，请稍后重试")
                                params.isCompareDifferent(it) -> {
                                    params.save(it)
                                    filterText = ""
                                    mockLocalData()
                                    SystemUITool.showNeedUpdateApplySnake(context = this)
                                }
                                else -> snake(msg = "列表数据已是最新")
                            }
                        } else showDialog {
                            title = "连接失败"
                            msg = "连接失败，错误如下：\n${if (!isDone1) ctOS else ctAPP}"
                            confirmButton(text = "解决方案") {
                                openBrowser(url = "https://www.baidu.com/s?wd=github%2Braw%2B%E6%97%A0%E6%B3%95%E8%AE%BF%E9%97%AE")
                            }
                            cancelButton()
                        }
                    }
                }
            }
        }
    }

    /**
     * 开始更新数据
     * @param url
     */
    private fun onRefreshingCustom(url: String) = ClientRequestTool.checkingInternetConnect(context = this) {
        ProgressDialog(this).apply {
            setDefaultStyle(context = this@ConfigureActivity)
            setCancelable(false)
            setTitle("同步中")
            setMessage("正在通过自定义地址同步数据")
            show()
        }.also {
            ClientRequestTool.wait(
                context = this,
                url = url
            ) { isDone, content ->
                it.cancel()
                IconPackParams(context = this).also { params ->
                    if (isDone)
                        when {
                            params.isHackString(content) -> snake(msg = "请求需要验证，请尝试魔法上网或关闭魔法")
                            params.isNotVaildJson(content) -> snake(msg = "目标地址不是有效的 JSON 数据")
                            params.isCompareDifferent(content) -> {
                                params.save(content)
                                filterText = ""
                                mockLocalData()
                                SystemUITool.showNeedUpdateApplySnake(context = this)
                            }
                            else -> snake(msg = "列表数据已是最新")
                        }
                    else showDialog {
                        title = "连接失败"
                        msg = "连接失败，错误如下：\n$content"
                        confirmButton(text = "我知道了")
                    }
                }
            }
        }
    }

    /** 刷新适配器结果相关 */
    private fun refreshAdapterResult() {
        onChanged?.invoke()
        findViewById<TextView>(R.id.config_title_count_text).text =
            if (filterText.isBlank()) "已适配 ${iconDatas.size} 个 APP 的通知图标"
            else "“${filterText}” 匹配到 ${iconDatas.size} 个结果"
        findViewById<TextView>(R.id.config_list_no_data_view).apply {
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
