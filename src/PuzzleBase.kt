import kotlin.system.measureTimeMillis

interface PuzzleBase<T> {
    fun solve() {

        val puzzleFolder = javaClass.packageName
        println("=== $puzzleFolder ===")
        val testInput = readInput(puzzleFolder, "test_input")
        check(part1(testInput) == part1TestResult())

        val input = readInput(puzzleFolder, "input")
        val timeTakenOne = measureTimeMillis {
            println(part1(input))
        }
        println("Time taken for part 1: $timeTakenOne")

        check(part2(testInput) == part2TestResult())
        val timeTakenTwo = measureTimeMillis {
            println("Part 2: ${part2(input)}")
        }
        println("Time taken for part 2: $timeTakenTwo")
        println()
    }

    fun part1(input: List<String>): T

    fun part1TestResult(): T

    fun part2(input: List<String>): T

    fun part2TestResult(): T
}