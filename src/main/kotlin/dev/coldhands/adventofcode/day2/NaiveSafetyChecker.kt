package dev.coldhands.adventofcode.day2

import kotlin.math.abs
import kotlin.math.sign

class NaiveSafetyChecker : SafetyChecker {
    override fun isSafe(levels: List<Int>): Boolean {
        val (first, second) = levels

        val difference = second - first

        return when (abs(difference)) {
            in 1..3 -> isSafe(levels, difference)
            else -> false
        }
    }

    private fun isSafe(levels: List<Int>, prevDifference: Int): Boolean {
        if (levels.size == 1) return true

        val (first, second) = levels

        val difference = second - first

        val changedDirection = sign(prevDifference.toDouble()) != sign(difference.toDouble())

        return when {
            changedDirection -> false
            abs(difference) in 1..3 -> isSafe(levels.drop(1), difference)
            else -> false
        }
    }
}