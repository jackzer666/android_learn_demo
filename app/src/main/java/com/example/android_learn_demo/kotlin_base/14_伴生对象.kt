package com.example.android_learn_demo.kotlin_base

class AppConfig {
    companion object {
        // 一般定义一些常量 类似java的static，可以通过类名直接访问
        const val BASE_URL = "1234567890"
        const val PAGE_SIZE = 10
        const val TIME_OUT = 1000
    }
}

fun main() {
    println(AppConfig.BASE_URL)
}