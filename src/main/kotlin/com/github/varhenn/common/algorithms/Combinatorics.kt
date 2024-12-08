package com.github.varhenn.common.algorithms

// WARNING: Dedicated low-level libraries are better
//          Only reason why I implemented it myself: learning syntax of kotlin

fun <T> cartesianProduct(vararg lists: List<T>): List<List<T>> =
    lists.fold(listOf(listOf())) { acc, list -> acc.flatMap { partial -> list.map { partial + it } } }

fun <T> combinations2(list: List<T>) = sequence {
    for (i in list.indices) {
        for (j in i + 1 until list.size) {
            yield(list[i] to list[j])
        }
    }
}
