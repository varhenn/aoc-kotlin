package com.github.varhenn.solutions.year2024.day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day03Test {
    @Nested
    inner class SampleData {
        @Test
        fun `should return correct solvePart1 for sample data`() {
            val sample = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
            assertEquals(161, Day03().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            val sample = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
            assertEquals(48, Day03().solvePart2(sample))
        }
    }
}
