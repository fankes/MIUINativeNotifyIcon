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
 * This file is created by fankes on 2022/2/19.
 */
package com.fankes.miui.notify.utils.tool

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.util.LruCache
import androidx.annotation.Px
import androidx.core.graphics.createBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.scale
import com.fankes.miui.notify.utils.factory.safeOf
import com.fankes.miui.notify.utils.factory.safeOfFalse
import java.util.WeakHashMap
import kotlin.math.abs

/**
 * 这是一个从 AOSP 源码中分离出来的功能
 *
 * 主要作用于兼容部分第三方系统修改颜色判断代码造成判断位图灰度功能失效
 */
object ContrastBitmapTool {

    /** 按位图及其内容版本缓存灰度结果，位图释放后允许回收对应条目 */
    private val cachedGrayscaleBitmaps = WeakHashMap<Bitmap, Pair<Int, Boolean>>()

    /** 缓存已优化的位图 */
    private val optimizedCache = LruCache<String, Bitmap>(64)

    private var tempBuffer = intArrayOf(0)
    private var tempCompactBitmap: Bitmap? = null
    private var tempCompactBitmapCanvas: Canvas? = null
    private var tempCompactBitmapPaint: Paint? = null
    private val tempMatrix = Matrix()

    /** 非锐化掩模强度 - 按 「相对局部均值」 加深对比，是唯一的清晰度旋钮 */
    private const val SHARPEN_AMOUNT = 1.65f

    /** 局部均值半径 - 以 「源像素」 为单位，取约 1 源像素才能横跨一条笔画 + 一条缝隙 */
    private const val LOCAL_MEAN_RADIUS = 1.0f

    /**
     * 将图标绘制到指定尺寸，缩放画布以保留包装图标的固定边距比例
     * @param drawable 原始图标
     * @param width 输出宽度 (px)
     * @param height 输出高度 (px)
     * @return [Bitmap] 指定尺寸的图标位图
     */
    fun rasterize(drawable: Drawable, @Px width: Int, @Px height: Int): Bitmap {
        if (drawable is BitmapDrawable) return drawable.toBitmap(width, height)
        val sourceWidth = drawable.intrinsicWidth.takeIf { it > 0 } ?: width
        val sourceHeight = drawable.intrinsicHeight.takeIf { it > 0 } ?: height
        val left = drawable.bounds.left
        val top = drawable.bounds.top
        val right = drawable.bounds.right
        val bottom = drawable.bounds.bottom
        val bitmap = createBitmap(width, height)
        val canvas = Canvas(bitmap)
        canvas.scale(width.toFloat() / sourceWidth, height.toFloat() / sourceHeight)
        try {
            drawable.setBounds(0, 0, sourceWidth, sourceHeight)
            drawable.draw(canvas)
        } finally {
            drawable.setBounds(left, top, right, bottom)
        }
        return bitmap
    }

    /**
     * 针对图标视图尺寸优化位图清晰度 (重采样)
     * @param bitmap 源位图
     * @param targetPx 目标边长 (px) - 小于等于 0 时不处理
     * @return [Bitmap]
     */
    fun resampling(bitmap: Bitmap, @Px targetPx: Int) = safeOf(bitmap) {
        val width = bitmap.width
        val height = bitmap.height
        if (targetPx <= 0 || width <= 0 || height <= 0) return@safeOf bitmap
        val cacheKey = "${bitmap.generationId}:$targetPx"
        optimizedCache.get(cacheKey)?.let { return@safeOf it }
        val result = when {
            maxOf(width, height) >= targetPx -> progressiveScale(bitmap, targetPx)
            else -> sharpenUpscale(bitmap, targetPx)
        }
        optimizedCache.put(cacheKey, result)
        result
    }

    /**
     * 判断 [Drawable] 是否为灰度位图
     * @param drawable 要判断的 [Drawable]
     * @return [Boolean] 是否灰度
     */
    @Synchronized
    fun isGrayscale(drawable: Drawable): Boolean = safeOfFalse {
        when (drawable) {
            is BitmapDrawable -> isGrayscale(drawable.bitmap)
            is AnimationDrawable -> drawable.numberOfFrames > 0 && isGrayscale(drawable.getFrame(0))
            is VectorDrawable -> true
            // 包装图标按固有尺寸的画布缩放后采样，临时位图不进入灰度缓存
            else -> {
                val width = drawable.intrinsicWidth.takeIf { it > 0 }?.coerceAtMost(64) ?: 64
                val height = drawable.intrinsicHeight.takeIf { it > 0 }?.coerceAtMost(64) ?: 64
                scanGrayscale(rasterize(drawable, width, height))
            }
        }
    }

    /**
     * 判断 [Bitmap] 是否为灰度位图
     * @param bitmap 要判断的位图
     * @return [Boolean] 是否灰度
     */
    private fun isGrayscale(bitmap: Bitmap): Boolean {
        val generationId = bitmap.generationId
        cachedGrayscaleBitmaps[bitmap]?.takeIf { it.first == generationId }?.let { return it.second }
        val result = scanGrayscale(bitmap)
        cachedGrayscaleBitmaps[bitmap] = generationId to result
        return result
    }

