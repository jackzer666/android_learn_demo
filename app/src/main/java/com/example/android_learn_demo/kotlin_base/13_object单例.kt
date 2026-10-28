package com.example.android_learn_demo.kotlin_base


/**
 * 单例 object开头
 * 全局（app）共享同一个实例
 * 单例类不需要实例化，直接使用
 */

object HttpClient {
    var token: String = ""

    fun isLoggedIn(): Boolean = token.isNotEmpty()
}

fun main() {
    val res = HttpClient.isLoggedIn()
    println(if (res) "登录" else "未登录")

    HttpClient.token = "123"
    val res1 = HttpClient.isLoggedIn()
    println(if (res1) "登录" else "未登录")
}