package com.example.android_learn_demo.kotlin_base

fun main() {
    listDemo1()
    mapDemo1()
}

fun listDemo1() {
    // 只读列表
    val subject = listOf("a", "b", "c", "d")
    println(subject[0])
    println(subject.size)

    // 可变列表
    val cartList = mutableListOf("e", "f", "g")
    cartList.add("asd")
    println(cartList)
    cartList.remove("e")
    println(cartList)
    cartList[2] = "two"
    println(cartList)
}

fun mapDemo1() {
    // 只读map
    val scores = mapOf("Jack" to 90, "Tom" to 100, true to 1)
    println(scores.keys)
    println(scores.values)
    println(scores["Tom"])
    println(scores[true])


    // 可变map
    val mutableScores = mutableMapOf("Sam" to 5)
    mutableScores["zhangsan"] = 10
    println(mutableScores)
}