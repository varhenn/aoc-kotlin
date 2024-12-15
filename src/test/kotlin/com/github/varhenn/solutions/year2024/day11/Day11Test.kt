package com.github.varhenn.solutions.year2024.day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day11Test {
    private val sample = "125 17"

    @Nested
    inner class SampleData {
        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals(55312L, Day11().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals(65601038650482L, Day11().solvePart2(sample))
        }
    }
}
