package com.github.varhenn.common.datastructures

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun times(scalar: Int) = Point(x * scalar, y * scalar)

    companion object {
        fun allDirections(): List<Point> =
            (-1..1).flatMap { x -> (-1..1).map { y -> Point(x, y) } }.filter { it != Point(0, 0) }
    }
}
