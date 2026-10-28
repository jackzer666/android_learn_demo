package com.example.android_learn_demo.kotlin_base

fun main() {
    val man = Person4("test", 111)
    man.intro()

    // 通过主构造函数产生对象
    val woman = Person5("lili")
    woman.intro()

    // 通过次构造函数1 产生对象
    val woman2 = Person5("", 10)
    woman2.intro()


    // 通过次构造函数2 产生对象
    val woman3 = Person5("koko", -1, city = "Beijing")
    woman3.intro()
}

// 次构造函数，一般需要主构造函数，这里例子展示无主构造函数的情况
class Person4 {
    var name: String = ""
    var age: Int = 1

    // 次构造函数
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    fun intro() {
        println("name: $name, age: $age")
    }
}

// 次构造函数，有主构造函数的例子如下
class Person5(var name: String) {
    var age: Int = 1
    var city: String = ""

    // 一般用于初始化验证。执行顺序是：主构造 -> init块 -> 次构造。根据这个顺序，可以立即为init中的验证是服务于主构造的
    init {
        println("init代码块")
        require(name.isNotEmpty()) { "姓名不能为空" }
        require(age > 0) { "年龄不能小于0" }
    }


    // 如果类有主构造函数，所有的次构造函数必须直接或间接委托给主构造（使用 this()）
    constructor(name: String, age: Int): this(name) {
        this.age = age
    }

    // 次构造函数可以有多个，用于匹配不同入参的情况
    constructor(name: String, age: Int, city: String): this(name) {
        this.age = age
        this.city = city
    }

    fun intro() {
        println("name: $name, age: $age, city: $city")
    }
}