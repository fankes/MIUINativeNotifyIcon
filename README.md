# MIUI 原生通知图标

[![Blank](https://img.shields.io/badge/build-pending-dbab09)](/#)
[![Blank](https://img.shields.io/badge/license-AGPL3.0-blue)](/#)
[![Blank](https://img.shields.io/badge/version-v2.16-green)](/#)
[![Blank](https://img.shields.io/github/downloads/fankes/MIUINativeNotifyIcon/total?label=Release)](https://github.com/fankes/MIUINativeNotifyIcon/releases)
[![Blank](https://img.shields.io/github/downloads/Xposed-Modules-Repo/com.fankes.miui.notify/total?label=LSPosed%20Repo&logo=Android&style=flat&labelColor=F48FB1&logoColor=ffffff)](https://github.com/Xposed-Modules-Repo/com.fankes.miui.notify/releases)
[![Telegram](https://img.shields.io/static/v1?label=Telegram&message=交流讨论&color=0088cc)](https://t.me/XiaofangInternet)
<br/><br/>
<img src="https://github.com/fankes/MIUINativeNotifyIcon/blob/master/app/src/main/ic_launcher-playstore.png" width = "100" height = "100"/>
<br/>
Fix the native notification bar icon function abandoned by the MIUI development team.<br/>
修复被 MIUI 开发组丢弃的原生通知图标，支持 MIUI 12、12.5、13 以及最新版本。

# Developer

[酷安 @星夜不荟](http://www.coolapk.com/u/876977)

# 适配说明

- 此模块仅支持 LSPosed(作用域“系统界面”)、~~EdXposed(随时停止支持)~~、不支持太极无极
- 请确保你使用的是 MIUI 官方版本，任何第三方官改包发生的问题，开发者没有义务去解决和修复，请自求多福
- 目前最低支持基于 Android 9 版本的 MIUI 12 或 MIUI 12.5(最低建议)
- 建议最低从 MIUI 12.5 `2021-5-18` 开发版以后开始使用模块，之前的版本可能或多或少存在 MIUI 自身 BUG 不生效、黑白块的问题
- 请始终保持最新版本的 LSPosed，旧版本可能会出现 Hook 不生效的问题，若最新版本依然不生效请在作用域中长按“系统界面”(“系统 UI”)选择重新优化

# 请勿用于非法用途

- 本模块完全开源免费，如果好用你可以打赏支持开发，但是请不要用于非法用途。
- 本模块发布地址仅有 [Xposed-Modules-Repo](https://github.com/Xposed-Modules-Repo/com.fankes.miui.notify/releases)、
  [Release](https://github.com/fankes/MIUINativeNotifyIcon/releases)
  及 [蓝奏云](https://fankes.lanzouy.com/b030o2e8h)，从其他非正规渠道下载到的版本或对您造成任何影响均与我们无关。

# 贡献通知图标优化名单

此项目是 `AndroidNotifyIconAdapt` 项目的一部分，详情请参考下方。<br/>

- [Android 通知图标规范适配计划](https://github.com/fankes/AndroidNotifyIconAdapt)

# 历史背景

这个模块诞生来源于 MIUI 的乱改和不规范，本来 MIUI 9 之后，官方给出了原生通知图标样式，后面由于用户反应通知栏经常出现黑白块。<br/><br/>
这当然不是系统的错，而是国内 APP 和 `MIPUSH` 的通知极其不规范的通知图标设计。<br/><br/>
但是呢，接到反馈后 MIUI 开发组选择直接忽略这个问题，在 `2021-5-18` 的开发版开始，把全部通知图标都改成了 APP 的彩色图标，使得之前拥有自有样式的原生图标也被破坏。<br/><br/>
对于 Android 开发者来说，官方文档中的 `setSmallIcon` 不再适用于魔改后的 MIUI，这将会严重破坏非常多的状态图标。<br/><br/>
当然，国内的手机生态除了 `MIPUSH` 的营销通知就是社交软件的通知，可能大部分人都不会在意这件事情。<br/><br/>
但是，这个模块就是为了修复被 MIUI 开发组忽略的图标问题才诞生的，并完美地给 MIUI 修复了黑白块图标的问题。
<br/>

# 探索历程

原生 Android 的小图标和通知图标具有状态性。<br/><br/>
<img src="https://github.com/fankes/MIUINativeNotifyIcon/blob/master/images/native.jpg" height = "35"/><br/><br/>
而 MIUI 最近的版本直接破坏了这一状态性，全部设置为 APP 的图标，不仅难看而且你无法下拉通知栏区别这些图标代表什么。<br/><br/>
<img src="https://github.com/fankes/MIUINativeNotifyIcon/blob/master/images/miui.jpg" height = "40"/><br/><br/>
同样地，通知面板的图标同样遵守这一状态性。<br/><br/>
<img src="https://github.com/fankes/MIUINativeNotifyIcon/blob/master/images/native_n_1.jpg" height = "100"/><br/>
<img src="https://github.com/fankes/MIUINativeNotifyIcon/blob/master/images/native_n_2.jpg" height = "100"/><br/><br/>
而 MIUI 做了什么呢？<br/><br/>
<img src="https://github.com/fankes/MIUINativeNotifyIcon/blob/master/images/miui_n_1.jpg" height = "100"/><br/>
<img src="https://github.com/fankes/MIUINativeNotifyIcon/blob/master/images/miui_n_2.jpg" height = "100"/><br/><br/>
不曾记得是什么版本开始，MIUI 把通知图标改成了这个鬼样子，寻找开发组提案也是无人问津，最后转念一想，自己干吧。<br/><br/>
由于目前大量通知图标都来自 `MIPUSH` 发出的营销通知，而 `MIPUSH` 的图标都是统一的彩色应用图标，很多应用也没有适配这一特性， 在通知栏广告满天飞的情况下，MIUI
选择放弃原生通知功能，而做出这种违反原生通知规则的做法，而这些彩色图标被设置为单色调图标，也确实会发生黑白块的问题，但是同时又会破坏遵守规范的图标。<br/><br/>
真的没有办法了吗？在不断探索下，我找到了原生支持色彩判断的类。

```
com.android.internal.util.ContrastColorUtil
```

这个类中有一个方法可以拿出来判断图标的灰度效果。

```
ContrastColorUtil.getInstance().isGrayscaleIcon(drawable)
```

问题就被解决了，顺便修了一下被 MIUI 破坏的通知图标以及优化了一下应用本身方块图标的圆角......<br/><br/>
最后，我想大声问一句 MIUI 开发组：“就这？” 就这么简单的问题为什么拖了这么长时间也没有结论，还要交给用户去修复，这真的是一种负责任的表现吗？<br/><br/>
后来一想，也是啊，被国内生态毒害的用户，怎么可能会去想到这些问题呢，最后只能是我自作多情，还对 MIUI 留有一点情怀吧。<br/><br/>
——来自一个无可奈何的 MIUI 老用户

# 后记

近期重新适配了 MIUI 12、12.5、13 版本，每个版本的图标设置方法都不一样，而且改的乱七八糟的，我都要无语了，只能用了很多折中方案，毕竟我也没有那么大精力每个版本去修复，实在是累了。<br/><br/>
特地的把自己能有的小米手机刷成各种 MIUI 版本去为酷友做专项适配，我也是很累了，也希望你们能够多多支持，也能让 MIUI 做得更好。<br/><br/>
MIUI 再不重写，怕是永远会变成安卓之光。雷军，金凡！！

# 许可证

- [AGPL-3.0](https://www.gnu.org/licenses/agpl-3.0.html)

```
Copyright (C) 2019-2022 Fankes Studio(qzmmcn@163.com)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
```

Powered by [YukiHookAPI](https://github.com/fankes/YukiHookAPI)<br/><br/>
版权所有 © 2019-2022 Fankes Studio(qzmmcn@163.com)