package com.github.varhenn.solutions.year2024.day25

import com.github.varhenn.common.algorithms.cartesianProduct
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day25()).runToConsole()

class Day25 : Solution<Int>() {
    override val year = 2024
    override val day = 25
    override val part1answers = mapOf("sample" to 3, "puzzle" to 3663)
    override val part2answers = mapOf("sample" to 0, "puzzle" to 0)

    override fun solvePart1(input: String): Int {
        val (locks, keys) = parse(input)
        return cartesianProduct(locks, keys).count { (lock, key) -> isFit(lock, key) }
    }

    override fun solvePart2(input: String): Int = 0 // as usual no last puzzle

    companion object {
        fun parse(input: String): Pair<List<List<Int>>, List<List<Int>>> {
            val keys = mutableListOf<List<Int>>()
            val locks = mutableListOf<List<Int>>()
            input
                .lines()
                .chunked(8)
                .map { it.take(7) } // last pattern is without empty line
                .forEach {
                    val pattern = mutableListOf(0, 0, 0, 0, 0)
                    it.flatMapIndexed { y, row ->
                        row.mapIndexed { x, c ->
                            pattern[x] += if (c == '#') 1 else 0
                        }
                    }
                    (if (it[0][0] == '#') locks else keys).add(pattern)
                }
            return locks to keys
        }

        fun isFit(lock: List<Int>, key: List<Int>) = lock.zip(key).all { (l, k) -> l + k <= 7 }
    }
}
