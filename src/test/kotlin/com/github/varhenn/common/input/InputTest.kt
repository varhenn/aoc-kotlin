package com.github.varhenn.common.input

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class InputTest {
    @Nested
    inner class ToListOfCharListsTests {
        @Test
        fun `should convert string to list of char lists`() {
            val input = "abc\ndef\nghi"
            val expected = listOf(
                listOf('a', 'b', 'c'),
                listOf('d', 'e', 'f'),
                listOf('g', 'h', 'i')
            )
            assertEquals(expected, input.toListOfCharLists())
        }
    }

    @Nested
    inner class ToListOfIntListsTests {
        @Test
        fun `should convert string to list of int lists seperate comma`() {
            val input = "1,2,3\n4,5,6\n7,8,9"
            val expected = listOf(
                listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9)
            )
            assertEquals(expected, input.toListOfIntLists())
        }

        @Test
        fun `should convert string to list of int lists space seperate`() {
            val input = "3 4\n4 3\n1 3\n3 9\n3 3"
            val expected = listOf(listOf(3, 4), listOf(4, 3), listOf(1, 3), listOf(3, 9), listOf(3, 3))
            assertEquals(expected, input.toListOfIntLists())
        }
    }

    @Nested
    inner class ToListOfLongListsTests {
        @Test
        fun `should convert string to list of long lists`() {
            val input = "10,20,30\n40,50,60\n70,80,90"
            val expected = listOf(
                listOf(10L, 20L, 30L),
                listOf(40L, 50L, 60L),
                listOf(70L, 80L, 90L)
            )
            assertEquals(expected, input.toListOfLongLists())
        }
    }

    @Nested
    inner class ToIntListTests {
        @Test
        fun `should convert string to list of integers`() {
            val input = "1,2,3,4,5"
            val expected = listOf(1, 2, 3, 4, 5)
            assertEquals(expected, input.toIntList())
        }
    }

    @Nested
    inner class ToLongListTests {
        @Test
        fun `should convert string to list of longs`() {
            val input = "100,200,300,400,500"
            val expected = listOf(100L, 200L, 300L, 400L, 500L)
            assertEquals(expected, input.toLongList())
        }
    }
}
