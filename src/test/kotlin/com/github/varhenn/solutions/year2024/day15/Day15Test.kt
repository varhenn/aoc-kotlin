package com.github.varhenn.solutions.year2024.day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day15Test {
    @Nested
    inner class SampleData {
        private val sample = """########
#..O.O.#
##@.O..#
#...O..#
#.#.O..#
#...O..#
#......#
########

<^^>>>vv<v>>v<<"""

        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals(2028, Day15().solvePart1(sample))
        }
    }
}