    /** 扫描位图像素，不缓存临时采样结果 */
    private fun scanGrayscale(bitmap: Bitmap): Boolean {
        var height = bitmap.height
        var width = bitmap.width
        var sampledBitmap = bitmap
        if (height > 64 || width > 64) {
            if (tempCompactBitmap == null) {
                tempCompactBitmap = createBitmap(64, 64)
                    .also { tempCompactBitmapCanvas = Canvas(it) }
                tempCompactBitmapPaint = Paint(Paint.FILTER_BITMAP_FLAG).apply { isFilterBitmap = true }
            }
            tempMatrix.reset()
            tempMatrix.setScale(64f / width, 64f / height, 0f, 0f)
            tempCompactBitmapCanvas?.drawColor(0, PorterDuff.Mode.SRC)
            tempCompactBitmapCanvas?.drawBitmap(bitmap, tempMatrix, tempCompactBitmapPaint)
            sampledBitmap = tempCompactBitmap ?: return false
            height = 64
            width = 64
        }
        val size = height * width
        ensureBufferSize(size)
        sampledBitmap.getPixels(tempBuffer, 0, width, 0, 0, width, height)
        for (i in 0 until size)
            if (isGrayscaleColor(tempBuffer[i]).not()) return false
        return true
    }

    /**
     * 提纯 [Bitmap] 颜色判断灰度
     * @param color 颜色
     * @return [Boolean] 是否灰度
     */
    private fun isGrayscaleColor(color: Int): Boolean {
        if (color shr 24 and 255 < 50) return true
        val r = color shr 16 and 255
        val g = color shr 8 and 255
        val b = color and 255
        return !(abs(r - g) >= 20 || abs(r - b) >= 20 || abs(g - b) >= 20)
    }

    /**
     * 计算字节数组
     * @param size 大小
     */
    private fun ensureBufferSize(size: Int) {
        if (tempBuffer.size < size) tempBuffer = IntArray(size)
    }

    /**
     * 放大低分辨率单色图标并在目标分辨率上锐化
     *
     * 1. 双线性平滑放大到目标尺寸 (低清源放大只能到此为止，超采样加不出细节，故不超采样)
     * 2. 求局部均值，在目标分辨率上做非锐化掩模 (相对局部对比) ——比周围暗的缝隙压透、亮的笔画顶实
     * 3. 直接钳制到 0..255 (保留连续渐变，不阈值化)，RGB 置白避免深色色晕
     *
     * 只锐化不阈值：相对局部均值即可分开源图里高 alpha 缝隙的相邻笔画 (不糊成一坨)，
     * 边是连续渐变而非硬切——图标经视图 FIT_XY/CENTER 缩放时不露锯齿 (硬切边才会)
     * 锐化必须在目标分辨率做：若先超采样锐化再降采样会把锐化平均掉、反而更糊
     * 单色图标最终都会被着色，只有 alpha 决定形状，所以只处理 alpha 即可
     * @param src 源位图
     * @param targetPx 目标边长 (px)
     * @return [Bitmap]
     */
    private fun sharpenUpscale(src: Bitmap, @Px targetPx: Int): Bitmap {
        val scale = targetPx.toFloat() / maxOf(src.width, src.height)
        val w = (src.width * scale).toInt().coerceAtLeast(1)
        val h = (src.height * scale).toInt().coerceAtLeast(1)
        val up = src.scale(w, h)
        val size = w * h
        val pixels = IntArray(size)
        up.getPixels(pixels, 0, w, 0, 0, w, h)
        val alpha = IntArray(size) { pixels[it] ushr 24 }
        val mean = alpha.copyOf()
        val radius = (scale * LOCAL_MEAN_RADIUS).toInt().coerceIn(1, 12)
        boxBlurAlpha(mean, w, h, radius)
        for (i in 0 until size) {
            val newAlpha = (alpha[i] + SHARPEN_AMOUNT * (alpha[i] - mean[i])).toInt().coerceIn(0, 255)
            pixels[i] = (newAlpha shl 24) or 0x00FFFFFF
        }
        val out = createBitmap(w, h)
        out.setPixels(pixels, 0, w, 0, 0, w, h)
        return out
    }

    /**
     * 对 alpha 通道做可分离盒式模糊 (原地修改)
     *
     * 用于估计局部均值以做非锐化掩模；叠加 [passes] 次近似高斯，边界采用就近钳制
     * @param a alpha 数组 (0..255) - 原地修改
     * @param w 宽 (px)
     * @param h 高 (px)
     * @param radius 模糊半径 (px)
     * @param passes 叠加次数
     */
    private fun boxBlurAlpha(a: IntArray, @Px w: Int, @Px h: Int, @Px radius: Int, passes: Int = 1) {
        if (radius < 1 || passes < 1) return
        val window = 2 * radius + 1
        val tmp = IntArray(a.size)
        repeat(passes) {
            for (y in 0 until h) {
                val base = y * w
                var sum = 0
                for (k in -radius..radius) sum += a[base + k.coerceIn(0, w - 1)]
                tmp[base] = sum / window
                for (x in 1 until w) {
                    sum += a[base + (x + radius).coerceIn(0, w - 1)] - a[base + (x - radius - 1).coerceIn(0, w - 1)]
                    tmp[base + x] = sum / window
                }
            }
            for (x in 0 until w) {
                var sum = 0
                for (k in -radius..radius) sum += tmp[k.coerceIn(0, h - 1) * w + x]
                a[x] = sum / window
                for (y in 1 until h) {
                    sum += tmp[(y + radius).coerceIn(0, h - 1) * w + x] - tmp[(y - radius - 1).coerceIn(0, h - 1) * w + x]
                    a[y * w + x] = sum / window
                }
            }
        }
    }

    /**
     * 渐进式 1/2 降采样，避免一步降采样导致模糊
     * @param bitmap 源位图
     * @param targetPx 目标边长 (px)
     * @return [Bitmap]
     */
    private fun progressiveScale(bitmap: Bitmap, targetPx: Int): Bitmap {
        var current = bitmap
        var w = bitmap.width
        var h = bitmap.height
        while (w / 2 >= targetPx && h / 2 >= targetPx) {
            w /= 2
            h /= 2
            current = current.scale(w, h)
        }
        return current
    }
}