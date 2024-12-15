package dev.coldhands.adventofcode.day1

import dev.coldhands.adventofcode.readPersonalInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SolutionTest {

    private val underTest = Solution()

    @Nested
    inner class Part1 {
        @Test
        fun `items already sorted with no differences`() {
            underTest.solvePart1(
                """
            1   1
            """.trimIndent()
            ) shouldBe 0
        }

        @Test
        fun `items already sorted with difference of 1`() {
            underTest.solvePart1(
                """
            1   2
        """.trimIndent()
            ) shouldBe 1
        }

        @Test
        fun `multiple items in input`() {
            underTest.solvePart1(
                """
            1   2
            2   3
        """.trimIndent()
            ) shouldBe 2
        }

        @Test
        fun `each column is unsorted`() {
            underTest.solvePart1(
                """
            1   4
            2   3
            3   2
        """.trimIndent()
            ) shouldBe 3
        }

        @Test
        fun `cater for blank lines`() {

            underTest.solvePart1(
                """
            1   3
            
            
            2   2
            
            
            
        """.trimIndent()
            ) shouldBe 2
        }

        @Test
        fun `personal input`() {
            val input = readPersonalInput(this)

            underTest.solvePart1(input) shouldBe 3574690
        }
    }


    @Nested
    inner class Part2 {
        @Test
        fun `left item occurs once in list`() {
            underTest.solvePart2(
                """
            1   1
            """.trimIndent()
            ) shouldBe 1
        }

        @Test
        fun `two items each occurring once`() {
            underTest.solvePart2(
                """
            1   1
            2   2
        """.trimIndent()
            ) shouldBe 3
        }

        @Test
        fun `item occurs multiple times multiplies value`() {
            underTest.solvePart2(
                """
            1   2
            2   2
        """.trimIndent()
            ) shouldBe 4
        }

        @Test
        fun `cater for blank lines`() {

            underTest.solvePart2(
                """
            1   3
            
            
            2   4
            
            
            
        """.trimIndent()
            ) shouldBe 0
        }

        @Test
        fun `personal input`() {
            val input = readPersonalInput(this)

            underTest.solvePart2(input) shouldBe 22565391
        }
    }

}