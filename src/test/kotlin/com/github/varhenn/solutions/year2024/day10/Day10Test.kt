package com.github.varhenn.solutions.year2024.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day10Test {
    private val sample = "89010123\n78121874\n87430965\n96549874\n45678903\n32019012\n01329801\n10456732"

    @Nested
    inner class SampleData {
        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals(36, Day10().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals(81, Day10().solvePart2(sample))
        }
    }
}
