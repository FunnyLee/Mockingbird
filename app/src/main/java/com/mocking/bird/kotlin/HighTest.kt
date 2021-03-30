package com.mocking.bird.kotlin

/**
 * Author: Funny
 * Time: 2021/3/25
 * Description: This is HighTest
 */
fun main() {

//    val result = method1(1, 2, { a: Int, b: Int -> a + b })
//    println(result)

//    val method1 = method1(2, 3, ::sum)
//    println(method1)
//
//    println(method1(10, 20, Test()::sum))
//
//    val result2 = method1(5, 6) { a: Int, b: Int -> 1111 }
//    println(result2)
//
//    val stringBuilder = StringBuilder()
//    stringBuilder.apply {
//        this.append("aaa")
//        stringBuilder.append("bbb")
//    }
//    println(stringBuilder.toString())
//
//    stringBuilder.otherApply {
//        this.append("ccc")
//        this.append("ddd")
//    }
//    println(stringBuilder.toString())

    val name = "aaa"
    name.myRun {
        println(it)
    }
}

private fun method1(a: Int, b: Int, add: (Int, Int) -> Int): Int {
    return add(a, b)
}

private fun sum(a: Int, b: Int): Int {
    return a + b
}

private fun StringBuilder.otherApply(method: StringBuilder.() -> Unit): StringBuilder {
    method()
    return this
}

fun <T, R> T.myRun(mm: (T) -> R): R {
    return mm(this)
}