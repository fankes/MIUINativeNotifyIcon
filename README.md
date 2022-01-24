# MIUINativeNotifyIcon

![Eclipse Marketplace](https://img.shields.io/badge/build-passing-brightgreen)
![Eclipse Marketplace](https://img.shields.io/badge/license-GPL3.0-blue)
![Eclipse Marketplace](https://img.shields.io/badge/version-v1.0-green)
<br/><br/>
![banner](https://github.com/fankes/TSBattery/blob/master/banner.png)<br/>
Fix the native notification bar icon function abandoned by the MIUI development team.<br/>
修复被 MIUI 开发组丢弃的原生通知图标，支持 MIUI 12.5、13 以及最新版本。

# 开始使用

点击下载最新版本
<a href='https://github.com/fankes/MIUINativeNotifyIcon/releases'>![Eclipse Marketplace](https://img.shields.io/badge/download-v1.0-green)</a>
<br/><br/>
⚠️适配说明：此模块仅支持 Lsposed(作用域“系统界面”)、EdXposed(不推荐)、不支持太极无极(阴)

# 禁止任何商业用途

本模块完全开源免费，如果好用你可以打赏支持开发，严禁未经许可进行二改贩卖，违者必惩必究。

# 背景历史

这个模块诞生来源于 MIUI 的乱改和不规范，本来 MIUI 9 之后，官方给出了原生通知图标样式，后面由于用户反应通知栏经常出现黑白块。 这当然不是系统的错，而是国内 APP 极其不规范的通知图标设计，于是 MIUI
选择直接忽略这个问题把全部图标都改成了 APP 的彩色图标， 使得之前拥有自有样式的原生图标也被破坏，通知中“setSmallIcon”不再有效，这个模块就是为了修复被 MIUI 开发组忽略的图标问题， 并完美地给 MIUI 修复了黑白块图标的问题。
<br/>

# 许可证

- [GPL-3.0](https://www.gnu.org/licenses/gpl-3.0.html)

```
Copyright (C) 2020-2021  Fankes Studio(qzmmcn@163.com)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
```