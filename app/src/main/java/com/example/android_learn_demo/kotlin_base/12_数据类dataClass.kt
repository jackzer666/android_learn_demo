package com.example.android_learn_demo.kotlin_base

// 注意这里是小括号，相当于主构造函数。数据类本质就是一个类
data class Student(
    val id: Int,
    var name: String, // var声明的 可变
    val className: String,
    var score: Int = 60, // 可变 且有默认值
    var test: List<String>
)

fun main() {
    val stu1 = Student(1, "zhangsan", "1-2", 88, listOf("A", "B"))
    val stu2 = Student(id = 2, name = "lisi", className = "1-3", score = 45, listOf("A", "B"))
    val stu3 = Student(id = 2, name = "lisi", className = "1-3", score = 45, listOf("A", "B"))

    // 数据类内部自动实现了toString方法，数据类和普通类是有区别的。普通类打印出来类似地址，看不到内部字段值
    println(stu1)
    println(stu2)

    // 数据类还实现了equals，数据类的equals比较的不是地址，是数据内容
    println(stu1 == stu2)
    println(stu2 == stu3) // true

    // copy可以修改字段值，也可以什么都不传
    val stu4 = stu1.copy(id = 4, name = "test")
    println(stu4)

    // 解构
    val (id, name) = stu2
    println("id: $id, name: $name")

    // hashCode() 相同的内容生成的hash散列值是一样的
    println(stu1.hashCode())
    println(stu2.hashCode())
    println(stu3.hashCode())

}

