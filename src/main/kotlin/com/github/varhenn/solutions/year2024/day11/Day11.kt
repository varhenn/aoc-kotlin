package com.github.varhenn.solutions.year2024.day11

import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day11()).runToConsole()

// Puzzle: https://adventofcode.com/2024/day/11
class Day11 : Solution<Long>() {
    override val year = 2024
    override val day = 11
    override val part1answers = mapOf("sample" to 55312L, "puzzle" to 224529L)
    override val part2answers = mapOf("puzzle" to 266820198587914L)

    override fun solvePart1(input: String): Long = parse(input).solve(25)

    override fun solvePart2(input: String): Long = parse(input).solve(75)

    companion object {
        fun parse(input: String): Map<Long, Long> =
            input.trim().split(" ").map { it.toLong() }.associateWith { 1L }

        fun Map<Long, Long>.solve(howMany: Int): Long =
            (1..howMany).fold(this) { stones, _ -> blink(stones) }.values.sum()

        private fun blink(stones: Map<Long, Long>): Map<Long, Long> =
            buildMap {
                stones.forEach { (stone, count) ->
                    stone.blink().forEach {
                        incKeyValueBy(it, count)
                    }
                }
            }

        private fun Long.blink(): List<Long> = when {
            this == 0L -> listOf(1L)
            "$this".length % 2 == 0 -> "$this".splitToHalves()
            else -> listOf(2024 * this)
        }

        private fun String.splitToHalves() =
            listOf(this.take(this.length / 2).toLong(), this.drop(this.length / 2).toLong())

        private fun MutableMap<Long, Long>.incKeyValueBy(key: Long, value: Long) =
            put(key, getOrElse(key) { 0 } + value)
    }
}
