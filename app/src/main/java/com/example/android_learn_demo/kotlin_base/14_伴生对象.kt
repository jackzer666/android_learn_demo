package com.example.android_learn_demo.kotlin_base

class AppConfig {
    companion object {
        // 一般定义一些常量 类似java的static，可以通过类名直接访问
        // const 常量 编译期就确定 必须是确定的值
        // const 平时不能用。只能用在1、顶层 2、object单例类中 3、companion object伴生对象中
        // const val 这种位置限制的根源在于“编译期内联”，编译器会将const val的值直接复制到所有使用它的字节码位置，并在字节码中不生成对应的字段和getter方法
        // 不能作为类成员：类的非静态属性属于实例，在编译时不存在实例，编译器无法获取一个实例相关的值来进行内联
        // 不能作为局部变量：局部变量作用域属于函数内部，将其值内联到函数外部没有意义，且破坏封装和作用域规则
        // val 不可变的变量 运行时才确定，可以是确定的值，也可以是表达式
        const val BASE_URL = "1234567890"
        const val PAGE_SIZE = 10
        const val TIME_OUT = 1000

        val test = PAGE_SIZE + TIME_OUT // 比如这里就是表达式
    }
}

fun main() {
    println(AppConfig.BASE_URL)
}