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
 * This file is Created by fankes on 2022/02/08.
 */
package com.fankes.miui.notify.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * 系统界面工具
 */
object SystemUITool {

    /**
     * 重启系统界面
     * @param context 实例
     */
    fun restartSystemUI(context: Context) =
        context.showDialog {
            title = "重启系统界面"
            msg = "你确定要立即重启系统界面吗？\n\n" +
                    "部分 MIUI 系统使用了状态栏主题可能会发生主题失效的情况，这种情况请再重启一次即可。"
            confirmButton {
                execShellSu(cmd = "pgrep systemui").also { pid ->
                    if (pid.isNotBlank())
                        execShellSu(cmd = "kill -9 $pid")
                    else Toast.makeText(context, "ROOT 权限获取失败", Toast.LENGTH_SHORT).show()
                }
            }
            cancelButton()
        }

    /**
     * 显示需要重启系统界面的 [Snackbar]
     * @param context 实例
     */
    fun showNeedRestartSnake(context: Context) =
        Snackbar.make((context as Activity).findViewById(android.R.id.content), "设置需要重启系统界面才能生效", Snackbar.LENGTH_LONG)
            .apply {
                setActionTextColor(Color.WHITE)
                setAction("立即重启") { restartSystemUI(context) }
            }.show()
}