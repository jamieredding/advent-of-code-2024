package dev.coldhands.adventofcode.day1

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SolutionTest {

    private val underTest = Solution()

    @Test
    fun `items already sorted with no differences`() {
        underTest.solve(
            """
            1   1
        """.trimIndent()
        ) shouldBe 0
    }

    @Test
    fun `items already sorted with difference of 1`() {
        underTest.solve(
            """
            1   2
        """.trimIndent()
        ) shouldBe 1
    }

    @Test
    fun `multiple items in input`() {
        underTest.solve(
            """
            1   2
            2   3
        """.trimIndent()
        ) shouldBe 2
    }

    @Test
    fun `each column is unsorted`() {
        underTest.solve(
            """
            1   4
            2   3
            3   2
        """.trimIndent()
        ) shouldBe 3
    }

    @Test
    fun `cater for blank lines`() {

        underTest.solve(
            """
            1   3
            
            
            2   2
            
            
            
        """.trimIndent()
        ) shouldBe 2
    }

    @Test
    fun `personal input`() {
        val input = SolutionTest::class.java.getResource("part1.txt")!!.readText()

        underTest.solve(input) shouldBe 3574690
    }
}