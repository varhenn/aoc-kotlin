package solutions.year2024

import base.Input
import base.OnePuzzleRunner
import base.Puzzle
import kotlin.math.abs

fun main() = OnePuzzleRunner(Day01()).runToConsole()

class Day01 : Puzzle<Int, Int>() {
    override val year = 2024
    override val day = 1
    override val part1answers: Map<String, Int?> = mapOf("sample" to 11, "puzzle" to 2756096)
    override val part2answers: Map<String, Int?> = mapOf("sample" to 31, "puzzle" to 23117829)

    override fun solvePart1(input: Input) = TwoLocationLists(input).totalDistance()

    override fun solvePart2(input: Input) = TwoLocationLists(input).similarityScore()
}

class TwoLocationLists(input: Input) {
    private val data: List<List<Int>> = input.asListOfIntLists()
    private val left: List<Int> = data.map { it[0] }
    private val right: List<Int> = data.map { it[1] }

    fun totalDistance() = left.sorted().zip(right.sorted()).sumOf { abs(it.first - it.second) }

    fun similarityScore(): Int {
        val counter = right.groupingBy { it }.eachCount()
        return left.sumOf { it * (counter[it] ?: 0) }
    }
}
