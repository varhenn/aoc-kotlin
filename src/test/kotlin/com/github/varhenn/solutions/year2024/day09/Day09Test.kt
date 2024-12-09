package com.github.varhenn.solutions.year2024.day09

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day09Test {
    private val sample = """2333133121414131402"""

    @Nested
    inner class SampleData {
        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals(1928L, Day09().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals(2858L, Day09().solvePart2(sample))
        }
    }
}
