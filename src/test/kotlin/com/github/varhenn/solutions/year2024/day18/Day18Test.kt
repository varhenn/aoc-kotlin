package com.github.varhenn.solutions.year2024.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day18Test {
    @Nested
    inner class SampleData {
        private val sample =
            "5,4 4,2 4,5 3,0 2,1 6,3 2,4 1,5 0,6 3,3 2,6 5,1 1,2 5,5 2,5 6,5 1,4 0,4 6,4 1,1 6,1 1,0 0,5 1,6 2,0"

        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals("22", Day18().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals("6,1", Day18().solvePart2(sample))
        }
    }
}
