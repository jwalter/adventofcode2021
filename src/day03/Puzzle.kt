package day03

import PuzzleBase

class Puzzle : PuzzleBase<Int> {
    override fun part1(input: List<String>): Int {
        val ones = input[0].map { 0 }.toMutableList()
        input.forEach { row ->
            row.forEachIndexed { index, c ->
                if (c == '1') {
                    ones[index]++
                } else {
                    ones[index]--
                }
            }
        }
        val (gammaBits, epsilonBits) = ones.map { if (it > 0) "1" to "0" else "0" to "1" }.unzip()
        val gamma = gammaBits.joinToString("").toInt(2)
        val epsilon = epsilonBits.joinToString("").toInt(2)
        return gamma * epsilon
    }

    override fun part1TestResult(): Int = 198

    override fun part2(input: List<String>): Int {
        var rows = input.toMutableList()
        var pos = 0
        while (rows.size > 1) {
            val (ones, zeroes) = rows.partition { it[pos] == '1' }
            rows = if (ones.size > zeroes.size) {
                ones.toMutableList()
            } else if (zeroes.size > ones.size) {
                zeroes.toMutableList()
            } else {
                ones.toMutableList()
            }
            pos++
        }
        val oxygenGeneratorRating = rows[0].toInt(2)
        rows = input.toMutableList()
        pos = 0
        while (rows.size > 1) {
            val (ones, zeroes) = rows.partition { it[pos] == '1' }
            rows = if (ones.size > zeroes.size) {
                zeroes.toMutableList()
            } else if (zeroes.size > ones.size) {
                ones.toMutableList()
            } else {
                zeroes.toMutableList()
            }
            pos++
        }
        return  oxygenGeneratorRating * rows[0].toInt(2)
    }

    override fun part2TestResult(): Int = 230

}