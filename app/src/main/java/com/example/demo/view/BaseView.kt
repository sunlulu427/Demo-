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
        style = Paint.Style.FILL
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val region = Region(Rect(50, 50, 200, 100))
    }

    private fun drawRegion(canvas: Canvas?, region: Region, paint: Paint) {
        val iter = RegionIterator(region)
        val r = Rect()

        while (iter.next(r)) {
            canvas?.drawRect(r, paint)
        }
    }
}