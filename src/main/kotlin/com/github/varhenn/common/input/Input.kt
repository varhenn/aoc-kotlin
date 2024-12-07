package com.github.varhenn.common.input

import java.io.File

private const val AOC_DATA_PATH = "AOC_DATA_PATH"

fun fromFile(year: Int, day: Int, type: String): String {
    val dataPath = System.getenv(AOC_DATA_PATH)
    val filename = "day${day.toString().padStart(2, '0')}_$type.txt"
    val file = File("$dataPath/year$year/$filename")

    require(!dataPath.isNullOrEmpty()) { "Environment variable AOC_DATA_PATH is not set" }
    require(file.exists() && file.isFile) { "File does not exist: $file" }
    return file.readText()
}

fun String.toListOfCharLists(): List<List<Char>> = this.lines().map { line -> line.map { it } }

fun String.toListOfIntLists(): List<List<Int>> = this.lines().map { it.toIntList() }

fun String.toListOfLongLists(): List<List<Long>> = this.lines().map { it.toLongList() }

fun String.toIntList(delimiter: Regex = Regex("\\D+")): List<Int> =
    this.split(delimiter).mapNotNull { it.toIntOrNull() }

fun String.toLongList(delimiter: Regex = Regex("\\D+")): List<Long> =
    this.split(delimiter).mapNotNull { it.toLongOrNull() }
