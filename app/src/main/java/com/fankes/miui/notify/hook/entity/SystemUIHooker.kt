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
 * This file is created by fankes on 2022/3/25.
 */
@file:Suppress("UNCHECKED_CAST", "DiscouragedApi")

package com.fankes.miui.notify.hook.entity

import android.app.Notification
import android.app.NotificationManager
import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.provider.Settings
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.core.graphics.createBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.scale
import androidx.core.view.children
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.fankes.miui.notify.R
import com.fankes.miui.notify.const.PackageName
import com.fankes.miui.notify.data.ConfigData
import com.fankes.miui.notify.params.factory.isAppNotifyEnabledOf
import com.fankes.miui.notify.params.factory.isAppNotifyOverlayOf
import com.fankes.miui.notify.service.IconRuleUpdateService
import com.fankes.miui.notify.utils.factory.appIconOf
import com.fankes.miui.notify.utils.factory.appLastUpdateTimeOf
import com.fankes.miui.notify.utils.factory.appNameOf
import com.fankes.miui.notify.utils.factory.brighterColor
import com.fankes.miui.notify.utils.factory.delayedRun
import com.fankes.miui.notify.utils.factory.isMIOS
import com.fankes.miui.notify.utils.factory.isSystemInDarkMode
import com.fankes.miui.notify.utils.factory.miosVersionCode
import com.fankes.miui.notify.utils.factory.miuiIncrementalVersion
import com.fankes.miui.notify.utils.factory.runInSafe
import com.fankes.miui.notify.utils.factory.safeOfFalse
import com.fankes.miui.notify.utils.factory.safeOfNull
import com.fankes.miui.notify.utils.factory.stampToDate
import com.fankes.miui.notify.utils.factory.systemAccentColor
import com.fankes.miui.notify.utils.tool.ActivationPromptTool
import com.fankes.miui.notify.utils.tool.ContrastBitmapTool
import com.fankes.miui.notify.utils.tool.IconAdaptationTool
import com.fankes.miui.notify.utils.tool.IconRuleManagerTool
import com.fankes.miui.notify.utils.tool.SystemUITool
import com.fankes.miui.notify.wrapper.BuildConfigWrapper
import com.highcapable.anip.sdk.Anip
import com.highcapable.betterandroid.system.extension.component.getParcelableCompat
import com.highcapable.betterandroid.system.extension.utils.AndroidVersion
import com.highcapable.betterandroid.ui.extension.component.base.getDrawableCompat
import com.highcapable.betterandroid.ui.extension.component.base.toPx
import com.highcapable.betterandroid.ui.extension.graphics.round
import com.highcapable.betterandroid.ui.extension.view.ViewLayoutParams
import com.highcapable.betterandroid.ui.extension.view.getTag
import com.highcapable.betterandroid.ui.extension.view.outlineProvider
import com.highcapable.betterandroid.ui.extension.view.parentOrNull
import com.highcapable.kavaref.KavaRef.Companion.asResolver
import com.highcapable.kavaref.KavaRef.Companion.resolve
import com.highcapable.kavaref.condition.MethodCondition
import com.highcapable.kavaref.extension.VariousClass
import com.highcapable.kavaref.extension.classOf
import com.highcapable.kavaref.extension.isSubclassOf
import com.highcapable.kavaref.extension.toClass
import com.highcapable.yukihookapi.YukiHook
import com.highcapable.yukihookapi.extension.kavaref.hook
import com.highcapable.yukihookapi.extension.kavaref.hookAll
import com.highcapable.yukihookapi.extension.kavaref.lazyClass
import com.highcapable.yukihookapi.extension.kavaref.lazyClassOrNull
import com.highcapable.yukihookapi.extension.kavaref.toClassOrNull
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.HookParam
import com.highcapable.yukihookapi.runtime.YukiHookRuntime
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import top.defaults.drawabletoolbox.DrawableBuilder
import kotlin.concurrent.thread
import kotlin.time.Duration.Companion.milliseconds

/**
 * 系统界面核心 Hook 类
 */
object SystemUIHooker : YukiBaseHooker() {

    /** 私有定时执行记录 */
    private const val LAST_AUTO_UPDATE_DATE = "${BuildConfigWrapper.APPLICATION_ID}.last_auto_update_date"

    /** MIUI 新版本存在的类 */
    private val SystemUIApplicationClass by lazyClassOrNull("${PackageName.SYSTEMUI}.SystemUIApplication")

    /** MIUI 新版本存在的类 */
    private val MiuiNotificationViewWrapperClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.MiuiNotificationViewWrapper")

    /** HyperOS 2 起的聊天通知类型判断入口 */
    private val MiuiBaseNotifUtilClass by lazyClassOrNull("com.miui.systemui.notification.MiuiBaseNotifUtil")

    /** MIUI 新版本存在的类 */
    private val MiuiNotificationChildrenContainerClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.stack.MiuiNotificationChildrenContainer")

    /** HyperOS 新版本存在的经典通知组容器注入器 */
    private val NotificationChildrenContainerInjectorImplClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.stack.NotificationChildrenContainerInjectorImpl")

    /** MIUI 新版本存在的类 */
    private val NotificationHeaderViewWrapperInjectorClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.NotificationHeaderViewWrapperInjector")

    /** MIUI 未确定版本存在的类 */
    private val NotificationContentInflaterInjectorClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.row.NotificationContentInflaterInjector")

    /** MIUI 未确定版本存在的类 */
    private val SettingsManagerClass by lazyClassOrNull("com.miui.systemui.SettingsManager")

    /** HyperOS 2 的设置管理器获取入口 */
    private val MiuiDependencyClass by lazyClassOrNull("com.miui.systemui.MiuiDependency")

    /** HyperOS 3、4 的设置管理器获取入口 */
    private val InterfacesImplManagerClass by lazyClassOrNull("com.miui.systemui.interfacesmanager.InterfacesImplManager")

    /** MIUI 未确定版本存在的类 */
    private val MiuiClockClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.views.MiuiClock")

    /** MIUI 新版本存在的类 */
    private val NotificationStatClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.analytics.NotificationStat")

    private val MiuiDozeServiceClass by lazyClassOrNull("com.android.keyguard.doze.MiuiDozeService")

    /** Android 15 的 AOD 插件加载入口 */
    private val DozeServiceClass by lazyClassOrNull("com.android.systemui.doze.DozeService")

    /** 原生存在的类 */
    private val NotificationChildrenContainerClass by lazyClass("${PackageName.SYSTEMUI}.statusbar.notification.stack.NotificationChildrenContainer")

    /** 原生存在的类 (A15 HyperOS 已变成 `interface`) */
    private val NotificationIconAreaControllerClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.phone.NotificationIconAreaController")

    /** Android 16、17 原生通知图标样式提供器 */
    private val NotificationIconStyleProviderImplClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.row.icon.NotificationIconStyleProviderImpl")

    /** HyperOS 2 的更多通知入口控制器 */
    private val FoldNotifControllerClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.history.FoldNotifController")

    /** HyperOS 3、4 的更多通知入口控制器 */
    private val FoldNotifControllerImplClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.history.FoldNotifControllerImpl")

    /** MIUI 的 APP 图标管理器 */
    private val AppIconsManagerClass by lazyClassOrNull("com.miui.systemui.graphics.AppIconsManager")

    /** HyperOS 新版本的通知样式设置工具 */
    private val NotificationSettingsHelperClass by lazyClassOrNull("com.miui.systemui.notification.NotificationSettingsHelper")

    /** 系统依赖容器，用于读取当前通知集合 */
    private val DependencyClass by lazyClassOrNull("${PackageName.SYSTEMUI}.Dependency")

    /** 原生存在的类 */
    private val DarkIconDispatcherClass by lazyClassOrNull("${PackageName.SYSTEMUI}.plugins.DarkIconDispatcher")

    /** 原生存在的类 */
    private val StatusBarWindowControllerClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.window.StatusBarWindowController")

    /** 原生存在的类 */
    private val ContrastColorUtilClass by lazyClass("com.android.internal.util.ContrastColorUtil")

    /** 原生存在的类 */
    private val StatusBarIconViewClass by lazyClass("${PackageName.SYSTEMUI}.statusbar.StatusBarIconView")

    /** 原生存在的类 */
    private val NotificationIconContainerClass by lazyClass("${PackageName.SYSTEMUI}.statusbar.phone.NotificationIconContainer")

