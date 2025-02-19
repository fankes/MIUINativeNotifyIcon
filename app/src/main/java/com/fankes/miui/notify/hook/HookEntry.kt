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
 * This file is created by fankes on 2022/2/15.
 */
package com.fankes.miui.notify.hook

import com.fankes.miui.notify.const.PackageName
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.hook.entity.SystemUIHooker
import com.fankes.miui.notify.utils.factory.isLowerAndroidP
import com.fankes.miui.notify.utils.factory.isNotMiSystem
import com.fankes.miui.notify.utils.factory.isNotSupportMiSystemVersion
import com.fankes.miui.notify.utils.factory.miSystemVersion
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@InjectYukiHookWithXposed
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
                isNotMiSystem -> YLog.warn("Aborted Hook -> This System is not MIUI or HyperOS")
                isLowerAndroidP -> YLog.warn("Aborted Hook -> This System is lower than Android P")
                isNotSupportMiSystemVersion -> YLog.warn("Aborted Hook -> This System Version ${miSystemVersion.ifBlank { "unknown" }} not supported")
                ConfigData.isEnableModule.not() -> YLog.warn("Aborted Hook -> Hook Closed")
                else -> loadHooker(SystemUIHooker)
            }
        }
    }
}