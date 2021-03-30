package com.mocking.bird.kotlin

/**
 * Author: Funny
 * Time: 2021/3/10
 * Description: This is Test
 */
class Test {
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

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

//    var person: Person = Person("Tom")
//    println("名字是${person.name}, 年龄是${person.age}, 地址是${person.address}")


    val method1 = { x: Int, y: Int -> x + y }
    println(method1(1, 2))

    val method2: (Int, Int) -> Int = { x, y -> x + y }

    val method3: (Int) -> Int = { it + 1 }
    println(method3(4))

    val method4: (Int) -> Unit = { println(it + 1) }
    method4(6)

    val method5 = { println("method5") }
    method5()

    val testLam = testLam(1, { x: Int, y: Int -> x + y })
    println(testLam)

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

fun testLam(a: Int, b: (Int, Int) -> Int): Int {
    return a + b.invoke(30, 40)
}

class Person constructor(name: String) {

    var name = name

    var age: Int = 18

    var address: String = "China"

    init {
        println("这是个人")
    }
}


