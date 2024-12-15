package dev.coldhands.adventofcode.day2

import dev.coldhands.adventofcode.readPersonalInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SolutionTest {

    private val underTest = Solution()

    @ParameterizedTest
    @ValueSource(
        strings = [
            "1 2 3 4 5",
            "1 3 6 7 8",
            "5 4 3 2 1",
            "8 7 6 3 1",
        ]
    )
    fun safeReports(input: String) {
        underTest.isSafe(input) shouldBe true
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "1 1 1 1 1",
            "1 5 6 7 8",
            "8 7 6 5 1",
            "1 2 3 2 1",
        ]
    )
    fun unsafeReports(input: String) {
        underTest.isSafe(input) shouldBe false
    }

    @Test
    fun countSafeReports() {
        val input = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()

        underTest.countSafeReports(input) shouldBe 2
    }

    @Test
    fun part1() {
        val input = readPersonalInput(this)

        underTest.countSafeReports(input) shouldBe 3
    }
}