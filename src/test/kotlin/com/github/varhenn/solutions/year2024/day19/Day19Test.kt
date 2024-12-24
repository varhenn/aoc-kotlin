package com.github.varhenn.solutions.year2024.day19

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day19Test {
    @Nested
    inner class SampleData {
        private val sample = """r, wr, b, g, bwu, rb, gb, br

brwrr
bggr
gbbr
rrbgbr
ubwu
bwurrg
brgr
bbrgwb"""

        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals(6L, Day19().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals(16L, Day19().solvePart2(sample))
        }
    }
}
