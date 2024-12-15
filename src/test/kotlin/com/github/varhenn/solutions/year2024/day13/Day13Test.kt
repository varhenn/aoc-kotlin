package com.github.varhenn.solutions.year2024.day13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day13Test {
    private val sample = """Button A: X+94, Y+34
Button B: X+22, Y+67
Prize: X=8400, Y=5400

Button A: X+26, Y+66
Button B: X+67, Y+21
Prize: X=12748, Y=12176

Button A: X+17, Y+86
Button B: X+84, Y+37
Prize: X=7870, Y=6450

Button A: X+69, Y+23
Button B: X+27, Y+71
Prize: X=18641, Y=10279"""

    @Nested
    inner class SampleData {
        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals(480L, Day13().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals(875318608908L, Day13().solvePart2(sample))
        }
    }
}
