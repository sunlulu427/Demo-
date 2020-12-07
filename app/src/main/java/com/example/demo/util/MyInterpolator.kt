package com.example.demo.util

import android.animation.TimeInterpolator

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/7 11:24 PM
 **/
class MyInterpolator: TimeInterpolator {
    override fun getInterpolation(input: Float): Float {
        return 1 - input
    }
}