package com.example.demo

import android.animation.AnimatorInflater
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}
