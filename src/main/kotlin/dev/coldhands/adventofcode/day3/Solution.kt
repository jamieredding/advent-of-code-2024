package dev.coldhands.adventofcode.day3

class Solution {

    private val regex: Regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    fun parseInput(input: String): List<Mul> {
        return regex.findAll(input)
            .map { mul -> mul.groupValues.drop(1) }
            .map { args -> Mul(args[0].toInt(), args[1].toInt()) }
            .toList()
    }

    fun computeInput(input: String): Int {
        return parseInput(input).asSequence()
            .map { mul -> mul.first * mul.second }
            .sum()
    }

    data class Mul(val first: Int, val second: Int)
}