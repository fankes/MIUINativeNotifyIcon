/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2019-2022 Fankes Studio(qzmmcn@163.com)
 * https://github.com/fankes/MIUINativeNotifyIcon
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version and our eula as published
 * by ferredoxin.
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

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.view.isVisible
import com.fankes.miui.notify.R
import com.fankes.miui.notify.hook.factory.isAppNotifyHookAllOf
import com.fankes.miui.notify.hook.factory.isAppNotifyHookOf
import com.fankes.miui.notify.hook.factory.putAppNotifyHookAllOf
import com.fankes.miui.notify.hook.factory.putAppNotifyHookOf
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.ui.base.BaseActivity
import com.fankes.miui.notify.utils.SystemUITool
import com.fankes.miui.notify.utils.showDialog
import com.fankes.miui.notify.view.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText

class ConfigureActivity : BaseActivity() {

    /** 当前筛选条件 */
    private var filterText = ""

    /** 回调适配器改变 */
    private var onChanged: (() -> Unit)? = null

    /** 回调滚动事件改变 */
    private var onScrollEvent: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        /** 返回按钮点击事件 */
        findViewById<View>(R.id.title_back_icon).setOnClickListener { onBackPressed() }
        /** 刷新适配器结果相关 */
        refreshAdapterResult()
        /** 设置上下按钮点击事件 */
        findViewById<View>(R.id.config_title_up).setOnClickListener { onScrollEvent?.invoke(false) }
        findViewById<View>(R.id.config_title_down).setOnClickListener { onScrollEvent?.invoke(true) }
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
                        onChanged?.invoke()
                        refreshAdapterResult()
                    } else {
                        Toast.makeText(applicationContext, "条件不能为空", Toast.LENGTH_SHORT).show()
                        it.performClick()
                    }
                }
                cancelButton()
                if (filterText.isNotBlank())
                    neutralButton(text = "清除条件") {
                        filterText = ""
                        onChanged?.invoke()
                        refreshAdapterResult()
                    }
            }
        }
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
            }.apply { onChanged = { notifyDataSetChanged() } }
            onScrollEvent = { post { setSelection(if (it) iconDatas.lastIndex else 0) } }
        }
        /** 设置点击事件 */
        findViewById<View>(R.id.config_cbr_button).setOnClickListener {
            runCatching {
                startActivity(Intent().apply {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse("https://github.com/fankes/MIUINativeNotifyIcon")
                    /** 防止顶栈一样重叠在自己的 APP 中 */
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }.onFailure {
                Toast.makeText(this, "无法启动系统默认浏览器", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /** 刷新适配器结果相关 */
    private fun refreshAdapterResult() {
        findViewById<TextView>(R.id.config_title_count_text).text =
            if (filterText.isBlank()) "已适配 ${iconDatas.size} 个 APP 的通知图标"
            else "“${filterText}” 匹配到 ${iconDatas.size} 个结果"
        findViewById<View>(R.id.config_list_no_data_view).isVisible = iconDatas.isEmpty()
    }

    /**
     * 当前结果下的图标数组
     * @return [Array]
     */
    private val iconDatas
        get() = if (filterText.isBlank()) IconPackParams.iconDatas
        else IconPackParams.iconDatas.filter {
            it.appName.lowercase().contains(filterText.lowercase()) || it.packageName.lowercase().contains(filterText.lowercase())
        }.toTypedArray()
}