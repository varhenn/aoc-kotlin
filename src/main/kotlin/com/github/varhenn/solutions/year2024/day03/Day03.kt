package com.github.varhenn.solutions.year2024.day03

import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day03()).runToConsole()

class Day03 : Solution<Int>() {
    override val year = 2024
    override val day = 3
    override val part1answers = mapOf("sample" to 161, "puzzle" to 196826776)
    override val part2answers = mapOf("sample2" to 48, "puzzle" to 106780429)

    override fun solvePart1(input: String) = input.sumOfMultiplications()

    override fun solvePart2(input: String) = input.extract("do()", "don't()").sumOfMultiplications()

    companion object {
        private val mulRegex = """mul\((\d+),(\d+)\)""".toRegex()

        private fun String.sumOfMultiplications(): Int = mulRegex.findAll(this)
            .sumOf { match -> match.destructured.toList().map { it.toInt() }.reduce { a, b -> a * b } }

        private fun String.extract(on: String, off: String): String =
            this.splitToSequence(on).joinToString { it.substringBefore(off) }
    }
}
