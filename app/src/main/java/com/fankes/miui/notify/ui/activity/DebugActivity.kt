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
 * This file is created by fankes on 2026/11/10.
 */
package com.fankes.miui.notify.ui.activity

import android.content.pm.PackageInfo
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.fankes.miui.notify.R
import com.fankes.miui.notify.const.MimeType
import com.fankes.miui.notify.const.PackageName
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.data.factory.bind
import com.fankes.miui.notify.databinding.ActivityDebugBinding
import com.fankes.miui.notify.databinding.AdapterScopeAppBinding
import com.fankes.miui.notify.ui.activity.base.BaseActivity
import com.fankes.miui.notify.utils.factory.appIconOf
import com.fankes.miui.notify.utils.factory.appNameOf
import com.fankes.miui.notify.utils.factory.callOnBackPressed
import com.fankes.miui.notify.utils.factory.snake
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.highcapable.betterandroid.system.extension.component.getPackageInfoOrNull
import com.highcapable.betterandroid.system.extension.component.versionCodeCompat
import com.highcapable.betterandroid.ui.component.adapter.factory.bindAdapter
import com.highcapable.betterandroid.ui.component.adapter.recycler.cosmetic.RecyclerCosmetic
import com.highcapable.betterandroid.ui.extension.component.launch
import com.highcapable.betterandroid.ui.extension.component.runDelayed
import com.highcapable.yukihookapi.YukiHook
import com.highcapable.yukihookapi.runtime.YukiHookRuntime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.io.File

class DebugActivity : BaseActivity<ActivityDebugBinding>() {

    /** 文件选择器打开期间保留待导出的 APK 路径 */
    private var pendingApkPath: String? = null

    private val exportApkLauncher = registerForActivityResult(ActivityResultContracts.CreateDocument(MimeType.APK)) { uri ->
        val path = pendingApkPath
        pendingApkPath = null
        if (uri == null) snake(msg = getString(R.string.tool_operation_cancelled))
        else if (path != null) launch {
            val result = withContext(Dispatchers.IO) {
                runCatching {
                    File(path).inputStream().use { input ->
                        checkNotNull(contentResolver.openOutputStream(uri)).use { output -> input.copyTo(output) }
                    }
                }
            }
            snake(msg = getString(if (result.isSuccess) R.string.tool_export_done else R.string.tool_export_error))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        pendingApkPath = savedInstanceState?.getString("pending_apk_path")
        super.onCreate(savedInstanceState)
    }

    override fun onCreate() {
        // 必须在 STARTED 之前注册，不能放进框架服务回调里
        SystemUITool.registerExportDebugLogsLauncher(activity = this)
        binding.titleBackIcon.setOnClickListener { callOnBackPressed() }
        binding.expAllDebugLogButton.setOnClickListener { SystemUITool.obtainAndExportDebugLogs(context = this) }
        binding.scopeAppsList.bindAdapter<PackageInfo>(RecyclerCosmetic.fromLinearVertical(this, rowSpacing = 10.dp)) {
            onBindData {
                listOf(
                    PackageName.SYSTEMUI,
                    PackageName.AOD
                ).mapNotNull { packageManager.getPackageInfoOrNull(it) }
            }
            onBindItemView<AdapterScopeAppBinding> { itemBinding, app, _ ->
                itemBinding.adpAppIcon.setImageDrawable(appIconOf(app.packageName))
                itemBinding.adpAppName.text = appNameOf(app.packageName)
                itemBinding.adpAppVersion.text = getString(R.string.debug_app_version, app.versionName.orEmpty(), app.versionCodeCompat)
                itemBinding.adpExtractButton.setOnClickListener { extractApk(app) }
            }
        }
        if (YukiHook.Status.runtime == YukiHookRuntime.LIBXPOSED) {
            var unavailableJob: Job? = null
            YukiHook.Status.observeFrameworkService(lifecycle) { available ->
                // 第一次回调可能是 false，稍后连上会再回调 true
                unavailableJob?.cancel()
                if (available) initLogBindings()
                else unavailableJob = runDelayed(500) { initLogBindings() }
            }
        } else initLogBindings()
    }

    private fun initLogBindings() {
        binding.moduleEnableLogSwitch.bind(ConfigData.ENABLE_MODULE_LOG) {
            onInitialize { binding.expAllDebugLogButton.isVisible = it }
            onChanged { reinitialize() }
        }
    }

    private fun extractApk(info: PackageInfo) {
        pendingApkPath = info.applicationInfo?.sourceDir ?: return
        runCatching {
            exportApkLauncher.launch("${info.packageName}_${info.versionName}_${info.versionCodeCompat}.apk")
        }.onFailure {
            pendingApkPath = null
            snake(msg = getString(R.string.tool_open_file_picker_failed))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("pending_apk_path", pendingApkPath)
        super.onSaveInstanceState(outState)
    }
}