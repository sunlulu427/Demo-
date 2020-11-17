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

//        canvas?.drawRGB(255, 0, 255)
        canvas?.drawARGB(0xFF, 0XFF, 0, 0XFF)
//        canvas?.drawColor(0xFFFF00FF.toInt())
    }

}