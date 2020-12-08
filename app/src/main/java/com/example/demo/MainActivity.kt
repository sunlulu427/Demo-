package com.example.demo

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_anim.setOnClickListener {
            val tv1Animator = ObjectAnimator.ofInt(tv_1, "BackgroundColor", 0xffff00ff.toInt(), 0xffffff00.toInt(), 0xffff00ff.toInt())
            val tv1TranslateAnimator = ObjectAnimator.ofFloat(tv_1, "translationY", 0f, 300f, 0f)
            val tv2Animator = ObjectAnimator.ofFloat(tv_2, "translationY", 0f, 400f, 0f)

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(tv1Animator, tv1TranslateAnimator, tv2Animator)
            animatorSet.setDuration(1000)
            animatorSet.start()
        }
    }
}
