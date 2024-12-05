package base

import kotlin.system.measureTimeMillis
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class OnePuzzleRunner<OutputPart1, OutputPart2>(
    private val solution: Puzzle<OutputPart1, OutputPart2>
) {
    private val headers = listOf("", "Data", "Result", "Time", "Notes")
    private val rows = mutableListOf<List<String>>()

    fun runToConsole() {
        solution.part1answers.keys.forEach { type ->
            processPart("part1", type, solution.part1answers[type]) { solution.solvePart1(it) }
        }
        solution.part2answers.keys.forEach { type ->
            processPart("part2", type, solution.part2answers[type]) { solution.solvePart2(it) }
        }
        printToConsole()
    }

    @Suppress("TooGenericExceptionCaught")
    private fun <T> processPart(part: String, type: String, expected: T?, solver: (Input) -> T) = try {
        val input = Input.fromFile(solution.year, solution.day, type)
        val (result, time) = measureExecution { solver(input) }
        val evaluation = evaluateResult(expected, result)
        val timeStr = "${time.inWholeMilliseconds}ms".padStart(6)
        val notes = when (evaluation) {
            "" -> "Got: $result"
            "NOK" -> "Expected: $expected, Got: $result"
            else -> ""
        }
        rows.add(listOf(part, type, evaluation, timeStr, notes))
    } catch (e: Exception) {
        rows.add(listOf(part, type, "NOK", "-", e.toString()))
    }

    private fun <T> measureExecution(block: () -> T): Pair<T, Duration> {
        var result: T
        val time = measureTimeMillis { result = block() }
        return result to time.toDuration(DurationUnit.MILLISECONDS)
    }

    private fun <T> evaluateResult(expected: T?, actual: T): String = when {
        expected == null -> ""
        expected == actual -> "ok"
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
