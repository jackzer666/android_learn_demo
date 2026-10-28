package com.example.android_learn_demo.kotlin_base

fun main() {
    val man = Person2("zhangsan", 19)
    man.intro()

    val woman = Person3("lisi", 10)
    woman.intro()
}

// 显式主构造函数
class Person2 constructor(var name: String, var age: Int) {
    fun intro() {
        println("name: $name, age: $age")
    }
}


// 隐式主构造函数
class Person3(var name: String, var age: Int) {
    fun intro() {
        println("name: $name, age: $age")
    }
}
