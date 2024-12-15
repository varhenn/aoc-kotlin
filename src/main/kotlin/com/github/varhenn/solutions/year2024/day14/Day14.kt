package com.github.varhenn.solutions.year2024.day14

import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day14()).runToConsole()

// Puzzle: https://adventofcode.com/2024/day/14
class Day14 : Solution<Long>() {
    override val year = 2024
    override val day = 14
    override val part1answers = mapOf("puzzle" to 219150360L)
    override val part2answers = mapOf("puzzle" to 8053L)

    override fun solvePart1(input: String): Long =
        parse(input)
            .map { it.steps(board, seconds = 100) }
            .filter { it.x != board.x / 2 && it.y != board.y / 2 }
            .map { 2 * (if (it.x > board.x / 2) 1 else 0) + (if (it.y > board.y / 2) 1 else 0) }
            .groupBy { it }
            .map { it.value.count().toLong() }
            .reduce { a, b -> a * b }

    // NOTE: only heuristic (tree pattern is unknown, variance is strange)
    override fun solvePart2(input: String): Long {
        val robots = parse(input)
        return generateSequence(0) { it + 1 }
            .first { seconds -> robots.map { it.steps(board, seconds) }.hasLongBar() }
            .toLong()
    }

    companion object {
        private val board = Point(101, 103)

        fun parse(input: String): List<Pair<Point, Point>> =
            Regex("-?\\d+")
                .findAll(input)
                .map { it.value.toInt() }
                .chunked(4)
                .map { Point(it[0], it[1]) to Point(it[2], it[3]) }
                .toList()

        fun Pair<Point, Point>.steps(board: Point, seconds: Int): Point {
            val raw = this.first + this.second * seconds
            return Point(raw.x.mod(board.x), raw.y.mod(board.y))
        }

        fun List<Point>.hasLongBar(size: Int = 10): Boolean = this.any { it.hasLongBar(size, this) }

        fun Point.hasLongBar(size: Int = 10, points: List<Point>): Boolean =
            (1..size).all { this + Point(it, 0) in points }
    }
}
