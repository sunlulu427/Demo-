package com.example.demo

import android.animation.AnimatorInflater
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val valueAnimator: ValueAnimator = AnimatorInflater.loadAnimator(this, R.animator.animator)
                as ValueAnimator
        valueAnimator.addUpdateListener {
            val offset = it.animatedValue as Int
            text_view.layout(offset, offset, text_view.width + offset, text_view.height + offset)
        }

        start_anim.setOnClickListener {
            valueAnimator.takeIf { !it.isRunning }?.start()
        }

        start_anim.setOnTouchListener { v, event ->
            val velocityTracker = VelocityTracker.obtain()
            velocityTracker.addMovement(event)
            velocityTracker.computeCurrentVelocity(1000)
            val xVelocity = velocityTracker.xVelocity
            val yVelocity = velocityTracker.yVelocity
            velocityTracker.clear()
            velocityTracker.recycle()
            true
        }

        val onGestureListener = object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {
                TODO("Not yet implemented")
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onDown(e: MotionEvent?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onLongPress(e: MotionEvent?) {
                TODO("Not yet implemented")
            }
        }

        val gestureDetector = GestureDetector(this, onGestureListener)
        // 解决长按屏幕无法拖动的现象
        gestureDetector.setIsLongpressEnabled(false)

        start_anim.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
        }
    }
}