    /** 根据多个版本存在不同的包名相同的类 */
    private val StatusBarNotificationPresenterClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.phone.StatusBarNotificationPresenter",
            "${PackageName.SYSTEMUI}.statusbar.phone.StatusBar"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val ExpandableNotificationRowClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.row.ExpandableNotificationRow",
            "${PackageName.SYSTEMUI}.statusbar.ExpandableNotificationRow"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationViewWrapperClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.NotificationViewWrapper",
            "${PackageName.SYSTEMUI}.statusbar.notification.NotificationViewWrapper"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationHeaderViewWrapperClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper",
            "${PackageName.SYSTEMUI}.statusbar.notification.NotificationHeaderViewWrapper"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val NotificationUtilClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.NotificationUtil",
            "${PackageName.SYSTEMUI}.miui.statusbar.notification.NotificationUtil",
            "${PackageName.SYSTEMUI}.statusbar.notification.utils.NotifImageUtil"
        )
    )

    /** 根据多个版本存在不同的包名相同的类 */
    private val ExpandedNotificationClass by lazyClass(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.notification.ExpandedNotification",
            "${PackageName.SYSTEMUI}.miui.statusbar.ExpandedNotification"
        )
    )

    /** HyperOS 2 媒体卡片的图标绑定入口 */
    private val MiuiMediaControlPanelClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.mediacontrol.MiuiMediaControlPanel")

    /** HyperOS 2 的现有媒体播放器集合 */
    private val MediaPlayerDataClass by lazyClassOrNull("${PackageName.SYSTEMUI}.media.controls.ui.controller.MediaPlayerData")

    /** HyperOS 媒体卡片的图标绑定入口 */
    private val MiuiMediaViewControllerImplClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.mediacontrol.MiuiMediaViewControllerImpl")

    /** HyperOS 胶囊媒体卡片的图标绑定入口 */
    private val MiuiIslandMediaViewBinderImplClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.mediaisland.MiuiIslandMediaViewBinderImpl")

    /** HyperOS 焦点通知的用到的 API */
    private val FocusUtilsClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.notification.utils.FocusUtils")

    /** HyperOS 焦点通知的用到的 API */
    private val FocusedNotifPromptViewClass by lazyClassOrNull("${PackageName.SYSTEMUI}.statusbar.phone.FocusedNotifPromptView")

    private val groupContainingRowMethod by lazy {
        NotificationChildrenContainerClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "getContainingNotification"; emptyParameters()
        }
    }
    private val groupHeaderMethod by lazy {
        NotificationChildrenContainerClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "getNotificationHeaderWrapper"; emptyParameters()
        }
    }
    private val groupMinimizedHeaderMethod by lazy {
        NotificationChildrenContainerClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "getMinimizedGroupHeaderWrapper"; emptyParameters()
        }
    }
    private val groupInjectorField by lazy {
        NotificationChildrenContainerClass.resolve().optional(silent = true).firstFieldOrNull { name = "mInjector" }
    }
    private val groupInjectorIconField by lazy {
        NotificationChildrenContainerInjectorImplClass?.resolve()?.optional(silent = true)?.firstFieldOrNull { name = "mAppIcon" }
    }
    private val groupIconField by lazy {
        (MiuiNotificationChildrenContainerClass ?: NotificationChildrenContainerClass).resolve().optional(silent = true)
            .firstFieldOrNull { name = "mAppIcon"; superclass() }
    }
    private val rowParentMethod by lazy {
        ExpandableNotificationRowClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "getNotificationParent"; emptyParameters()
        }
    }
    private val rowChildrenContainerMethod by lazy {
        ExpandableNotificationRowClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "getChildrenContainer"; emptyParameters()
        }
    }
    private val statusIconNotificationField by lazy {
        StatusBarIconViewClass.resolve().optional(silent = true).firstFieldOrNull { name = "mNotification" }
    }
    private val notificationWrapperIconField by lazy {
        NotificationHeaderViewWrapperClass.resolve().optional().firstFieldOrNull { name = "mIcon" }
    }
    private val notificationWrapperRowField by lazy {
        NotificationViewWrapperClass.resolve().optional().firstFieldOrNull { name = "mRow" }
    }
    private val notificationBuilderNotificationField by lazy {
        Notification.Builder::class.resolve().firstField { name = "mN" }
    }
    private val remoteViewsActionsField by lazy {
        RemoteViews::class.resolve().firstField { name = "mActions" }
    }
    private val remoteViewsActionIdField by lazy {
        $$"android.widget.RemoteViews$Action".toClass().resolve().firstField { name = "mViewId" }
    }
    private val remoteViewsBitmapActionClass by lazyClass($$"android.widget.RemoteViews$BitmapReflectionAction")
    private val remoteViewsBitmapMethodField by lazy {
        remoteViewsBitmapActionClass.resolve().firstField { name = "mMethodName" }
    }
    private val remoteViewsReflectionActionClass by lazyClass($$"android.widget.RemoteViews$BaseReflectionAction")
    private val remoteViewsReflectionMethodField by lazy {
        remoteViewsReflectionActionClass.resolve().firstField { name = "mMethodName" }
    }
    private val notificationChatTypeMethod by lazy {
        MiuiBaseNotifUtilClass?.resolve()?.optional(silent = true)?.firstMethodOrNull { name = "getNotificationTypeForIm" }
    }
    private val notificationRowExpandedMethod by lazy {
        ExpandableNotificationRowClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "isExpanded"
            parameters(Boolean::class)
            returnType = Boolean::class
        }
    }
    private val notificationRowExpandedFallbackMethod by lazy {
        ExpandableNotificationRowClass.resolve().optional(silent = true).firstMethodOrNull {
            name { it == "isExpanded" || it == $$"isExpanded$1" }
            emptyParameters()
            returnType = Boolean::class
        }
    }
    private val notificationRowEntryMethod by lazy {
        ExpandableNotificationRowClass.resolve().optional(silent = true).firstMethodOrNull { name = "getEntry"; emptyParameters() }
    }
    private val notificationRowEntryField by lazy {
        ExpandableNotificationRowClass.resolve().optional(silent = true).firstFieldOrNull { name = "mEntry" }
    }
    private val notificationRowEntryAdapterMethod by lazy {
        ExpandableNotificationRowClass.resolve().optional(silent = true).firstMethodOrNull { name = "getEntryAdapter"; emptyParameters() }
    }
    private val notificationRowEntryAdapterField by lazy {
        ExpandableNotificationRowClass.resolve().optional(silent = true).firstFieldOrNull { name = "mEntryAdapter" }
    }

    private val dependencyInstanceField by lazy {
        DependencyClass?.resolve()?.optional(silent = true)?.firstFieldOrNull { name = "sDependency" }
    }
    private val dependencyMediaManagerField by lazy {
        DependencyClass?.resolve()?.optional(silent = true)?.firstFieldOrNull { name = "mNotificationMediaManager" }
    }
    private val lazyMediaManagerGetMethod by lazy {
        dependencyMediaManagerField?.self?.type?.toClass()?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "get"; emptyParameters()
        }
    }
    private val presenterMediaManagerField by lazy {
        StatusBarNotificationPresenterClass.resolve().optional(silent = true).firstFieldOrNull { name = "mMediaManager" }
    }
    private val mediaManagerClass by lazyClassOrNull(
        VariousClass(
            "${PackageName.SYSTEMUI}.statusbar.NotificationMediaManager",
            "${PackageName.SYSTEMUI}.media.NotificationMediaManager"
        )
    )
    private val mediaManagerPipelineField by lazy {
        mediaManagerClass?.resolve()?.optional(silent = true)?.firstFieldOrNull { name = "mNotifPipeline" }
    }
    private val pipelineNotificationsMethod by lazy {
        mediaManagerPipelineField?.self?.type?.toClass()?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "getAllNotifs"; emptyParameters()
        }
    }

    private val notificationEntryClass by lazy {
        (notificationRowEntryMethod?.self?.returnType ?: notificationRowEntryField?.self?.type)?.toClass()
    }
    private val notificationEntrySbnMethod by lazy {
        notificationEntryClass?.resolve()?.optional(silent = true)?.firstMethodOrNull { name = "getSbn"; emptyParameters() }
    }
    private val notificationEntrySbnField by lazy {
        notificationEntryClass?.resolve()?.optional(silent = true)?.firstFieldOrNull { name = "mSbn" }
    }
    private val notificationEntryRankingMethod by lazy {
        notificationEntryClass?.resolve()?.optional(silent = true)?.firstMethodOrNull { name = "getRanking"; emptyParameters() }
    }
    private val notificationEntryRankingField by lazy {
        notificationEntryClass?.resolve()?.optional(silent = true)?.firstFieldOrNull { name = "mRanking" }
    }
    private val promotedOngoingMethod by lazy {
        Notification::class.resolve().optional(silent = true).firstMethodOrNull { name = "isPromotedOngoing"; emptyParameters() }
    }
    private val focusNotificationField by lazy {
        ExpandedNotificationClass.resolve().optional(silent = true).firstFieldOrNull { name = "mIsFocusNotification" }
    }
    private val promotedNotificationField by lazy {
        ExpandedNotificationClass.resolve().optional(silent = true).firstFieldOrNull { name = "mIsPromotedOngoing" }
    }
    private val globalContextMethod by lazy {
        SystemUIApplicationClass?.resolve()?.optional(silent = true)?.firstMethodOrNull { name = "getContext" }
    }
    private val systemUIComponentField by lazy {
        SystemUIApplicationClass?.resolve()?.optional(silent = true)?.firstFieldOrNull {
            name = "mSysUIComponent"; superclass()
        }
    }
    private val iconManagerProviderField by lazy {
        globalContext?.let { systemUIComponentField?.copy()?.of(it)?.get() }
            ?.javaClass?.resolve()?.optional(silent = true)?.firstFieldOrNull { name = "iconManagerProvider" }
    }
    private val iconManagerProviderGetMethod by lazy {
        val component = globalContext?.let { systemUIComponentField?.copy()?.of(it)?.get() }
        component?.let { iconManagerProviderField?.copy()?.of(it)?.get() }
            ?.javaClass?.resolve()?.firstMethod { name = "get"; emptyParameters() }
    }
    private val iconManagerUpdateIconsMethod by lazy {
        "${PackageName.SYSTEMUI}.statusbar.notification.icon.IconManager".toClassOrNull()
            ?.resolve()?.firstMethod { name = "updateIconsSafe"; parameterCount = 1 }
    }

    private const val XMSF_PACKAGE_NAME = "com.xiaomi.xmsf"

    /** 等待模块定时更新服务响应的最长时间 */
    private const val ICON_RULE_UPDATE_TIMEOUT = 60_000L

    private val foldEntranceIconContext = ThreadLocal<Pair<List<StatusBarNotification>, Int>>()

    /** SystemUI 进程内用于恢复和更新 ANIP 快照的协程作用域 */
    private val iconRuleScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    /** 当前运行时是否提供模块共享文件，不提供时回退为 SystemUI 自行获取资源 */
    private val isSharedCacheAvailable get() = YukiHook.Status.runtime == YukiHookRuntime.LIBXPOSED

    /** 当前是否处于深色图标模式 - 跟随 Hook 保存 */
    private var isDarkIconMode = false

    /** 是否显示通知图标 - 跟随 Hook 保存 */
    private var isShowNotificationIcons = true

    /** 状态栏原始通知图标最大个数限制 */
    private var statusBarMaxStaticIcons = -1

    /** 模块上次设置的通知图标最大个数限制 */
    private var moduleLastSetStatusBarMaxStaticIcons = -1

    /** 是否已经使用过缓存刷新功能 */
    private var isUsingCachingMethod = false

    /** 模块定时更新服务未响应时的兜底提醒 */
    private var iconRuleUpdateFallbackJob: Job? = null

    /** 旧版宿主内当前的后台同步任务 */
    private var legacyIconRuleUpdateJob: Job? = null

    /** 状态栏通知图标容器 */
    @Suppress("StaticFieldLeak")
    private var notificationIconContainer: ViewGroup? = null

    /** 通知栏通知控制器 */
    private var notificationPresenter: Any? = null

    /** 设置管理器 */
    private var settingsManager: Any? = null

    /** 仅监听一次主题壁纸颜色变化 */
    private var isWallpaperColorListenerSetUp = false

    /** 已注册的主题壁纸颜色监听，模块实例释放时注销 */
    private var wallpaperColorsListener: Pair<WallpaperManager, WallpaperManager.OnColorsChangedListener>? = null

    /** 当前模块实例加载时模块 APK 的最后更新时间 */
    private var moduleLastUpdateTime: Long? = null

    /** 用来同步是否需要焦点图标染色 */
    private var focusedIcon = false

    /** 只复用资源 ID，像素尺寸仍按当前配置读取 */
    private val statusBarIconSizeIds by lazy {
        globalContext?.resources?.let { resources ->
            listOf("status_bar_icon_size", "status_bar_icon_size_sp").mapNotNull { name ->
                resources.getIdentifier(name, "dimen", PackageName.SYSTEMUI).takeIf { it != 0 }
            }
        }.orEmpty()
    }

    /**
     * 获取全局上下文
     * @return [Context] or null
     */
    private val globalContext
        get() = globalContextMethod?.copy()?.invoke<Context?>() ?: hostApplication

    /**
     * 是否为 MIUI 样式通知栏
     * @return [Boolean]
     */
    private val isShowMiuiStyle
        get() = (NotificationUtilClass.resolve().optional(silent = true)
            .firstMethodOrNull { name = "showMiuiStyle" }
            ?.invoke<Boolean>() ?: NotificationSettingsHelperClass?.resolve()?.optional(silent = true)
            ?.firstMethodOrNull { name = "showMiuiStyle" }
            ?.invoke<Boolean>()) == true

    private val Notification.isPromotedOngoingCompat
        get() = promotedOngoingMethod?.copy()?.of(this)?.invoke<Boolean>() == true

    /**
     * OS3 会清除部分焦点通知的 promoted 标记，但仍保留 ongoing 和短关键文本，
     * 使用这两个公开通知特征恢复其焦点通知语义，确保 progress 模板继续执行外圈绘制
     */
    private val Notification.isLegacyPromotedOngoingCompat
        get() = flags and Notification.FLAG_ONGOING_EVENT != 0 &&
            extras.getCharSequence("android.shortCriticalText").isNullOrBlank().not()

    private val StatusBarNotification.isFocusNotificationCompat
        get() = notification.isPromotedOngoingCompat || notification.isLegacyPromotedOngoingCompat ||
            (if (ExpandedNotificationClass.isInstance(this))
                focusNotificationField?.copy()?.of(this)?.get<Boolean>() == true ||
                    promotedNotificationField?.copy()?.of(this)?.get<Boolean>() == true
            else asResolver().optional(silent = true).let { resolver ->
                resolver.firstFieldOrNull { name = "mIsFocusNotification" }?.get<Boolean>() == true ||
                    resolver.firstFieldOrNull { name = "mIsPromotedOngoing" }?.get<Boolean>() == true
            }) || notification.extras.let {
                it.getBoolean("miui.focus.isFocus") || it.getParcelableCompat<RemoteViews>("miui.focus.rv") != null
            }

    /**
     * 是否没有单独的 MIUI 通知栏样式
     * @return [Boolean]
     */
    private val isNotHasAbsoluteMiuiStyle get() = MiuiNotificationViewWrapperClass == null

    /**
     * 获取状态栏通知图标透明度
     * @return [Float]
     */
    private val statusBarIconAlpha
        get() = (if (isDarkIconMode) ConfigData.statusIconDarkAlphaLevel else ConfigData.statusIconLightAlphaLevel) / 100f

    /**
     * 获取推送通知的包名
     *
     * 自动兼容旧版本系统
     * @return [String]
     */
    private val StatusBarNotification.senderPackageName
        get() = if (AndroidVersion.isAtLeast(AndroidVersion.Q)) opPkg else packageName ?: ""

    /**
     * 判断通知是否来自 MIPUSH
     * @return [Boolean]
     */
    private val StatusBarNotification.isXmsf get() = senderPackageName == XMSF_PACKAGE_NAME

    /**
     * 获取推送通知的包名
     *
     * 自动判断 MIPUSH
     * @return [String]
     */
    private val StatusBarNotification.notificationPackageName get() = if (isXmsf) xmsfPkgName else packageName

    /**
     * 获取 MIPUSH 通知真实包名
     * @return [String]
     */
    private val StatusBarNotification.xmsfPkgName: String
        get() {
            val xmsfPkg = notification.extras.getString("xmsf_target_package") ?: ""
            val targetPkg = notification.extras.getString("target_package") ?: ""
            return xmsfPkg.ifBlank { targetPkg.ifBlank { packageName } }
        }

    /**
     * 获取 MIUI 自己设置的通知图标
     * @return [Icon] or null
     */
    private val StatusBarNotification.miuiAppIcon get() = notification?.extras?.getParcelableCompat<Icon>("miui.appIcon")

    /**
     * 打印日志
     * @param tag 标识
     * @param context 实例
     * @param nf 通知实例
     * @param isCustom 是否为通知优化生效图标
     * @param isGrayscale 是否为灰度图标
     */
    private fun logNotificationIcon(tag: String, context: Context, nf: StatusBarNotification?, isCustom: Boolean, isGrayscale: Boolean) {
        if (ConfigData.isEnableModuleLog) {
            val focusTicker = nf?.notification?.extras?.getString("miui.focus.ticker")
            val focusIconId = nf?.notification?.extras?.getInt("miui.focus.iconId", -1)
            val focusRv = nf?.notification?.extras?.getParcelableCompat<RemoteViews>("miui.focus.rv")
            val focusParam = nf?.notification?.extras?.getString("miui.focus.param")
            if (focusRv != null)
                YLog.debug(
                    msg = "(Processing $tag with Focus Notification) ↓\n" +
                        "[Title]: ${nf.notification?.extras?.getString(Notification.EXTRA_TITLE)}\n" +
                        "[Content]: ${nf.notification?.extras?.getString(Notification.EXTRA_TEXT)}\n" +
                        "[App Name]: ${context.appNameOf(packageName = nf.packageName ?: "")}\n" +
                        "[Package Name]: ${nf.packageName}\n" +
                        "[Sender Package Name]: ${nf.senderPackageName}\n" +
                        "[Custom Icon]: $isCustom\n" +
                        "[Grayscale Icon]: $isGrayscale\n" +
                        "[From Xmsf]: ${nf.isXmsf}\n" +
                        "[String]: ${nf.notification}\n" +
                        "[Focus IconId]: $focusIconId\n" +
                        "[Focus String]: ${focusParam}\n" +
                        "[Focus rv String]: ${focusRv}\n" +
                        "[Focus Ticker String]: ${focusTicker}\n" +
                        "[Dark Icon Mode]: $isDarkIconMode"
                )
            else
                YLog.debug(
                    msg = "(Processing $tag) ↓\n" +
                        "[Title]: ${nf?.notification?.extras?.getString(Notification.EXTRA_TITLE)}\n" +
                        "[Content]: ${nf?.notification?.extras?.getString(Notification.EXTRA_TEXT)}\n" +
                        "[App Name]: ${context.appNameOf(packageName = nf?.packageName ?: "")}\n" +
                        "[Package Name]: ${nf?.packageName}\n" +
                        "[Sender Package Name]: ${nf?.senderPackageName}\n" +
                        "[Custom Icon]: $isCustom\n" +
                        "[Grayscale Icon]: $isGrayscale\n" +
                        "[From Xmsf]: ${nf?.isXmsf}\n" +
                        "[String]: ${nf?.notification}\n" +
                        "[Dark Icon Mode]: $isDarkIconMode"
                )
        }
    }

    /**
     * - 这个是修复彩色图标的关键核心代码判断
     *
     * 判断是否为灰度图标，默认使用模块算法，可切换到系统方案
     * @param context 实例
     * @param drawable 要判断的图标
     * @return [Boolean]
     */
    private fun isGrayscaleIcon(context: Context, drawable: Drawable) =
        if (ConfigData.isUseSystemIconInversion) safeOfFalse {
            ContrastColorUtilClass.resolve()
                .optional(silent = true)
                .let {
                    it.firstMethodOrNull {
                        name = "isGrayscaleIcon"
                        parameters(Drawable::class)
                    }?.of(
                        it.firstMethodOrNull {
                            name = "getInstance"
                            parameters(Context::class)
                        }?.invoke(context)
                    )?.invokeQuietly<Boolean>(drawable) == true
                }
        } else ContrastBitmapTool.isGrayscale(drawable)

    /**
     * 按系统状态栏图标尺寸生成位图，保留宽高比，不放大小图标
     * @param context 系统界面实例
     * @return 状态栏图标位图
     */
    private fun Drawable.toStatusBarIconBitmap(context: Context) = run {
        // 缓存的 ID 属于 SystemUI，通知包的 Context 不能用来读取这些尺寸
        val resources = globalContext?.resources ?: context.resources
        val size = statusBarIconSizeIds.maxOfOrNull { resources.getDimensionPixelSize(it) }
            ?.takeIf { it > 0 } ?: 24.toPx(context)
        val width = intrinsicWidth
        val height = intrinsicHeight
        if (width <= 0 || height <= 0) return@run ContrastBitmapTool.rasterize(this, size, size)
        val scale = minOf(1.0, size.toDouble() / maxOf(width, height))
        ContrastBitmapTool.rasterize(this, (width * scale).toInt().coerceAtLeast(1), (height * scale).toInt().coerceAtLeast(1))
    }

    /**
     * 注册主题壁纸改变颜色监听
     *
     *  - 仅限在 Android 12 以下注册
     * @param view 实例
     */
    private fun registerWallpaperColorChanged(view: View) = runInSafe {
        if (isWallpaperColorListenerSetUp.not() && AndroidVersion.isLessThan(AndroidVersion.S)) view.apply {
            val manager = WallpaperManager.getInstance(context)
            val listener = WallpaperManager.OnColorsChangedListener { _, _ -> refreshNotificationIcons() }
            manager.addOnColorsChangedListener(listener, handler)
            wallpaperColorsListener = manager to listener
        }
        isWallpaperColorListenerSetUp = true
    }

    /** 刷新状态栏小图标 */
    private fun refreshStatusBarIcons(): Unit = runInSafe {
        if (isMIOS && miosVersionCode >= 2) {
            val manager = currentIconManager() ?: return@runInSafe
            val resolver = manager.asResolver()
            val collection = resolver.firstField { name = "notifCollection" }.get() ?: return@runInSafe
            val entries = collection.asResolver().firstMethod { name = "getAllNotifs"; emptyParameters() }
                .invoke<Collection<Any>>() ?: return@runInSafe
            val update = resolver.firstMethod { name = "updateIconsSafe"; parameterCount = 1 }
            entries.forEach { update.invoke(it) }
            return@runInSafe
        }
        StatusBarIconViewClass.resolve().optional(silent = true).firstFieldOrNull { name = "mNotification" }?.also { result ->
            notificationIconContainer?.children?.forEach {
                // 得到通知实例
                val nf = result.copy().of(it).get<StatusBarNotification>() ?: return
                // 刷新状态栏图标
                resolveStatusBarIcon(it.context, nf, nf.notification.smallIcon.loadDrawable(it.context)).also { pair ->
                    pair.first.let { drawable ->
                        // 与系统回调中的替换路径保持相同尺寸，不在刷新时写回原尺寸图标
                        val icon = if (pair.second) drawable?.toStatusBarIconBitmap(it.context)?.toDrawable(it.resources) else drawable
                        (it as? ImageView)?.also { view ->
                            view.setStatusBarIconDrawable(icon)
                            updateStatusBarIconColor(view)
                        }
                    }
                }
            }
        }
    }

    private fun ImageView.setStatusBarIconDrawable(icon: Drawable?) {
        setTag(R.id.status_icon_render_state, null)
        val previous = drawable
        setImageDrawable(icon)
        // HyperOS 2、3 按 Drawable 的固有尺寸计算绘制缩放，手动换图后也要更新
        if (isMIOS && miosVersionCode.toInt() in 2..3 && icon != null &&
            (previous?.intrinsicWidth != icon.intrinsicWidth || previous.intrinsicHeight != icon.intrinsicHeight))
            asResolver().optional(silent = true).firstMethodOrNull {
                name = "maybeUpdateIconScaleDimens"
                emptyParameters()
            }?.invoke()
    }

    /** 刷新通知小图标 */
    private fun refreshNotificationIcons() = runInSafe {
        val updateNotificationMethodName = "updateNotificationsOnDensityOrFontScaleChanged"
        val result = notificationPresenter?.asResolver()?.optional(silent = true)?.firstMethodOrNull {
            name = updateNotificationMethodName
            emptyParameters()
        }?.invoke()
        if (result == null) settingsManager?.asResolver()?.optional(silent = true)?.apply {
            firstFieldOrNull { name = "notifStyle" }?.set(-100)
            firstMethodOrNull { name = "onNotifStyleChanged" }?.invoke()
        }
        iconRuleScope.launch {
            refreshMediaNotificationIcons()
            refreshFocusNotificationIcons()
        }
    }

    /** 读取当前通知，不保存通知集合以免跨热重载持有宿主数据 */
    private fun currentNotifications(): Sequence<StatusBarNotification>? {
        // NotifPipeline.getAllNotifs 只允许主线程访问，后台图标加载保留原有图标
        if (Looper.myLooper() != Looper.getMainLooper()) return null
        val dependency = dependencyInstanceField?.copy()?.get()
        val mediaManagerProvider = dependency?.let { dependencyMediaManagerField?.copy()?.of(it)?.get() }
        val mediaManager = mediaManagerProvider?.let { lazyMediaManagerGetMethod?.copy()?.of(it)?.invoke() }
            ?: notificationPresenter?.let { presenterMediaManagerField?.copy()?.of(it)?.get() }
        val pipeline = mediaManager?.let { mediaManagerPipelineField?.copy()?.of(it)?.get() }
        val entries = pipeline?.let { pipelineNotificationsMethod?.copy()?.of(it)?.invoke<Collection<Any?>>() } ?: return null
        val entry = entries.firstOrNull { it != null } ?: return emptySequence()
        // 复用成员声明，每次仍从当前 Pipeline 读取集合，不保存 Entry 或通知实例
        val sbnField = if (notificationEntryClass?.isInstance(entry) == true) notificationEntrySbnField
        else entry.asResolver().optional(silent = true).firstFieldOrNull { name = "mSbn" }
        if (sbnField == null) return null
        return entries.asSequence().filterNotNull().mapNotNull { sbnField.copy().of(it).get<StatusBarNotification>() }
    }

    /** 为通知组选择系统摘要图标或最后一条子通知图标，不修改原通知 */
    private fun StatusBarNotification.selectGroupNotificationIcon(context: Context): Pair<StatusBarNotification, Drawable>? {
        if (notification.flags and Notification.FLAG_GROUP_SUMMARY == 0) return null
        val children = currentNotifications()?.filter {
            it.key != key && it.groupKey == groupKey && it.user == user && it.packageName == packageName &&
                it.notification.flags and Notification.FLAG_GROUP_SUMMARY == 0
        } ?: return null
        if (ConfigData.isEnableNotifyGroupSummaryIcon) {
            // 早期 Android 14 及更低版本没有此资源，沿用最后一条通知图标
            val iconId = context.resources.getIdentifier("ic_notification_summary_auto", "drawable", "android")
            if (iconId != 0) {
                val icons = children.mapNotNull { it.notification.smallIcon }.iterator()
                if (!icons.hasNext()) return null
                val firstIcon = icons.next()
                if (!icons.hasNext()) return null
                // GroupHelper 也用 Icon.sameAs 比较原始图标，渠道 ID 不参与比较
                val sameAs = Icon::class.resolve().optional(silent = true).firstMethodOrNull {
                    name = "sameAs"
                    parameters(Icon::class)
                }?.of(firstIcon) ?: return null
                return if (icons.asSequence().any { sameAs.invokeQuietly<Boolean>(it) == false })
                    this to context.resources.getDrawableCompat(iconId) else null
            }
        }
        val child = children.maxByOrNull { it.postTime } ?: return null
        val icon = child.notification.smallIcon ?: return null
        val drawable = icon.loadDrawable(context) ?: safeOfNull {
            icon.loadDrawable(context.createPackageContext(child.notificationPackageName, Context.CONTEXT_IGNORE_SECURITY))
        } ?: return null
        return child to drawable
    }

    /**
     * 自动适配状态栏、通知栏自定义小图标
     * @param context 实例
     * @param isGrayscaleIcon 是否为灰度图标
     * @param packageName APP 包名
     * @return [Triple] - ([Drawable] 小图标,[Int] 颜色,[Boolean] 是否为占位符图标)
     */
    private fun resolveRuleIcon(context: Context, isGrayscaleIcon: Boolean, packageName: String): Triple<Drawable?, Int, Boolean> {
        var customPair: Triple<Drawable?, Int, Boolean>? = null
        if (ConfigData.isEnableNotifyIconFix) run {
            val icon = IconRuleManagerTool.getIcon(packageName)
            if (icon != null) {
                if (isAppNotifyEnabledOf(icon))
                    customPair = Triple(
                        if (isGrayscaleIcon.not() || isAppNotifyOverlayOf(icon))
                            IconRuleManagerTool.getBitmap(icon)?.toDrawable(context.resources) else null,
                        icon.color ?: 0,
                        false
                    )
                return@run
            }
            if (isGrayscaleIcon.not() && ConfigData.isEnableNotifyIconFixPlaceholder)
                customPair = Triple(module.resources.getDrawableCompat(R.drawable.ic_message), 0, true)
        }
        return customPair ?: Triple(null, 0, false)
    }

    /**
     * Hook 状态栏小图标
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param nf 通知实例
     * @param iconDrawable 小图标 [Drawable]
     * @return [Pair] 回调小图标 - ([Drawable] 小图标,[Boolean] 是否替换)
     */
    private fun resolveStatusBarIcon(
        context: Context, nf: StatusBarNotification?, iconDrawable: Drawable?, tag: String = "Status Bar Icon"
    ): Pair<Drawable?, Boolean> = nf?.let { notifyInstance ->
        val groupIcon = notifyInstance.selectGroupNotificationIcon(context)
        if (groupIcon != null) {
            val (source, drawable) = groupIcon
            if (source.key == notifyInstance.key) return@let Pair(drawable, true)
            val pair = resolveStatusBarIcon(context, source, drawable, tag)
            return@let Pair(pair.first, true)
        }
        if (iconDrawable == null) return@let Pair(null, false)
        // 图标样式只根据 smallIcon 的实际像素内容判断
        val isGrayscaleIcon = isGrayscaleIcon(context, iconDrawable)

        // 读取通知是否附加包名，如果没有则使用通知包名
        val extras = notifyInstance.notification.extras
        val pkgName = extras.getString("app_package")?.takeIf { it.isNotBlank() } ?: notifyInstance.notificationPackageName

        // 目标彩色通知 APP 图标
        val customTriple = resolveRuleIcon(context, isGrayscaleIcon, pkgName)

        // 是否为通知优化生效图标
        val isCustom = customTriple.first != null && customTriple.third.not()
        // 打印日志
        logNotificationIcon(tag = tag, context, notifyInstance, isCustom = isCustom, isGrayscaleIcon)
        when {
            // 处理自定义通知图标优化
            customTriple.first != null -> Pair(customTriple.first, true)
            // 未适配的彩色图标保持原始形状
            isGrayscaleIcon.not() -> Pair(iconDrawable, true)
            // 否则返回原始小图标
            else -> Pair(notifyInstance.notification.smallIcon.loadDrawable(context), false)
        }
    } ?: Pair(null, false)

    /** 在 SystemUI 更新 Drawable 或颜色后重新应用状态栏通知图标 */
    private fun refreshStatusBarIconView(iconView: ImageView, isGroupRefresh: Boolean = false) {
        val expandedNf = statusIconNotificationField?.copy()?.of(iconView)?.get<StatusBarNotification>() ?: return
        resolveStatusBarIcon(
            context = iconView.context,
            nf = expandedNf,
            iconDrawable = expandedNf.notification?.smallIcon?.loadDrawable(iconView.context)
        ).also { pair ->
            if (pair.second) pair.first?.also { drawable ->
                iconView.setStatusBarIconDrawable(drawable.toStatusBarIconBitmap(iconView.context).toDrawable(iconView.resources))
                // HyperOS 3 起会保留系统着色，彩色图标写入后需要同时清除两层 Tint
                if (isGrayscaleIcon(iconView.context, drawable).not()) {
                    iconView.imageTintList = null
                    iconView.colorFilter = null
                }
            }
            // 组内图标恢复一致时，写回原始图标以清除上一次写入的通用摘要图标
            // HyperOS 1/2 也需要写回灰度小图标，避免继续显示之前替换的彩色图标
            else if (isGroupRefresh || miosVersionCode < 3) pair.first?.also { iconView.setStatusBarIconDrawable(it) }
        }
        if (isMIOS && miosVersionCode.toInt() == 3) {
            // View 只保存系统 Int，不保存模块对象，热重载后不会引用旧 ClassLoader
            val colored = iconView.drawable?.let { isGrayscaleIcon(iconView.context, it).not() } == true
            iconView.setTag(R.id.status_icon_render_state, (if (iconView.isGrayscaleIcon()) 1 else 0) or (if (colored) 2 else 0))
        }
        updateStatusBarIconColor(iconView)
    }

    /**
     * Hook 通知栏小图标
     *
     * 区分系统版本 - 由于每个系统版本的方法不一样这里单独拿出来进行 Hook
     * @param context 实例
     * @param nf 通知实例
     * @param iconView 通知图标实例
     * @param isExpanded 通知是否展开 - 可做最小化通知处理 - 默认：是
     * @param isMiuiPanel 是否来自 MIUI 自己的通知面板
     * @param isUseMaterial3Style 是否使用 M3 风格，默认跟随设置
     * @param isStockPanel 是否来自原生通知面板
     * @param isDarkMode 是否使用夜间配色，AOD 固定为是；OS4 通知 View 使用强制夜间 Context，默认读取 SystemUI Application 的真实日夜配置
     */
    private fun bindNotificationIcon(
        context: Context,
        nf: StatusBarNotification?,
        iconView: ImageView,
        isExpanded: Boolean = true,
        isMiuiPanel: Boolean = false,
        isUseMaterial3Style: Boolean = ConfigData.isEnableMd3NotifyIconStyle,
        isStockPanel: Boolean = false,
        isDarkMode: Boolean = (globalContext ?: context).isSystemInDarkMode
    ): Unit = runInSafe(msg = "Bind notification icon") {
        /**
         * 设置默认通知图标
         * @param drawable 通知图标
         */
        fun bindDefaultNotificationIcon(drawable: Drawable?) {
            iconView.apply {
                // 重新设置图标
                setImageDrawable(drawable)
                // 设置裁切到边界
                clipToOutline = true
                // 设置一个圆角轮廓裁切
                outlineProvider { view, out ->
                    out.setRoundRect(
                        0, 0,
                        view.width, view.height, 5.toPx(context).toFloat()
                    )
                }
                // 清除原生或 Material 3 风格留下的背景和边距
                setPadding(0)
                background = null
                // 清除遮罩颜色
                colorFilter = null
                imageTintList = null
            }
        }
        // 获取通知对象 - 由于 MIUI 的版本迭代不规范性可能是空的
        nf?.let { notifyInstance ->
            val groupIcon = notifyInstance.selectGroupNotificationIcon(context)
            if (groupIcon != null && groupIcon.first.key != notifyInstance.key) {
                bindNotificationIcon(context, groupIcon.first, iconView, isExpanded, isMiuiPanel, isUseMaterial3Style, isStockPanel, isDarkMode)
                return@let
            }
            val isSystemSummaryIcon = groupIcon != null

            val systemContext = globalContext ?: context
            // 柔光玻璃为 1，轻透磨砂为 0；仅非 Material 3 风格按玻璃材质使用夜间着色
            val isSoftLightGlass = isUseMaterial3Style.not() && isMIOS && miosVersionCode >= 4 &&
                Settings.Secure.getInt(systemContext.contentResolver, "material_style", -1) == 1

            // 新版风格反色
            val newStyle = if (isDarkMode) 0xFF2D2D2D.toInt() else Color.WHITE

            // 旧版风格反色
            val oldStyle = if (isSoftLightGlass || isDarkMode) 0xFFDCDCDC.toInt() else 0xFF707173.toInt()

            // 通知图标原始颜色
            val iconColor = notifyInstance.notification.color

            // 是否有通知栏图标颜色
            val hasIconColor = iconColor != 0 && ConfigData.isEnableNotifyIconForceSystemColor.not()

            // 通知图标适配颜色
            val supportColor = (iconColor.takeIf { ConfigData.isEnableNotifyIconForceSystemColor.not() } ?: 0).let {
                when {
                    isUseMaterial3Style -> newStyle
                    it == 0 || isExpanded.not() -> oldStyle
                    else -> it
                }
            }

            // OS3 的部分 RemoteViews 使用 SystemUI 的受限 Context，无法直接解析通知包内的资源型 smallIcon，
            // 首次加载失败后切换到通知包 Context，避免面板只留下空白图标占位
            val smallIcon = notifyInstance.notification.smallIcon
            val iconDrawable = groupIcon?.second ?: smallIcon.loadDrawable(context) ?: safeOfNull {
                smallIcon.loadDrawable(
                    context.createPackageContext(notifyInstance.notificationPackageName, Context.CONTEXT_IGNORE_SECURITY)
                )
            } ?: return@let YLog.warn("Bind notification icon got null smallIcon")

            // 图标样式只根据 smallIcon 的实际像素内容判断
            val isGrayscaleIcon = isGrayscaleIcon(context, iconDrawable)

            // Android 16 起的 PROMOTED_ONGOING 焦点通知仍使用 smallIcon 作为有色外圈内的前景，
            // 但部分图标会被系统灰度检测误判为彩色；通知面板中需要固定沿用单色图标绘制语义
            val isFocusNotification = notifyInstance.isFocusNotificationCompat
            val isMonochromePanelIcon = isSystemSummaryIcon || isGrayscaleIcon || isFocusNotification

            val isUsePromotedOngoingComposedIcon = isFocusNotification && isUseMaterial3Style

            // Android 17 起原生图标为 40dp，外圈每边内缩 1dp，前景为 24dp
            val isStockPanelRowIcon = isUseMaterial3Style && isStockPanel &&
                AndroidVersion.isAtLeast(AndroidVersion.CINNAMON_BUN)
            val iconPadding = when {
                isStockPanelRowIcon -> 8.toPx(context)
                isUseMaterial3Style && isMIOS && isMiuiPanel -> 7.toPx(context)
                isUseMaterial3Style -> 4.toPx(context)
                else -> 0
            }
            val horizontalPadding = iconPadding * 2

            /** 按图标实际内容尺寸处理单色位图 */
            fun Drawable.optimizeForPanel(horizontalPadding: Int): Drawable {
                if (ConfigData.isEnableNotifyIconResampling.not()) return this
                val bitmap = (this as? BitmapDrawable)?.bitmap ?: return this
                val viewSizePx = iconView.layoutParams?.width?.takeIf { it > 0 } ?: iconView.width.takeIf { it > 0 } ?: 0
                val contentPx = if (viewSizePx > 0) (viewSizePx - horizontalPadding).coerceAtLeast(1) else 0
                return ContrastBitmapTool.resampling(bitmap, contentPx).toDrawable(context.resources)
            }

            /**
             * OS4 的焦点通知会忽略 RemoteViews 中 android:id/icon 的 background，
             * 将外圈与前景合成一个 Drawable，避免绘制阶段只剩下 smallIcon
             */
            fun setPromotedOngoingComposedIcon(drawable: Drawable, foregroundColor: Int, backgroundColor: Int) {
                val inset = if (isStockPanelRowIcon) iconPadding else 7.toPx(context)
                val backgroundDrawable = DrawableBuilder()
                    .rectangle()
                    .cornerRadius(ConfigData.notifyIconCornerSize.toPx(context))
                    .solidColor(if (isDarkMode) backgroundColor.brighterColor else backgroundColor)
                    .build()
                val glyph = drawable.optimizeForPanel(inset * 2)
                val foregroundDrawable = (glyph.constantState?.newDrawable(context.resources) ?: glyph).mutate()
                val composedDrawable = LayerDrawable(arrayOf(backgroundDrawable, foregroundDrawable)).apply {
                    if (isStockPanelRowIcon) {
                        val backgroundInset = 1.toPx(context)
                        setLayerInset(0, backgroundInset, backgroundInset, backgroundInset, backgroundInset)
                    }
                    setLayerInset(1, inset, inset, inset, inset)
                }
                iconView.apply {
                    clipToOutline = false
                    background = null
                    setPadding(0)
                    colorFilter = null
                    imageTintList = null
                    setImageDrawable(composedDrawable)
                    // ImageView 写入 Drawable 时会向所有图层重放空 Tint，前景需在写入后着色
                    composedDrawable.getDrawable(1).setTint(foregroundColor)
                }
            }

            // 自定义默认小图标
            var customIcon: Drawable? = null

            // 读取通知是否附加包名，如果没有则使用通知包名
            val extras = notifyInstance.notification.extras
            val pkgName = extras.getString("app_package")?.takeIf { it.isNotBlank() } ?: notifyInstance.notificationPackageName

            /** 自定义默认小图标颜色 */
            var customIconColor = 0
            if (isSystemSummaryIcon.not()) resolveRuleIcon(context, isGrayscaleIcon, pkgName).also {
                // 不处理占位符图标
                if (it.third) return@also
                customIcon = it.first
                customIconColor = if (customIcon == null || isUseMaterial3Style || isExpanded)
                    (it.second.takeIf { e -> e != 0 && ConfigData.isEnableNotifyIconForceSystemColor.not() }
                        ?: (if (isUseMaterial3Style) context.systemAccentColor else 0)) else 0
            }
            // 打印日志
            logNotificationIcon(
                tag = "Notification Panel Icon",
                context,
                notifyInstance,
                isCustom = customIcon != null,
                isGrayscale = isMonochromePanelIcon
            )
            // 处理自定义通知图标优化
            when {
                ConfigData.isEnableNotifyIconForceAppIcon && isSystemSummaryIcon.not() -> bindDefaultNotificationIcon(
                    drawable = notifyInstance.miuiAppIcon?.loadDrawable(context) ?: context.appIconOf(notifyInstance.notificationPackageName)
                )
                customIcon != null -> customIcon.also { drawable ->
                    if (isUsePromotedOngoingComposedIcon && customIconColor != 0)
                        setPromotedOngoingComposedIcon(drawable, supportColor, customIconColor)
                    else iconView.apply {
                        // 设置不要裁切到边界
                        clipToOutline = false
                        // 清除切换风格前的背景、边距和系统着色
                        background = null
                        setPadding(0)
                        imageTintList = null
                        // 设置自定义小图标
                        setImageDrawable(drawable.optimizeForPanel(horizontalPadding))
                        // 上色
                        setColorFilter(if (isUseMaterial3Style || customIconColor == 0) supportColor else customIconColor)
                        // 设置图标外圈颜色
                        if (isUseMaterial3Style && customIconColor != 0)
                            background = DrawableBuilder()
                                .rectangle()
                                .cornerRadius(ConfigData.notifyIconCornerSize.toPx(context))
                                .solidColor(if (isDarkMode) customIconColor.brighterColor else customIconColor)
                                .build()
                        setPadding(iconPadding)
                    }
                }
                else -> {
                    // 处理原生单色通知图标
                    if (isMonochromePanelIcon) {
                        // 保留原生单色图标，通知未提供颜色时使用已启用规则的颜色
                        val backgroundColor = when {
                            hasIconColor -> iconColor
                            customIconColor != 0 -> customIconColor
                            isUseMaterial3Style -> context.systemAccentColor
                            else -> oldStyle
                        }
                        if (isUsePromotedOngoingComposedIcon)
                            setPromotedOngoingComposedIcon(iconDrawable, supportColor, backgroundColor)
                        else iconView.apply {
                            // 清除切换风格前的背景、边距和系统着色
                            background = null
                            setPadding(0)
                            imageTintList = null
                            // 重新设置图标 - 防止系统更改它
                            setImageDrawable(iconDrawable.optimizeForPanel(horizontalPadding))
                            // 设置不要裁切到边界
                            clipToOutline = false
                            // 非 Material 3 使用通知或规则颜色，无颜色时使用旧版灰度色
                            setColorFilter(if (isUseMaterial3Style) supportColor else backgroundColor)
                            // 设置图标外圈颜色
                            if (isUseMaterial3Style)
                                background = DrawableBuilder()
                                    .rectangle()
                                    .cornerRadius(ConfigData.notifyIconCornerSize.toPx(context))
                                    .solidColor(if (isDarkMode) backgroundColor.brighterColor else backgroundColor)
                                    .build()
                            setPadding(iconPadding)
                        }
                    } else bindDefaultNotificationIcon(iconDrawable)
                }
            }
            if (isStockPanelRowIcon && iconView.background != null) iconView.apply {
                background = InsetDrawable(background, 1.toPx(context))
                // InsetDrawable 会提供背景 padding，写入后恢复前景所需的边距
                setPadding(iconPadding)
            }
        }
    }

    private fun StatusBarNotification.createFoldEntranceIcon(context: Context, size: Int) = safeOfNull {
        // 强制使用 APP 图标时保留系统原本的 APP 图标，不再处理圆角
        if (ConfigData.isEnableNotifyIconForceAppIcon) return@safeOfNull null
        val isMiuiPanel = isShowMiuiStyle
        if (isMiuiPanel && ConfigData.isEnableReplaceMiuiStyleNotifyIcon.not())
            return@safeOfNull null
        val iconView = ImageView(context).apply {
            layoutParams = ViewLayoutParams(size, size)
        }
        bindNotificationIcon(
            context = context,
            nf = this,
            iconView = iconView,
            isMiuiPanel = isMiuiPanel
        )
        if (iconView.drawable == null) return@safeOfNull null
        // 离屏 ImageView 不预先使用通知面板的固定轮廓裁切，最终合成统一跟随模块圆角设置
        iconView.clipToOutline = false
        val sizeSpec = View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY)
        iconView.measure(sizeSpec, sizeSpec)
        iconView.layout(0, 0, size, size)
        createBitmap(size, size).also { iconView.draw(Canvas(it)) }
            .round(ConfigData.notifyIconCornerSize.toPx(context).toFloat())
            .toDrawable(context.resources)
    }

    /** 更新 HyperOS 2 媒体卡片的角标，不重新绑定播放数据 */
    private fun bindLegacyMediaNotificationIcon(panel: Any) {
        val resolver = panel.asResolver().optional(silent = true)
        val data = resolver.firstFieldOrNull { name = "mMediaData" }?.get() ?: return
        val key = data.asResolver().optional(silent = true).firstFieldOrNull {
            name = "notificationKey"
        }?.get<String>() ?: return
        val nf = currentNotifications()?.firstOrNull { it.key == key } ?: return
        val holder = resolver.firstFieldOrNull {
            name = "mMediaViewHolder"
            superclass()
        }?.get() ?: return
        val iconView = holder.asResolver().optional(silent = true).firstFieldOrNull {
            name = "appIcon"
        }?.get<ImageView>() ?: return
        // 系统的轮廓回调会重新开启固定圆角裁切，合成图标改用模块设置的圆角
        iconView.outlineProvider = null
        bindComposedNotificationIcon(iconView, nf, isMediaIcon = true)
    }

    /** 更新媒体卡片的当前角标和翻转时复制的图标，不重新绑定播放数据 */
    private fun bindMediaNotificationIcons(controller: Any) {
        val resolver = controller.asResolver().optional(silent = true)
        val data = resolver.firstFieldOrNull { name = "mediaData" }?.get() ?: return
        val key = data.asResolver().optional(silent = true).firstFieldOrNull {
            name = "notificationKey"
        }?.get<String>() ?: return
        val nf = currentNotifications()?.firstOrNull { it.key == key } ?: return
        val drawableField = resolver.firstFieldOrNull { name = "appIconDrawable" } ?: return
        val views = listOf("holder", "dummyHolder").mapNotNull { fieldName ->
            val holder = resolver.firstFieldOrNull { name = fieldName }?.get() ?: return@mapNotNull null
            holder.asResolver().optional(silent = true).firstFieldOrNull { name = "appIcon" }?.get<ImageView>()
        }
        val first = views.firstOrNull() ?: return
        bindComposedNotificationIcon(first, nf, isMediaIcon = true)
        val drawable = first.drawable ?: return
        val bitmap = (drawable as BitmapDrawable).bitmap
        drawableField.set(bitmap.toDrawable(first.resources))
        views.drop(1).forEach { view ->
            view.background = null
            view.colorFilter = null
            view.imageTintList = null
            view.clipToOutline = false
            view.setPadding(0)
            view.setImageDrawable(bitmap.toDrawable(view.resources))
        }
    }

    /** 从系统组件取得现有媒体控制器，主动刷新角标，不保存控制器或视图 */
    private fun refreshMediaNotificationIcons() = runInSafe(msg = "Refresh media notification icons") {
        if (isMIOS && miosVersionCode.toInt() == 2) {
            val players = MediaPlayerDataClass?.resolve()?.optional(silent = true)?.firstFieldOrNull {
                name = "mediaPlayers"
            }?.get<Map<*, *>>() ?: return@runInSafe
            players.values.filterNotNull().forEach { panel ->
                if (MiuiMediaControlPanelClass?.isInstance(panel) == true) bindLegacyMediaNotificationIcon(panel)
            }
            return@runInSafe
        }
        val component = globalContext?.asResolver()?.optional(silent = true)?.firstFieldOrNull {
            name = "mSysUIComponent"
            superclass()
        }?.get()?.asResolver()?.optional(silent = true) ?: return@runInSafe
        listOf("miuiMediaViewControllerImplProvider", "miuiIslandMediaViewBinderImplProvider").forEach { fieldName ->
            val provider = component.firstFieldOrNull { name = fieldName }?.get() ?: return@forEach
            val controller = provider.asResolver().optional(silent = true).firstMethodOrNull {
                name = "get"
                emptyParameters()
            }?.invoke() ?: return@forEach
            bindMediaNotificationIcons(controller)
        }
    }

    /** 按焦点通知开关适配胶囊图标 */
    private fun bindFocusNotificationIcon(iconView: ImageView, nf: StatusBarNotification) {
        if (ConfigData.isEnableFocusNotifyIcon) bindComposedNotificationIcon(iconView, nf)
    }

    /** 将通知图标和外圈合成一张位图，供胶囊、媒体、聊天角标和 AOD 使用；媒体角标固定使用 M3 */
    private fun bindComposedNotificationIcon(
        iconView: ImageView,
        nf: StatusBarNotification,
        isMediaIcon: Boolean = false,
        isUseMaterial3Style: Boolean = ConfigData.isEnableMd3NotifyIconStyle,
        isDarkMode: Boolean = (globalContext ?: iconView.context).isSystemInDarkMode
    ) {
        val size = 40.toPx(iconView.context)
        val panelIcon = ImageView(iconView.context).apply {
            layoutParams = ViewLayoutParams(size, size)
        }
        bindNotificationIcon(iconView.context, nf, panelIcon, isUseMaterial3Style = isMediaIcon || isUseMaterial3Style, isDarkMode = isDarkMode)
        if (panelIcon.drawable == null) return
        panelIcon.clipToOutline = false
        val sizeSpec = View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY)
        panelIcon.measure(sizeSpec, sizeSpec)
        panelIcon.layout(0, 0, size, size)
        val bitmap = createBitmap(size, size).also { panelIcon.draw(Canvas(it)) }
        iconView.apply {
            background = null
            colorFilter = null
            imageTintList = null
            clipToOutline = false
            setPadding(0)
            setImageDrawable(bitmap.toDrawable(resources))
        }
    }

    private fun installRemoteFocusNotificationBindings() {
        if (isMIOS.not() || miosVersionCode < 2) return
        if (miosVersionCode >= 3) {
            // 插件可能先生成自定义 View，既不经过 handleFocusAppIcon，也没有原生 HeaderWrapper
            fun bindCustomFocusIcon(wrapper: Any) {
                if (NotificationHeaderViewWrapperClass.isInstance(wrapper)) return
                if (ConfigData.isEnableFocusNotifyIcon.not()) return
                val nf = wrapper.getNotificationRow().second.getNotification() ?: return
                if (nf.isFocusNotificationCompat.not()) return
                val resolver = wrapper.asResolver()
                // 基类构造时也调用 onReinflated，此时 wrapper 尚未完成初始化
                if (resolver.firstField { name = "mContext"; superclass() }.get<Context>() == null) return
                val root = resolver.firstField { name = "mView"; superclass() }.get<View>() ?: return
                val iconId = nf.notification.extras.getInt("miui.focus.iconId")
                val icon = iconId.takeIf { it != 0 }?.let { root.findViewById<ImageView>(it) }
                    ?: root.findViewById(root.resources.getIdentifier("icon", "id", "android"))
                bindComposedNotificationIcon(icon, nf)
            }
            NotificationViewWrapperClass.resolve().optional(silent = true).apply {
                firstMethodOrNull { name = "wrap"; parameterCount = 3 }?.hook()?.after {
                    result<Any>()?.let { bindCustomFocusIcon(it) }
                }
                firstMethodOrNull { name = "onReinflated"; emptyParameters() }?.hook()?.after {
                    bindCustomFocusIcon(instance)
                }
            }
            return
        }
        // 通知身份只在同步生成 RemoteViews 期间保留，不从后台线程读取 Pipeline
        val notification = ThreadLocal<StatusBarNotification>()
        NotificationContentInflaterInjectorClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "createRemoteViews"
            parameters { it.getOrNull(7) == ExpandableNotificationRowClass }
        }?.hook()?.apply {
            before {
                notification.remove()
                arg(7).get<Any>()?.getNotification()?.takeIf {
                    it.isFocusNotificationCompat && ConfigData.isEnableFocusNotifyIcon
                }?.let { notification.set(it) }
            }
            after { notification.remove() }
        }
        NotificationUtilClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "handleFocusAppIcon"
            parameters(Context::class, RemoteViews::class, Int::class, Notification::class)
        }?.hook()?.before {
            val nf = notification.get() ?: return@before
            val context = firstArg().get<Context>() ?: return@before
            val views = arg(1).get<RemoteViews>() ?: return@before
            val iconId = arg(2).get<Int>() ?: return@before
            if (iconId == 0) return@before
            val icon = ImageView(context)
            bindComposedNotificationIcon(icon, nf)
            val drawable = icon.drawable ?: return@before
            views.setImageViewBitmap(iconId, (drawable as BitmapDrawable).bitmap)
            result = null
        }
    }

    /** 从插件已有的通知数据适配胶囊主图标，不替换右侧进度、动画或展开卡片的大图 */
    private fun installFocusNotificationIcons(loader: ClassLoader, hookedClasses: MutableSet<Class<*>>) {
        // 只标记同次头像绑定是否已经委托公共角标入口，返回后清除，不保存通知或 View
        val boundBadge = ThreadLocal<Boolean>()
        val holderClass = "miui.systemui.dynamicisland.module.IslandIconViewHolder".toClassOrNull(loader)
        if (holderClass != null && hookedClasses.add(holderClass)) holderClass.resolve().optional().apply {
            mapOf("setAppIcon" to "appIcon", "setFixIcon" to "fixIcon", "setIcon" to "icon", "setSmallIcon" to "smallIcon")
                .forEach { (methodName, fieldName) ->
                    firstMethodOrNull {
                        name = methodName
                        parameterCount = 1
                    }?.hook()?.after {
                        val resolver = instance.asResolver().optional(silent = true)
                        val module = resolver.firstMethodOrNull {
                            name = "getModule"
                            superclass()
                        }?.invoke<String>()
                        if (module != "moduleImageText_1" && module != "moduleImageText_3" &&
                            module != "modulePicSmallIsland" && module != "moduleCombinePic")
                            return@after
                        val data = firstArg().get() ?: return@after
                        if (methodName != "setAppIcon") {
                            val picInfo = resolver.firstFieldOrNull { name = "picInfo" }?.get() ?: return@after
                            val pic = picInfo.asResolver().optional(silent = true).firstMethodOrNull {
                                name = "getPic"
                                emptyParameters()
                            }?.invoke<String>() ?: return@after
                            if (pic != "miui.focus.pic_app_icon_big_island" && pic != "miui.focus.pic_app_icon_small_island")
                                return@after
                        }
                        val extras = data.asResolver().optional(silent = true).firstMethodOrNull {
                            name = "getExtras"
                            emptyParameters()
                        }?.invoke<Bundle>() ?: return@after
                        val nf = extras.getParcelableCompat<StatusBarNotification>("miui.sbn") ?: return@after
                        val icon = resolver.firstFieldOrNull { name = fieldName }?.get<ImageView>() ?: return@after
                        if (icon.isVisible) bindFocusNotificationIcon(icon, nf)
                    }
                }
        }
        // 部分 OS3 图文模板直接写入应用角标，不经过 showAppIcon，需在头像绑定完成后单独适配
        val badgeId = $$"com.android.systemui.miui.notification.R$id".toClassOrNull(loader)?.resolve()?.optional(silent = true)
            ?.firstFieldOrNull { name = "focus_app_icon" }?.get<Int>()
        if (badgeId != null) listOf("ModuleImageTextImViewHolder", "ModuleTinyImageTextImViewHolder").forEach { className ->
            val imageTextClass = "miui.systemui.notification.focus.moduleV3.$className".toClassOrNull(loader) ?: return@forEach
            if (hookedClasses.add(imageTextClass).not()) return@forEach
            imageTextClass.resolve().optional(silent = true).firstMethodOrNull {
                name = "showProfilePicture"
                parameterCount = 2
            }?.hook()?.apply {
                before { boundBadge.set(false) }
                after {
                    val alreadyBound = boundBadge.get() == true
                    boundBadge.remove()
                    if (alreadyBound) return@after
                    val nf = lastArg().get<StatusBarNotification>() ?: return@after
                    val root = instance.asResolver().optional(silent = true).firstMethodOrNull {
                        name = "getView"
                        emptyParameters()
                        superclass()
                    }?.invoke<View>() ?: return@after
                    val icon = root.findViewById<ImageView>(badgeId) ?: return@after
                    if (icon.isVisible) bindFocusNotificationIcon(icon, nf)
                }
            }
        }
        // OS3、OS4 保留的旧进度模板使用独立的应用图标绑定入口
        val templateClass = "miui.systemui.notification.focus.template.FocusTemplate".toClassOrNull(loader)
        if (templateClass != null && hookedClasses.add(templateClass)) templateClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "showAppIcon"
            parameters(Context::class, ImageView::class, StatusBarNotification::class)
        }?.hook()?.after {
            val icon = arg(1).get<ImageView>() ?: return@after
            val nf = lastArg().get<StatusBarNotification>() ?: return@after
            if (icon.isVisible) bindFocusNotificationIcon(icon, nf)
        }
        val badgeClass = "miui.systemui.notification.focus.moduleV3.ext.ModuleViewHolderExtKt".toClassOrNull(loader)
        if (badgeClass != null && hookedClasses.add(badgeClass)) {
            badgeClass.resolve().optional().firstMethodOrNull {
                name = "showAppIcon"
                parameters { it.firstOrNull() == classOf<ImageView>() && it.size == 3 }
            }?.hook()?.after {
                val icon = firstArg().get<ImageView>() ?: return@after
                val nf = arg(1).get<StatusBarNotification>() ?: return@after
                if (icon.isVisible) bindFocusNotificationIcon(icon, nf)
            }
            badgeClass.resolve().optional().firstMethodOrNull {
                name = "showAppIcon"
                parameters {
                    it.firstOrNull() == classOf<ImageView>() && it.size == 4 &&
                        it[1] == classOf<StatusBarNotification>() && it[2] == classOf<String>()
                }
            }?.hook()?.after {
                val icon = firstArg().get<ImageView>() ?: return@after
                val nf = arg(1).get<StatusBarNotification>() ?: return@after
                if (icon.isVisible && ConfigData.isEnableFocusNotifyIcon) {
                    bindFocusNotificationIcon(icon, nf)
                    if (boundBadge.get() != null) boundBadge.set(true)
                }
            }
        }
    }

    /** 从当前插件的作用域获取焦点控制器，重新绑定已有通知，不保存插件或视图实例 */
    private fun refreshFocusNotificationIcons() = runInSafe(msg = "Refresh focus notification icons") {
        val loader = "com.miui.systemui.plugin.PluginInstanceInjector".toClassOrNull()?.resolve()?.optional(silent = true)
            ?.firstFieldOrNull { name = "sClassLoaders" }?.get<Map<String, ClassLoader>>()
            ?.get("miui.systemui.plugin") ?: return@runInSafe
        val component = "miui.systemui.dagger.PluginComponentInitializer".toClassOrNull(loader)?.resolve()?.optional(silent = true)
            ?.firstFieldOrNull { name = "_pluginComponent" }?.get() ?: return@runInSafe
        val provider = component.asResolver().optional(silent = true)
            .firstFieldOrNull { name = "focusNotificationControllerProvider" }?.get() ?: return@runInSafe
        val controller = provider.asResolver().firstMethod {
            name = "get"
            emptyParameters()
        }.invoke() ?: return@runInSafe
        val resolver = controller.asResolver()
        val notifications = resolver.firstField { name = "sbnMap" }.get<Map<String, StatusBarNotification>>()
            ?.values?.toList().orEmpty()
        if (notifications.isEmpty()) return@runInSafe
        val pluginContext = resolver.firstField { name = "pluginCtx" }.get<Context>() ?: return@runInSafe
        val sysuiContext = resolver.firstField { name = "sysuiCtx" }.get<Context>() ?: return@runInSafe
        val notificationProvider = resolver.firstField { name = "mProvider" }.get() ?: return@runInSafe
        val utils = resolver.firstField { name = "focusNotifUtils" }.get() ?: return@runInSafe
        val maxSeq = utils.asResolver().firstMethod {
            name = "getMaxSeq"
            emptyParameters()
        }.invoke<MutableMap<String, Long>>() ?: return@runInSafe
        val repost = resolver.firstMethod {
            name = "onNotificationPosted"
            parameterCount = 4
        }
        notifications.forEach { nf ->
            // 与宿主配置变化刷新一致：允许当前序号重新绑定，且不因图标设置变化再次弹出悬浮卡片
            maxSeq.remove(nf.key)
            nf.notification.extras.putBoolean("miui.island.updateNoFloat", true)
            repost.invoke(nf, pluginContext, sysuiContext, notificationProvider)
        }
    }

    /** 读取控制中心当前播放应用对应的媒体通知，不保存通知或插件实例 */
    private fun controlCenterMediaNotification(loader: ClassLoader, packageName: String? = null): StatusBarNotification? {
        val viewModel = "com.android.systemui.MiPlayDetailViewModel".toClassOrNull(loader)?.resolve()?.optional(silent = true)
            ?.firstFieldOrNull { name = "INSTANCE" }?.get() ?: return null
        val metadata = viewModel.asResolver().optional(silent = true).firstMethodOrNull {
            name = "getMAppMetadata"
            emptyParameters()
        }?.invoke()?.asResolver()?.optional(silent = true)?.firstMethodOrNull {
            name = "getValue"
            emptyParameters()
            superclass()
        }?.invoke()?.asResolver()?.optional(silent = true) ?: return null
        val activePackage = metadata.firstMethodOrNull { name = "getPackageName"; emptyParameters() }?.invoke<String>() ?: return null
        if (packageName != null && activePackage != packageName) return null
        val uid = metadata.firstMethodOrNull { name = "getUid"; emptyParameters() }?.invoke<Int>() ?: return null
        // 图标加载也在 IO 协程调用，使用通知监听器快照，不读取仅限主线程的 NotifPipeline
        val component = globalContext?.asResolver()?.optional(silent = true)?.firstFieldOrNull {
            name = "mSysUIComponent"
            superclass()
        }?.get() ?: return null
        val provider = component.asResolver().optional(silent = true).firstFieldOrNull {
            name = "provideNotificationListenerProvider"
        }?.get() ?: return null
        val listener = provider.asResolver().optional(silent = true).firstMethodOrNull {
            name = "get"
            emptyParameters()
        }?.invoke<NotificationListenerService>() ?: return null
        // 带 keys 的重载直接读取系统快照，不经过插件可变的通知过滤器集合
        return listener.getActiveNotifications(null)?.filter {
            it.packageName == activePackage && AndroidVersion.require(AndroidVersion.Q, false) { it.uid == uid } &&
                it.notification.extras.containsKey(Notification.EXTRA_MEDIA_SESSION) &&
                it.notification.flags and Notification.FLAG_GROUP_SUMMARY == 0
        }?.maxByOrNull { it.postTime }
    }

    /** 清除展开角标自身及圆形底板的裁切，保留封面、角标尺寸和动画布局 */
    private fun clearControlCenterMediaIconStyle(header: View) {
        val iconView = header.asResolver().optional(silent = true).firstFieldOrNull {
            name = "appIcon"
        }?.get<ImageView>() ?: return
        iconView.apply {
            background = null
            colorFilter = null
            imageTintList = null
            outlineProvider = null
            clipToOutline = false
            setPadding(0)
        }
        iconView.parentOrNull()?.apply {
            // app_icon_container 的 CardView 圆形底板会继续裁切已合成的模块图标
            background = null
            outlineProvider = null
            clipToOutline = false
        }
    }

    /** 控制中心展开媒体面板的应用角标，与通知媒体卡片共用绘制，不修改封面和布局 */
    private fun installControlCenterMediaIcon(loader: ClassLoader, hookedClasses: MutableSet<Class<*>>) {
        if (isMIOS.not() || miosVersionCode < 2) return
        val headerClass = "com.android.systemui.QSControlMiPlayDetailHeader".toClassOrNull(loader) ?: return
        if (hookedClasses.add(headerClass).not()) return
        headerClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "getApplicationIconSafely"
            parameters(String::class)
        }?.hook()?.after {
            val packageName = firstArg().get<String>() ?: return@after
            val nf = controlCenterMediaNotification(loader, packageName) ?: return@after
            val header = instance<View>()
            val iconView = ImageView(header.context)
            bindComposedNotificationIcon(iconView, nf, isMediaIcon = true)
            iconView.drawable?.let {
                result = it
                if (Looper.myLooper() == Looper.getMainLooper()) clearControlCenterMediaIconStyle(header)
                else header.post { clearControlCenterMediaIconStyle(header) }
            }
        }
        headerClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "prepareShowPanel"
            emptyParameters()
        }?.hook()?.after { clearControlCenterMediaIconStyle(instance()) }
    }

    /** 使用宿主已有的插件加载器，兼顾首次加载和模块热重载，不创建另一份插件实例 */
    private fun installNotificationPluginIcons() {
        val hookedClasses = mutableSetOf<Class<*>>()
        fun hookPlugin(loader: ClassLoader) {
            installFocusNotificationIcons(loader, hookedClasses)
            installControlCenterMediaIcon(loader, hookedClasses)
        }
        fun hookLoadedPlugin() {
            val loaderClass = "com.miui.systemui.plugin.PluginInstanceInjector".toClassOrNull()
                ?: "com.android.systemui.shared.plugins.PluginInstance".toClassOrNull()
            loaderClass?.resolve()?.optional(silent = true)
                ?.firstFieldOrNull { name = "sClassLoaders" }?.get<Map<String, ClassLoader>>()
                ?.get("miui.systemui.plugin")?.let { hookPlugin(it) }
        }
        // OS2、OS3 在 Supplier 内创建加载器，OS4 改为 createClassLoader；都会返回插件 Context
        $$"com.android.systemui.shared.plugins.PluginInstance$PluginFactory".toClassOrNull()?.resolve()?.optional(silent = true)
            ?.firstMethodOrNull {
                name = "createPluginContext"
                emptyParameters()
            }?.hook()?.after {
                result<Context>()?.takeIf { it.packageName == "miui.systemui.plugin" }
                    ?.let { hookPlugin(it.classLoader) }
            }
        hookLoadedPlugin()
    }

    /**
     * 获取 [StatusBarIconViewClass] 实例是否为灰度图标 (单色图标)
     * @return [Boolean]
     */
    private fun ImageView.isGrayscaleIcon(): Boolean {
        // 获取 [StatusBarNotification] 实例
        val notifyInstance = asResolver().optional().firstFieldOrNull { name = "mNotification" }?.get<StatusBarNotification>() ?: return false
        // 通知组的实际图标已由开关选定，不能再用摘要原来的彩色 APP 图标决定反色
        if (notifyInstance.notification.flags and Notification.FLAG_GROUP_SUMMARY != 0)
            return drawable?.let { isGrayscaleIcon(context, it) } == true

        val appContext = context.createPackageContext(notifyInstance.notificationPackageName, Context.CONTEXT_IGNORE_SECURITY)

        // 获取通知小图标
        val iconDrawable = notifyInstance.notification?.smallIcon?.loadDrawable(appContext) ?: return false

        // 图标样式只根据 smallIcon 的实际像素内容判断
        val isGrayscaleIcon = isGrayscaleIcon(context, iconDrawable)

        // 获取目标修复彩色图标的 APP
        val isTargetFixApp = resolveRuleIcon(context, isGrayscaleIcon, notifyInstance.notificationPackageName).first != null
        // 只要不是灰度就返回彩色图标
        // 否则不对颜色进行反色处理防止一些系统图标出现异常
        return isTargetFixApp || isGrayscaleIcon
    }

    /**
     * 更新状态栏通知图标颜色
     * @param container 当前 [NotificationIconAreaControllerClass] 的实例
     * @param isDarkIconMode 是否为深色图标模式
     * @param animColor 动画过渡颜色
     */
    private fun updateStatusBarIconsColor(container: ViewGroup, isDarkIconMode: Boolean = this.isDarkIconMode, animColor: Int? = null) {
        if (container.isNotEmpty()) container.children.forEach { iconView ->
            if (iconView !is ImageView) return@forEach
            updateStatusBarIconColor(iconView, isDarkIconMode, animColor)
        }
    }

    /**
     * 更新状态栏每个通知图标的颜色
     * @param isDarkIconMode 是否为深色图标模式
     * @param animColor 动画过渡颜色
     */
    private fun updateStatusBarIconColor(iconView: ImageView, isDarkIconMode: Boolean = this.isDarkIconMode, animColor: Int? = null) {
        if (isMIOS && miosVersionCode >= 2) {
            applyPreparedStatusBarTint(iconView)
            return
        }
        if (iconView.isGrayscaleIcon()) {
            // 防止图标不是纯黑的问题
            // 图标在任何场景下跟随状态栏其它图标保持半透明
            iconView.alpha = if (animColor != null) 1f else statusBarIconAlpha
            iconView.setColorFilter(animColor ?: (if (isDarkIconMode) Color.BLACK else Color.WHITE))
        } else {
            iconView.alpha = 1f
            iconView.colorFilter = null
        }
    }

    /**
     * 更新状态栏通知图标透明度
     * @param container 当前 [NotificationIconContainerClass] 的实例
     */
    private fun updateStatusBarIconsAlpha(container: ViewGroup) {
        if (isMIOS && miosVersionCode >= 2) {
            container.children.filterIsInstance<ImageView>().forEach { applyPreparedStatusBarTint(it) }
            return
        }
        val iconStatesMap = container.asResolver().optional().firstFieldOrNull { name = "mIconStates" }?.get<HashMap<View, Any>>()
        if (container.isNotEmpty()) container.children.filterIsInstance<ImageView>().forEach { iconView ->
            val iconAlpha = if (iconView.isGrayscaleIcon()) statusBarIconAlpha else 1f
            iconView.alpha = iconAlpha
            iconStatesMap?.get(iconView)?.asResolver()?.optional()?.firstFieldOrNull {
                name { it == "alpha" || it == "mAlpha" }
                superclass()
            }?.set(iconAlpha)
        }
    }

    /**
     * Hook 状态栏通知图标最大数量
     * @param instance 被 Hook 的 Method 的实例
     */
    private fun applyStatusBarIconLimit(instance: Any) {
        val maxStaticIconsResolver = NotificationIconContainerClass.resolve().optional().firstFieldOrNull {
            name {
                /** 旧版名称 */
                val oldVersion = it == "mMaxStaticIcons"

                /** 旧版名称 */
                val oldVersion2 = it == "MAX_STATIC_ICONS"

                /** 新版本名称 */
                val newVersion = it == "mMaxIcons"
                oldVersion || oldVersion2 || newVersion
            }
        }?.of(instance) ?: return
        if (statusBarMaxStaticIcons == -1 ||
            // 系统设置内修改，模块同步更新
            moduleLastSetStatusBarMaxStaticIcons != maxStaticIconsResolver.get<Int>()) {
            statusBarMaxStaticIcons = maxStaticIconsResolver.get<Int>() ?: return
        }
        if (!ConfigData.isEnableLiftedStatusIconCount) {
            maxStaticIconsResolver.set(statusBarMaxStaticIcons)
            return
        }
        // 解除状态栏通知图标个数限制
        if (isShowNotificationIcons) {
            moduleLastSetStatusBarMaxStaticIcons = ConfigData.liftedStatusIconCount.let { if (it in 0..100) it else 5 }
            maxStaticIconsResolver.set(moduleLastSetStatusBarMaxStaticIcons)
        } else {
            maxStaticIconsResolver.set(0)
            moduleLastSetStatusBarMaxStaticIcons = 0
        }
    }

    /**
     * Hook 原生通知包装纸实例内容
     * @param wrapper 通知包装纸实例
     */
    private fun bindNativeNotificationIcon(wrapper: Any) {
        // 忽略较旧版本 - 在没有 MIUI 通知栏样式的时候可能出现奇怪的问题
        if (isNotHasAbsoluteMiuiStyle && isShowMiuiStyle) return

        // 获取小图标
        val iconImageView = notificationWrapperIconField?.copy()?.of(wrapper)?.get<ImageView>() ?: return

        // 获取 [ExpandableNotificationRowClass]
        val rowPair = wrapper.getNotificationRow()

        // 获取 [StatusBarNotification]
        val expandedNf = rowPair.second.getNotification()

        // 通知是否展开
        var isExpanded = rowPair.first

        // 获取优先级
        val importance = rowPair.second.getNotificationImportance(iconImageView.context, expandedNf)
        // 非最小化优先级的通知全部设置为展开状态
        if (importance != NotificationManager.IMPORTANCE_MIN) isExpanded = true
        // 执行 Hook
        bindNotificationIcon(iconImageView.context, expandedNf, iconImageView, isExpanded, isStockPanel = true)
    }

    /** 聊天通知保留系统头像，应用角标固定使用 M3 风格，普通通知继续适配主图标 */
    private fun bindClassicNotificationIcon(wrapper: Any) {
        val resolver = MiuiNotificationViewWrapperClass?.resolve()?.optional(silent = true) ?: return
        val nf = wrapper.getNotificationRow().second.getNotification() ?: return
        val iconView = resolver.firstFieldOrNull { name = "mAppIcon" }?.of(wrapper)?.get<ImageView>() ?: return
        val imType = MiuiBaseNotifUtilClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "getNotificationTypeForIm"
        }?.let {
            // OS2 仅接收通知，OS3 起额外接收 Context 以读取应用名单
            if (it.self.parameterCount == 1) it.invoke<Int>(nf.notification)
            else it.invoke<Int>(nf.notification, iconView.context)
        }
        if (imType != null && imType != -1) {
            // 公开布局由系统清空角标，不在锁屏上补画；私有布局与媒体角标共用合成画法
            val badgeView = resolver.firstFieldOrNull { name = "mAppImIcon" }?.of(wrapper)?.get<ImageView>()
            if (badgeView?.drawable != null) bindComposedNotificationIcon(badgeView, nf, isUseMaterial3Style = true)
            return
        }
        if (ConfigData.isEnableReplaceMiuiStyleNotifyIcon || ConfigData.isEnableNotifyIconForceAppIcon)
            bindNotificationIcon(iconView.context, nf, iconView, isMiuiPanel = true)
        else iconView.setImageDrawable(nf.miuiAppIcon?.loadDrawable(iconView.context) ?: iconView.context.appIconOf(nf.notificationPackageName))
    }

    /** 在组成员变化后，同步该组的面板和状态栏图标 */
    private fun refreshGroupNotificationIcons(container: Any?) {
        if (container == null) return
        val row = groupContainingRowMethod?.copy()?.of(container)?.invoke() ?: return
        val nf = row.getNotification() ?: return
        if (nf.notification.flags and Notification.FLAG_GROUP_SUMMARY == 0) return
        val header = groupHeaderMethod?.copy()?.of(container)?.invoke()
        val minimizedHeader = groupMinimizedHeaderMethod?.copy()?.of(container)?.invoke()
        listOfNotNull(header, minimizedHeader).forEach { bindNativeNotificationIcon(it) }
        if (ConfigData.isEnableReplaceMiuiStyleNotifyIcon || ConfigData.isEnableNotifyIconForceAppIcon) {
            val injector = groupInjectorField?.copy()?.of(container)?.get()
            val iconView = injector?.let { groupInjectorIconField?.copy()?.of(it)?.get<ImageView>() }
                ?: groupIconField?.copy()?.of(container)?.get<ImageView>()
            iconView?.let { bindNotificationIcon(it.context, nf, it, isMiuiPanel = true) }
        }
        if (isMIOS && miosVersionCode >= 2) {
            val manager = currentIconManager() ?: return
            val entry = row.getNotificationEntry() ?: return
            iconManagerUpdateIconsMethod?.copy()?.of(manager)?.invoke(entry)
        } else notificationIconContainer?.children?.filterIsInstance<ImageView>()?.forEach { iconView ->
            val source = statusIconNotificationField?.copy()?.of(iconView)?.get<StatusBarNotification>()
            if (source?.key == nf.key) refreshStatusBarIconView(iconView, isGroupRefresh = true)
        }
    }

    /**
     * 从 [NotificationViewWrapperClass] 中获取 [ExpandableNotificationRowClass]
     * @return [Pair] - ([Boolean] 通知是否展开,[Any] 通知 Row 实例)
     */
    private fun Any.getNotificationRow(): Pair<Boolean, Any?> {
        /** 通知是否展开 */
        var isExpanded = false

        /**
         * 从父类中得到 mRow 变量 - [ExpandableNotificationRowClass]
         * 获取其中的得到通知方法
         */
        val row = notificationWrapperRowField?.copy()?.of(this)?.get()?.also {
            isExpanded = notificationRowExpandedMethod?.copy()?.of(it)?.invoke<Boolean>(false) == true
            if (!isExpanded) isExpanded = notificationRowExpandedFallbackMethod?.copy()?.of(it)?.invoke<Boolean>() == true
        }; return isExpanded to row
    }

    /**
     * 从 [ExpandableNotificationRowClass] 中获取通知 Entry 或 EntryAdapter
     * @return Entry 实例 or null
     */
    private fun Any?.getNotificationEntry() = this?.let { row ->
        notificationRowEntryMethod?.copy()?.of(row)?.invoke()
            ?: notificationRowEntryField?.copy()?.of(row)?.get()
            ?: notificationRowEntryAdapterMethod?.copy()?.of(row)?.invoke()
            ?: notificationRowEntryAdapterField?.copy()?.of(row)?.get()
    }

    /**
     * 从通知 Ranking 中获取优先级，Android 17 不再允许 SystemUI 查询其它包的 Channel
     * @return 通知优先级 or null
     */
    private fun Any?.getNotificationImportance(context: Context, sbn: StatusBarNotification?): Int? {
        val ranking = getNotificationEntry()?.let { entry ->
            if (notificationEntryClass?.isInstance(entry) == true)
                notificationEntryRankingMethod?.copy()?.of(entry)?.invoke<NotificationListenerService.Ranking>()
                    ?: notificationEntryRankingField?.copy()?.of(entry)?.get<NotificationListenerService.Ranking>()
            else entry.asResolver().optional(silent = true).let { resolver ->
                resolver.firstMethodOrNull {
                    name = "getRanking"
                    emptyParameters()
                }?.invoke<NotificationListenerService.Ranking>()
                    ?: resolver.firstFieldOrNull { name = "mRanking" }?.get<NotificationListenerService.Ranking>()
            }
        }
        safeOfNull { ranking?.channel?.importance }?.let { return it }
        if (AndroidVersion.isAtLeast(AndroidVersion.CINNAMON_BUN)) return null
        return safeOfNull {
            (context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager?)
                ?.getNotificationChannel(sbn?.notification?.channelId)?.importance
        }
    }

    /**
     * 从 [ExpandableNotificationRowClass] 中获取 [StatusBarNotification]
     * @return [StatusBarNotification] or null
     */
    private fun Any?.getNotification() =
        getNotificationEntry()?.let { entry ->
            if (notificationEntryClass?.isInstance(entry) == true)
                notificationEntrySbnMethod?.copy()?.of(entry)?.invoke<StatusBarNotification>()
                    ?: notificationEntrySbnField?.copy()?.of(entry)?.get<StatusBarNotification>()
            else entry.asResolver().optional(silent = true).let { resolver ->
                resolver.firstMethodOrNull {
                    name = "getSbn"
                    emptyParameters()
                }?.invoke<StatusBarNotification>()
                    ?: resolver.firstFieldOrNull { name = "mSbn" }?.get<StatusBarNotification>()
            }
        } ?: ExpandableNotificationRowClass.resolve()
            .optional(silent = true)
            .firstMethodOrNull { name = "getStatusBarNotification" }
            ?.of(this)?.invoke<StatusBarNotification>()

    /** 注册生命周期 */
    private fun registerLifecycle() {
        registerAppLifecycle {
            registerReceiver(IconRuleManagerTool.ACTION_CANCEL_SYNC, exported = false) { context, _ ->
                legacyIconRuleUpdateJob?.cancel()
                IconRuleManagerTool.dismissSyncing(context)
            }
            // 解锁后只刷新缓存路径的状态栏图标；面板图标由绑定回调处理，避免重建全部通知
            registerReceiver(Intent.ACTION_USER_PRESENT) { _, _ ->
                if (isUsingCachingMethod) refreshStatusBarIcons()
            }
            // 注册定时监听
            registerReceiver(Intent.ACTION_TIME_TICK) { context, _ ->
                if (ConfigData.isEnableNotifyIconFix && ConfigData.isEnableNotifyIconFixAuto) {
                    val timestamp = System.currentTimeMillis()
                    val nowTime = timestamp.stampToDate(format = "HH:mm")
                    val today = timestamp.stampToDate(format = "yyyy-MM-dd")
                    // 息屏 Doze 时 TIME_TICK 会被合并为几分钟一次，可能错过设定的那一分钟，
                    // 所以过了设定时间后当天的任意一次 TIME_TICK 都可以补触发，两者均为补零的 "HH:mm" 可直接比较
                    val preferences = preferences.native()
                    if (nowTime >= ConfigData.notifyIconFixAutoTime && today != preferences.getString(LAST_AUTO_UPDATE_DATE)) {
                        preferences.edit { putString(LAST_AUTO_UPDATE_DATE, today) }
                        requestIconRuleUpdate(context)
                    }
                }
            }
            // 注册发送适配新的 APP 图标通知监听
            registerReceiver(IntentFilter().apply {
                addDataScheme("package")
                addAction(Intent.ACTION_PACKAGE_ADDED)
                addAction(Intent.ACTION_PACKAGE_REPLACED)
                addAction(Intent.ACTION_PACKAGE_REMOVED)
            }) { context, intent ->
                intent.data?.schemeSpecificPart?.also { packageName ->
                    if (intent.action.equals(Intent.ACTION_PACKAGE_REPLACED)) ActivationPromptTool.prompt(
                        context, packageName,
                        isReloaded = moduleLastUpdateTime != null && context.appLastUpdateTimeOf(packageName) == moduleLastUpdateTime,
                        resources = module.resources
                    )
                    if (intent.action.equals(Intent.ACTION_PACKAGE_REPLACED).not() &&
                        intent.getBooleanExtra(Intent.EXTRA_REPLACING, false)
                    ) return@registerReceiver
                    if (ConfigData.isEnableNotifyIconFix && ConfigData.isEnableNotifyIconFixNotify)
                        when (intent.action) {
                            Intent.ACTION_PACKAGE_ADDED -> {
                                if (IconRuleManagerTool.getIcon(packageName) == null)
                                    IconAdaptationTool.pushNewAppSupportNotify(context, packageName, module.resources)
                            }
                            Intent.ACTION_PACKAGE_REMOVED -> IconAdaptationTool.removeNewAppSupportNotify(context, packageName)
                        }
                }
            }
        }
        // 宿主首次就绪和模块热重载后统一初始化
        withApplicationReady { application ->
            moduleLastUpdateTime = application.appLastUpdateTimeOf(BuildConfigWrapper.APPLICATION_ID)
            iconRuleScope.launch {
                restoreSettingsManager()
                if (isMIOS && miosVersionCode.toInt() == 2) restoreOs2StatusBarIconTint(application)
                // 从模块共享文件恢复 ANIP 快照
                globalContext?.let { refreshIconData(it) }
                if (ActivationPromptTool.isPrompting(application))
                    ActivationPromptTool.prompt(application, BuildConfigWrapper.APPLICATION_ID, isReloaded = true, resources = module.resources)
            }
        }
        // 模块页面进入前台后移除更新激活提醒通知
        SystemUITool.Host.onDismissActivationPrompt(param = this) {
            globalContext?.let { ActivationPromptTool.dismiss(it) }
        }
        // 刷新图标缓存
        SystemUITool.Host.onRefreshSystemUI(param = this) { refreshPreferences(it) }
        // 模块获取资源并写入共享文件后发送刷新命令，SystemUI 从共享文件重新装载
        SystemUITool.Host.onRefreshIconData(param = this) { version, result ->
            refreshPreferences(isRefreshCacheOnly = true)
            globalContext?.let { refreshIconData(it, requestedVersion = version, result = result) } ?: result(false)
        }
        // 模块获取已安装的应用，用于展示通知图标优化名单的适配情况
        SystemUITool.Host.onPushInstalledApps(param = this) {
            globalContext?.let { IconAdaptationTool.obtainInstalledApps(it) } ?: arrayListOf()
        }
        // 模块获取未收录应用的图标
        SystemUITool.Host.onPushAppIcons(param = this) { packageName ->
            globalContext?.let { IconAdaptationTool.obtainAppIconData(it, packageName) }
        }
        // 模块的定时更新服务已启动，后续结果由模块自行通知
        SystemUITool.Host.onIconRuleUpdateStarted(param = this) {
            iconRuleUpdateFallbackJob?.cancel()
            iconRuleUpdateFallbackJob = null
        }
    }

    /** OS2 热重载时，系统不会再次分发当前颜色，直接读取状态栏已有的状态 */
    private fun restoreOs2StatusBarIconTint(context: Context) {
        val dispatcherClass = DarkIconDispatcherClass ?: return
        val windowControllerClass = StatusBarWindowControllerClass ?: return
        val dependency = DependencyClass?.resolve()?.optional(silent = true)?.firstFieldOrNull { name = "sDependency" }?.getQuietly() ?: return
        val getDependency = dependency.asResolver().optional(silent = true).firstMethodOrNull {
            name = "getDependencyInner"
            parameters(Any::class)
        } ?: return
        val dispatcher = getDependency.invokeQuietly(dispatcherClass)?.asResolver()?.optional(silent = true) ?: return
        val intensity = dispatcher.firstFieldOrNull { name = "mDarkIntensity" }?.getQuietly<Float>() ?: return
        isDarkIconMode = intensity == 1f
        val window = getDependency.invokeQuietly(windowControllerClass)?.asResolver()?.optional(silent = true)
            ?.firstFieldOrNull { name = "mStatusBarWindowView" }?.getQuietly<View>() ?: return
        val iconsId = context.resources.getIdentifier("notificationIcons", "id", PackageName.SYSTEMUI)
        val icons = window.findViewById<ViewGroup>(iconsId) ?: return
        notificationIconContainer = icons
        val animColor = if (intensity > 0f && intensity < 1f)
            dispatcher.firstFieldOrNull { name = "mIconTint" }?.getQuietly<Int>() else null
        updateStatusBarIconsColor(icons, animColor = animColor)
    }

    /**
     * 热重载后设置管理器的构造方法不会再次执行，[settingsManager] 会一直为空，通知图标无法刷新
     *
     * 设置管理器是单例，这里从系统的依赖获取入口中找回已存在的实例
     */
    private fun restoreSettingsManager() = runInSafe {
        if (settingsManager != null || SettingsManagerClass == null) return@runInSafe
        settingsManager = InterfacesImplManagerClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "getImpl"
            parameters(Class::class)
        }?.invokeQuietly(SettingsManagerClass) ?: MiuiDependencyClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "get"
            parameters(Class::class)
        }?.invokeQuietly(SettingsManagerClass)
        if (settingsManager == null) YLog.warn("Failed to restore settings manager after reloaded")
    }

    /** 释放当前模块实例的宿主任务、监听和私有协程线程 */
    fun release() {
        wallpaperColorsListener?.let { (manager, listener) -> manager.removeOnColorsChangedListener(listener) }
        wallpaperColorsListener = null
        val job = iconRuleScope.coroutineContext.job
        job.invokeOnCompletion {
            IconRuleManagerTool.release()
            iconRuleUpdateFallbackJob = null
            legacyIconRuleUpdateJob = null
            notificationIconContainer = null
            notificationPresenter = null
            settingsManager = null
            // 完成回调可能运行在调度线程上，另起普通线程关闭，避免阻塞主线程或等待自己退出
            thread(name = "MIUINativeNotifyIcon-dispose") {
                // 只能关闭当前模块加载的协程副本，不能关闭宿主共用的运行时
                @OptIn(DelicateCoroutinesApi::class)
                if (classOf<Dispatchers>().classLoader === classOf<SystemUIHooker>().classLoader) Dispatchers.shutdown()
                else YLog.warn("Skipping coroutine shutdown: the runtime is shared with the host")
            }
        }
        job.cancel()
    }

    /**
     * 启动模块的定时更新服务，SystemUI 只能读取共享文件，获取资源需要交给模块进程
     *
     * 无法启动或超时未响应时提醒用户手动同步，legacy 运行时回退到 [refreshLegacyIconRules]
     * @param context SystemUI 上下文
     */
    private fun requestIconRuleUpdate(context: Context) {
        if (isSharedCacheAvailable.not()) return refreshLegacyIconRules(context, isNotifyResult = true)
        /** 提醒用户手动同步 */
        fun notifyManualUpdate() = runCatching {
            IconRuleManagerTool.notifyFetchResult(
                context, Anip.FetchResult(module.resources.getString(R.string.sync_cannot_start_module), Anip.FetchResult.Status.FAILED),
                module.resources
            )
        }.onFailure { YLog.error("Failed to notify icon resource update", it) }
        iconRuleUpdateFallbackJob?.cancel()
        runCatching {
            context.startForegroundService(Intent().apply {
                component = ComponentName(BuildConfigWrapper.APPLICATION_ID, classOf<IconRuleUpdateService>().name)
            })
        }.onFailure {
            YLog.error("Failed to start icon rule update service", it)
            notifyManualUpdate()
            return
        }
        iconRuleUpdateFallbackJob = iconRuleScope.launch {
            delay(ICON_RULE_UPDATE_TIMEOUT.milliseconds)
            YLog.warn("Icon rule update service did not respond")
            notifyManualUpdate()
        }
    }

    /**
     * 从模块共享文件重新装载 SystemUI 进程的 ANIP 快照，legacy 运行时回退到 [refreshLegacyIconRules]
     * @param context SystemUI 上下文
     * @param requestedVersion 模块已获取的仓库版本，已生效时直接确认
     * @param result 装载完成回调
     */
    private fun refreshIconData(
        context: Context,
        requestedVersion: Pair<String, Long>? = null,
        result: (Boolean) -> Unit = {}
    ) {
        if (isSharedCacheAvailable.not()) return refreshLegacyIconRules(context, requestedVersion, result = result)
        iconRuleScope.launch {
            if (requestedVersion != null && IconRuleManagerTool.hasSnapshotVersion(requestedVersion)) {
                result(true)
                return@launch
            }
            val previousVersion = IconRuleManagerTool.snapshotVersion
            runCatching {
                IconRuleManagerTool.reload(context)
                val isAvailable = IconRuleManagerTool.icons.isNotEmpty()
                if (isAvailable && previousVersion != IconRuleManagerTool.snapshotVersion) {
                    refreshStatusBarIcons()
                    refreshNotificationIcons()
                }
                result(isAvailable && (requestedVersion == null || IconRuleManagerTool.hasSnapshotVersion(requestedVersion)))
            }.onFailure {
                if (it is CancellationException) throw it
                YLog.error("Failed to reload ANIP icon resources", it)
                result(false)
            }
        }
    }

    /**
     * 回退：legacy 运行时没有模块共享文件，SystemUI 恢复自己的私有缓存并自行获取资源
     * @param context SystemUI 上下文
     * @param requestedVersion 模块已获取的仓库版本，已生效时直接确认
     * @param isNotifyResult 是否发送定时同步结果通知
     * @param result 更新完成回调
     */
    private fun refreshLegacyIconRules(
        context: Context,
        requestedVersion: Pair<String, Long>? = null,
        isNotifyResult: Boolean = false,
        result: (Boolean) -> Unit = {}
    ) {
        if (isNotifyResult && legacyIconRuleUpdateJob?.isCompleted == false) return
        val job = iconRuleScope.launch {
            if (requestedVersion != null && IconRuleManagerTool.hasSnapshotVersion(requestedVersion)) {
                result(true)
                return@launch
            }
            if (isNotifyResult) IconRuleManagerTool.notifySyncing(context, module.resources)
            try {
                val previousVersion = IconRuleManagerTool.snapshotVersion
                val fetchResult = runCatching {
                    IconRuleManagerTool.reload(context)
                    IconRuleManagerTool.fetch(context, module.resources).also {
                        coroutineContext.ensureActive()
                        val isAvailable = IconRuleManagerTool.icons.isNotEmpty()
                        if (isAvailable && previousVersion != IconRuleManagerTool.snapshotVersion) {
                            refreshStatusBarIcons()
                            refreshNotificationIcons()
                        }
                        result(it.isOk && isAvailable &&
                            (requestedVersion == null || IconRuleManagerTool.hasSnapshotVersion(requestedVersion)))
                    }
                }.getOrElse {
                    if (it is CancellationException) throw it
                    YLog.error("Failed to refresh ANIP icon resources", it)
                    result(false)
                    Anip.FetchResult(it.message ?: "Failed to get ANIP icon resources", Anip.FetchResult.Status.FAILED)
                }
                if (isNotifyResult) runCatching { IconRuleManagerTool.notifyFetchResult(context, fetchResult, module.resources) }
                    .onFailure { YLog.error("Failed to notify icon resource update", it) }
            } finally {
                if (isNotifyResult) {
                    IconRuleManagerTool.dismissSyncing(context)
                    legacyIconRuleUpdateJob = null
                }
            }
        }
        if (isNotifyResult) legacyIconRuleUpdateJob = job
    }

    /**
     * 刷新缓存数据
     * @param isRefreshCacheOnly 仅刷新缓存不刷新图标和通知改变 - 默认：否
     * @return [Boolean] 是否成功
     */
    private fun refreshPreferences(isRefreshCacheOnly: Boolean = false): Boolean {
        // 必要的延迟防止 Sp 存储不刷新
        SystemClock.sleep(300)
        // 获取可读写状态
        return preferences.isAvailable.also {
            isUsingCachingMethod = true
            if (isRefreshCacheOnly) return@also
            refreshStatusBarIcons()
            refreshNotificationIcons()
            notificationIconContainer?.let { container ->
                if (isMIOS && miosVersionCode >= 2) restoreStatusBarIconLimit(container)
                updateStatusBarIconsAlpha(container)
            }
        }
    }

    override fun onHook() {
        if (isSharedCacheAvailable) IconRuleManagerTool.attachHost(sharedFiles)
        registerLifecycle()
        installHostReferences()
        installNotificationStatisticsFix()
        installNativeIconPolicies()
        installNativeNotificationBindings()
        installStatusBarNotificationBindings()
        installGroupNotificationUpdates()
        installMediaNotificationBindings()
        installNotificationPluginIcons()
        installRemoteFocusNotificationBindings()
        installLegacyStatusBarIcons()
        installLegacyNotificationPanel()
        installFocusTickerIcons()
        installStatusBarIconCount()
        installFoldNotificationIcons()
        installAodNotificationIcons()
    }

    private fun installNativeIconPolicies() {
        NotificationUtilClass.resolve().optional().method {
            name { it == "shouldSubstituteSmallIcon" || it == "shouldSubstituteSmallIconForStatusBarNotification" }
            parameters { it.firstOrNull()?.isSubclassOf(StatusBarNotification::class) == true }
        }.hookAll().intercept(false)
        NotificationIconStyleProviderImplClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "shouldShowAppIcon"
            parameterCount = 2
            returnType = Boolean::class
        }?.hook()?.intercept(false)
        listOfNotNull(
            NotificationContentInflaterInjectorClass,
            "${PackageName.SYSTEMUI}.statusbar.notification.row.NotificationRowContentBinderInjectorImpl".toClassOrNull()
        ).forEach { injectorClass ->
            injectorClass.resolve().optional(silent = true).method {
                name { it == "handleAppIcon" || it == "handleAppIconInternal" }
                parameterCount { it in 2..5 }
            }.hookAll().apply {
                before {
                    if (lastArg().get() != true) result = null
                }
                after {
                    if (isMIOS.not() || miosVersionCode < 2 || lastArg().get() != true) return@after
                    val views = firstArg().get<RemoteViews>() ?: return@after
                    val notification = arg(1).get<Notification>() ?: return@after
                    val context = arg(2).get<Context>() ?: return@after
                    removeClassicAppIconActions(views, notification, context)
                }
            }
            if (isMIOS && miosVersionCode >= 2) injectorClass.resolve().optional(silent = true).firstMethodOrNull {
                name = "createMiuiPublicView"
                parameters(Notification.Builder::class, Context::class)
            }?.hook()?.after {
                val views = result<RemoteViews>() ?: return@after
                val builder = firstArg().get<Notification.Builder>() ?: return@after
                val context = lastArg().get<Context>() ?: return@after
                // 公开模板在 handleAppIcon 之后还会直接写入一次 APP 图标
                val notification = notificationBuilderNotificationField.copy().of(builder).get<Notification>() ?: return@after
                removeClassicAppIconActions(views, notification, context)
            }
        }
        NotificationUtilClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "applyAppIconAllowCustom"
            parameterCount = 4
        }?.hook()?.before {
            val target = arg(2).get<ImageView>() ?: return@before
            val nativeIconId = target.resources.getIdentifier("icon", "id", "android")
            if (target.id == nativeIconId && target.isNotificationPanelIcon()) {
                result = null
                return@before
            }
            if (!isMIOS) return@before
            val resourceName = if (target.id != View.NO_ID && target.id != 0) target.resources.getResourceEntryName(target.id) else null
            if (resourceName != "app_icon" && resourceName != "app_im_icon") return@before
            if (!target.isNotificationPanelIcon()) return@before
            val notification = arg(1).get<StatusBarNotification>() ?: return@before
            if (resourceName == "app_icon" && notification.notification.flags and Notification.FLAG_GROUP_SUMMARY == 0) {
                val chatType = MiuiBaseNotifUtilClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
                    name = "getNotificationTypeForIm"
                }?.let {
                    if (it.self.parameterCount == 1) it.invoke<Int>(notification.notification)
                    else it.invoke<Int>(notification.notification, target.context)
                }
                // 聊天主图由宿主选择头像或公开 APP 图，模块只接管独立角标
                if (chatType != null && chatType != -1) return@before
            }
            if (resourceName == "app_im_icon")
                bindComposedNotificationIcon(target, notification, isUseMaterial3Style = true)
            else if (ConfigData.isEnableReplaceMiuiStyleNotifyIcon || ConfigData.isEnableNotifyIconForceAppIcon)
                bindNotificationIcon(target.context, notification, target, isMiuiPanel = true)
            else target.setImageDrawable(notification.miuiAppIcon?.loadDrawable(target.context)
                ?: target.context.appIconOf(notification.notificationPackageName))
            result = null
        }
    }

    private fun removeClassicAppIconActions(views: RemoteViews, notification: Notification, context: Context) {
        val chatType = notificationChatTypeMethod?.let {
            if (it.self.parameterCount == 1) it.invoke<Int>(notification)
            else it.invoke<Int>(notification, context)
        }
        if (notification.flags and Notification.FLAG_GROUP_SUMMARY == 0 && chatType != null && chatType != -1) return
        val iconId = context.resources.getIdentifier("app_icon", "id", PackageName.SYSTEMUI)
        if (iconId == 0) return
        // 系统模板先清空再写 APP 图标；在布局应用前移除这两类指令，保留聊天头像、角标和其它内容
        val actions = remoteViewsActionsField.copy().of(views).get<MutableList<Any>>() ?: return
        actions.removeAll { action ->
            if (remoteViewsActionIdField.copy().of(action).get<Int>() != iconId) return@removeAll false
            val method = when {
                remoteViewsBitmapActionClass.isInstance(action) -> remoteViewsBitmapMethodField.copy().of(action).get<String>()
                remoteViewsReflectionActionClass.isInstance(action) -> remoteViewsReflectionMethodField.copy().of(action).get<String>()
                else -> null
            }
            method == "setImageBitmap" || method == "setImageIcon"
        }
    }

    private fun View.isNotificationPanelIcon(): Boolean {
        var current: View? = this
        while (current != null) {
            if (ExpandableNotificationRowClass.isInstance(current) || NotificationChildrenContainerClass.isInstance(current))
                return true
            current = current.parent as? View
        }
        return false
    }

    private fun installNativeNotificationBindings() {
        NotificationHeaderViewWrapperClass.resolve().optional().apply {
            val resolveHeader = firstMethodOrNull {
                name { it == "resolveHeaderViews" || it == "handleHeaderViews" || it == "resolveViews" }
            }
            resolveHeader?.hook()?.after { bindNativeNotificationIcon(instance) }
            if (resolveHeader?.self?.name != "resolveHeaderViews") firstMethodOrNull {
                name = "onContentUpdated"
            }?.hook()?.after { bindNativeNotificationIcon(instance) }
        }
        // 最小化组头重用后不保证收到普通内容回调，只处理容器当前持有的同一个 wrapper
        NotificationViewWrapperClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "onReinflated"
            emptyParameters()
        }?.hook()?.after {
            if (!NotificationHeaderViewWrapperClass.isInstance(instance)) return@after
            val row = notificationWrapperRowField?.copy()?.of(instance)?.get() ?: return@after
            val container = rowChildrenContainerMethod?.copy()?.of(row)?.invoke() ?: return@after
            if (groupMinimizedHeaderMethod?.copy()?.of(container)?.invoke() === instance)
                bindNativeNotificationIcon(instance)
        }
    }

    private fun currentIconManager(): Any? {
        val context = globalContext ?: return null
        val component = systemUIComponentField?.copy()?.of(context)?.get() ?: return null
        val provider = iconManagerProviderField?.copy()?.of(component)?.get() ?: return null
        return iconManagerProviderGetMethod?.copy()?.of(provider)?.invoke()
    }

    private fun applyPreparedStatusBarTint(view: ImageView) {
        val state = view.getTag<Int>(R.id.status_icon_render_state) ?: return
        if (state and 2 != 0) {
            view.imageTintList = null
            view.colorFilter = null
        }
        // 容器动画拥有 View.alpha，图标内容透明度独立处理
        view.imageAlpha = ((if (state and 1 != 0) statusBarIconAlpha else 1f) * 255).toInt()
    }

    private fun installStatusBarNotificationBindings() {
        if (isMIOS.not() || miosVersionCode < 2) return
        val managerClass = "${PackageName.SYSTEMUI}.statusbar.notification.icon.IconManager".toClassOrNull() ?: return
        managerClass.resolve().apply {
            val important = firstMethod { name = "isImportantConversation"; parameterCount = 1 }
            val createDescriptor = firstMethod { name = "toStatusBarIcon"; parameterCount = 3 }
            val iconType = createDescriptor.self.parameterTypes[2]?.enumConstants?.first { (it as Enum<*>).name == "NotifSmallIcon" }
            firstMethod { name = "getIconDescriptor"; parameterCount = 2 }.hook().before {
                val entry = firstArg().get() ?: return@before
                if (important.copy().of(instance).invoke<Boolean>(entry) == true) return@before
                val entryResolver = entry.asResolver()
                val notification = entryResolver.firstField { name = "mSbn" }.get<StatusBarNotification>() ?: return@before
                val icons = entryResolver.firstField { name = "mIcons" }.get() ?: return@before
                val cached = icons.asResolver().firstField { name = "mSmallIconDescriptor" }
                result = cached.get() ?: createDescriptor.copy().of(instance).invoke(notification.notification.smallIcon, entry, iconType)
                    ?.also { cached.set(it) }
            }
            firstMethod { name = "setIcon"; parameterCount = 3 }.hook().apply {
                before {
                    val entry = firstArg().get() ?: return@before
                    val view = lastArg().get<ImageView>() ?: return@before
                    view.parentOrNull()?.takeIf { NotificationIconContainerClass.isInstance(it) }
                        ?.also { notificationIconContainer = it }
                    // OS2 无准备状态的旧图标可能仍保留 View.alpha，首次接管时移交给容器动画
                    if (miosVersionCode.toInt() == 2 && view.getTag<Int>(R.id.status_icon_render_state) == null) view.alpha = 1f
                    if (important.copy().of(instance).invoke<Boolean>(entry) == true) {
                        if (view.getTag<Int>(R.id.status_icon_render_state) != null) view.imageAlpha = 255
                        view.setTag(R.id.status_icon_render_state, null)
                        return@before
                    }
                    val notification = entry.asResolver().firstField { name = "mSbn" }.get<StatusBarNotification>() ?: return@before
                    val original = notification.notification.smallIcon
                    val (drawable, replaced) = resolveStatusBarIcon(view.context, notification, original.loadDrawable(view.context))
                    val descriptor = arg(1).get() ?: return@before
                    val descriptorResolver = descriptor.asResolver()
                    descriptorResolver.firstField { name = "icon" }.set(
                        if (replaced && drawable != null) Icon.createWithBitmap(drawable.toStatusBarIconBitmap(view.context)) else original
                    )
                    descriptorResolver.optional(silent = true).firstFieldOrNull { name = "preloadedIcon" }?.set(null)
                    val colored = drawable?.let { isGrayscaleIcon(view.context, it).not() } == true
                    view.setTag(R.id.status_icon_render_state, (if (!colored) 1 else 0) or (if (colored) 2 else 0))
                    registerWallpaperColorChanged(view)
                }
                after { lastArg().get<ImageView>()?.let { applyPreparedStatusBarTint(it) } }
            }
        }
        StatusBarIconViewClass.resolve().optional(silent = true).apply {
            firstMethodOrNull { name = "updateIconColor"; emptyParameters() }?.hook()?.after {
                applyPreparedStatusBarTint(instance())
            }
            method { name { it == "onDarkChanged" || it == "updateLightDarkTint" }; parameterCount { it >= 3 } }
                .hookAll().after {
                    val intensity = arg(1).get<Float>() ?: return@after
                    if (intensity == 0f || intensity == 1f) isDarkIconMode = intensity == 1f
                    applyPreparedStatusBarTint(instance())
                }
        }
        iconRuleScope.launch { refreshStatusBarIcons() }
    }

    private fun installGroupNotificationUpdates() {
        val movingChild = ThreadLocal<Boolean>()
        "${PackageName.SYSTEMUI}.statusbar.notification.row.ExpandableNotificationRowController".toClassOrNull()
            ?.resolve()?.optional(silent = true)?.firstMethodOrNull {
                name = "moveChildTo"
                parameterCount = 2
            }?.hook()?.apply {
                before { movingChild.set(true) }
                after {
                    movingChild.remove()
                    val row = instance.asResolver().firstField { name = "mView" }.get() ?: return@after
                    val container = rowChildrenContainerMethod?.copy()?.of(row)?.invoke() ?: return@after
                    refreshGroupNotificationIcons(container)
                }
            }
        ExpandableNotificationRowClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "onAttachedChildrenCountChanged"
            emptyParameters()
        }?.hook()?.after {
            if (movingChild.get() == true) return@after
            val row = rowParentMethod?.copy()?.of(instance)?.invoke() ?: instance
            val container = rowChildrenContainerMethod?.copy()?.of(row)?.invoke() ?: return@after
            if ((container as View).isAttachedToWindow.not()) return@after
            refreshGroupNotificationIcons(container)
        }
    }

    private fun installMediaNotificationBindings() {
        if (isMIOS && miosVersionCode.toInt() == 2)
            MiuiMediaControlPanelClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
                name = "bindPlayer"
                parameterCount = 2
            }?.hook()?.after { bindLegacyMediaNotificationIcon(instance) }
        listOfNotNull(MiuiMediaViewControllerImplClass, MiuiIslandMediaViewBinderImplClass).forEach { controllerClass ->
            controllerClass.resolve().optional(silent = true).firstMethodOrNull {
                name = "bindMediaData"
                parameterCount = 1
            }?.hook()?.after { bindMediaNotificationIcons(instance) }
        }
        iconRuleScope.launch { refreshMediaNotificationIcons() }
    }

    private fun installLegacyNotificationPanel() {
        if (isMIOS) return
        MiuiNotificationViewWrapperClass?.resolve()?.optional()?.apply {
            constructor {}.hookAll().after { bindClassicNotificationIcon(instance) }
            firstMethodOrNull { name = "onContentUpdated"; parameterCount = 1 }
                ?.hook()?.after { bindClassicNotificationIcon(instance) }
        }
        MiuiNotificationChildrenContainerClass?.resolve()?.optional()?.firstMethodOrNull {
            name = "updateAppIcon"
            parameters(Boolean::class)
        }?.hook()?.after {
            val row = groupContainingRowMethod?.copy()?.of(instance)?.invoke() ?: return@after
            val notification = row.getNotification() ?: return@after
            val icon = groupIconField?.copy()?.of(instance)?.get<ImageView>() ?: return@after
            if (ConfigData.isEnableReplaceMiuiStyleNotifyIcon || ConfigData.isEnableNotifyIconForceAppIcon)
                bindNotificationIcon(icon.context, notification, icon, isMiuiPanel = true)
            else icon.setImageDrawable(notification.miuiAppIcon?.loadDrawable(icon.context)
                ?: icon.context.appIconOf(notification.notificationPackageName))
        }
        NotificationHeaderViewWrapperInjectorClass?.resolve()?.optional(silent = true)?.apply {
            firstMethodOrNull { name = "setAppIcon"; parameters(Context::class, ImageView::class, ExpandedNotificationClass) }
                ?.hook()?.intercept()
            firstMethodOrNull { name = "setAppIcon"; parameters(ImageView::class, ExpandedNotificationClass) }
                ?.hook()?.intercept()
            firstMethodOrNull { name = "resetIconBgAndPaddings"; parameters(ImageView::class, ExpandedNotificationClass) }
                ?.hook()?.intercept()
        }
    }

    private fun installLegacyStatusBarIcons() {
        if (isMIOS && miosVersionCode >= 2) return
        // 注入 MIUI 自己增加的一个工具类
        NotificationUtilClass.run {
            var hasLeadingContext = false
            // 强制修改 getCustomAppIcon 获取的图标为 smallIcon
            // 部分系统没有 "getCustomAppIcon" 这个方法 - 所以直接忽略
            resolve().optional(silent = true).firstMethodOrNull {
                name = "getCustomAppIcon"
                parameters(Notification::class, Context::class)
            }?.hook()?.after {
                val nf = firstArg().get<Notification>() ?: return@after
                val context = lastArg().get<Context>() ?: return@after
                if (foldEntranceIconContext.get() != null) return@after
                val appName = nf.extras?.getString("miui.opPkg")
                if (appName != null) {
                    val appContext = context.createPackageContext(appName, Context.CONTEXT_IGNORE_SECURITY)
                    val iconBitmap = nf.smallIcon?.loadDrawable(appContext)?.toStatusBarIconBitmap(context)
                    result = if (iconBitmap != null && !iconBitmap.isRecycled)
                        iconBitmap.toDrawable(context.resources)
                    else null
                }
            }
            // 强制回写系统的状态栏图标样式为原生
            // 部分系统没有 "getSmallIcon" 这个方法 - 所以直接忽略
            val getSmallIconCondition1 = MethodCondition<Any>().apply {
                name = "getSmallIcon"
                parameters { it[0] isSubclassOf StatusBarNotification::class && it[1] == classOf<Int>() }
            }
            val getSmallIconCondition2 = MethodCondition<Any>().apply {
                name = "getSmallIcon"
                parameters(ExpandedNotificationClass)
            }
            val getSmallIconCondition3 = MethodCondition<Any>().apply {
                name = "getSmallIcon"
                parameters { it[0] == classOf<Context>() && it[1] isSubclassOf StatusBarNotification::class }
            }
            val getSmallIconResolver = resolve().optional(silent = true)
            val getSmallIcon = getSmallIconResolver.firstMethodOrNull(getSmallIconCondition1)
                ?: getSmallIconResolver.firstMethodOrNull(getSmallIconCondition2)
                ?: getSmallIconResolver.firstMethodOrNull(getSmallIconCondition3)
                    ?.also { hasLeadingContext = true }
            getSmallIcon?.hook()?.after {
                (globalContext ?: firstArg().get<Context?>())?.also { context ->
                    val expandedNf = arg(if (hasLeadingContext) 1 else 0).get<StatusBarNotification?>()
                    val smallIcon = result<Icon>()
                    resolveStatusBarIcon(
                        context = context,
                        nf = expandedNf,
                        iconDrawable = smallIcon?.loadDrawable(context)
                    ).also { (drawable, replaced) ->
                        if (replaced && drawable != null) result = Icon.createWithBitmap(drawable.toStatusBarIconBitmap(context))
                    }
                }
            }
        }
        // 注入状态栏通知图标实例
        StatusBarIconViewClass.resolve().optional().apply {
            firstMethodOrNull {
                name = "updateIconColor"
                emptyParameters()
            }?.hook()?.after { refreshStatusBarIconView(instance()) }
        }
        // 注入状态栏通知图标容器管理实例
        // 在 Android 15 中，这个类被移除变成了 `interface`，所以判断并跳过 Hook 行为
        val isPlaceholder = NotificationIconAreaControllerClass == null || NotificationIconAreaControllerClass?.isInterface == true
        if (!isPlaceholder) NotificationIconAreaControllerClass?.resolve()?.optional()?.apply {
            // Hook 深色图标模式改变
            firstMethodOrNull {
                name = "onDarkChanged"
                parameterCount { it > 0 }
            }?.hook()?.after {
                firstFieldOrNull { name = "mNotificationIcons" }?.of(instance)?.get<ViewGroup>()?.also {
                    // 重新设置通知图标容器实例
                    notificationIconContainer = it
                    when (arg(index = 1).get<Float>()) {
                        1.0f -> {
                            isDarkIconMode = true
                            updateStatusBarIconsColor(it, isDarkIconMode = true)
                        }
                        0.0f -> {
                            isDarkIconMode = false
                            updateStatusBarIconsColor(it, isDarkIconMode = false)
                        }
                        else -> updateStatusBarIconsColor(it, isDarkIconMode = false, arg(index = 2).get<Int>() ?: 0)
                    }
                }
            }
            // Hook 更新通知图标事件
            method {
                name { it == "updateNotificationIcons" || it.startsWith("onChanged") }
            }.hookAll().after {
                firstFieldOrNull { name = "mNotificationIcons" }?.of(instance)?.get<ViewGroup>()?.also {
                    // 重新设置通知图标容器实例
                    notificationIconContainer = it
                    updateStatusBarIconsColor(it)
                    // 延迟防止新添加的通知图标不刷新
                    delayedRun { updateStatusBarIconsColor(it) }
                }
            }
        } else MiuiClockClass?.resolve()?.optional()?.apply {
            // OS2 同时声明 3、6 参数重载但实际分发 6 参数；不能只取第一个，否则深色状态不会更新
            method {
                name = "onDarkChanged"
                parameterCount { it >= 3 }
            }.hookAll().after {
                val intensity = arg(index = 1).get<Float>()
                // OS2 首次分发颜色时，通知图标容器可能还未绑定
                if (isMIOS && miosVersionCode.toInt() == 2 && (intensity == 0f || intensity == 1f))
                    isDarkIconMode = intensity == 1f
                notificationIconContainer?.let {
                    when (intensity) {
                        1.0f -> {
                            isDarkIconMode = true
                            updateStatusBarIconsColor(it, isDarkIconMode = true)
                        }
                        0.0f -> {
                            isDarkIconMode = false
                            updateStatusBarIconsColor(it, isDarkIconMode = false)
                        }
                        else -> updateStatusBarIconsColor(it, isDarkIconMode = false, arg(index = 2).get<Int>() ?: 0)
                    }
                }
            }
        }
        // 注入状态栏通知图标实例
        StatusBarIconViewClass.resolve().optional().apply {
            firstMethodOrNull {
                name = "setNotification"
                parameters {
                    (it.first() == classOf<StatusBarNotification>() ||
                        it.first() == ExpandedNotificationClass) && it.size == 1
                }
            }?.hook()?.after {
                // 注册壁纸颜色监听
                if (firstArg().get() != null) instance<ImageView>().also {
                    registerWallpaperColorChanged(it)
                    if (isMIOS && miosVersionCode.toInt() == 3) refreshStatusBarIconView(it)
                }
            }
            // OS2 使用 6 参数回调，OS3、OS4 使用 3 参数回调，覆盖所有重载以跟随实际分发
            if (isPlaceholder) method {
                name = "onDarkChanged"
                parameterCount { it >= 3 }
            }.hookAll().after {
                val self = instance<ImageView>()
                when (arg(index = 1).get<Float>()) {
                    1.0f -> {
                        isDarkIconMode = true
                        updateStatusBarIconColor(self, isDarkIconMode = true)
                    }
                    0.0f -> {
                        isDarkIconMode = false
                        updateStatusBarIconColor(self, isDarkIconMode = false)
                    }
                    else -> updateStatusBarIconColor(self, isDarkIconMode = false, arg(index = 2).get<Int>() ?: 0)
                }
            }
        }
    }

    private fun restoreStatusBarIconLimit(container: ViewGroup) {
        val amount = container.getTag<Int>(R.id.status_icon_system_limit) ?: run {
            // 首次从无 tag 的模块热重载时，字段可能已被覆盖，必须读取当前用户的系统设置
            val manager = currentIconManager() ?: return
            val user = manager.asResolver().firstField { name = "userTracker" }.get() ?: return
            val userId = user.asResolver().firstMethod { name = "getUserId"; emptyParameters() }.invoke<Int>() ?: return
            var limit = Settings.System::class.resolve().firstMethod {
                name = "getIntForUser"
                parameterCount = 4
            }.invoke<Int>(container.context.contentResolver, "status_bar_show_notification_icon", 1, userId) ?: return
            if (miosVersionCode >= 4 && limit == 3) {
                val component = globalContext?.asResolver()?.firstField { name = "mSysUIComponent"; superclass() }?.get() ?: return
                val provider = component.asResolver().firstField { name = "notificationIconObserverProvider" }.get() ?: return
                val observer = provider.asResolver().firstMethod { name = "get"; emptyParameters() }.invoke() ?: return
                val resolver = observer.asResolver()
                val region = resolver.firstField { name = "regionFlow" }.get()?.asResolver()
                    ?.firstMethod { name = "getValue"; emptyParameters() }?.invoke<String>()
                val policy = resolver.firstField { name = "operatorCustomizedPolicy" }.get() ?: return
                val resources = policy.asResolver().firstField { name = "mNoMccMncResources" }.get<Resources>() ?: return
                // 与 NotificationIconObserver 的地区/运营商限制保持一致
                val resourceId = resources.getIdentifier("config_showOperatorNameInStatusBar", "bool", PackageName.SYSTEMUI)
                if (region == "SA" || resources.getBoolean(resourceId)) limit = 1
            }
            limit
        }
        container.asResolver().firstMethod { name = "setMaxIconsAmount"; parameters(Int::class) }.invoke(amount)
    }

    private fun installStatusBarIconCount() {
        if (isMIOS && miosVersionCode >= 2) {
            NotificationIconContainerClass.resolve().firstMethod {
                name = "setMaxIconsAmount"
                parameters(Int::class)
            }.hook().before {
                val amount = firstArg().get<Int>() ?: return@before
                statusBarMaxStaticIcons = amount
                isShowNotificationIcons = amount > 0
                notificationIconContainer = instance<ViewGroup>().also { it.setTag(R.id.status_icon_system_limit, amount) }
                if (ConfigData.isEnableLiftedStatusIconCount && amount > 0)
                    firstArg().set(ConfigData.liftedStatusIconCount.let { if (it in 0..100) it else 5 })
            }
            iconRuleScope.launch {
                val container = notificationIconContainer ?: return@launch
                restoreStatusBarIconLimit(container)
            }
            return
        }
        // 注入状态栏通知图标容器实例
        NotificationIconContainerClass.apply {
            // OS3 起容器只改变 View.alpha，不在每次布局中重读所有通知图标
            if (isMIOS.not() || miosVersionCode < 3) resolve().optional().firstMethodOrNull {
                name = "applyIconStates"
            }?.hook()?.after { updateStatusBarIconsAlpha(instance()) }
            resolve().optional().firstMethodOrNull {
                name = "resetViewStates"
            }?.hook()?.after {
                if (isMIOS.not() || miosVersionCode < 3) updateStatusBarIconsAlpha(instance())
                // HyperOS 系统设置修改通知图标个数触发此方法
                applyStatusBarIconLimit(instance)
            }
            resolve().optional().firstMethodOrNull {
                name { it == "calculateIconTranslations" || it == "calculateIconXTranslations" }
            }?.hook()?.after {
                // 缓存实例
                notificationIconContainer = instance<ViewGroup>()
                // 修复部分开发版状态栏图标只能显示一个的问题
                when (miuiIncrementalVersion.lowercase()) {
                    "22.3.14", "22.3.15", "22.3.16", "v13.0.1.1.16.dev", "22.3.18" ->
                        instance<ViewGroup>().layoutParams.width = 9999
                }
            }
            // 旧版方法 (A13 MIUI) - 新版不存在
            resolve().optional(silent = true).firstMethodOrNull {
                name = "updateState"
            }?.hook()?.before { applyStatusBarIconLimit(instance) }
            // 新版方法 (A14 MIUI14 / A14 HyperOS) - 旧版不存在
            resolve().optional(silent = true).firstMethodOrNull {
                name = "onMeasure"
            }?.hook()?.before { applyStatusBarIconLimit(instance) }
            // 旧版方法 - 新版不存在
            resolve().optional(silent = true).firstMethodOrNull {
                name = "setMaxStaticIcons"
                parameters(Int::class)
            }?.hook()?.before { isShowNotificationIcons = (firstArg().get<Int>() ?: 0) > 0 }
            // 旧版方法 - 新版 (A15 HyperOS) 不存在
            resolve().optional(silent = true).firstMethodOrNull {
                name = "miuiShowNotificationIcons"
                parameters(Boolean::class)
            }?.hook()?.before { isShowNotificationIcons = firstArg().get<Boolean>() ?: false }
        }
    }

    private fun installFocusTickerIcons() {
        if (miosVersionCode >= 3) return
        // 旧版焦点通知深色模式切换点
        FocusedNotifPromptViewClass?.resolve()?.optional()?.apply {
            firstMethodOrNull {
                name = "onDarkChanged"
                parameters(ArrayList::class, Float::class, Int::class, Int::class, Int::class, Boolean::class)
            }?.hook()?.after {
                val isDark = arg(index = 1).get<Float>() ?: 0f
                val mIcon = firstFieldOrNull { name = "mIcon" }?.of(instance)?.get()
                if (focusedIcon || ConfigData.isEnableFocusNotificationFix)
                    mIcon?.asResolver()?.optional()?.firstMethodOrNull {
                        name = "setColorFilter"
                        superclass()
                    }?.invoke(if (isDark <= 0.5f) Color.WHITE else Color.BLACK)
            }
        }
        // 旧版移除焦点通知彩色图标
        FocusUtilsClass?.resolve()?.optional()?.apply {
            fun HookParam.bindFocusTickerIcon(isDark: Boolean) {
                (globalContext ?: firstArg().get<Context?>())?.also { context ->
                    val expandedNf = firstArg().get<StatusBarNotification?>()
                    val small = expandedNf?.notification?.smallIcon
                    // Hook 状态栏小图标
                    resolveStatusBarIcon(
                        context = context,
                        nf = expandedNf,
                        iconDrawable = small?.loadDrawable(context)
                    ).also { pair ->
                        focusedIcon = pair.second
                        val originalBitmap = pair.first?.toBitmap()
                        val bitmap = originalBitmap?.scale(50, 50)
                        result = Icon.createWithBitmap(bitmap).apply {
                            if (pair.second || ConfigData.isEnableFocusNotificationFix)
                                setTint(if (isDark) Color.BLACK else Color.WHITE)
                        }
                    }
                }
            }
            firstMethodOrNull {
                name = "getStatusBarTickerDarkIcon"
                parameters {
                    (it.first() == classOf<StatusBarNotification>() ||
                        it.first() == ExpandedNotificationClass) && it.size == 1
                }
            }?.hook()?.after { bindFocusTickerIcon(isDark = true) }
            firstMethodOrNull {
                name = "getStatusBarTickerIcon"
                parameters {
                    (it.first() == classOf<StatusBarNotification>() ||
                        it.first() == ExpandedNotificationClass) && it.size == 1
                }
            }?.hook()?.after { bindFocusTickerIcon(isDark = false) }
        }
    }

    private fun installFoldNotificationIcons() {
        // 更多通知入口在控制器内立即合成 Bitmap，没有可单独处理的 ImageView
        // 用 ThreadLocal 仅标记同步生成入口的这段调用，随后复用通知面板方案离屏绘制
        val isOs2FoldEntrance = isMIOS && miosVersionCode.toInt() == 2
        val foldControllerClass = if (isOs2FoldEntrance) FoldNotifControllerClass else FoldNotifControllerImplClass
        foldControllerClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "sendFoldNotification"
            parameterCount = if (isOs2FoldEntrance) 1 else 2
        }?.hook()?.apply {
            before {
                foldEntranceIconContext.remove()
                instance.runInSafe(msg = "Collect fold entrance notifications") {
                    val controllerResolver = instance.asResolver().optional(silent = true)
                    val notifications = controllerResolver.firstFieldOrNull { name = "pipeline" }?.get()
                        ?.asResolver()?.optional(silent = true)?.firstMethodOrNull {
                            name = "getAllNotifs"
                            emptyParameters()
                        }?.invoke<Collection<Any?>>()?.mapNotNull { entry ->
                            entry?.asResolver()?.optional(silent = true)
                                ?.firstFieldOrNull { name = "mSbn" }?.get<StatusBarNotification>()
                        }.orEmpty()
                    val iconSize = controllerResolver.firstFieldOrNull { name = "iconSize" }?.get<Int>()
                        ?: return@runInSafe
                    foldEntranceIconContext.set(notifications to iconSize)
                }
            }
            after { foldEntranceIconContext.remove() }
        }
        // OS2 不调用 getCustomAppIcon，而是直接从 AppIconsManager 读取桌面图标
        // 只替换入口生成期间的返回值，不将合成图标写入系统的 APP 图标缓存
        if (isOs2FoldEntrance) AppIconsManagerClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "getAppIconInner"
            parameterCount = 4
        }?.hook()?.after {
            val (notifications, iconSize) = foldEntranceIconContext.get() ?: return@after
            val packageName = firstArg().get<String>() ?: return@after
            val userId = arg(index = 1).get<Int>() ?: return@after
            // 与系统入口一样，按应用和用户选择时间最新的折叠通知
            val nf = notifications.filter {
                it.packageName == packageName && it.user.asResolver().optional(silent = true)
                    .firstMethodOrNull { name = "getIdentifier"; emptyParameters() }?.invoke<Int>() == userId &&
                    it.notification.extras.getBoolean("miui.unimportant", false)
            }.maxByOrNull { it.notification.`when` } ?: return@after
            val context = instance.asResolver().optional(silent = true)
                .firstFieldOrNull { name = "mContext" }?.get<Context>() ?: return@after
            nf.createFoldEntranceIcon(context, iconSize)?.also { result = it }
        }
        // 只替换折叠入口生成期间的图标
        NotificationUtilClass.resolve().optional(silent = true).firstMethodOrNull {
            name = "getCustomAppIcon"
            parameters(Notification::class, Context::class)
        }?.hook()?.after {
            val nf = firstArg().get<Notification>() ?: return@after
            val context = lastArg().get<Context>() ?: return@after
            foldEntranceIconContext.get()?.also { (notifications, iconSize) ->
                notifications.firstOrNull { it.notification === nf }
                    ?.createFoldEntranceIcon(context, iconSize)
                    ?.also { result = it }
                return@after
            }
        }
    }

    private fun installNotificationStatisticsFix() {
        // 尝试修复从 MIUI 14 开始出现的一个崩溃问题
        // 由于模块注入推送的通知没有对 [StatusBarNotification] 设置 TAG 会导致其空指针
        // 直接替换掉它自己的实现方法 - 使用自己的方式实现这个功能
        // ```java
        // public final boolean isUnimportantEntry(NotificationEntry notificationEntry) {
        //     return notificationEntry.getSbn().getPackageName().equals("com.android.systemui") &&
        //         notificationEntry.getSbn().getTag().equals("UNIMPORTANT");
        // }
        // ```
        NotificationStatClass?.resolve()?.optional(silent = true)?.firstMethodOrNull {
            name = "isUnimportantEntry"
            parameterCount = 1
        }?.hook()?.intercept {
            firstArg().get()?.asResolver()?.optional()?.firstMethodOrNull {
                name = "getSbn"
                superclass()
            }?.invoke<StatusBarNotification>()?.let { sbn ->
                sbn.packageName == PackageName.SYSTEMUI && sbn.tag == "UNIMPORTANT"
            } ?: false
        }
    }

    private fun installAodNotificationIcons() {
        val installed = mutableSetOf<Class<*>>()
        fun install(loader: ClassLoader?) {
            val controller = "com.miui.aod.notification.NotificationController".toClassOrNull(loader) ?: return
            if (!installed.add(controller)) return
            val data = $$"com.miui.aod.notification.NotificationController$NotificationData".toClassOrNull(loader) ?: return
            controller.resolve().optional(silent = true).method {
                name = "fillNotificationData"
                parameters { it.size in listOf(2, 5) && it[0] == data && it[1] == classOf<StatusBarNotification>() }
            }.hookAll().after {
                updateAodNotificationIcon(instance, firstArg().get(), arg(1).get<StatusBarNotification>())
            }
        }
        listOfNotNull(MiuiDozeServiceClass, DozeServiceClass).distinct().forEach { service ->
            service.resolve().optional(silent = true).firstMethodOrNull {
                name = "onPluginConnected"
                parameterCount { it in 1..2 }
            }?.hook()?.before { firstArg().get()?.javaClass?.classLoader?.let { install(it) } }
        }
        val loaderClass = "com.miui.systemui.plugin.PluginInstanceInjector".toClassOrNull()
            ?: "com.android.systemui.shared.plugins.PluginInstance".toClassOrNull()
        loaderClass?.resolve()?.optional(silent = true)?.firstFieldOrNull { name = "sClassLoaders" }
            ?.get<Map<String, ClassLoader>>()?.values?.forEach { install(it) }
    }

    private fun installHostReferences() {
        SettingsManagerClass?.resolve()?.optional()?.constructor {}?.hookAll()?.after { settingsManager = instance }
        StatusBarNotificationPresenterClass.resolve().optional(silent = true).constructor {}.hookAll().after {
            notificationPresenter = instance
        }
    }

    private fun updateAodNotificationIcon(controller: Any?, notificationData: Any?, notifyInstance: StatusBarNotification?) {
        if (ConfigData.isEnableAodNotifyIcon.not()) return
        if (controller == null || notificationData == null || notifyInstance == null)
            return YLog.warn("AOD fillNotificationData got null arguments")

        val context = controller.asResolver().firstFieldOrNull { name = "mContext" }?.get<Context>()
            ?: return YLog.warn("fillNotificationData got null context")
        val iconDrawableField = notificationData.asResolver().firstFieldOrNull { name = "mIconDrawable" }
            ?: return YLog.warn("fillNotificationData got null iconDrawableField")
        // AOD 只接收 Drawable，将外圈和前景一起合成，并固定使用夜间配色
        val iconView = ImageView(context)
        bindComposedNotificationIcon(iconView, notifyInstance, isDarkMode = true)
        iconView.drawable?.also { iconDrawableField.set(it) }
    }
}