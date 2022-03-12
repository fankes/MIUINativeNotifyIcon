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
 * This file is Created by fankes on 2022/1/7.
 */
@file:Suppress("unused", "DEPRECATION")

package com.fankes.miui.notify.utils.factory

import android.app.AlertDialog
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import kotlin.math.round

/**
 * 构造对话框
 * @param it 对话框方法体
 */
fun Context.showDialog(it: DialogBuilder.() -> Unit) = DialogBuilder(this).apply(it).show()

/**
 * 对话框构造器
 * @param context 实例
 */
class DialogBuilder(private val context: Context) {

    private var instance: AlertDialog.Builder? = null // 实例对象

    private var customLayoutView: View? = null // 自定义布局

    init {
        instance = AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog)
    }

    /** 设置对话框不可关闭 */
    fun noCancelable() = instance?.setCancelable(false)

    /** 设置对话框标题 */
    var title
        get() = ""
        set(value) {
            instance?.setTitle(value)
        }

    /** 设置对话框消息内容 */
    var msg
        get() = ""
        set(value) {
            instance?.setMessage(value)
        }

    /**
     * 设置对话框自定义布局
     * @param resId 属性资源 Id
     * @return [View]
     */
    fun addView(resId: Int): View {
        customLayoutView = LayoutInflater.from(context).inflate(resId, null)
        return customLayoutView ?: error("Inflate $resId failed")
    }

    /**
     * 设置对话框确定按钮
     * @param text 按钮文本内容
     * @param it 点击事件
     */
    fun confirmButton(text: String = "确定", it: () -> Unit = {}) =
        instance?.setPositiveButton(text) { _, _ -> it() }

    /**
     * 设置对话框取消按钮
     * @param text 按钮文本内容
     * @param it 点击事件
     */
    fun cancelButton(text: String = "取消", it: () -> Unit = {}) =
        instance?.setNegativeButton(text) { _, _ -> it() }

    /**
     * 设置对话框第三个按钮
     * @param text 按钮文本内容
     * @param it 点击事件
     */
    fun neutralButton(text: String = "更多", it: () -> Unit = {}) =
        instance?.setNeutralButton(text) { _, _ -> it() }

    /** 显示对话框 */
    internal fun show() = instance?.create()?.apply {
        val dm = DisplayMetrics()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(dm)
        customLayoutView?.let { setView(it.apply { minimumWidth = round(x = dm.widthPixels / 1.3).toInt() }) }
        setDefaultStyle(context = this@DialogBuilder.context)
    }?.show()
}