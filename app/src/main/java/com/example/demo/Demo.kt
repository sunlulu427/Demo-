package com.example.demo

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/11 12:44 AM
 **/
abstract class CivicCenterTask {
    fun execute(askForHelp: () -> Unit) {
        lineUp()
        askForHelp()
        evaluate()
    }
    private fun lineUp() {
        println("line up to take a number")
    }
    private fun evaluate() {
        println("evaluate service attitude")
    }

    abstract fun askForHelp()
}

class CivicCenterTask2 {
    fun execute(askForHelp: () -> Unit) {
        lineUp()
        askForHelp()
        evaluate()
    }
    private fun lineUp() {
        println("line up to take a number")
    }
    private fun evaluate() {
        println("evaluate service attitude")
    }
}