package com.example.demo

import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private fun showFragment(fragment: Fragment) {
        transFragment {
            replace(R.id.container, fragment)
        }
    }
}
