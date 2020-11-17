package com.example.demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
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
        strokeWidth = 5f
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        path.moveTo(10f, 10f)
        path.lineTo(10f, 100f)
        path.lineTo(300f, 100f)
        path.close()
        canvas?.drawPath(path, paint)
    }

}