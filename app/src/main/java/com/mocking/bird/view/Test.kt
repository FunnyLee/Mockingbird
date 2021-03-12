package com.mocking.bird.view

/**
 * Author: Funny
 * Time: 2021/3/10
 * Description: This is Test
 */
class Test {
}

fun main(args: Array<String>) {
//    val result = add(1, 3)
//    println(result)
//
//    str()
//    array()
//    val max = testIf(3, 4)
//    println(max)
//    testWhen(3)

//    testArray()

    var person: Person = Person("Tom")
    println("名字是${person.name}, 年龄是${person.age}, 地址是${person.address}")
}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun str(): Unit {
    var a: String = "abcd"
    var str: String = "$a length is ${a.length}"
    println(str)
}

fun array() {
    var array = arrayOf(1, 2, 3, 4)
    for (i in array) {
        println(i)
    }
}

fun testIf(a: Int, b: Int): Int {
//    var max: Int
//    if (a > b) {
//        max = a
//    } else {
//        max = b
//    }

    var max: Int = if (a > b) a else b
    return max
}

fun testWhen(a: Int) {
    when (a) {
        1 -> println("11111")
        2 -> println("22222")
        else -> print("00000")
    }
}

fun testArray() {
    var arr = arrayOf("a", "b", "c", "d", "e")
    for (i in arr) {
        println(i)
    }

    arr.forEach {
        print(it)
    }

    for (i in arr.indices) {
        println("${arr[i]}的索引是$i")
    }

    for ((index, s) in arr.withIndex()) {
        println("${s}的索引是$index")
    }
}

class Person constructor(name: String) {

    var name = name

    var age: Int = 18

    var address: String = "China"

    init {
        println("这是个人")
    }

}

