fun main() {
    fun part1(input: List<String>): Int {
        val result = input.map { it.toInt() }.fold(-1 to 0) { acc, i ->
            if (acc.first != -1 && acc.first < i) i to acc.second + 1
            else i to acc.second
        }
        return result.second
    }

    fun part2(input: List<String>): Int {
        val result = input.map { it.toInt() }.fold(listOf<Int>() to 0) { acc, i ->
            if (acc.first.size < 3) (acc.first + i) to acc.second
            else if (acc.first.first() < i) (acc.first.drop(1) + i) to acc.second + 1
            else (acc.first.drop(1) + i) to acc.second
        }
        return result.second
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
