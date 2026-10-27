package com.example.android_learn_demo.kotlin_base

fun main() {
    log()
}


fun variable() {
    // val 只读变量
    val name = "zhang san"
    // var 可变变量
    var age = 20
    age = 21
}

fun log() {
    println("hello world测试")
    println("123")
}

fun type() {
    val i: Int = 10
    val l: Long = 10L
    val d: Double = 3.14
    val f: Float = 3.14f
    val b: Boolean = true
    val c: Char = 'v'
    val s: String = "Hello"
}