package com.github.varhenn.solutions.year2024.day16

import com.github.varhenn.common.datastructures.Grid
import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.input.toListOfCharLists
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day16()).runToConsole()

class Day16 : Solution<Int>() {
    override val year = 2024
    override val day = 16
    override val part1answers = mapOf("sample" to 7036, "sample2" to 11048, "puzzle" to 102488)
    override val part2answers = mapOf("sample" to 45, "sample2" to 64, "puzzle" to 559)

    override fun solvePart1(input: String): Int {
        val maze = Maze(input)
        maze.chaoticBfs()
        return maze.exitScore()
    }

    override fun solvePart2(input: String): Int {
        val maze = Maze(input)
        maze.chaoticBfs()
        val exitScore = maze.exitScore()
        val bestPoints = mutableSetOf<Point>()
        val queue = ArrayDeque(
            maze.visited.filter { it.key.point == maze.exitPoint && it.value == exitScore }.toList()
        )
        while (queue.isNotEmpty()) {
            val (state, score) = queue.removeFirst()
            bestPoints.add(state.point)
            queue.addAll(state.reverseMoves(score).filter { maze.visited[it.first] == it.second })
        }
        return bestPoints.size
    }

    companion object {
        class Maze(input: String) {
            private val grid = Grid(input.toListOfCharLists()) { ' ' }
            private val startState = MovingPoint(grid.indices().first { grid[it] == 'S' }, Point.east)
            val exitPoint = grid.indices().first { grid[it] == 'E' }
            val visited = mutableMapOf<MovingPoint, Int>()

            // NOTE: PriorityQueue can be not optimal here, "chaotic" Dijkstra here (too lazy to check)
            fun chaoticBfs() {
                val queue = ArrayDeque(listOf(startState to 0))
                while (queue.isNotEmpty()) {
                    val (state, score) = queue.removeFirst()
                    if (visited.getOrDefault(state, Int.MAX_VALUE) > score) {
                        visited[state] = score
                        // NOTE: "0–1 BFS" (not priority queue but always something: addFirst/addLast)
                        if (grid[state.forward().point] != '#') {
                            queue.addFirst(state.forward() to score + 1)
                        }
                        queue.addLast(state.left() to score + 1_000)
                        queue.addLast(state.right() to score + 1_000)
                    }
                }
            }

            fun exitScore() = visited.filter { it.key.point == exitPoint }.minOf { it.value }
        }

        data class MovingPoint(val point: Point, val delta: Point) {
            fun reverseMoves(score: Int) = listOf(
                backward() to score - 1,
                left() to score - 1_000,
                right() to score - 1_000
            )

            fun forward() = MovingPoint(point + delta, delta)
            private fun backward() = MovingPoint(point - delta, delta)
            fun left() = MovingPoint(point, delta.rotate90CounterClockwise())
            fun right() = MovingPoint(point, delta.rotate90Clockwise())
        }
    }
}
