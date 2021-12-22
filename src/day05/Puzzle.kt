package day05

import PuzzleBase
import java.lang.Integer.max
import kotlin.math.abs

class Puzzle : PuzzleBase<Int> {
    override fun part1TestResult(): Int = 5
    override fun part2TestResult(): Int = 12

    override fun part1(input: List<String>): Int {
        val allPoints = input.flatMap { line ->
            line.split("->")
                .flatMap { coord ->
                    coord.trim().split(",")
                }.let {
                    expand(it)
                }
        }
        val pointToCount = mutableMapOf<Point, Int>()
        var count = 0
        allPoints.forEach {
            if (pointToCount.contains(it) && pointToCount[it] == 1) {
                count++
            }
            pointToCount[it] = pointToCount.getOrDefault(it, 0) + 1
        }
        return count
    }

    private fun expand(p: List<String>): List<Point> {
        val from = Point(p[0].toInt(), p[1].toInt())
        val to = Point(p[2].toInt(), p[3].toInt())
        val points = mutableListOf<Point>()
        if (from.x == to.x || from.y == to.y) {
            for (x in rangeOf(from.x, to.x)) {
                for (y in rangeOf(from.y, to.y)) {
                    points.add(Point(x, y))
                }
            }
        }
        return points
    }

    private fun rangeOf(a: Int, b: Int) = if (a < b) a..b else b..a

    private data class Point(val x: Int, val y: Int)

    override fun part2(input: List<String>): Int {
        val allPoints = input.flatMap { line ->
            line.split("->")
                .flatMap { coord ->
                    coord.trim().split(",")
                }.let {
                    expand2(it)
                }
        }
        val pointToCount = mutableMapOf<Point, Int>()
        var count = 0
        allPoints.forEach {
            if (pointToCount.contains(it) && pointToCount[it] == 1) {
                count++
            }
            pointToCount[it] = pointToCount.getOrDefault(it, 0) + 1
        }
        return count
    }

    private fun expand2(p: List<String>): List<Point> {
        val from = Point(p[0].toInt(), p[1].toInt())
        val to = Point(p[2].toInt(), p[3].toInt())
        val pointCount = max(abs(from.x - to.x), abs(from.y - to.y)) + 1
        val deltaX = if (from.x < to.x) {
            1
        } else if (from.x > to.x) {
            -1
        } else {
            0
        }
        val deltaY = if (from.y < to.y) {
            1
        } else if (from.y > to.y) {
            -1
        } else {
            0
        }
        val pointsNew = (0 until pointCount).map {
            Point(from.x + it * deltaX, from.y + it * deltaY)
        }
        return pointsNew
    }
}