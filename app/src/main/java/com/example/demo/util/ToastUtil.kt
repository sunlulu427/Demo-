package com.example.demo.util

import android.widget.Toast
import com.example.demo.MyApplication


fun toast(content: String) {
    Toast.makeText(MyApplication.context, content, Toast.LENGTH_SHORT).show()
}
