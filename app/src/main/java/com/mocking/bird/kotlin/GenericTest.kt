package com.mocking.bird.kotlin

import java.util.ArrayList

/**
 * Author: Funny
 * Time: 2021/3/30
 * Description: This is GenericTest
 */
open class Fu()
class Zi : Fu()

val fu = Fu()
val zi = Zi()

fun main() {



    val list: MutableList<out Fu> = ArrayList<Zi>()
    list.get(0)

    val list2: MutableList<in Zi> = ArrayList<Fu>()
    list2.add(zi)
    list2.get(0)
}
