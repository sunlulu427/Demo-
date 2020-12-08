package com.example.demo.evaluator

import android.animation.TypeEvaluator
import android.graphics.Point

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/8 12:15 AM
 **/
class FallingBallEvaluator: TypeEvaluator<Point> {

    private val point = Point()

    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
    }
}