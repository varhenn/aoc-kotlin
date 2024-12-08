package com.github.varhenn.solutions.year2024.day08

import com.github.varhenn.common.algorithms.combinations2
import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day08()).runToConsole()

class Day08 : Solution<Int>() {
    override val year = 2024
    override val day = 8
    override val part1answers = mapOf("sample" to 14, "puzzle" to 336)
    override val part2answers = mapOf("sample" to 34, "puzzle" to 1131)

    override fun solvePart1(input: String): Int = Antennas(input).solve { listOf(1).asSequence() }

    override fun solvePart2(input: String): Int = Antennas(input).solve { generateSequence(0) { it + 1 } }

    companion object {
        class Antennas(input: String) {
            private val lines = input.lines()
            private val width = lines[0].length
            private val height = lines.size
            private val antennasPoints: Map<Char, List<Point>> = lines
                .flatMapIndexed { x, row -> row.mapIndexed { y, v -> v to Point(x, y) } }
                .filter { it.first != '.' }
                .groupBy({ it.first }, { it.second })

            fun solve(steps: () -> Sequence<Int>): Int {
                val points = mutableSetOf<Point>()
                antennasPoints.values.forEach { oneAntennaPoints ->
                    combinations2(oneAntennaPoints).forEach { (first, second) ->
                        val delta = second - first
                        points.addAll(antinodes(second, delta, steps))
                        points.addAll(antinodes(first, -delta, steps))
                    }
                }
                return points.size
            }

            private fun antinodes(point: Point, delta: Point, steps: () -> Sequence<Int>) =
                steps()
                    .map { point + delta * it }
                    .takeWhile { it.x in 0 until width && it.y in 0 until height }
        }
    }
}
