package com.example.demo

import android.animation.TypeEvaluator
import android.graphics.Point

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/9 12:54 AM
 **/
class FallingBallEvaluator: TypeEvaluator<Point> {

    private val point = Point()

    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
        if (startValue == null || endValue == null) return point
        point.x = (startValue.x + fraction * (endValue.x - startValue.x)).toInt()
        if (fraction * 2 <= 1) {
            point.y = (startValue.y + fraction * 2 * (endValue.y - startValue.y)).toInt()
        } else {
            point.y = endValue.y
        }
        return point
    }
}