package com.example.android_learn_demo.kotlin_base

fun main() {
    runDemo()
    applyDemo()
}

// run返回最后一个结果
fun runDemo() {
    // 1. 简化多步计算
    val area = run {
        val width = 10
        val height = 5
        width * height
    }
    println(area)

    // 2. 临时变量隔离
    val result = run {
        // 这里的临时变量不会影响外部
        val a = 5
        val b = 3
        a * b
    }
    println(result)

    // 3. 安全转换
    val userInput = "1vghj"
    val number = run {
        val parsed = userInput.toIntOrNull()
        parsed ?: 0 // 如果失败返回0
    }
    println(number)

    // 4. 对象初始化
    val person = run {
        val name = "zhangsan"
        val age = 5
        "$name, $age"
    }
    println(person)
}


class Phone {
    var owner = ""
    var wallpaper = "默认壁纸"
}

// 一般用于配置对象，拿到一个新对象，在对象中处理设置，最后自动返回配置好的对象
fun applyDemo() {
    val myPhone = Phone().apply {
        owner = "zhangsan"
    }
    println(myPhone.owner)
}