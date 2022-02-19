/*
 * MIUINativeNotifyIcon - Fix the native notification bar icon function abandoned by the MIUI development team.
 * Copyright (C) 2019-2022 Fankes Studio(qzmmcn@163.com)
 * https://github.com/fankes/MIUINativeNotifyIcon
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version and our eula as published
 * by ferredoxin.
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
 * This file is Created by fankes on 2022/2/19.
 */
package com.fankes.miui.notify.utils

import android.graphics.*
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.util.ArrayMap
import androidx.core.graphics.drawable.toBitmap
import kotlin.math.abs

/**
 * 这是一个从 AOSP 源码中分离出来的功能
 *
 * 主要作用于兼容部分 MIUI 魔改颜色判断代码造成判断位图灰度功能失效
 */
object BitmapCompatTool {

    /** 缓存已判断的结果防止卡顿 */
    private var cachedBitmapGrayscales = ArrayMap<Int, Boolean>()

    private var tempBuffer = intArrayOf(0)
    private var tempCompactBitmap: Bitmap? = null
    private var tempCompactBitmapCanvas: Canvas? = null
    private var tempCompactBitmapPaint: Paint? = null
    private val tempMatrix = Matrix()

    /**
     * 判断 [Drawable] 是否为灰度位图
     * @param drawable 要判断的 [Drawable]
     * @return [Boolean] 是否灰度
     */
    fun isGrayscaleDrawable(drawable: Drawable) = safeOfFalse {
        when (drawable) {
            is BitmapDrawable -> isGrayscaleBitmap(drawable.bitmap)
            is AnimationDrawable -> !(drawable.numberOfFrames <= 0 || !isGrayscaleBitmap(drawable.getFrame(0).toBitmap()))
            is VectorDrawable -> true
            else -> isGrayscaleBitmap(drawable.toBitmap())
        }
    }

    /**
     * 判断 [Bitmap] 是否为灰度位图
     * @param bitmap 要判断的位图
     * @return [Boolean] 是否灰度
     */
    private fun isGrayscaleBitmap(bitmap: Bitmap) =
        cachedBitmapGrayscales[bitmap.generationId] ?: let {
            var height = bitmap.height
            var width = bitmap.width
            if (height > 64 || width > 64) {
                if (tempCompactBitmap == null) {
                    tempCompactBitmap = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888)
                        .also { tempCompactBitmapCanvas = Canvas(it) }
                    tempCompactBitmapPaint = Paint(Paint.FILTER_BITMAP_FLAG).apply { isFilterBitmap = true }
                }
                tempMatrix.reset()
                tempMatrix.setScale(64f / width, 64f / height, 0f, 0f)
                tempCompactBitmapCanvas?.drawColor(0, PorterDuff.Mode.SRC)
                tempCompactBitmapCanvas?.drawBitmap(bitmap, tempMatrix, tempCompactBitmapPaint)
                height = 64
                width = 64
            }
            val size = height * width
            ensureBufferSize(size)
            tempCompactBitmap?.getPixels(tempBuffer, 0, width, 0, 0, width, height)
            for (i in 0 until size)
                if (!isGrayscaleColor(tempBuffer[i])) {
                    cachedBitmapGrayscales[bitmap.generationId] = false
                    return@let false
                }
            cachedBitmapGrayscales[bitmap.generationId] = true
            true
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
}