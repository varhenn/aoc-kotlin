package com.github.varhenn.solutions.year2024.day10

import com.github.varhenn.common.datastructures.Grid
import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day10()).runToConsole()

// Puzzle: https://adventofcode.com/2024/day/10
class Day10 : Solution<Int>() {
    override val year = 2024
    override val day = 10
    override val part1answers = mapOf("sample" to 36, "puzzle" to 746)
    override val part2answers = mapOf("sample" to 81, "puzzle" to 1541)

    override fun solvePart1(input: String): Int = TopographicMap(input).solve { it.count() }

    override fun solvePart2(input: String): Int = TopographicMap(input).solve { it.sumOf { it } }

    companion object {
        class TopographicMap(val input: String) {
            private val grid = Grid(input.trim().lines().map { it.map { it.toString().toInt() } }) { -1 }

            fun solve(counter: (List<Int>) -> Int): Int =
                trailHeads().map { trailEnds(it) }.sumOf { counter(it) }

            private fun trailHeads() = grid.indices().filter { grid[it] == 0 }

            private fun trailEnds(head: Point): List<Int> {
                val visited = mutableMapOf(head to 1) // "place" to "how many paths lead there"
                val queue = ArrayDeque(listOf(head))
                while (queue.isNotEmpty()) {
                    val cur = queue.removeFirst()
                    for (next in this.trailSteps(cur)) {
                        visited[next] = visited.getOrDefault(next, 0) + 1
                        queue.add(next)
                    }
                }
                return visited.filter { grid[it.key] == 9 }.map { it.value }
            }

            private fun trailSteps(p: Point) = p.cardinalNeighbors().filter { isTrailStep(p, it) }

            private fun isTrailStep(prev: Point, next: Point) = next in grid && grid[next] - grid[prev] == 1
        }
    }
}
