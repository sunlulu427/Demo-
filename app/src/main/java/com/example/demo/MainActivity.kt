package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_anim.setOnClickListener {
            val animation = TranslateAnimation(
                Animation.ABSOLUTE, 0f, Animation.ABSOLUTE, -80f,
                Animation.ABSOLUTE, 0F, Animation.ABSOLUTE, -80F
            ).also {
                it.duration = 2000
                it.fillBefore = true
            }
            tv.startAnimation(animation)
        }
    }
}
