package com.example.demo

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_anim.setOnClickListener {
            val animator = ObjectAnimator.ofFloat(image, "translateX", 0f, 270f, 0f)
            animator.duration = 5000
            animator.start()
        }
    }
}
