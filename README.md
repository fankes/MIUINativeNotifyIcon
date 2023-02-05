# MIUI 原生通知图标

[![Blank](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/fankes/MIUINativeNotifyIcon)
[![Blank](https://img.shields.io/badge/license-AGPL3.0-blue)](https://github.com/fankes/MIUINativeNotifyIcon/blob/master/LICENSE)
[![Blank](https://img.shields.io/badge/version-v2.8-green)](https://github.com/fankes/MIUINativeNotifyIcon/releases)
[![Blank](https://img.shields.io/github/downloads/fankes/MIUINativeNotifyIcon/total?label=Release)](https://github.com/fankes/MIUINativeNotifyIcon/releases)
[![Blank](https://img.shields.io/github/downloads/Xposed-Modules-Repo/com.fankes.miui.notify/total?label=LSPosed%20Repo&logo=Android&style=flat&labelColor=F48FB1&logoColor=ffffff)](https://github.com/Xposed-Modules-Repo/com.fankes.miui.notify/releases)
[![Telegram](https://img.shields.io/badge/Follow-Telegram-blue.svg?logo=telegram)](https://t.me/XiaofangInternet)
<br/><br/>
<img src="https://github.com/fankes/MIUINativeNotifyIcon/blob/master/app/src/main/ic_launcher-playstore.png?raw=true" width = "100" height = "100"/>
<br/>
Fix the native notification bar icon function abandoned by the MIUI development team.<br/>
修复被 MIUI 开发组丢弃的原生通知图标，支持 MIUI 11、12、12.5、13、14 以及最新版本。

## Developer

[酷安 @星夜不荟](http://www.coolapk.com/u/876977)

## 项目迁移公告

由于本人同时维护 **MIUI** 与 **ColorOS** 两个系统需要同时维护两个模块，十分不方便，所以我决定在后期逐渐合并两个项目并解耦合为一个新项目并计划适配更多系统与设备，例如原生与类原生系统。

在新的项目确定后，会在这里添加新项目的链接，届时我会终止维护这个项目并建议大家转移到新项目。

## 适配说明

- 此模块仅支持 <b>LSPosed</b>(作用域“系统界面”)、<b>~~EdXposed(随时停止支持)~~</b>、不支持<b>太极、无极</b>

- 请确保你使用的是 MIUI 官方版本，任何第三方官改包发生的问题，开发者没有义务去解决和修复，请自求多福

- 目前最低支持基于 Android 9 版本的 MIUI 11 或 MIUI 12、12.5 (最低建议)

- 建议最低从 MIUI 12.5 `2021-5-18` 开发版以后开始使用模块，之前的版本可能或多或少存在 MIUI 自身 BUG 不生效、图标黑白块的问题

- 请始终保持最新版本的 LSPosed，旧版本可能会出现 Hook 不生效的问题，若最新版本依然不生效请在作用域中长按“系统界面”(“系统 UI”)选择重新优化

## 历史背景

点击下方的链接查看此模块的历史背景与探索历程。

- [EXPLORE_HISTORY](https://github.com/fankes/MIUINativeNotifyIcon/blob/master/EXPLORE_HISTORY.md)

## 贡献通知图标优化名单

此项目是 `AndroidNotifyIconAdapt` 项目的一部分，详情请参考下方。

- [Android 通知图标规范适配计划](https://github.com/fankes/AndroidNotifyIconAdapt)

## 请勿用于非法用途

- 本模块完全开源免费，如果好用你可以打赏支持开发，但是请不要用于非法用途。

## 发行渠道说明

- [Automatic Build on Commit](https://github.com/fankes/MIUINativeNotifyIcon/actions/workflows/commit_ci.yml)

上述更新为代码 `commit` 后自动触发，具体更新内容可点击上方的文字前往 **Github Actions** 进行查看，本更新由开源的流程自动编译发布，**不保证其稳定性**，所发布的版本**仅供测试**，且不会特殊说明甚至可能会变更版本号或保持与当前稳定版相同的版本号。

- [Release](https://github.com/fankes/MIUINativeNotifyIcon/releases)
- [Xposed-Modules-Repo](https://github.com/Xposed-Modules-Repo/com.fankes.miui.notify/releases)
- [蓝奏云 **密码：62ll**](https://fankes.lanzouy.com/b030o2e8h)
- [酷安应用市场](https://www.coolapk.com/apk/com.fankes.miui.notify)

上述更新为手动发布的稳定版，具体更新内容可点击上方的文字前往指定的发布页面查看，稳定版的更新将会同时发布到上述地址中，同步更新。

## 发行状态说明

![Blank](https://img.shields.io/badge/build-passing-brightgreen)

上述状态为当前稳定版与自动构建版本一致或当前代码改动与稳定版无功能差异。

![Blank](https://img.shields.io/badge/build-pending-dbab09)

上述状态为存在自动构建版本和新功能的更新但当前并未发布稳定版，处于预发行状态。

![Blank](https://img.shields.io/badge/build-problem-red)

上述状态为当前发行的稳定版可能存在严重问题但并未及时进行修复且并未发布稳定版。

## 捐赠支持

- 工作不易，无意外情况此项目将继续维护下去，提供更多可能，欢迎打赏。<br/><br/>
  <img src="https://github.com/fankes/YuKiHookAPI/blob/master/img-src/wechat_code.jpg?raw=true" width = "200" height = "200"/>

## 隐私政策

- [PRIVACY](https://github.com/fankes/MIUINativeNotifyIcon/blob/master/PRIVACY.md)

## 许可证

- [AGPL-3.0](https://www.gnu.org/licenses/agpl-3.0.html)

```
Copyright (C) 2017-2023 Fankes Studio(qzmmcn@163.com)

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

Powered by [YukiHookAPI](https://github.com/fankes/YukiHookAPI)

版权所有 © 2017-2023 Fankes Studio(qzmmcn@163.com)