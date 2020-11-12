package com.example.demo

import android.app.Application
import android.content.Context

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/11/13 2:43 AM
 **/
class MyApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}