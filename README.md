# MIUI 原生通知图标

[![GitHub license](https://img.shields.io/github/license/fankes/MIUINativeNotifyIcon?color=blue&style=flat-square)](https://github.com/fankes/MIUINativeNotifyIcon/blob/master/LICENSE)
[![GitHub CI](https://img.shields.io/github/actions/workflow/status/fankes/MIUINativeNotifyIcon/commit_ci.yml?label=CI%20builds&style=flat-square)](https://github.com/fankes/MIUINativeNotifyIcon/actions/workflows/commit_ci.yml)
[![GitHub release](https://img.shields.io/github/v/release/fankes/MIUINativeNotifyIcon?display_name=release&logo=github&color=green&style=flat-square)](https://github.com/fankes/MIUINativeNotifyIcon/releases)
![GitHub all releases](https://img.shields.io/github/downloads/fankes/MIUINativeNotifyIcon/total?label=downloads&style=flat-square)
![GitHub all releases](https://img.shields.io/github/downloads/Xposed-Modules-Repo/com.fankes.miui.notify/total?label=LSPosed%20downloads&labelColor=F48FB1&style=flat-square)

[![Telegram CI](https://img.shields.io/badge/CI%20builds-Telegram-blue.svg?logo=telegram&style=flat-square)](https://t.me/MIUINativeNotifyIcon_CI)
[![Telegram](https://img.shields.io/badge/discussion-Telegram-blue.svg?logo=telegram&style=flat-square)](https://t.me/XiaofangInternet)
[![QQ](https://img.shields.io/badge/discussion-QQ-blue.svg?logo=tencent-qq&logoColor=red&style=flat-square)](https://qm.qq.com/cgi-bin/qm/qr?k=dp2h5YhWiga9WWb_Oh7kSHmx01X8I8ii&jump_from=webapi&authKey=Za5CaFP0lk7+Zgsk2KpoBD7sSaYbeXbsDgFjiWelOeH4VSionpxFJ7V0qQBSqvFM)
[![QQ 频道](https://img.shields.io/badge/discussion-QQ%20频道-blue.svg?logo=tencent-qq&logoColor=red&style=flat-square)](https://pd.qq.com/s/44gcy28h)

<img src="img-src/icon.png" width = "100" height = "100" alt="LOGO"/>

Fix the native notification bar icon function abandoned by the MIUI development team.

修复被 MIUI 开发组丢弃的原生通知图标，支持 MIUI 11~14 以及 HyperOS 1.0。

## For Non-Chinese Users

This project will not be adapted i18n, please stay tuned for my new projects in the future.

## 项目迁移公告

由于本人同时维护 **MIUI** 与 **ColorOS** 两个系统需要同时维护两个模块，十分不方便，所以我决定在后期逐渐合并两个项目并解耦合为一个新项目并计划适配更多系统与设备，例如原生与类原生系统。

在新的项目确定后，会在这里添加新项目的链接，届时我会终止维护这个项目并建议大家转移到新项目。

## 适配说明

- 此模块仅支持 **LSPosed** (作用域“系统界面”)、**~~EdXposed(随时停止支持)~~**、不支持**太极、无极**

- 请确保你使用的是 MIUI 官方版本，任何第三方官改包发生的问题，开发者没有义务去解决和修复，请自求多福

- 目前最低支持基于 Android 9 版本的 MIUI 11 或 MIUI 12、12.5 (最低建议)

- 建议最低从 MIUI 12.5 `2021-5-18` 开发版以后开始使用模块，之前的版本可能或多或少存在 MIUI 自身 BUG 不生效、图标黑白块的问题

- 请始终保持最新版本的 **LSPosed**，旧版本可能会出现 Hook 不生效的问题，若最新版本依然不生效请在作用域中长按“系统界面” (“系统 UI”) 选择重新优化

## 历史背景

点击下方的链接查看此模块的历史背景与探索历程。

- [EXPLORE_HISTORY](EXPLORE_HISTORY.md)

## 贡献通知图标优化名单

此项目是 `AndroidNotifyIconAdapt` 项目的一部分，详情请参考下方。

- [Android 通知图标规范适配计划](https://github.com/fankes/AndroidNotifyIconAdapt)

## 发行渠道

| <img src="https://avatars.githubusercontent.com/in/15368?s=64&v=4" width = "30" height = "30" alt="LOGO"/> | [GitHub CI](https://github.com/fankes/MIUINativeNotifyIcon/actions/workflows/commit_ci.yml) | CI 自动构建 (测试版) |
|------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|---------------|

| <img src="https://github.com/peter-iakovlev/Telegram/blob/public/Icon.png?raw=true" width = "30" height = "30" alt="LOGO"/> | [Telegram CI 频道](https://t.me/MIUINativeNotifyIcon_CI) | CI 自动构建 (测试版) |
|-----------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------|---------------|

| <img src="https://avatars.githubusercontent.com/in/15368?s=64&v=4" width = "30" height = "30" alt="LOGO"/> | [GitHub Releases](https://github.com/fankes/MIUINativeNotifyIcon/releases) | 正式版 (稳定版) |
|------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------|-----------|

| <img src="https://avatars.githubusercontent.com/u/78217009?s=200&v=4?raw=true" width = "30" height = "30" alt="LOGO"/> | [Xposed-Modules-Repo](https://github.com/Xposed-Modules-Repo/com.fankes.miui.notify/releases) | 正式版 (稳定版) |
|------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|-----------|

| <img src="https://github.com/fankes/fankes/assets/37344460/82113d3c-aa7b-4dd1-95c7-cda650065c12" width = "30" height = "30" alt="LOGO"/> | [123 云盘 **(密码：62ll)**](https://www.123pan.com/s/5SlUVv-W8DBh.html) | 正式版 (稳定版) |
|------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------|-----------|

本模块发布地址仅限于上述所列出的地址，从其他非正规渠道下载到的版本或对您造成任何影响均与我们无关。

## 注意事项

<h3>1.&nbsp;本软件免费、由兴趣驱动开发，仅供学习交流使用。如果你是从其他非官方渠道付费获得本软件，可能已遭遇欺诈，欢迎向我们举报可疑行为。</h3>

<h3>2.&nbsp;本软件采用 <strong>GNU Affero General Public License (AGPL 3.0)</strong> 许可证。根据该许可证的要求：</h3>

- 任何衍生作品必须采用相同的 AGPL 许可证
- 分发本软件或其修改版本时，必须提供完整的源代码
- 必须保留原始的版权声明及许可证信息
- 不得额外施加限制来限制他人对本软件的自由使用

<h3>3.&nbsp;我们鼓励在遵守 AGPL 3.0 条款的前提下进行自由传播和改进，但请尊重作者署名权，勿冒用原作者名义。</h3>

## 项目推广

<!--suppress HtmlDeprecatedAttribute -->
<div align="center">
    <h2>嘿，还请君留步！👋</h2>
    <h3>这里有 Android 开发工具、UI 设计、Gradle 插件、Xposed 模块和实用软件等相关项目。</h3>
    <h3>如果下方的项目能为你提供帮助，不妨为我点个 star 吧！</h3>
    <h3>所有项目免费、开源，遵循对应开源许可协议。</h3>
    <h1><a href="https://github.com/fankes/fankes/blob/main/project-promote/README-zh-CN.md">→ 查看更多关于我的项目，请点击这里 ←</a></h1>
</div>

## Star History

![Star History Chart](https://api.star-history.com/svg?repos=fankes/MIUINativeNotifyIcon&type=Date)

## 隐私政策

- [PRIVACY](PRIVACY.md)

## 许可证

- [AGPL-3.0](https://www.gnu.org/licenses/agpl-3.0.html)

```
Copyright (C) 2017 Fankes Studio(qzmmcn@163.com)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
```

Powered by [YukiHookAPI](https://github.com/HighCapable/YukiHookAPI)

版权所有 © 2017 Fankes Studio(qzmmcn@163.com)