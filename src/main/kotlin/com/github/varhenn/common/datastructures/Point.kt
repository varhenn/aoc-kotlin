package com.github.varhenn.common.datastructures

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)
    operator fun times(scalar: Int) = Point(x * scalar, y * scalar)
    operator fun unaryMinus() = Point(-x, -y)

    fun rotate90Clockwise(): Point = Point(y, -x)
    fun rotate90CounterClockwise(): Point = Point(-y, x)
    fun cardinalNeighbors(): List<Point> = listOf(Point(x + 1, y), Point(x - 1, y), Point(x, y - 1), Point(x, y + 1))

    companion object {
        fun allNeighboringDirections(): List<Point> =
            (-1..1).flatMap { x -> (-1..1).map { y -> Point(x, y) } }.filter { it != Point(0, 0) }
    }
}
