package dev.coldhands.adventofcode.day2

import dev.coldhands.adventofcode.readPersonalInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SolutionTest {

    @Test
    fun countSafeReports() {
        val underTest = Solution(object : SafetyChecker {
            override fun isSafe(levels: List<Int>): Boolean {
                return levels.first() == 1
            }
        })

        val input = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()

        underTest.countSafeReports(input) shouldBe 3
    }

    @Nested
    inner class Part1 {
        private val underTest = Solution(NaiveSafetyChecker())

        @Test
        fun part1() {
            val input = readPersonalInput(this)

            underTest.countSafeReports(input) shouldBe 686
        }
    }
}