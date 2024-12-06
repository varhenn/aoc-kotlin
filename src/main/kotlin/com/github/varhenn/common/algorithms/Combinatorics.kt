package com.github.varhenn.common.algorithms

// WARNING: Dedicated low-level libraries are better, e.g. kotlin-combinatorics, kotlinx.collections.immutable
//          Only reason why I implemented it myself: learning syntax of kotlin

fun <T> cartesianProduct(vararg lists: List<T>): List<List<T>> =
    lists.fold(listOf(listOf())) { acc, list -> acc.flatMap { partial -> list.map { partial + it } } }
