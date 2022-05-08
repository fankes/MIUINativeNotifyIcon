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
 * This file is Created by fankes on 2022/2/15.
 */
package com.fankes.miui.notify.hook

import com.fankes.miui.notify.data.DataConst
import com.fankes.miui.notify.hook.HookConst.SYSTEMUI_PACKAGE_NAME
import com.fankes.miui.notify.hook.entity.SystemUIHooker
import com.fankes.miui.notify.utils.factory.isLowerAndroidP
import com.fankes.miui.notify.utils.factory.isNotMIUI
import com.fankes.miui.notify.utils.factory.isNotSupportMiuiVersion
import com.fankes.miui.notify.utils.factory.miuiVersion
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.log.loggerW
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugTag = "MIUINativeNotifyIcon"
        isDebug = false
    }

    override fun onHook() = encase {
        when {
            /** 不是 MIUI 系统停止 Hook */
            isNotMIUI -> loggerW(msg = "Aborted Hook -> This System is not MIUI")
            /** 系统版本低于 Android P 停止 Hook */
            isLowerAndroidP -> loggerW(msg = "Aborted Hook -> This System is lower than Android P")
            /** 不是支持的 MIUI 系统停止 Hook */
            isNotSupportMiuiVersion -> loggerW(msg = "Aborted Hook -> This MIUI Version $miuiVersion not supported")
            /** Hook 被手动关闭停止 Hook */
            prefs.get(DataConst.ENABLE_MODULE).not() -> loggerW(msg = "Aborted Hook -> Hook Closed")
            /** 开始 Hook */
            else -> loadApp(SYSTEMUI_PACKAGE_NAME, SystemUIHooker)
        }
    }
}