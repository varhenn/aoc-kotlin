package com.github.varhenn.solutions.year2024.day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day02Test {
    @Nested
    inner class SampleData {
        private val sample = "7 6 4 2 1\n1 2 7 8 9\n9 7 6 2 1\n1 3 2 4 5\n8 6 4 4 1\n1 3 6 7 9"

        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals(2, Day02().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals(4, Day02().solvePart2(sample))
        }
    }
}
