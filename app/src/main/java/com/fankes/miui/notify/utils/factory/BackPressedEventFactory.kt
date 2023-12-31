/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2017-2024 Fankes Studio(qzmmcn@163.com)
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
 * This file is created by fankes on 2022/10/6.
 */
package com.fankes.miui.notify.utils.factory

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

/** 已添加的返回监听事件 */
private val onBackPressedCallbacks = HashMap<AppCompatActivity, OnBackPressedCallback>()

/**
 * 手动调用返回事件
 * @param ignored 是否忽略现有返回监听事件立即返回 - 否则将执行返回事件 - 默认否
 */
fun AppCompatActivity.callOnBackPressed(ignored: Boolean = false) {
    if (isDestroyed) return
    onBackPressedCallbacks[this]?.isEnabled = ignored.not()
    onBackPressedDispatcher.onBackPressed()
}

/**
 * 添加返回监听事件
 * @param callback 回调事件
 */
fun AppCompatActivity.addOnBackPressedEvent(callback: OnBackPressedEvent.() -> Unit) {
    object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            OnBackPressedEvent(this@addOnBackPressedEvent).apply(callback)
        }
    }.also { result ->
        onBackPressedCallbacks.computeIfAbsent(this) {
            onBackPressedDispatcher.addCallback(result)
            result
        }
    }
}

/**
 * 返回监听事件实现类
 * @param instance 当前实例
 */
class OnBackPressedEvent(private val instance: AppCompatActivity) {

    /** 立即释放返回事件并调用返回功能 */
    fun releaseEventAndBack() = instance.callOnBackPressed(ignored = true)
}