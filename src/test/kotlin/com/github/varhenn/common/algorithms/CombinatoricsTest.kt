package com.github.varhenn.common.algorithms

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CombinatoricsTest {
    @Nested
    inner class CartesianProductTests {

        @Test
        fun `should return correct product for multiple lists`() {
            val list1 = listOf(1, 2)
            val list2 = listOf("A", "B")
            val expected = listOf(listOf(1, "A"), listOf(1, "B"), listOf(2, "A"), listOf(2, "B"))
            assertEquals(expected, cartesianProduct(list1, list2))
        }

        @Test
        fun `should handle empty lists`() {
            val list1 = listOf(1, 2)
            val list2 = emptyList<String>()
            assertTrue(cartesianProduct(list1, list2).isEmpty())
        }
    }

    @Nested
    inner class Combinations2Tests {
        @Test
        fun `should return all pairs from the list`() {
            val list = listOf(1, 2, 3)
            val expected = sequenceOf(1 to 2, 1 to 3, 2 to 3).toList()
            assertEquals(expected, combinations2(list).toList())
        }

        @Test
        fun `should return an empty sequence for an empty list`() {
            val list = emptyList<Int>()
            assertTrue(combinations2(list).toList().isEmpty())
        }
    }
}
