package com.github.varhenn.solutions.year2024.day15

import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day15()).runToConsole()

class Day15 : Solution<Int>() {
    override val year = 2024
    override val day = 15
    override val part1answers = mapOf("sample1" to 2028, "sample2" to 10092, "puzzle" to 1318523)
    override val part2answers = mapOf("sample2" to 9021, "puzzle" to null)

    override fun solvePart1(input: String): Int = solve(input)

    override fun solvePart2(input: String): Int = 0

    companion object {
        fun parse(input: String): Pair<MutableMap<Point, Char>, List<Point>> {
            val lines = input.lines()
            val grid = lines
                .take(lines.indexOf(""))
                .flatMapIndexed { y, row -> row.mapIndexed { x, v -> Point(x, y) to v } }
                .toMap().toMutableMap()
            val moves = lines
                .drop(lines.indexOf(""))
                .flatMap { row -> row.map { it.toDirection() } }
            return grid to moves
        }

        fun solve(input: String): Int {
            val (grid, moves) = parse(input)
            var robot = grid.keys.first { grid[it] == '@' }
            moves.forEach { move ->
                when (grid[robot + move]) {
                    '.' -> {
                        grid[robot] = '.'
                        robot += move
                        grid[robot] = '@'
                    }
                    'O' -> {
                        val notBoxPoint = generateSequence(robot + move) { it + move }.first { grid[it] != 'O' }
                        if (grid[notBoxPoint] == '.') {
                            grid[robot] = '.'
                            robot += move
                            grid[robot] = '@'
                            grid[notBoxPoint] = 'O'
                        }
                    }
                    else -> Unit
                }
            }
            return grid.boxes().gpsCoordinates()
        }

        private fun Char.toDirection(): Point =
            mapOf('^' to Point.north, 'v' to Point.south, '>' to Point.east, '<' to Point.west)[this]!!

        private fun Map<Point, Char>.boxes() = keys.filter { this[it] == 'O' }

        private fun List<Point>.gpsCoordinates() = sumOf { 100 * it.y + it.x }
    }
}
