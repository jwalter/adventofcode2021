package day01

import PuzzleBase

class Puzzle : PuzzleBase<Int> {
    override fun part1(input: List<String>): Int {
        val result = input.map { it.toInt() }.fold(-1 to 0) { acc, i ->
            if (acc.first != -1 && acc.first < i) i to acc.second + 1
            else i to acc.second
        }
        return result.second
    }

    override fun part2(input: List<String>): Int {
        val result = input.map { it.toInt() }.fold(listOf<Int>() to 0) { acc, i ->
            if (acc.first.size < 3) (acc.first + i) to acc.second
            else if (acc.first.first() < i) (acc.first.drop(1) + i) to acc.second + 1
            else (acc.first.drop(1) + i) to acc.second
        }
        return result.second
    }

    override fun part1TestResult(): Int = 7

    override fun part2TestResult(): Int = 5
}