package dev.coldhands.adventofcode.day3

class Solution {

    private val regex: Regex = """mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""".toRegex()

    fun parseInput(input: String): List<Operation> {
        return regex.findAll(input)
            .map {
                when {
                    it.value.startsWith("mul") -> parseMul(it)
                    it.value.startsWith("do()") -> parseDo()
                    it.value.startsWith("don't()") -> parseDont()
                    else -> throw IllegalArgumentException("Unknown operation")
                }
            }
            .toList()
    }

    fun computeInput(input: String): Int {
        return parseInput(input).asSequence()
            .filter { it is Mul }
            .map { it as Mul }
            .map { mul -> mul.first * mul.second }
            .sum()
    }

    private fun parseMul(input: MatchResult): Mul = input.groupValues.drop(1).let { args ->
        Mul(args[0].toInt(), args[1].toInt())
    }

    private fun parseDo(): Do = Do

    private fun parseDont(): Dont = Dont

    sealed interface Operation

    data class Mul(val first: Int, val second: Int) : Operation
    data object Do : Operation
    data object Dont : Operation
}