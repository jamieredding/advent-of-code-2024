package dev.coldhands.adventofcode.day2

class Solution(private val safetyChecker: SafetyChecker) {
    fun countSafeReports(input: String): Int {
        return input.lineSequence()
            .count { isSafe(it) }
    }

    fun isSafe(input: String): Boolean {
        val levels = input.split(" ").asSequence()
            .map { it.toInt() }
            .toList()


        return safetyChecker.isSafe(levels)
    }
}