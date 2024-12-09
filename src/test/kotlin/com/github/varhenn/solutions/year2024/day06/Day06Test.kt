package com.github.varhenn.solutions.year2024.day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day06Test {
    private val sample = """....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#..."""

    @Nested
    inner class SampleData {
        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals(41, Day06().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals(6, Day06().solvePart2(sample))
        }
    }
}
