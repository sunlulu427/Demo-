package com.example.demo

import android.animation.Animator
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

        var repeatAnimator: ValueAnimator? = null
        start_anim.setOnClickListener {
            if (repeatAnimator == null || !repeatAnimator!!.isRunning)
            repeatAnimator = doRepeatAnim()
        }

        cancel_anim.setOnClickListener {
            repeatAnimator?.cancel()
        }
    }

    private fun doRepeatAnim(): ValueAnimator {
        val animator = ValueAnimator.ofInt(0, 400)
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv.layout(tv.left, curValue, tv.right, tv.height + curValue)
        }
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 1000
        animator.start()
        return animator
    }
}
