package com.example.demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/11/18 2:07 AM
 **/
class BaseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : View(context, attrs, defStyle) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 设置画笔的基本属性
        val paint = Paint().apply {
            color = 0xFFFF0000.toInt()
            style = Paint.Style.FILL
            strokeWidth = 50f
        }

        // 画圆
        canvas?.drawCircle(190f, 200f, 150f, paint)

        paint.color = 0x7EFFFF00
        canvas?.drawCircle(190f, 200f, 100f, paint)
    }
}