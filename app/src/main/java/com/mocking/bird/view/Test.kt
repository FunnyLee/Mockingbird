package com.mocking.bird.view

/**
 * Author: Funny
 * Time: 2021/3/10
 * Description: This is Test
 */
class Test {
}

fun main(args: Array<String>) {
    val result = add(1, 3)
    println(result)

    var a: Int = 1
    var str: String = "a is $a"
    println(str)

    for (i in 10 downTo 1) {
        println(i)
    }
}

fun add(a: Int, b: Int): Int {
    return a + b
}