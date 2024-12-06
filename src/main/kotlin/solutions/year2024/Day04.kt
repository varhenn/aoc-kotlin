package solutions.year2024

import base.Input
import base.OnePuzzleRunner
import base.Puzzle

fun main() = OnePuzzleRunner(Day04()).runToConsole()

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun times(scalar: Int) = Point(x * scalar, y * scalar)

    companion object {
        fun allDeltas(): List<Point> =
            (-1..1).flatMap { x -> (-1..1).map { y -> Point(x, y) } }.filter { it != Point(0, 0) }
    }
}

class Grid<T>(data: List<List<T>>, private val default: (Point) -> T) {
    private val data: MutableMap<Point, T> = mutableMapOf()

    init {
        require(data.isEmpty() || data.all { it.size == data[0].size }) { "All rows must have the same length." }
        for ((rowIndex, row) in data.withIndex()) {
            for ((colIndex, value) in row.withIndex()) {
                this.data[Point(colIndex, rowIndex)] = value
            }
        }
    }

    operator fun get(coordinate: Point): T = data[coordinate] ?: default(coordinate)

    fun indices(): Set<Point> = data.keys
}

fun <T> cartesianProduct(vararg lists: List<T>): List<List<T>> =
    lists.fold(listOf(listOf())) { acc, list -> acc.flatMap { partial -> list.map { partial + it } } }

class Day04 : Puzzle<Int, Int>() {
    override val year = 2024
    override val day = 4
    override val part1answers: Map<String, Int?> = mapOf("sample" to 18, "puzzle" to 2458)
    override val part2answers: Map<String, Int?> = mapOf("sample" to 9, "puzzle" to 1945)

    override fun solvePart1(input: Input): Int = solve(
        grid = Grid(input.asListOfCharLists()) { ' ' },
        word = "XMAS".toList(),
        shapes = Point.allDeltas().map { delta -> "XMAS".indices.map { delta * it } }
    )

    override fun solvePart2(input: Input): Int = solve(
        grid = Grid(input.asListOfCharLists()) { ' ' },
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
