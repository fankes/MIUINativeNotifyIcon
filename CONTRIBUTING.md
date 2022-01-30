# 开始贡献

欢迎为通知图标优化名单贡献宝贵资源！<br/>

## 分支规定

不管是直接 Push 代码还是提交 Pull Request，都必须使 commit 指向 master 分支。

## 代码格式规范

- 1.全部提交代码必须使用 IDE(Android Studio 或 IDEA) 进行格式化，未经格式化的代码将拒绝合并提交请求
- 2.代码必须使用 4 spaces 缩进格式化

## 贡献方法

- 在下方的类中添加新的 APP 通知图标适配条目
- [IconPackParams.kt](https://github.com/fankes/MIUINativeNotifyIcon/blob/master/app/src/main/java/com/fankes/miui/notify/params/IconPackParams.kt)
- 使用灰度位图转 Base64 来得到 Base64 的位图数据字符串
- [BitmapToBase64](https://github.com/fankes/BitmapToBase64)
- 新增条目的模板如下所示

```kotlin
IconDataBean(
    isEnabled = true, // 是否默认启用替换
    isEnabledAll = false, // 是否默认启用完全替换
    appName = "", // APP 名称
    packageName = "", // APP 包名
    iconBitmap = ("").bitmap, // 位图数据 Base64
    contributorName = "" // 贡献者昵称
)
```

- 提交时请将后方的注释删除，否则不予合并代码

## 其它要求

- 1.调试性质或大批量注释代码，禁止提交
- 2.类名和方法名仅能由开发者进行修改和提交，禁止随意修改项目名称、方法名称以及类名
- 3.禁止随意更新项目依赖以及增加新的依赖，有问题请提前提交到 issues 进行说明
- 4.禁止更新项目版本号，版本号交由开发者合并代码并发布 release 版本
- 5.代码语言要求，请统一使用 Kotlin，除特殊情况外，不接受其他语言的提交
- 6.以上