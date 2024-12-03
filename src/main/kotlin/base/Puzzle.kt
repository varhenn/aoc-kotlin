package base

abstract class Puzzle<OutputPart1, OutputPart2> {
    abstract val year: Int
    abstract val day: Int
    abstract val part1answers: Map<String, OutputPart1?>
    abstract val part2answers: Map<String, OutputPart2?>

    abstract fun solvePart1(input: Input): OutputPart1
    abstract fun solvePart2(input: Input): OutputPart2
}
