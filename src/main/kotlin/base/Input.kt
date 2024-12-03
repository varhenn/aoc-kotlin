package base

import java.io.File

class Input private constructor(private val data: String) {

    fun asText(): String = data

    fun asLines(): List<String> = asText().lines()

    fun asListOfIntLists(): List<List<Int>> = asLines().map { it.toIntList() }

    companion object {
        private const val AOC_DATA_PATH = "AOC_DATA_PATH"

        fun fromFile(year: Int, day: Int, type: String): Input {
            val dataPath = System.getenv(AOC_DATA_PATH)
            val filename = "day${day.toString().padStart(2, '0')}_$type.txt"
            val file = File("$dataPath/year$year/$filename")

            require(!dataPath.isNullOrEmpty()) { "Environment variable AOC_DATA_PATH is not set" }
            require(file.exists() && file.isFile) { "File does not exist: $file" }
            return Input(file.readText())
        }
    }
}

fun String.toIntList(delimiter: Regex = Regex("\\s+")): List<Int> =
    this.split(delimiter).mapNotNull { it.toIntOrNull() }
