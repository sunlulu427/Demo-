package com.example.demo

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/11 12:44 AM
 **/

fun main() {

    val list = listOf<Int>(1, 2, 5, 8)
    println(list.fold(0) { res, element -> res + element })
}