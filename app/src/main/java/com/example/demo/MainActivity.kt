package com.example.demo

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var animatorSet: AnimatorSet? = null
        start_anim.setOnClickListener {
            if (animatorSet?.isRunning == true) {
                animatorSet?.cancel()
                return@setOnClickListener
            }
//            val tv1Animator = ObjectAnimator.ofInt(tv_1, "BackgroundColor", 0xffff00ff.toInt(), 0xffffff00.toInt(), 0xffff00ff.toInt())
            val tv1TranslateAnimator = ObjectAnimator.ofFloat(tv_1, "translationY", 0f, 400f, 0f)

            val tv2Animator = ObjectAnimator.ofFloat(tv_2, "translationY", 0f, 400f, 0f)
            tv2Animator.startDelay = 2000L

            animatorSet = AnimatorSet()
            animatorSet?.duration = 2000
            animatorSet?.startDelay = 2000
            animatorSet?.play(tv1TranslateAnimator)
                ?.with(tv2Animator)
            animatorSet?.start()
        }
    }
}
