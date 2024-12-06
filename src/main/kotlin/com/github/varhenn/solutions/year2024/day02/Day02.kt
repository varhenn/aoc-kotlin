package com.github.varhenn.solutions.year2024.day02

import com.github.varhenn.common.input.toListOfIntLists
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day02()).runToConsole()

class Day02 : Solution<Int>() {
    override val year = 2024
    override val day = 2
    override val part1answers = mapOf("sample" to 2, "puzzle" to 502)
    override val part2answers = mapOf("sample" to 4, "puzzle" to 544)

    override fun solvePart1(input: String) = safe(input) { report, range -> deltasInRange(report, range) }

    override fun solvePart2(input: String) = safe(input) { report, range ->
        report.indices.any { idx -> deltasInRange(report.withoutElementAt(idx), range) }
    }

    companion object {
        private val ranges = listOf(1..3, -3..-1)

        private fun safe(input: String, block: (report: List<Int>, range: IntRange) -> Boolean): Int =
            input.toListOfIntLists().count { report -> ranges.any { range -> block(report, range) } }

        private fun deltasInRange(report: List<Int>, range: IntRange): Boolean =
            report.zipWithNext().all { (a, b) -> a - b in range }

        private fun List<Int>.withoutElementAt(index: Int): List<Int> = this.filterIndexed { i, _ -> i != index }
    }
}
