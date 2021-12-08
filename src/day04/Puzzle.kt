package day04

import PuzzleBase

class Puzzle : PuzzleBase<Int> {
    override fun part1(input: List<String>): Int {
        val drawnNumbers = input[0].split(",")
        val boards = parseBoards(input)
        var pos = 0
        var currentNumber = drawnNumbers[pos++]
        var winner: ScorableBoard? = null
        while (winner == null) {
            boards.forEach {
                it.score(currentNumber)
                if (it.bingo()) {
                    winner = it
                    return@forEach
                }
            }
            if (winner == null) {
                currentNumber = drawnNumbers[pos++]
            }
        }
        println("winner is: $winner")
        return (winner?.sumOfRemainingNumbers() ?: 0) * currentNumber.toInt()
    }

    data class Cell(var num: String)

    data class ScorableBoard(private val rows: List<List<Cell>>) {
        private var hasBingo = false
        private var rowScores = (0 until (rows.size)).map { 0 }.toMutableList()
        private var colScores = (0 until (rows[0].size)).map { 0 }.toMutableList()

        fun bingo(): Boolean {
            return hasBingo
        }

        fun sumOfRemainingNumbers(): Int {
            return rows.flatten().filterNot { it.num.isEmpty() }.sumOf { it.num.toInt() }
        }

        fun score(s: String) {
            rows.forEachIndexed { rowNo, row ->
                row.forEachIndexed { colNo, cell ->
                    if (cell.num == s) {
                        rowScores[rowNo]++
                        colScores[colNo]++
                        cell.num = ""
                        if (rowScores[rowNo] == 5 || colScores[colNo] == 5) {
                            hasBingo = true
                        }
                    }
                }
            }
        }
    }

    override fun part1TestResult(): Int = 4512

    override fun part2(input: List<String>): Int {
        val drawnNumbers = input[0].split(",")
        var boards = parseBoards(input)
        var pos = 0
        var currentNumber = drawnNumbers[pos++]
        var winner: ScorableBoard? = null
        while (winner == null) {
            boards.forEach {
                it.score(currentNumber)
            }
            val (bingoBoards, remainingBoards) = boards.partition { it.bingo() }
            if (remainingBoards.isEmpty()) {
                winner = bingoBoards.last()
            }
            if (winner == null) {
                currentNumber = drawnNumbers[pos++]
            }
            boards = remainingBoards
        }
        println("winner is: $winner")
        val result = winner.sumOfRemainingNumbers() * currentNumber.toInt()
        println("winning result is: $result")
        return result
    }

    private fun parseBoards(input: List<String>) = input.drop(1).chunked(6).map {
        it.drop(1).map {
            it.split(" ").map { it.trim() }.filterNot { it.isEmpty() }.map { Cell(it) }
        }
    }.map { board -> ScorableBoard(board) }

    override fun part2TestResult(): Int = 1924

}