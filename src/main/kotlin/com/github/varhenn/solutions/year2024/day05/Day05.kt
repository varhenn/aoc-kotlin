package com.github.varhenn.solutions.year2024.day05

import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day05()).runToConsole()

class Day05 : Solution<Int>() {
    override val year = 2024
    override val day = 5
    override val part1answers = mapOf("sample" to 143, "puzzle" to 4959)
    override val part2answers = mapOf("sample" to 123, "puzzle" to 4655)

    override fun solvePart1(input: String): Int = solve(input.lines()) { it.first == it.second }

    override fun solvePart2(input: String): Int = solve(input.lines()) { it.first != it.second }

    companion object {
        fun solve(lines: List<String>, chooser: (Pair<List<String>, List<String>>) -> Boolean): Int {
            val rules = lines.filter { "|" in it }.toSet()
            val updates = lines.filter { "," in it }.map { it.split(",") }
            val comparator = Comparator<String> { a, b ->
                when {
                    "$a|$b" in rules -> -1
                    "$b|$a" in rules -> 1
                    else -> 0
                }
            }
            return updates
                .map { it to it.sortedWith(comparator) }
                .filter { chooser(it) }
                .sumOf { it.second.middle().toInt() }
        }

        private fun <T> List<T>.middle() = this[this.size / 2]
    }
}
