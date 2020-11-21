package com.example.demo.util

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/11/22 2:43 AM
 **/
object ResUtil {
    private val scale = ContextUtil.context.resources.displayMetrics.density

    fun dp2px(dpValue: Float): Int {
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dp(pxValue: Float): Int {
        return (pxValue / scale + 0.5f).toInt()
    }
}