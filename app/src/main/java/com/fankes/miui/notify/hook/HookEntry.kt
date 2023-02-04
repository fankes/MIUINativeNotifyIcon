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

import com.fankes.miui.notify.const.PackageName
import com.fankes.miui.notify.data.ConfigData
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

@InjectYukiHookWithXposed(isUsingResourcesHook = false)
object HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "MIUINativeNotifyIcon"
            isRecord = true
            elements(PRIORITY)
        }
        isDebug = false
    }

    override fun onHook() = encase {
        loadApp(PackageName.SYSTEMUI) {
            ConfigData.init(instance = this)
            when {
                isNotMIUI -> loggerW(msg = "Aborted Hook -> This System is not MIUI")
                isLowerAndroidP -> loggerW(msg = "Aborted Hook -> This System is lower than Android P")
                isNotSupportMiuiVersion -> loggerW(msg = "Aborted Hook -> This MIUI Version ${miuiVersion.ifBlank { "unknown" }} not supported")
                ConfigData.isEnableModule.not() -> loggerW(msg = "Aborted Hook -> Hook Closed")
                else -> loadHooker(SystemUIHooker)
            }
        }
    }
}