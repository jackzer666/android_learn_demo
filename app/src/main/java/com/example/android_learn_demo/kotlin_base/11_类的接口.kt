package com.example.android_learn_demo.kotlin_base

fun main() {
    val man = Man("zhangsan", 19, "蹦迪")
    man.intro()
    man.work("学生")
    man.eat()

}

// 一般I开头
interface IPerson {
    // 抽象属性
    val name: String
    val age: Int

    // 抽象方法
    fun intro()
    fun work(job: String)

    // 默认实现（非正常情况 一般不用）
    fun eat() {
        println("吃")
    }
}

interface IOther {
    val hobby: String

    fun test()
}

// 继承并实现了接口，接口用于约束类中需要实现的字段和函数方法
class Man(override val name: String, override val age: Int, override val hobby: String): IPerson, IOther {
    override fun intro() {
        println("my name is $name, age is $age")
    }

    override fun work(job: String) {
        println("my job is $job")
    }

    override fun test() {

    }

}