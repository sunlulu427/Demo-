package com.example.demo

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/11 12:44 AM
 **/

fun main() {

    val list = listOf<Int>(1, 2, 5, 8)
    println(list.fold(0) { res, element -> res + element })
}

interface SwimStrategy {
    fun swim()
}
class Breakstroke: SwimStrategy {
    override fun swim() {
        println("I am breaststrokeing...")
    }
}
class BackStroke: SwimStrategy {
    override fun swim() {
        println("I am backstroking...")
    }
}
class FreeStyle: SwimStrategy {
    override fun swim() {
        println("I am freeStyle...")
    }
}
class Swimmer(val strategy: SwimStrategy) {
    fun swim() {
        strategy.swim()
    }
}