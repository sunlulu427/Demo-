package com.example.demo

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
            val animator = ObjectAnimator.ofObject(image, "fallingPos",
                FallingBallEvaluator(), Point(0, 0), Point(500, 500))
            animator.duration = 2000
            animator.start()
        }
    }
}
