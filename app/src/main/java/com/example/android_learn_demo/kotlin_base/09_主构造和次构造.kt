package com.example.android_learn_demo.kotlin_base

fun main() {
    val car = Vehicle(type = "自行车")
    car.intro()

    val car2 = Vehicle("自行车", 3)
    car2.intro()
}

class Vehicle(var type: String) {
    var wheels: Int = 0

    init {
        wheels = when(type) {
            "小轿车" -> 4
            "大货车" -> 8
            "自行车" -> 2
            else -> 0
        }
    }

    // 次构造函数 一定要注意执行顺序 主构造 -> init块 -> 次构造。这里type委托给了主构造
    constructor(type: String, wheels: Int): this(type) {
        this.wheels = wheels
    }

    fun intro() {
        println("type: $type, wheel num: $wheels")
    }
}