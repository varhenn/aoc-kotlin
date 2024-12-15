package com.github.varhenn.solutions.year2024.day12

import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day12()).runToConsole()

// Puzzle: https://adventofcode.com/2024/day/12
class Day12 : Solution<Int>() {
    override val year = 2024
    override val day = 12
    override val part1answers = mapOf("sample1a" to 772, "sample" to 1930, "puzzle" to 1461752)
    override val part2answers = mapOf("sample2a" to 80, "sample" to 1206, "puzzle" to 904114)

    override fun solvePart1(input: String): Int = solve(input, { countShortSides(it) })

    override fun solvePart2(input: String): Int = solve(input, { countLongSides(it) })

    companion object {
        fun solve(input: String, counter: (Map<Point, Set<Point>>) -> Int): Int =
            parse(input).regions().sumOf { it.size * counter(it.perimeter()) }

        fun parse(input: String): Map<Point, Char> =
            input.trim().lines().flatMapIndexed { x, row -> row.mapIndexed { y, v -> Point(x, y) to v } }.toMap()

        fun Map<Point, Char>.regions(): List<Set<Point>> {
            val visited = mutableSetOf<Point>()
            fun bfs(start: Point): Set<Point> = buildSet {
                val queue = ArrayDeque(listOf(start))
                while (queue.isNotEmpty()) {
                    val current = queue.removeFirst()
                    add(current)
                    current.cardinalNeighbors().forEach { n ->
                        if (n in this@regions && n !in visited && this@regions[n] == this@regions[start]) {
                            visited.add(n)
                            queue.add(n)
                        }
                    }
                }
            }
            return buildList {
                for (point in keys) {
                    if (point !in visited) {
                        add(bfs(point))
                    }
                }
            }
        }

        fun Set<Point>.perimeter(): Map<Point, Set<Point>> =
            this.flatMap { point -> point.cardinalNeighbors().map { it - point to it } }
                .filterNot { it.second in this }
                .groupBy({ it.first }, { it.second })
                .mapValues { (_, outsides) -> outsides.toSet() }

        fun countShortSides(perimeter: Map<Point, Set<Point>>): Int =
            perimeter.values.sumOf { it.size }

        fun countLongSides(perimeter: Map<Point, Set<Point>>): Int =
            perimeter.entries.sumOf { (direction, outsides) ->
                outsides.count { direction.rotate90Clockwise() + it !in outsides }
            }
    }
}
