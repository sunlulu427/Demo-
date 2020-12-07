package com.example.demo

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/7 10:40 PM
 **/
class LoadingImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    companion object {
        private const val IMAGE_COUNT = 3
    }

    private var mTop = 0
    private var mCurImageIndex = 0

    init {
        val valueAnimator = ValueAnimator.ofInt(0, 100, 0).also {
            it.repeatCount = ValueAnimator.INFINITE
            it.repeatMode = ValueAnimator.RESTART
            it.duration = 2000
            it.interpolator = AccelerateDecelerateInterpolator()
            it.addUpdateListener { animator ->
                val dx = animator.animatedValue as Int
                top = mTop - dx
            }
        }

        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                mCurImageIndex ++
                setImageDrawable(when (mCurImageIndex % IMAGE_COUNT) {
                    0 -> resources.getDrawable(R.drawable.x1, null)
                    1 -> resources.getDrawable(R.drawable.x2, null)
                    2 -> resources.getDrawable(R.drawable.x3, null)
                    else -> resources.getDrawable(R.drawable.x4, null)
                })
            }

            override fun onAnimationEnd(animation: Animator?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationCancel(animation: Animator?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationStart(animation: Animator?) {
                TODO("Not yet implemented")
            }

        })

        valueAnimator.start()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        mTop = top
    }
}