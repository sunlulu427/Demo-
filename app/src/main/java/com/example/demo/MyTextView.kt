package com.example.demo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView

/**
 * @author: sunlulu.tomato
 * @date: 2020-12-07
 */
class MyTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    companion object {
        const val TAG = "MyTextView"
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG,  when(event?.action) {
            MotionEvent.ACTION_DOWN -> "dispatchTouchEvent ACTION_DOWN"
            MotionEvent.ACTION_MOVE -> "dispatchTouchEvent ACTION_MOVE"
            MotionEvent.ACTION_UP -> "dispatchTouchEvent ACTION_UP"
            MotionEvent.ACTION_CANCEL -> "dispatchTouchEvent ACTION_CANCEL"
            else -> ""
        })
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG,  when(event?.action) {
            MotionEvent.ACTION_DOWN -> "onTouchEvent ACTION_DOWN"
            MotionEvent.ACTION_MOVE -> "onTouchEvent ACTION_MOVE"
            MotionEvent.ACTION_UP -> "onTouchEvent ACTION_UP"
            MotionEvent.ACTION_CANCEL -> "onTouchEvent ACTION_CANCEL"
            else -> ""
        })
        return super.onTouchEvent(event)
    }
}