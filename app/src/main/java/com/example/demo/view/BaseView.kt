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

        canvas?.drawColor(Color.RED)
        canvas?.save()

        canvas?.clipRect(100, 100, 800, 800)
        canvas?.drawColor(Color.GREEN)

        canvas?.restore()
        canvas?.drawColor(Color.BLUE)
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