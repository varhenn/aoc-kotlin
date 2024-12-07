package com.github.varhenn.solutions.year2024.day07

import com.github.varhenn.common.input.toListOfLongLists
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day07()).runToConsole()

class Day07 : Solution<Long>() {
    override val year = 2024
    override val day = 7
    override val part1answers = mapOf("sample" to 3749L, "puzzle" to 12839601725877L)
    override val part2answers = mapOf("sample" to 11387L, "puzzle" to 149956401519484)

    override fun solvePart1(input: String): Long = solve(
        input,
        listOf({ a, b -> a + b }, { a, b -> a * b })
    )

    override fun solvePart2(input: String): Long = solve(
        input,
        listOf({ a, b -> a + b }, { a, b -> a * b }, { a, b -> "$a$b".toLong() })
    )

    companion object {
        fun solve(input: String, operators: List<(Long, Long) -> Long>): Long {
            class Equation(val numbers: List<Long>) {
                fun solve(sum: Long, idx: Int): Boolean = when {
                    idx == numbers.lastIndex -> numbers[0] == sum
                    sum > numbers[0] -> false
                    else -> operators.any { solve(it(sum, numbers[idx + 1]), idx + 1) }
                }
            }
            return input.toListOfLongLists().filter { Equation(it).solve(it[1], 1) }.sumOf { it[0] }
        }
    }
}
