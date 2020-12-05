package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_anim.setOnClickListener {
            val animation = AlphaAnimation(1f, 0.1f).also {
                it.duration = 3000L
                it.fillBefore = true
            }
            tv.startAnimation(animation)
        }
    }
}
