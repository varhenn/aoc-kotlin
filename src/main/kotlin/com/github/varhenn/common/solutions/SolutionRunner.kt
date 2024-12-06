package com.github.varhenn.common.solutions

import com.github.varhenn.common.input.fromFile
import kotlin.system.measureTimeMillis
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class SolutionRunner<Output>(private val solution: Solution<Output>) {
    private val headers = listOf("Puzzle", "", "Data", "Result", "Time", "Notes")
    private val rows = mutableListOf<List<String>>()

    fun runToConsole() {
        val puzzle = "${solution.year}/${solution.day.toString().padStart(2, '0')}"
        solution.part1answers.keys.forEach { type ->
            processPart(puzzle, "part1", type, solution.part1answers[type]) { solution.solvePart1(it) }
        }
        solution.part2answers.keys.forEach { type ->
            processPart(puzzle, "part2", type, solution.part2answers[type]) { solution.solvePart2(it) }
        }
        printToConsole()
    }

    @Suppress("TooGenericExceptionCaught")
    private fun <T> processPart(puzzle: String, part: String, type: String, expected: T?, solver: (String) -> T) = try {
        val input = fromFile(solution.year, solution.day, type)
        val (result, time) = measureExecution { solver(input) }
        val evaluation = evaluateResult(expected, result)
        val timeStr = "${time.inWholeMilliseconds}ms".padStart(6)
        val notes = when (evaluation) {
            "" -> "Got: $result"
            "NOK" -> "Expected: $expected, Got: $result"
            else -> ""
        }
        rows.add(listOf(puzzle, part, type, evaluation, timeStr, notes))
    } catch (e: Exception) {
        rows.add(listOf(puzzle, part, type, "NOK", "-", e.toString()))
    }

    private fun <T> measureExecution(block: () -> T): Pair<T, Duration> {
        var result: T
        val time = measureTimeMillis { result = block() }
        return result to time.toDuration(DurationUnit.MILLISECONDS)
    }

    private fun <T> evaluateResult(expected: T?, actual: T): String = when (expected) {
        null -> ""
        actual -> "ok"
        else -> "NOK"
    }

    private fun printToConsole() {
        val widths = headers.indices.map { i -> (listOf(headers) + rows).maxOf { it[i].length } }
        val table = (listOf(headers) + rows).joinToString("\n") { row ->
            row.mapIndexed { i, col ->
                if (i < 4) col.padStart((widths[i] + col.length) / 2).padEnd(widths[i]) else col
            }.joinToString(" | ")
        }
        println(table)
    }
}
