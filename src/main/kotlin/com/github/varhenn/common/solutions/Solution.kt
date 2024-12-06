package com.github.varhenn.common.solutions

abstract class Solution<Output> {
    abstract val year: Int
    abstract val day: Int
    abstract val part1answers: Map<String, Output?>
    abstract val part2answers: Map<String, Output?>

    abstract fun solvePart1(input: String): Output
    abstract fun solvePart2(input: String): Output
}
