package com.example.demo

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/9 1:40 AM
 **/
class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    fun setScaleSize(num: Float) {
        scaleX = num
    }
}