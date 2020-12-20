package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/21 12:41 AM
 **/
open class BaseActivity: AppCompatActivity() {

    inline fun BaseActivity.transFragment(action: FragmentTransaction.() -> Unit) {
        supportFragmentManager.beginTransaction().apply(action).commit()
    }
}