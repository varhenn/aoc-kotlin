package com.github.varhenn.solutions.year2024.day20

import com.github.varhenn.common.algorithms.combinations2
import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner
import kotlin.math.abs

fun main() = SolutionRunner(Day20()).runToConsole()

class Day20 : Solution<Int>() {
    override val year = 2024
    override val day = 20
    override val part1answers = mapOf("sample" to null, "puzzle" to 1307)
    override val part2answers = mapOf("sample" to null, "puzzle" to 986545)

    override fun solvePart1(input: String): Int = input.solve(disableTimeout = 2, threshold = 100)

    override fun solvePart2(input: String): Int = input.solve(disableTimeout = 20, threshold = 100)

    companion object {
        fun String.solve(disableTimeout: Int, threshold: Int): Int {
            val grid = trim().lines().flatMapIndexed { y, row -> row.mapIndexed { x, v -> Point(x, y) to v } }.toMap()
            val race = grid.raceTrack()
            return combinations2(race.indices.toList())
                .count { (a, b) ->
                    val dist = manhattanDistance(race[a], race[b])
                    abs(a - b) >= threshold + dist && dist <= disableTimeout
                }
        }

        private fun Map<Point, Char>.raceTrack(): List<Point> {
            var curr = keys.first { this[it] == 'S' }
            val race = mutableListOf(curr)
            do {
                curr = curr.cardinalNeighbors().first { this[it] != '#' && it != race.elementAtOrNull(race.size - 2) }
                race.add(curr)
            } while (this[curr] != 'E')
            return race
        }

        private fun manhattanDistance(a: Point, b: Point): Int = abs(a.x - b.x) + abs(a.y - b.y)
    }
}
