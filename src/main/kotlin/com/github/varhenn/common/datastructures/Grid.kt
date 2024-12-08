package com.github.varhenn.common.datastructures

class Grid<T>(data: List<List<T>>, private val default: (Point) -> T) {
    private val data: MutableMap<Point, T> = mutableMapOf()

    init {
        require(data.isEmpty() || data.all { it.size == data[0].size }) { "All rows must have the same length." }
        for ((rowIndex, row) in data.withIndex()) {
            for ((colIndex, value) in row.withIndex()) {
                this.data[Point(colIndex, rowIndex)] = value
            }
        }
    }

    operator fun get(coordinate: Point): T = data[coordinate] ?: default(coordinate)

    operator fun contains(coordinate: Point): Boolean = coordinate in data

    fun indices(): Set<Point> = data.keys
}
