package com.example.android_learn_demo.kotlin_base

fun main() {
    letFun("zhangsan")
    withFun()
}

fun letFun(name: String? = null) {
    // 返回值是let中的最后一行
    val res = name?.let {
        it.isEmail()
        it == "cccc"
    }
    println(res)
}

class Car {
    var isEngineRunning = false

    fun startEngine() {
        isEngineRunning = true
        println("引擎已启动")
    }
}

fun withFun() {
    // with函数 常用于对象的初始化和操作。返回的是最后一行
    // 在with代码块中，可以直接使用对象的所有属性和方法，就像在对象内部操作一样。简单理解为可以省略部分代码，比如以下例子可以少写「car.」
    val car = Car()
    val test = with(car) {
        startEngine()
        "ccc"
    }
    println(test)
}