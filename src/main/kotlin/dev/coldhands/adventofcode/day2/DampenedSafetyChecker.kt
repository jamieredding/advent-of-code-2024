package dev.coldhands.adventofcode.day2

import kotlin.math.abs
import kotlin.math.sign

class DampenedSafetyChecker : SafetyChecker {
    override fun isSafe(levels: List<Int>): Boolean {
        return isSafeInit(levels, hasBeenDampened = false)
    }

    private fun isSafeInit(levels: List<Int>, hasBeenDampened: Boolean): Boolean {
        val (first, second) = levels

        val difference = second - first

        return when {
            abs(difference) in 1..3 -> isSafe(levels, difference, hasBeenDampened)
            hasBeenDampened -> false
            else -> isSafeInit(levels.drop(1), hasBeenDampened = true)
        }
    }

    private fun isSafe(levels: List<Int>, prevDifference: Int, hasBeenDampened: Boolean): Boolean {
        if (levels.size == 1) return true

        val (first, second) = levels

        val difference = second - first

        val changedDirection = sign(prevDifference.toDouble()) != sign(difference.toDouble())

        return when {
            meetsOriginalCondition(changedDirection, difference) -> isSafe(levels.drop(1), difference, hasBeenDampened)
            !hasBeenDampened -> isSafe(levels.dropSecondElement(), prevDifference, hasBeenDampened = true)
            else -> false
        }
    }

    private fun meetsOriginalCondition(changedDirection: Boolean, difference: Int): Boolean {
        return !changedDirection && abs(difference) in 1..3
    }

    private fun <T> List<T>.dropSecondElement() : List<T> {
        return take(1) + drop(2)
    }
}