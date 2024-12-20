package dev.coldhands.adventofcode.day2

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DampenedSafetyCheckerTest {

    private val underTest = Solution(DampenedSafetyChecker())

    /*

    The levels are either all increasing or all decreasing.
    Any two adjacent levels differ by at least one and at most three.

     */

    @ParameterizedTest
    @ValueSource(
        strings = [
            // existing behaviour
            "1 2 3 4 5",
            "5 4 3 2 1",
            "1 3 6 7 8",
            "8 7 6 3 1",
            // new behaviour
            "1 5 6 7 8",    // drop first
            "1 30 2 3 4",   // drop second
            "1 2 30 3 4",   // drop third
            "1 2 3 40 5",   // drop fourth
            "8 7 6 5 1",    // drop last
        ]
    )
    fun safeReports(input: String) {
        underTest.isSafe(input) shouldBe true
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "1 1 1 1 1",
            "1 2 3 2 1",
            "1 5 6 7 6",
            "1 2 3 7 8",
        ]
    )
    fun unsafeReports(input: String) {
        underTest.isSafe(input) shouldBe false
    }

}