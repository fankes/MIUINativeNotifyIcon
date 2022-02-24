# 开始贡献

欢迎为通知图标优化名单贡献宝贵资源！<br/>

## 分支规定

不管是直接 Push 代码还是提交 Pull Request，都必须使 commit 指向 master 分支。

## 代码格式规范

- 1.全部提交代码必须使用 IDE(Android Studio 或 IDEA) 进行格式化，未经格式化的代码将拒绝合并提交请求
- 2.代码必须使用 4 spaces 缩进格式化

## 贡献方法

- 2022.02.25 适配数据已变更为云端更新
- 在下方的 JSON 文件中添加新的 APP 通知图标适配条目
- [NotifyIconsSupportConfig.json](https://github.com/fankes/MIUINativeNotifyIcon/blob/master/iconPack/NotifyIconsSupportConfig.json)
- 使用灰度位图转 Base64 来得到 Base64 的位图数据字符串
- [BitmapToBase64](https://github.com/fankes/BitmapToBase64)
- 新增条目的模板如下所示

```json
{
  "appName": "", // APP 名称
  "packageName": "", // APP 包名
  "isEnabled": true, // 是否默认启用替换彩色图标 - 关闭后将全部停止替换
  "isEnabledAll": false, // 是否默认启用替换全部图标
  "iconBitmap": "", // 位图数据 Base64 字符串
  "iconColor": "#ff232323", // 通知栏中显示的图标颜色 - 不设置使用系统默认颜色 (不设置颜色可删除此项)
  "contributorName": "" // 贡献者昵称
}
```

- 图标大小建议保持在 50x50
- 提交时请将后方的注释删除，否则不予合并代码

## 同步列表地址

- Github 直连地址 [Raw](https://raw.githubusercontent.com/fankes/MIUINativeNotifyIcon/master/iconPack/NotifyIconsSupportConfig.json)
- 数据将在稍后同步至 [Surge](https://fankes.mnn.surge.sh/NotifyIconsSupportConfig.json)

## 其它要求

- 1.调试性质或大批量注释代码，禁止提交
- 2.类名和方法名仅能由开发者进行修改和提交，禁止随意修改项目名称、方法名称以及类名
- 3.禁止随意更新项目依赖以及增加新的依赖，有问题请提前提交到 issues 进行说明
- 4.禁止更新项目版本号，版本号交由开发者合并代码并发布 release 版本
- 5.代码语言要求，请统一使用 Kotlin，除特殊情况外，不接受其他语言的提交
- 6.以上