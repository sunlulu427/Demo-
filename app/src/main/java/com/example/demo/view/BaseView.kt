package com.example.demo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/11/18 2:07 AM
 **/
class BaseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val greenPaint = generatePaint(Color.GREEN, Paint.Style.STROKE, 3)
        val redPaint = generatePaint(Color.RED, Paint.Style.STROKE, 3)

        val rect1 = Rect(0, 0, 400, 220)
        canvas?.drawRect(rect1, greenPaint)

        canvas?.translate(100f, 100f)
        canvas?.drawRect(rect1, redPaint)
    }

    private fun drawRegion(canvas: Canvas?, region: Region, paint: Paint) {
        val iter = RegionIterator(region)
        val r = Rect()
        while (iter.next(r)) {
            canvas?.drawRect(r, paint)
        }
    }

    private fun generatePaint(color: Int, style: Paint.Style, width: Int): Paint {
        return Paint().apply {
            setColor(color)
            setStyle(style)
            strokeWidth = width.toFloat()
        }
    }
}