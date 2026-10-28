package com.example.android_learn_demo.kotlin_base

fun main() {
    println(calculate(10, 5, {a, b -> a + b}))
    println(calculate(10, 5, {a, b -> a - b}))

    // lambda表达式放在函数后面时，其实是作为函数的参数传入函数。注意，这种场景必须要求函数的最后一个参数是lambda才行！
    // 类比 filter { it.age > 10 } 也是放在函数后面
    // lambda表达式如果参数只有一个，可以直接使用it
    println(calculate(10, 5) { a, b -> a + b })

    // lambda表达式可以理解成以下的推导
    // val easy = questions.filter({ item -> item.difficuty <= 2 })
    // val easy = questions.filter{ item -> item.difficuty <= 2 }
    // val easy = questions.filter { it.difficuty <= 2 }
}


// 函数的参数是一个函数，那么这个外层函数就是一个 高阶函数
fun calculate(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}