package com.github.varhenn.solutions.year2024.day04

import com.github.varhenn.common.algorithms.cartesianProduct
import com.github.varhenn.common.datastructures.Grid
import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.input.toListOfCharLists
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day04()).runToConsole()

class Day04 : Solution<Int>() {
    override val year = 2024
    override val day = 4
    override val part1answers = mapOf("sample" to 18, "puzzle" to 2458)
    override val part2answers = mapOf("sample" to 9, "puzzle" to 1945)

    override fun solvePart1(input: String): Int = solve(
        grid = Grid(input.toListOfCharLists()) { ' ' },
        word = "XMAS".toList(),
        shapes = Point.allNeighboringDirections().map { delta -> "XMAS".indices.map { delta * it } }
    )

    override fun solvePart2(input: String): Int = solve(
        grid = Grid(input.toListOfCharLists()) { ' ' },
        word = "AMSMS".toList(),
        shapes = cartesianProduct(listOf(1, -1), listOf(1, -1)).map { (a, b) ->
            listOf(Point(0, 0), Point(-a, -a), Point(a, a), Point(b, -b), Point(-b, b))
        }
    )

    companion object {
        fun solve(grid: Grid<Char>, word: List<Char>, shapes: List<List<Point>>): Int =
            grid.indices().sumOf { point ->
                shapes.count { shape ->
                    shape.zip(word).all { (delta, char) ->
                        grid[point + delta] == char
                    }
                }
            }
    }
}
