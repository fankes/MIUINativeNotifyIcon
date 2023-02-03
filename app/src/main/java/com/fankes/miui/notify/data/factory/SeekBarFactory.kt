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
 * This file is Created by fankes on 2023/2/4.
 */
@file:Suppress("SetTextI18n")

package com.fankes.miui.notify.data.factory

import android.widget.SeekBar
import android.widget.TextView
import com.fankes.miui.notify.data.ConfigData
import com.highcapable.yukihookapi.hook.xposed.prefs.data.PrefsData

/**
 * 绑定到 [SeekBar] 自动设置进度与 [TextView]
 * @param data 键值数据模板
 * @param textView 文本框
 * @param suffix 文本显示的后缀 - 默认空
 * @param onChange 当改变停止时回调
 */
fun SeekBar.bind(data: PrefsData<Int>, textView: TextView, suffix: String = "", onChange: (Int) -> Unit = {}) {
    ConfigData.getInt(data).also { value ->
        textView.text = "$value$suffix"
        progress = value
    }
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            textView.text = "$progress$suffix"
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            ConfigData.putInt(data, seekBar.progress)
            onChange(seekBar.progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    })
}