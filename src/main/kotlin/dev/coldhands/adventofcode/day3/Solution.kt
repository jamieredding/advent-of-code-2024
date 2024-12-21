package dev.coldhands.adventofcode.day3

class Solution {

    private val regex: Regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    fun parseInput(input: String): List<Operation> {
        return regex.findAll(input)
            .map { mul -> mul.groupValues.drop(1) }
            .map { args -> Mul(args[0].toInt(), args[1].toInt()) }
            .toList()
    }

    fun computeInput(input: String): Int {
        return parseInput(input).asSequence()
            .filter { it is Mul }
            .map { it as Mul }
            .map { mul -> mul.first * mul.second }
            .sum()
    }

    sealed interface Operation

    data class Mul(val first: Int, val second: Int) : Operation
    data object Do : Operation
    data object Dont: Operation
}