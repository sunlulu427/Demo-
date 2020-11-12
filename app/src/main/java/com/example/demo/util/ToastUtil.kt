package com.example.demo.util

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.demo.MyApplication
import com.example.demo.R


fun normalToast(content: String) {
    Toast.makeText(MyApplication.context, content, Toast.LENGTH_SHORT).show()
}

fun centerToast(content: String) {
    val context = MyApplication.context
    Toast(context).apply {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
        val view = inflater?.inflate(R.layout.toast_center, null)
        val tv = view?.findViewById<TextView>(R.id.toast_msg)
        tv?.text = content
        setView(view)
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}