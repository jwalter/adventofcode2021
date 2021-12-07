package day02

import PuzzleBase

class Puzzle : PuzzleBase<Int> {
    override fun part1(input: List<String>): Int {
        val distanceByDirection = input.groupBy { it[0] }.map {
            it.key to it.value.sumOf { cmd -> cmd.split(" ").last().toInt() }
        }.toMap()
        val depth = distanceByDirection.getOrDefault('d', 0) - distanceByDirection.getOrDefault('u', 0)
        val horizontal = distanceByDirection['f'] ?: 0

        return depth * horizontal
    }

    override fun part1TestResult(): Int = 150

    override fun part2(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0
        input.map { it[0] to (it.split(" ").last().toIntOrNull() ?: 0) }.forEach {
            when (it.first) {
                'u' -> aim -= it.second
                'd' -> aim += it.second
                'f' -> {
                    horizontal += it.second
                    depth += aim * it.second
                }
            }
        }
        return depth * horizontal
    }

    override fun part2TestResult(): Int = 900

}