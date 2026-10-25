# HyperOS/MIUI Stock Notification Icon

[![GitHub license](https://img.shields.io/github/license/fankes/MIUINativeNotifyIcon?color=blue&style=flat-square)](https://github.com/fankes/MIUINativeNotifyIcon/blob/master/LICENSE)
[![GitHub CI](https://img.shields.io/github/actions/workflow/status/fankes/MIUINativeNotifyIcon/commit_ci.yml?label=CI%20builds&style=flat-square)](https://github.com/fankes/MIUINativeNotifyIcon/actions/workflows/commit_ci.yml)
[![GitHub release](https://img.shields.io/github/v/release/fankes/MIUINativeNotifyIcon?display_name=release&logo=github&color=green&style=flat-square)](https://github.com/fankes/MIUINativeNotifyIcon/releases)
![GitHub all releases](https://img.shields.io/github/downloads/fankes/MIUINativeNotifyIcon/total?label=downloads&style=flat-square)
![GitHub all releases](https://img.shields.io/github/downloads/Xposed-Modules-Repo/com.fankes.miui.notify/total?label=LSPosed%20downloads&labelColor=F48FB1&style=flat-square)

[![Telegram CI](https://img.shields.io/badge/CI%20builds-Telegram-blue.svg?logo=telegram&style=flat-square)](https://t.me/MIUINativeNotifyIcon_CI)
[![Telegram Channel](https://img.shields.io/badge/channel-Telegram-blue.svg?logo=telegram&style=flat-square)](https://t.me/XiaofangDaily)
[![Telegram](https://img.shields.io/badge/discussion-Telegram-blue.svg?logo=telegram&style=flat-square)](https://t.me/XiaofangInternet)
[![QQ](https://img.shields.io/badge/discussion-QQ-blue.svg?logo=tencent-qq&logoColor=red&style=flat-square)](https://qm.qq.com/cgi-bin/qm/qr?k=dp2h5YhWiga9WWb_Oh7kSHmx01X8I8ii&jump_from=webapi&authKey=Za5CaFP0lk7+Zgsk2KpoBD7sSaYbeXbsDgFjiWelOeH4VSionpxFJ7V0qQBSqvFM)
[![QQ Channel](https://img.shields.io/badge/discussion-QQ%20频道-blue.svg?logo=tencent-qq&logoColor=red&style=flat-square)](https://pd.qq.com/s/44gcy28h)

<img src="img-src/icon.png" width = "100" height = "100" alt="LOGO"/>

English | [简体中文](README-zh-CN.md)

Fix the stock notification bar icon function abandoned by the HyperOS/MIUI development team.

## Project Migration Notice

Maintaining two modules for both **HyperOS/MIUI** and **ColorOS** at the same time is very inconvenient, so I plan to gradually merge and decouple the
two
projects into a new one, with plans to support more systems and devices, such as AOSP and AOSP-like systems.

Once the new project is settled, its link will be added here. At that time I will stop maintaining this project and recommend that everyone move to
the new project.

## Compatibility Notes

- **LSPosed** (scope: "System UI") is recommended
- Please make sure you are using an official HyperOS/MIUI build. The developer has no obligation to solve or fix problems that occur on any
  third-party
  modified ROM
- The minimum supported version is MIUI 11 based on Android 9, or MIUI 12 / 12.5
- It is recommended to use the module from the MIUI 12.5 `2021-5-18` development build onwards. Earlier versions may have MIUI's own bugs that make it
  ineffective or show black and white block icons

## Background

Click [here](EXPLORE_HISTORY.md) to read the background and exploration history of this module (Chinese only).

## Contribute to the ANIP

The notification icons of this project come from the ANIP project. [Click here](https://github.com/BetterAndroid/android-notification-icon-project)
for details.

## Release Channels

| <img src="https://avatars.githubusercontent.com/in/15368?s=64&v=4" width = "30" height = "30" alt="LOGO"/> | [GitHub CI](https://github.com/fankes/MIUINativeNotifyIcon/actions/workflows/commit_ci.yml) | CI automatic builds (beta) |
|------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|----------------------------|

| <img src="https://github.com/peter-iakovlev/Telegram/blob/public/Icon.png?raw=true" width = "30" height = "30" alt="LOGO"/> | [Telegram CI Channel](https://t.me/MIUINativeNotifyIcon_CI) | CI automatic builds (beta) |
|-----------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------|----------------------------|

| <img src="https://avatars.githubusercontent.com/in/15368?s=64&v=4" width = "30" height = "30" alt="LOGO"/> | [GitHub Releases](https://github.com/fankes/MIUINativeNotifyIcon/releases) | Official releases (stable) |
|------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------|----------------------------|

| <img src="https://avatars.githubusercontent.com/u/78217009?s=200&v=4?raw=true" width = "30" height = "30" alt="LOGO"/> | [Xposed-Modules-Repo](https://github.com/Xposed-Modules-Repo/com.fankes.miui.notify/releases) | Official releases (stable) |
|------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|----------------------------|

The release addresses of this module are limited to those listed above. We are not responsible for any impact caused by versions downloaded from other
unofficial channels.

### Choose a Module Version by Scenario

The installation packages of this project come in two tracks: **modern** and **legacy**.

- Versions marked **modern** are for Xposed frameworks that support the libxposed API, such as LSPosed
- Versions marked **legacy** are for Xposed frameworks that support the Xposed Framework API, such as old LSPosed (≤ 1.9.0), EdXposed, etc.

The **modern** version supports automatic hot reload on LSPosed 2.1.0+. After the first installation, later module updates take effect without
restarting System UI.

## Notes

1. This software is free and developed out of interest, for learning and communication only. If you obtained it from an unofficial channel by paying
   for it, you may have been defrauded, and you are welcome to report suspicious behavior to us.
2. This software is licensed under the **GNU Affero General Public License (AGPL 3.0)**. According to the license:
    1. Any derivative work must use the same AGPLv3 license
    2. The complete source code must be provided when distributing this software or its modified versions
    3. The original copyright notice and license information must be retained
    4. No additional restrictions may be imposed to limit others' free use of this software
3. We encourage free distribution and improvement under the terms of AGPLv3, but please respect the author's attribution and do not impersonate the
   original author

## More Projects

<!--suppress HtmlDeprecatedAttribute -->
<div align="center">
    <h2>Hey, please stay a moment! 👋</h2>
    <h3>If you find this project helpful, take a look at my other projects below!</h3>
    <h3>If these projects help you, consider giving me a follow or a star ⭐️!</h3>
    <h1><a href="https://github.com/fankes/fankes/blob/main/project-promote/README.md">→ Click here to see more of my projects ←</a></h1>
</div>

## Star History

[![Star History Chart](https://api.star-history.com/chart?repos=fankes/MIUINativeNotifyIcon&type=date&legend=top-left&sealed_token=e08B7CwtCWLIhfayg8iVh4-3aBIVSVi2eNIWy2MbhxI2KND4ObyRmak37WHM3IGMOmmlFV8qaYfrTpqFA6IPjYuhCPNOlSCUeQ2Jj3Kt1bU--xm6e3Q6uQ)](https://www.star-history.com/?repos=fankes%2FMIUINativeNotifyIcon&type=date&legend=top-left)

## License

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

Copyright © 2017 Fankes Studio(qzmmcn@163.com)