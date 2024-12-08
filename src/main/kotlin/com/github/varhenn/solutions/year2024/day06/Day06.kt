package com.github.varhenn.solutions.year2024.day06

import com.github.varhenn.common.datastructures.Grid
import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.input.toListOfCharLists
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day06()).runToConsole()

class Day06 : Solution<Int>() {
    override val year = 2024
    override val day = 6
    override val part1answers = mapOf("sample" to 41, "puzzle" to 4819)
    override val part2answers = mapOf("sample" to 6, "puzzle" to 1796)

    override fun solvePart1(input: String) = Solver(input).walk().first.count()

    override fun solvePart2(input: String): Int {
        val solver = Solver(input)
        return solver.walk().first
            .filter { it != solver.start }
            .count { solver.walk(it).second }
    }

    companion object {
        class Solver(val input: String) {
            private val grid = Grid(input.toListOfCharLists()) { null }
            val start = grid.indices().find { grid[it] == '^' }!!

            fun walk(obstacle: Point? = null): Pair<Set<Point>, Boolean> {
                var cur = start
                var dir = Point(0, -1) // north
                val seen = mutableSetOf<Pair<Point, Point>>()
                while (cur in grid && (cur to dir) !in seen) {
                    val next = cur + dir
                    if (grid[next] == '#' || next == obstacle) {
                        dir = Point(-dir.y, dir.x) // turn right
                    } else {
                        seen.add(cur to dir)
                        cur = next
                    }
                }
                return (seen.map { it.first }.toSet()) to (cur in grid)
            }
        }
    }
}
