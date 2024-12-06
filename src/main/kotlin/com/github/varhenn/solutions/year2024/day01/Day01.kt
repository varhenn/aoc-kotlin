package com.github.varhenn.solutions.year2024.day01

import com.github.varhenn.common.input.toListOfIntLists
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner
import kotlin.math.abs

fun main() = SolutionRunner(Day01()).runToConsole()

class Day01 : Solution<Int>() {
    override val year = 2024
    override val day = 1
    override val part1answers = mapOf("sample" to 11, "puzzle" to 2756096)
    override val part2answers = mapOf("sample" to 31, "puzzle" to 23117829)

    override fun solvePart1(input: String) = TwoLocationLists(input).totalDistance()

    override fun solvePart2(input: String) = TwoLocationLists(input).similarityScore()
}

class TwoLocationLists(input: String) {
    private val data: List<List<Int>> = input.toListOfIntLists()
    private val left: List<Int> = data.map { it[0] }
    private val right: List<Int> = data.map { it[1] }

    fun totalDistance() = left.sorted().zip(right.sorted()).sumOf { abs(it.first - it.second) }

    fun similarityScore(): Int {
        val counter = right.groupingBy { it }.eachCount()
        return left.sumOf { it * (counter[it] ?: 0) }
    }
}
