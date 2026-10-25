# HyperOS/MIUI 原生通知图标

[![GitHub license](https://img.shields.io/github/license/fankes/MIUINativeNotifyIcon?color=blue&style=flat-square)](https://github.com/fankes/MIUINativeNotifyIcon/blob/master/LICENSE)
[![GitHub CI](https://img.shields.io/github/actions/workflow/status/fankes/MIUINativeNotifyIcon/commit_ci.yml?label=CI%20builds&style=flat-square)](https://github.com/fankes/MIUINativeNotifyIcon/actions/workflows/commit_ci.yml)
[![GitHub release](https://img.shields.io/github/v/release/fankes/MIUINativeNotifyIcon?display_name=release&logo=github&color=green&style=flat-square)](https://github.com/fankes/MIUINativeNotifyIcon/releases)
![GitHub all releases](https://img.shields.io/github/downloads/fankes/MIUINativeNotifyIcon/total?label=downloads&style=flat-square)
![GitHub all releases](https://img.shields.io/github/downloads/Xposed-Modules-Repo/com.fankes.miui.notify/total?label=LSPosed%20downloads&labelColor=F48FB1&style=flat-square)

[![Telegram CI](https://img.shields.io/badge/CI%20builds-Telegram-blue.svg?logo=telegram&style=flat-square)](https://t.me/MIUINativeNotifyIcon_CI)
[![Telegram Channel](https://img.shields.io/badge/channel-Telegram-blue.svg?logo=telegram&style=flat-square)](https://t.me/XiaofangDaily)
[![Telegram](https://img.shields.io/badge/discussion-Telegram-blue.svg?logo=telegram&style=flat-square)](https://t.me/XiaofangInternet)
[![QQ](https://img.shields.io/badge/discussion-QQ-blue.svg?logo=tencent-qq&logoColor=red&style=flat-square)](https://qm.qq.com/cgi-bin/qm/qr?k=dp2h5YhWiga9WWb_Oh7kSHmx01X8I8ii&jump_from=webapi&authKey=Za5CaFP0lk7+Zgsk2KpoBD7sSaYbeXbsDgFjiWelOeH4VSionpxFJ7V0qQBSqvFM)
[![QQ 频道](https://img.shields.io/badge/discussion-QQ%20频道-blue.svg?logo=tencent-qq&logoColor=red&style=flat-square)](https://pd.qq.com/s/44gcy28h)

<img src="img-src/icon.png" width = "100" height = "100" alt="LOGO"/>

[English](README.md) | 简体中文

修复被 HyperOS/MIUI 开发组丢弃的原生通知图标。

## 项目迁移公告

由于本人同时维护 **HyperOS/MIUI** 与 **ColorOS** 两个系统需要同时维护两个模块，十分不方便，所以我决定在后期逐渐合并两个项目并解耦合为一个新项目并计划适配更多系统与设备，例如原生与类原生系统。

在新的项目确定后，会在这里添加新项目的链接，届时我会终止维护这个项目并建议大家转移到新项目。

## 适配说明

- 推荐使用 **LSPosed** (作用域 “系统界面”)
- 请确保你使用的是 HyperOS/MIUI 官方版本，任何第三方官改包发生的问题，开发者没有义务去解决和修复
- 目前最低支持基于 Android 9 版本的 MIUI 11 或 MIUI 12、12.5
- 建议最低从 MIUI 12.5 `2021-5-18` 开发版以后开始使用模块，之前的版本可能或多或少存在 MIUI 自身 BUG 不生效、图标黑白块的问题

## 历史背景

点击 [这里](EXPLORE_HISTORY.md) 查看此模块的历史背景与探索历程。

## 为 ANIP 做出贡献

此项目的通知图标来自 ANIP 项目，[点击这里](https://github.com/BetterAndroid/android-notification-icon-project) 前往查看详情。

## 发行渠道

| <img src="https://avatars.githubusercontent.com/in/15368?s=64&v=4" width = "30" height = "30" alt="LOGO"/> | [GitHub CI](https://github.com/fankes/MIUINativeNotifyIcon/actions/workflows/commit_ci.yml) | CI 自动构建 (测试版) |
|------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|---------------|

| <img src="https://github.com/peter-iakovlev/Telegram/blob/public/Icon.png?raw=true" width = "30" height = "30" alt="LOGO"/> | [Telegram CI 频道](https://t.me/MIUINativeNotifyIcon_CI) | CI 自动构建 (测试版) |
|-----------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------|---------------|

| <img src="https://avatars.githubusercontent.com/in/15368?s=64&v=4" width = "30" height = "30" alt="LOGO"/> | [GitHub Releases](https://github.com/fankes/MIUINativeNotifyIcon/releases) | 正式版 (稳定版) |
|------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------|-----------|

| <img src="https://avatars.githubusercontent.com/u/78217009?s=200&v=4?raw=true" width = "30" height = "30" alt="LOGO"/> | [Xposed-Modules-Repo](https://github.com/Xposed-Modules-Repo/com.fankes.miui.notify/releases) | 正式版 (稳定版) |
|------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|-----------|

| <img src="https://cdn.nlark.com/yuque/0/2023/png/39215739/1697095421529-avatar/0305d093-2687-4529-834f-505f11f1ac50.png" width = "30" height = "30" alt="LOGO"/> | [123 云盘](https://1857720810.share.123pan.cn/123pan/zdcUvd-Hy65d?pwd=mnnc) | 正式版 (稳定版) |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------|-----------|

本模块发布地址仅限于上述所列出的地址，从其他非正规渠道下载到的版本或对您造成任何影响均与我们无关。

### 按场景选择模块版本

此项目的模块安装包采用双轨制，分为 **modern** 和 **legacy** 两个版本。

- 标有 **modern** 的版本适配支持 libxposed API 的 Xposed 框架，例如 LSPosed
- 标有 **legacy** 的版本适配支持 Xposed Framework API 的 Xposed 框架，例如旧版 LSPosed (≤ 1.9.0)、EdXposed 等

**modern** 版本的模块在 LSPosed 2.1.0+ 支持自动热重载功能，在首次安装后，接下来的版本中继续更新模块，不再需要重启系统界面即可生效。

## 注意事项

1. 本软件免费、由兴趣驱动开发，仅供学习交流使用。如果你是从其他非官方渠道付费获得本软件，可能已遭遇欺诈，欢迎向我们举报可疑行为。
2. 本软件采用 **GNU Affero General Public License (AGPL 3.0)** 许可证。根据该许可证的要求：
    1. 任何衍生作品必须采用相同的 AGPLv3 许可证
    2. 分发本软件或其修改版本时，必须提供完整的源代码
    3. 必须保留原始的版权声明及许可证信息
    4. 不得额外施加限制来限制他人对本软件的自由使用
3. 我们鼓励在遵守 AGPLv3 条款的前提下进行自由传播和改进，但请尊重作者署名权，勿冒用原作者名义

## 更多项目

<!--suppress HtmlDeprecatedAttribute -->
<div align="center">
    <h2>嘿，还请君留步！👋</h2>
    <h3>如果你觉得这个项目能给你提供帮助，不妨继续往下看看我的更多项目吧！</h3>
    <h3>如果这些项目能为你提供帮助，不妨为我点个关注或者 star ⭐️ 吧！</h3>
    <h1><a href="https://github.com/fankes/fankes/blob/main/project-promote/README-zh-CN.md">→ 查看更多关于我的项目，请点击这里 ←</a></h1>
</div>

## Star History

[![Star History Chart](https://api.star-history.com/chart?repos=fankes/MIUINativeNotifyIcon&type=date&legend=top-left&sealed_token=e08B7CwtCWLIhfayg8iVh4-3aBIVSVi2eNIWy2MbhxI2KND4ObyRmak37WHM3IGMOmmlFV8qaYfrTpqFA6IPjYuhCPNOlSCUeQ2Jj3Kt1bU--xm6e3Q6uQ)](https://www.star-history.com/?repos=fankes%2FMIUINativeNotifyIcon&type=date&legend=top-left)

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

Powered by [YukiHook](https://github.com/HighCapable/YukiHook)

版权所有 © 2017 Fankes Studio(qzmmcn@163.com)