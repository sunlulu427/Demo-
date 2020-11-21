package com.example.demo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.demo.util.ResUtil

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/11/22 2:09 AM
 **/
class KtvMusicTagsView  @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attributeSet, defStyle) {

    var content = listOf<String>()
        set(tags) {
            field = tags
            invalidate()
        }
    private val paint = Paint().also {
        it.color = Color.BLUE
        it.isAntiAlias = true
        it.textAlign = Paint.Align.LEFT
    }
    private var centerLine = 0f
    private var baseLine = 0f
    private val gap = ResUtil.dp2px(5f)
    private val padding = ResUtil.dp2px(2f)
    private val rect = RectF()
    private var startX = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        centerLine = (height) / 2f
        baseLine = centerLine + (paint.fontMetrics.bottom - paint.fontMetrics.top) / 2 - paint.fontMetrics.bottom
        paint.textSize = ResUtil.dp2px(13f).toFloat()

        content.forEachIndexed { index, tag ->
            startX += calcStartX(index)
            canvas?.drawText(tag, startX, baseLine, paint)
            startX += paint.measureText(tag)
        }

        paint.strokeWidth = 2f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        startX = 0f
        rect.top = paint.strokeWidth
        rect.bottom = height - paint.strokeWidth

        for (i in content.indices) {
            rect.left = startX
            rect.right = rect.left + 2 * padding + paint.measureText(content[i])
            canvas?.drawRoundRect(rect, 23f, 23f, paint)
            startX = rect.right + gap
        }
    }

    private fun calcStartX(index: Int): Float {
        return index * 2f * padding + index * gap
    }
}