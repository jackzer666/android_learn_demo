package com.example.android_learn_demo.kotlin_base

fun main() {
    val animal = Animal("某动物")
    animal.makeSound()

    val dog = Dog("旺财")
    dog.makeSound()

    val cat = Cat("小米")
    cat.makeSound()
}

// 动物类。如果需要被继承，必须加open
open class Animal(var name: String) {
    // 类的方法如果想被子类重写，必须也要加open
    open fun makeSound() {
        println("$name 正在发出声音")
    }
}

// 狗类 继承动物类。类似委托给父类的主构造函数
class Dog(name: String): Animal(name) {
    override fun makeSound() {
        super.makeSound()
        println("$name 正在犬吠")
    }
}

// 猫类 继承动物类。类似委托给父类的主构造函数
class Cat(name: String): Animal(name) {
    override fun makeSound() {
        super.makeSound()
        println("$name 正在喵喵叫")
    }
}