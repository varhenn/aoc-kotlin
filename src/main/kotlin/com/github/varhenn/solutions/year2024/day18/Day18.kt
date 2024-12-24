package com.github.varhenn.solutions.year2024.day18

import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day18()).runToConsole()

class Day18 : Solution<String>() {
    override val year = 2024
    override val day = 18
    override val part1answers = mapOf("sample" to "22", "puzzle" to "304")
    override val part2answers = mapOf("sample" to "6,1", "puzzle" to "50,28")

    override fun solvePart1(input: String): String = parse(input).bfsPathLength().toString()

    override fun solvePart2(input: String): String = parse(input).firstBytePreventsExit().let { "${it?.x},${it?.y}" }

    companion object {
        data class MemorySpace(val corrupted: List<Point>, val firstBytes: Int, val start: Point, val exit: Point)

        fun parse(input: String): MemorySpace {
            val corrupted = Regex("\\d+")
                .findAll(input)
                .map { it.value.toInt() }
                .chunked(2)
                .map { Point(it[0], it[1]) }
                .toList()
            val isTest = corrupted.size < 100
            return MemorySpace(
                corrupted = corrupted,
                firstBytes = if (isTest) 12 else 1024,
                start = Point(0, 0),
                exit = if (isTest) Point(6, 6) else Point(70, 70)
            )
        }

        fun MemorySpace.bfsPathLength(bytes: Int = this.firstBytes): Int? {
            val visited = corrupted.take(bytes).toMutableSet()
            val queue = mutableListOf(start to 0)
            while (queue.isNotEmpty()) {
                val (point, length) = queue.removeFirst()
                point.cardinalNeighbors()
                    .filter { it.x in 0..exit.x && it.y in 0..exit.y && it !in visited }
                    .forEach { neighbor ->
                        if (neighbor == exit) {
                            return length + 1
                        }
                        visited.add(neighbor)
                        queue.add(neighbor to (length + 1))
                    }
            }
            return null
        }

        fun MemorySpace.firstBytePreventsExit(): Point? {
            var left = firstBytes - 1
            var right = corrupted.size - 1
            var result: Point? = null
            // binary search
            while (left <= right) {
                val mid = (left + right) / 2
                if (bfsPathLength(mid + 1) == null) {
                    result = corrupted[mid]
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }
            return result
        }
    }
}
