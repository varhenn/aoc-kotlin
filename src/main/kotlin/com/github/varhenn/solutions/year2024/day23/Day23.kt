package com.github.varhenn.solutions.year2024.day23

import com.github.varhenn.common.solutions.Solution
import com.github.varhenn.common.solutions.SolutionRunner

fun main() = SolutionRunner(Day23()).runToConsole()

class Day23 : Solution<String>() {
    override val year = 2024
    override val day = 23
    override val part1answers = mapOf("sample" to "7", "puzzle" to "1323")
    override val part2answers = mapOf("sample" to "co,de,ka,ta", "puzzle" to "er,fh,fi,ir,kk,lo,lp,qi,ti,vb,xf,ys,yu")

    override fun solvePart1(input: String): String =
        parseToNeighbors(input)
            .clique3()
            .count { it.first[0] == 't' || it.second[0] == 't' || it.third[0] == 't' }
            .toString()

    override fun solvePart2(input: String): String {
        val edges = parseToNeighbors(input)
        return edges
            .maxClique(edges.keys)
            .joinToString(separator = ",")
    }

    companion object {
        fun parseToNeighbors(input: String): Map<String, Set<String>> = buildMap<String, MutableSet<String>> {
            Regex("""(\w+)-(\w+)""")
                .findAll(input)
                .forEach { match ->
                    val (a, b) = match.destructured
                    getOrPut(minOf(a, b), ::mutableSetOf).add(maxOf(a, b))
                    getOrPut(maxOf(a, b), ::mutableSetOf)
                }
        }

        fun Map<String, Set<String>>.clique3() =
            entries.flatMap { (first, seconds) ->
                seconds.flatMap { second ->
                    (this[second]!! intersect seconds).map { third ->
                        Triple(first, second, third)
                    }
                }
            }

        // NOTE: brute force, but enough here. General better is Bron–Kerbosch algorithm
        fun Map<String, Set<String>>.maxClique(todo: Set<String>, clique: Set<String> = emptySet()): Set<String> =
            todo
                .map { maxClique(todo intersect this[it]!!, clique + it) }
                .maxByOrNull { it.size }
                ?: clique
    }
}
