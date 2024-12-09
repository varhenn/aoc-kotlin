package com.github.varhenn.solutions.year2024.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day01Test {
    @Nested
    inner class SampleData {
        val sample = "3 4\n4 3\n2 5\n1 3\n3 9\n3 3"

        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals(11, Day01().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals(31, Day01().solvePart2(sample))
        }
    }
}
