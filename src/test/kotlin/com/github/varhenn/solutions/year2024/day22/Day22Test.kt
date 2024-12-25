package com.github.varhenn.solutions.year2024.day22

import com.github.varhenn.solutions.year2024.day22.Day22.Companion.secrets
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day22Test {
    @Nested
    inner class SampleData {
        @Test
        fun `should return correct solvePart1 for sample data`() {
            val sample = "1 10 100 2024"
            assertEquals(37327623L, Day22().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            val sample = "1 2 3 2024"
            assertEquals(23L, Day22().solvePart2(sample))
        }
    }

    @Nested
    inner class CompanionFunctions {
        @Test
        fun `should parse input correctly`() {
            val sample = "1 10 100 2024"
            assertEquals(listOf(1, 10, 100, 2024), Day22.Companion.parse(sample))
        }

        @Test
        fun `should calc secrets correctly`() {
            val expected =
                listOf(15887950, 16495136, 527345, 704524, 1553684, 12683156, 11100544, 12249484, 7753432, 5908254)
            assertEquals(expected, 123.secrets(expected.size).drop(1).toList())
        }
    }
}
