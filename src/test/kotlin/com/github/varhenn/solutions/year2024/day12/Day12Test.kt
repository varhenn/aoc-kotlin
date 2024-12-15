package com.github.varhenn.solutions.year2024.day12

import com.github.varhenn.common.datastructures.Point
import com.github.varhenn.solutions.year2024.day12.Day12.Companion.countLongSides
import com.github.varhenn.solutions.year2024.day12.Day12.Companion.countShortSides
import com.github.varhenn.solutions.year2024.day12.Day12.Companion.perimeter
import com.github.varhenn.solutions.year2024.day12.Day12.Companion.regions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day12Test {

    @Nested
    inner class SampleData {
        @Test
        fun `should return correct solvePart1 for sample data`() {
            val sample = "OOOOO\nOXOXO\nOOOOO\nOXOXO\nOOOOO"
            assertEquals(772, Day12().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            val sample = "AAAA\nBBCD\nBBCC\nEEEC"
            assertEquals(80, Day12().solvePart2(sample))
        }
    }

    @Nested
    inner class EasyInputSolveStepByStep {
        private val input = "AA\nAB"
        private val grid = mapOf(Point(0, 0) to 'A', Point(1, 0) to 'A', Point(0, 1) to 'A', Point(1, 1) to 'B')
        private val regions = listOf(
            setOf(Point(0, 0), Point(1, 0), Point(0, 1)),
            setOf(Point(1, 1))
        )
        private val perimiter0 = mapOf(
            Point(x = 1, y = 0) to setOf(Point(x = 2, y = 0), Point(x = 1, y = 1)),
            Point(x = -1, y = 0) to setOf(Point(x = -1, y = 0), Point(x = -1, y = 1)),
            Point(x = 0, y = -1) to setOf(Point(x = 0, y = -1), Point(x = 1, y = -1)),
            Point(x = 0, y = 1) to setOf(Point(x = 1, y = 1), Point(x = 0, y = 2))
        )
        private val perimiter1 = mapOf(
            Point(x = 1, y = 0) to setOf(Point(x = 2, y = 1)),
            Point(x = -1, y = 0) to setOf(Point(x = 0, y = 1)),
            Point(x = 0, y = -1) to setOf(Point(x = 1, y = 0)),
            Point(x = 0, y = 1) to setOf(Point(1, 2))
        )

        @Test
        fun `should parse input to grid`() {
            assertEquals(grid, Day12.parse(input))
        }

        @Test
        fun `should parse grid to input`() {
            assertEquals(regions, grid.regions())
        }

        @Test
        fun `should calc region to perimeter`() {
            assertEquals(perimiter0, regions[0].perimeter())
            assertEquals(perimiter1, regions[1].perimeter())
        }

        @Test
        fun `should count short sides correctly`() {
            assertEquals(8, countShortSides(perimiter0))
            assertEquals(4, countShortSides(perimiter1))
        }

        @Test
        fun `should count long sides correctly`() {
            assertEquals(6, countLongSides(perimiter0))
            assertEquals(4, countLongSides(perimiter1))
        }
    }
}
