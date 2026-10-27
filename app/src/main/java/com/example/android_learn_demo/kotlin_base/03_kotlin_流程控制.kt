package com.example.android_learn_demo.kotlin_base

fun main() {
    ifDemo(80)
    whenDemo(3)
    whenDemo2(43)
    forDemo()
}

fun ifDemo(score: Int) {
//    if (score >= 60) {
//        println("Good!")
//    } else if (score >= 50) {
//        println("Not Bad!")
//    } else {
//        println("Bad!")
//    }
    val res = if (score >= 60) "及格" else "不及格"
    println(res)
}

fun whenDemo(difficutty: Int) {
    val level = when(difficutty) {
        1, 2 -> "简单"
        3, 4 -> "一般"
        5 -> "困难"
        else -> "未知"
    }
    println(level)
}

fun whenDemo2(score: Int) {
    val level = when {
        score >= 60 -> "及格"
        else -> "不及格"
    }
    println(level)
}

fun forDemo() {
    for (i in 1..10 step 2) {
        println(i)
    }
    for (i in 4 downTo 1) {
        println(i)
    }
    val questions = listOf("Activity", "Class", "Function")
    for (i in questions) {
        println(i)
    }
    for ((index, i) in questions.withIndex()) {
        println("$index ---- $i")
    }
}

fun whileDemo() {}