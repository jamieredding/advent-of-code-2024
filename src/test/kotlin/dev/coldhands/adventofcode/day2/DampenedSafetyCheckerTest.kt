package dev.coldhands.adventofcode.day2

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DampenedSafetyCheckerTest {

    private val underTest = Solution(DampenedSafetyChecker())

    @ParameterizedTest
    @ValueSource(
        strings = [
            "1 2 3 4 5",
            "1 3 6 7 8",
            "5 4 3 2 1",
            "8 7 6 3 1",
            "1 5 6 7 8",
            "8 7 6 5 1",
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