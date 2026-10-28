package com.example.android_learn_demo.kotlin_base

fun main() {
    fun01()
}

/**
 * 扩展函数可以让 在不修改原类 就能给它添加新方法。这在给系统类添加工具方法时非常有用
 */
fun String.isEmail(): Boolean {
    return this.contains("@") && this.contains(".")
}

fun Int.toPx(): Int = (this * 2.5).toInt()

fun String.upperCaseFirst(): String {
    return if (isEmpty()) this else this[0].uppercaseChar() + this.substring(1)
}

// 给系统类扩充属性
val String.isLong: Boolean
    get() = this.length > 10

fun fun01() {
    val myEmail = "test@qq.com"
    println(myEmail.isEmail())

    val invalidEmail = "123"
    println(invalidEmail.isEmail())

    val height = 100 // dp
    println(height.toPx()) // px

    var lowerStr = "jack"
    println(lowerStr.upperCaseFirst())
    println(lowerStr.isLong)
    lowerStr = "1234567890-oiuyfdf"
    println(lowerStr.isLong)
}