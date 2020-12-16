package com.example.demo

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/11 12:44 AM
 **/
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
class Swimmer(val swimming: () -> Unit) {
    fun swim() {
        swimming.invoke()
    }
}