package com.example.android_learn_demo.kotlin_base

fun main() {
    val p1 = Person()
    p1.name = "zhangsan"
    p1.age = 18
    println(p1.name)

    val p2 = Person()
    p2.name = "lisi"
    p2.age = 10
}

class Person {
    var name: String = ""
    var age: Int = 1

    fun intro() {
        println("name: $name, age: $age")
    }
}