/**
 * Copyright (C) 2022. Fankes Studio(qzmmcn@163.com)
 *
 * This file is part of MIUINativeNotifyIcon.
 *
 * MIUINativeNotifyIcon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MIUINativeNotifyIcon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * This file is Created by fankes on 2022/01/30.
 */
@file:Suppress("SetTextI18n", "InflateParams")

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
import com.fankes.miui.notify.R
import com.fankes.miui.notify.hook.HookMedium
import com.fankes.miui.notify.params.IconPackParams
import com.fankes.miui.notify.ui.base.BaseActivity
import com.fankes.miui.notify.view.MaterialSwitch

class ConfigureActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        /** 返回按钮点击事件 */
        findViewById<View>(R.id.title_back_icon).setOnClickListener { onBackPressed() }
        /** 设置列表元素和 Adapter */
        findViewById<ListView>(R.id.config_list_view).apply {
            adapter = object : BaseAdapter() {

                private val inflater = LayoutInflater.from(context)

                override fun getCount() = IconPackParams.iconDatas.size

                override fun getItem(position: Int) = IconPackParams.iconDatas[position]

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
                        holder.appName.text = it.appName
                        holder.pkgName.text = it.packageName
                        holder.cbrName.text = "贡献者：" + it.contributorName
                        HookMedium.isAppNotifyHookOf(it).also { e ->
                            holder.switchOpen.isChecked = e
                            holder.switchAll.isEnabled = e
                        }
                        holder.switchOpen.setOnCheckedChangeListener { btn, b ->
                            if (!btn.isPressed) return@setOnCheckedChangeListener
                            HookMedium.putAppNotifyHookOf(it, b)
                            holder.switchAll.isEnabled = b
                        }
                        holder.switchAll.isChecked = HookMedium.isAppNotifyHookAllOf(it)
                        holder.switchAll.setOnCheckedChangeListener { btn, b ->
                            if (!btn.isPressed) return@setOnCheckedChangeListener
                            HookMedium.putAppNotifyHookAllOf(it, b)
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
            }
        }
        /** 设置点击事件 */
        findViewById<View>(R.id.config_cbr_button).setOnClickListener {
            try {
                startActivity(Intent().apply {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse("https://github.com/fankes/MIUINativeNotifyIcon")
                    /** 防止顶栈一样重叠在自己的 APP 中 */
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            } catch (e: Exception) {
                Toast.makeText(this, "无法启动系统默认浏览器", Toast.LENGTH_SHORT).show()
            }
        }
    }
}