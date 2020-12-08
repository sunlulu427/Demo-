package com.example.demo

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.widget.ImageView

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/9 12:51 AM
 **/
class FallingBallImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    fun setFallingPos(pos: Point) {
        layout(pos.x, pos.y, pos.x + width, pos.y + height)
    }
}