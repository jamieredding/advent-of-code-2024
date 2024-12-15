package dev.coldhands.adventofcode.day2

import kotlin.math.abs
import kotlin.math.sign

class DampenedSafetyChecker : SafetyChecker {
    override fun isSafe(levels: List<Int>): Boolean {
        return isSafeInit(levels, hasBeenDampened = false)
    }

    private fun isSafeInit(levels: List<Int>, hasBeenDampened: Boolean): Boolean {
        if (levels.size == 1) {
            return false
        }

        val (first, second) = levels

        val difference = second - first

        return when (abs(difference)) {
            in 1..3 -> isSafe(levels, difference, hasBeenDampened)
            else -> isSafeInit(levels.drop(1), hasBeenDampened = true)
        }
    }

    private fun isSafe(levels: List<Int>, prevDifference: Int, hasBeenDampened: Boolean): Boolean {
        if (levels.size == 1) return true

        val (first, second) = levels

        val difference = second - first

        val changedDirection = sign(prevDifference.toDouble()) != sign(difference.toDouble())

        return when {
            changedDirection && hasBeenDampened -> false
            changedDirection -> isSafe(levels.drop(1), prevDifference, hasBeenDampened = true)
            abs(difference) in 1..3 -> isSafe(levels.drop(1), difference, hasBeenDampened)
            hasBeenDampened -> false
            else -> isSafe(levels.drop(1), prevDifference, hasBeenDampened = true)
        }
    }
}