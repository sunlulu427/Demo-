package com.example.demo

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import com.example.demo.util.normalToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_anim.setOnClickListener {
            val animator = ValueAnimator.ofFloat(0f, 400f, 50f, 300f).also {
                it.duration = 3000
            }
            animator.addUpdateListener {
                val curValue = (it.animatedValue as Float).toInt()
                tv.layout(curValue, curValue, curValue + tv.width, curValue + tv.height)
            }
            animator.start()
        }

        tv.setOnClickListener {
            normalToast("click me")
        }
    }
}
