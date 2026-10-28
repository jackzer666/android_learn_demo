package com.example.android_learn_demo.kotlin_base

fun main() {
    test1()

    test2("测试名称")
}

fun test1() {
    // 可空
    var name: String? = "张三"
    name = null
    println(name?.toInt())
}

fun test2(name: String? = null) {
    // 1. 安全调用 ?. 为null时不执行，返回null
    val length = name?.length

    // 2. ?: Elvis操作符 - 为null时返回默认值
    val len = name?.length ?: 0
    val displayName = name ?: "匿名"

    // 3. 非空断言 !!，确定不为null时用，为null会崩溃
    val forced = name!!

    // 4. ?.let {} 安全处理非空值
    name?.let {
        println("${it.length}")
    }
    name?.apply {}
    name?.also {}

    /**
     * 引申 let also apply 差异用法
     * let: 接收it，返回lambda结果，一般用于取结果、转换、空检查后执行
     * also: 接收it，返回接收者本身，一般用于副作用（日志、校验）后继续链式
     * apply: 接收this，返回接收者本身，一般用于配置对象（改属性、调方法）
     */
}
