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
 * This file is created by fankes on 2022/1/7.
 */
@file:Suppress("unused")

package com.fankes.miui.notify.utils.factory

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.type.android.LayoutInflaterClass

/**
 * 显示时间选择对话框
 * @param timeSet 当前时间 - 不写将使用当前时间格式：HH:mm
 * @param result 回调 - 小时与分钟 HH:mm
 */
fun Context.showTimePicker(timeSet: String = "", result: (String) -> Unit) =
    TimePickerDialog(this, { _, h, m -> result("${h.autoZero}:${m.autoZero}") }, timeSet.hour, timeSet.minute, true).show()

/**
 * 构造 [VB] 自定义 View 对话框
 * @param initiate 对话框方法体
 */
@JvmName(name = "showDialog_Generics")
inline fun <reified VB : ViewBinding> Context.showDialog(initiate: DialogBuilder<VB>.() -> Unit) =
    DialogBuilder<VB>(context = this, VB::class.java).apply(initiate).show()

/**
 * 构造对话框
 * @param initiate 对话框方法体
 */
inline fun Context.showDialog(initiate: DialogBuilder<*>.() -> Unit) = DialogBuilder<ViewBinding>(context = this).apply(initiate).show()

/**
 * 对话框构造器
 * @param context 实例
 * @param bindingClass [ViewBinding] 的 [Class] 实例 or null
 */
@Suppress("DEPRECATION")
class DialogBuilder<VB : ViewBinding>(val context: Context, private val bindingClass: Class<*>? = null) {

    /** 实例对象 */
    private var instance: AlertDialog.Builder? = null

    /** 对话框取消监听 */
    private var onCancel: (() -> Unit)? = null

    /** 对话框实例 */
    private var dialogInstance: Dialog? = null

    /** 自定义布局 */
    private var customLayoutView: View? = null

    /**
     * 获取 [DialogBuilder] 绑定布局对象
     * @return [VB]
     */
    val binding by lazy {
        bindingClass?.method {
            name = "inflate"
            param(LayoutInflaterClass)
        }?.get()?.invoke<VB>(LayoutInflater.from(context))?.apply {
            customLayoutView = root
        } ?: error("This dialog maybe not a custom view dialog")
    }

    init {
        if (YukiHookAPI.Status.isXposedEnvironment) error("This dialog is not allowed to created in Xposed environment")
        instance = MaterialAlertDialogBuilder(context)
    }

    /** 设置对话框不可关闭 */
    fun noCancelable() {
        instance?.setCancelable(false)
    }

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

    /** 设置进度条对话框消息内容 */
    var progressContent
        get() = ""
        set(value) {
            if (customLayoutView == null)
                customLayoutView = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER or Gravity.START
                    addView(CircularProgressIndicator(context).apply {
                        isIndeterminate = true
                        trackCornerRadius = 10.dp(context)
                    })
                    addView(View(context).apply { layoutParams = ViewGroup.LayoutParams(20.dp(context), 5) })
                    addView(TextView(context).apply {
                        tag = "progressContent"
                        text = value
                    })
                    setPadding(20.dp(context), 20.dp(context), 20.dp(context), 20.dp(context))
                }
            else customLayoutView?.findViewWithTag<TextView>("progressContent")?.text = value
        }

    /**
     * 设置对话框确定按钮
     * @param text 按钮文本内容
     * @param callback 点击事件
     */
    fun confirmButton(text: String = "确定", callback: () -> Unit = {}) {
        instance?.setPositiveButton(text) { _, _ -> callback() }
    }

    /**
     * 设置对话框取消按钮
     * @param text 按钮文本内容
     * @param callback 点击事件
     */
    fun cancelButton(text: String = "取消", callback: () -> Unit = {}) {
        instance?.setNegativeButton(text) { _, _ -> callback() }
    }

    /**
     * 设置对话框第三个按钮
     * @param text 按钮文本内容
     * @param callback 点击事件
     */
    fun neutralButton(text: String = "更多", callback: () -> Unit = {}) {
        instance?.setNeutralButton(text) { _, _ -> callback() }
    }

    /**
     * 当对话框关闭时
     * @param callback 回调
     */
    fun onCancel(callback: () -> Unit) {
        onCancel = callback
    }

    /** 取消对话框 */
    fun cancel() = dialogInstance?.cancel()

    /** 显示对话框 */
    fun show() = runInSafe {
        /** 若当前自定义 View 的对话框没有调用 [binding] 将会对其手动调用一次以确保显示布局 */
        if (bindingClass != null) binding
        instance?.create()?.apply {
            customLayoutView?.let { setView(it) }
            dialogInstance = this
            setOnCancelListener { onCancel?.invoke() }
        }?.show()
    }
}