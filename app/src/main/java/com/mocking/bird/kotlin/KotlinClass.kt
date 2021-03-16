package com.mocking.bird.kotlin

/**
 * Author: Funny
 * Time: 2021/3/15
 * Description: This is KotlinClass
 */

open class People(var name: String, var age: Int) {

    open fun eat() {
        println("people eat")
    }

}

class Student(name: String, age: Int, var sex: String) : People(name, age) {

    override fun eat() {
        super.eat()
        println("student eat")
    }

}

class Worker : People {

    constructor(name: String, age: Int) : super(name, age) {

    }

}

fun main() {
    val student = Student("Tom", 18, "男")
    println("name is ${student.name}, age is ${student.age}, sex is ${student.sex}")
    student.eat()

    val worker = Worker("Jack", 38)
    println("name is ${worker.name}, age is ${worker.age}")
}