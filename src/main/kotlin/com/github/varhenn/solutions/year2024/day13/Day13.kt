package com.github.varhenn.solutions.year2024.day13

import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day13()).runToConsole()

// Puzzle: https://adventofcode.com/2024/day/13
class Day13 : Solution<Long>() {
    override val year = 2024
    override val day = 13
    override val part1answers = mapOf("sample" to 480L, "puzzle" to 34787L)
    override val part2answers = mapOf("sample" to 875318608908L, "puzzle" to 85644161121698L)

    override fun solvePart1(input: String): Long = parse(input).sumOf { it.tokens() }

    override fun solvePart2(input: String): Long = parse(input).sumOf { it.higherPrice().tokens() }

    companion object {
        fun parse(input: String): List<Machine> = input
            .split(Regex("\\D+"))
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
            .chunked(6)
            .map { Machine(it[0], it[1], it[2], it[3], it[4], it[5]) }

        data class Machine(val ax: Long, val ay: Long, val bx: Long, val by: Long, val x: Long, val y: Long) {
            fun tokens(): Long {
                // base algorithm: https://en.wikipedia.org/wiki/Cramer%27s_rule
                val det = determinant2(ax, bx, ay, by)
                val detA = determinant2(x, bx, y, by)
                val detB = determinant2(ax, x, ay, y)
                val isOneResultInNaturalNumbers = det != 0L && detA % det == 0L && detB % det == 0L
                val isNotNegativePushes = det * detA >= 0 && det * detB >= 0
                return when {
                    isOneResultInNaturalNumbers && isNotNegativePushes -> 3 * detA / det + detB / det
                    det != 0L || detA != 0L || detB != 0L -> 0L
                    else -> TODO("several possible results possible by definition but luckily no in real data")
                }
            }

            fun higherPrice(addPrice: Long = 10_000_000_000_000) = this.copy(
                x = this.x + addPrice,
                y = this.y + addPrice
            )
        }

        fun determinant2(a: Long, b: Long, c: Long, d: Long) = a * d - c * b
    }
}
