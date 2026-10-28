package com.example.android_learn_demo.kotlin_base

/**
 * 枚举解决的问题：
 * 1、接口需要的某个属性的值是几个不规律的数字
 *      全部（10）待付款（25）待收款（2）
 *
 */

enum class SortType(val value: Int) {
    Default(0),
    DifficultyLow(10),
    DifficultyHigh(11),
    ViewLow(20),
    ViewHigh(21),
    Command(30),
}

fun main() {
    val sort = SortType.DifficultyHigh
    println(sort.value)
    println(sort.name)

    sortFun(SortType.Command)
}

fun sortFun(sortType: SortType) {
    when(sortType) {
        SortType.Default -> println("默认排序")
        SortType.ViewHigh, SortType.ViewLow -> println("按浏览量排序")
        SortType.DifficultyHigh, SortType.DifficultyLow -> println("按难度排序")
        SortType.Command -> println("按推荐排序")
    }
}