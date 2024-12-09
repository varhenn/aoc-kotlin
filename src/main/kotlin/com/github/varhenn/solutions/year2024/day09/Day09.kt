package com.github.varhenn.solutions.year2024.day09

import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day09()).runToConsole()

// Puzzle: https://adventofcode.com/2024/day/9
class Day09 : Solution<Long>() {
    override val year = 2024
    override val day = 9
    override val part1answers = mapOf("sample" to 1928L, "puzzle" to 6353658451014L)
    override val part2answers = mapOf("sample" to 2858L, "puzzle" to 6382582136592L)

    override fun solvePart1(input: String): Long = input.toOneBlockAreas().compacting().checksum()

    override fun solvePart2(input: String): Long = input.toAreas().compacting().checksum()

    companion object {
        data class Area(val file: Int?, var start: Int, var size: Int)

        fun String.toAreas(): List<Area> {
            val files = this.indices.map { if (it % 2 == 0) it / 2 else null }
            val sizes = this.map { it.toString().toInt() }
            val starts = sizes.runningFold(0) { acc, size -> acc + size }.dropLast(1)
            return this.indices.map { Area(files[it], starts[it], sizes[it]) }
        }

        fun String.toOneBlockAreas(): List<Area> =
            this.toAreas().flatMap { area ->
                (area.start until area.start + area.size).map { start ->
                    Area(area.file, start, size = 1)
                }
            }

        private fun List<Area>.compacting(): List<Area> {
            val empties = this.filter { it.file == null }.toMutableList()
            val todo = this.filter { it.file != null }.reversed()
            return todo.map { area ->
                val idx = empties.indexOfFirst { it.size >= area.size }
                val dest = if (idx == -1) null else empties[idx]
                if (dest == null || dest.start >= area.start) {
                    area
                } else {
                    val moved = area.copy(start = dest.start)
                    if (dest.size == moved.size) {
                        empties.removeAt(idx)
                    } else {
                        dest.size -= moved.size
                        dest.start += moved.size
                    }
                    moved
                }
            }
        }

        fun List<Area>.checksum(): Long = this.sumOf { it.checksum() }

        private fun Area.checksum(): Long =
            (file ?: 0).toLong() * ((start + size - 1) * (start + size) - (start - 1) * start) / 2
    }
}
