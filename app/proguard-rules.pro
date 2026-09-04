# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-ignorewarnings
-optimizationpasses 10
-dontusemixedcaseclassnames
-dontoptimize
-verbose
-overloadaggressively
-allowaccessmodification
-adaptclassstrings
-adaptresourcefilenames
-adaptresourcefilecontents

-renamesourcefileattribute P
-keepattributes SourceFile,Signature,LineNumberTable

-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    public static *** throwUninitializedProperty(...);
    public static *** throwUninitializedPropertyAccessException(...);
}

-keep class * extends android.app.Activity
-keep class * implements androidx.viewbinding.ViewBinding {
    <init>();
    *** inflate(android.view.LayoutInflater);
}

# LSPosed 以注入类加载器载入模块，无法依赖 META-INF/services 发现 Main dispatcher
# kotlinx-coroutines-android 的 R8 规则会假定 ServiceLoader 已被优化，因此保留其 Android 快速加载路径
-assumevalues class kotlinx.coroutines.internal.MainDispatcherLoader {
    boolean FAST_SERVICE_LOADER_ENABLED return true;
}
-keep class kotlinx.coroutines.internal.MainDispatcherLoader {*;}
-keep class kotlinx.coroutines.internal.FastServiceLoader {*;}
-keep class kotlinx.coroutines.android.AndroidDispatcherFactory {*;}