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
 * This file is created by fankes on 2022/3/26.
 */
@file:Suppress("DEPRECATION")

package com.fankes.miui.notify.ui.activity.auto

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.fankes.miui.notify.ui.activity.base.BaseActivity
import com.fankes.miui.notify.utils.factory.delayedRun
import com.fankes.miui.notify.utils.tool.IconRuleManagerTool
import com.fankes.miui.notify.utils.tool.SystemUITool

class NotifyIconRuleUpdateActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** 设置透明窗口 */
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        /** 检测运行状态 */
        if (BaseActivity.isMainThreadRunning) {
            finish()
            return
        }
        /** 拉取云端数据 */
        IconRuleManagerTool.sync(context = this) {
            /** 刷新系统界面 */
            SystemUITool.refreshSystemUI()
            /** 结束当前窗口 */
            runOnUiThread { delayedRun(ms = 1000) { finish() } }
        }
        /** 切换到后台 */
        moveTaskToBack(true)
    }
}