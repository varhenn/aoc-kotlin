package com.github.varhenn.solutions.year2024.day23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day23Test {
    @Nested
    inner class SampleData {
        private val sample = """
            kh-tc qp-kh de-cg ka-co yn-aq qp-ub cg-tb vc-aq tb-ka wh-tc yn-cg kh-ub ta-co de-co tc-td tb-wq
            wh-td ta-ka td-qp aq-cg wq-ub ub-vc de-ta wq-aq wq-vc wh-yn ka-de kh-ta co-tc wh-qp tb-vc td-yn
        """

        @Test
        fun `should return correct solvePart1 for sample data`() {
            assertEquals("7", Day23().solvePart1(sample))
        }

        @Test
        fun `should return correct solvePart2 for sample data`() {
            assertEquals("co,de,ka,ta", Day23().solvePart2(sample))
        }
    }
}
