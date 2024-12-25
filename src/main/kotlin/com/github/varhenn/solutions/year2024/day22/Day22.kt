package com.github.varhenn.solutions.year2024.day22

import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day22()).runToConsole()

class Day22 : Solution<Long>() {
    override val year = 2024
    override val day = 22
    override val part1answers = mapOf("sample" to 37327623L, "puzzle" to 13584398738L)
    override val part2answers = mapOf("sample2" to 23L, "puzzle" to 1612L)

    override fun solvePart1(input: String): Long =
        parse(input).sumOf { it.secrets().last().toLong() }

    override fun solvePart2(input: String): Long =
        buildMap {
            parse(input).forEach { buyer ->
                val secrets = buyer.secrets().map { it % 10 }.toList()
                secrets
                    .zipWithNext { a, b -> b - a }
                    .windowed(4)
                    .mapIndexed { i, p -> p to secrets[i + 4] }
                    .distinctBy { it.first }
                    .forEach { (pattern, price) ->
                        this[pattern] = (this[pattern] ?: 0) + price
                    }
            }
        }.maxOf { it.value.toLong() }

    companion object {
        fun parse(input: String): List<Int> = Regex("\\d+").findAll(input).map { it.value.toInt() }.toList()

        fun Int.secrets(n: Int = 2_000) = generateSequence(this) {
            val a = it shl 6 xor it and 0xFFFFFF
            val b = a shr 5 xor a and 0xFFFFFF
            b shl 11 xor b and 0xFFFFFF
        }.take(n + 1)
    }
}
