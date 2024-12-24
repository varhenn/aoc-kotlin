package com.github.varhenn.solutions.year2024.day19

import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day19()).runToConsole()

class Day19 : Solution<Long>() {
    override val year = 2024
    override val day = 19
    override val part1answers = mapOf("sample" to 6L, "puzzle" to 344L)
    override val part2answers = mapOf("sample" to 16L, "puzzle" to 996172272010026L)

    override fun solvePart1(input: String): Long = parse(input).countWaysPerTarget().count { it != 0L }.toLong()

    override fun solvePart2(input: String): Long = parse(input).countWaysPerTarget().sumOf { it }

    companion object {
        data class Onsen(val patterns: List<String>, val targets: List<String>)

        fun parse(input: String): Onsen {
            val lines = input.lines()
            return Onsen(
                patterns = lines[0].split(", "),
                targets = lines.drop(2)
            )
        }

        fun Onsen.countWaysPerTarget() = targets.map { it.countWays(patterns) }

        private fun String.countWays(patterns: List<String>): Long {
            val ways = LongArray(length + 1)
            ways[0] = 1
            indices.forEach { idx ->
                patterns
                    .filter { idx + it.length <= length && regionMatches(idx, it, 0, it.length) }
                    .forEach {
                        ways[idx + it.length] += ways[idx]
                    }
            }
            return ways[length]
        }
    }
}
