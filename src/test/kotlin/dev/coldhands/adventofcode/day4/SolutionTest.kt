package dev.coldhands.adventofcode.day4

import dev.coldhands.adventofcode.day4.Solution.Direction
import dev.coldhands.adventofcode.readPersonalInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SolutionTest {

    private val underTest = Solution()

    @Nested
    inner class CountXmasInLines {

        @Test
        fun `no occurrences`() {
            underTest.countXmasInLines(
                """
                ....
                ....
            """.trimIndent()
                    .lines()
            ) shouldBe 0
        }

        @Test
        fun `one occurrence`() {
            underTest.countXmasInLines(
                """
                XMAS
                ....
            """.trimIndent()
                    .lines()
            ) shouldBe 1
        }

        @Test
        fun `occurrences on multiple lines`() {
            underTest.countXmasInLines(
                """
                XMAS
                .... XMAS
            """.trimIndent()
                    .lines()
            ) shouldBe 2
        }

        @Test
        fun `multiple occurrences on a single line`() {
            underTest.countXmasInLines(
                """
                XMASXMAS
                ....
            """.trimIndent()
                    .lines()
            ) shouldBe 2
        }

        @Test
        fun `allow SAMX as valid XMAS`() {
            underTest.countXmasInLines(
                """
                SAMX
                ....
            """.trimIndent()
                    .lines()
            ) shouldBe 1
        }

        @Test
        fun `count overlapping twice`() {
            underTest.countXmasInLines(
                """
                XMASAMX
                ....
            """.trimIndent()
                    .lines()
            ) shouldBe 2
        }
    }

    @Nested
    inner class AllViewsOverWordSearch {

        @Test
        fun `horizontal view`() {
            underTest.viewLines(
                Direction.HORIZONTAL, """
                123
                456
                789
            """.trimIndent()
            ) shouldBe listOf(
                "123",
                "456",
                "789"
            )
        }

        @Test
        fun `vertical view`() {
            underTest.viewLines(
                Direction.VERTICAL, """
                123
                456
                789
            """.trimIndent()
            ) shouldBe listOf(
                "147",
                "258",
                "369"
            )
        }

        @Test
        fun `diagonal from top left`() {
            underTest.viewLines(
                Direction.DIAGONAL_TOP_LEFT, """
                123
                456
                789
            """.trimIndent()
            ) shouldBe listOf(
                "1",
                "24",
                "357",
                "68",
                "9"
            )
        }

        @Test
        fun `diagonal from top right`() {
            underTest.viewLines(
                Direction.DIAGONAL_TOP_RIGHT, """
                123
                456
                789
            """.trimIndent()
            ) shouldBe listOf(
                "3",
                "26",
                "159",
                "48",
                "7"
            )
        }
    }

    @Nested
    inner class CountOfXmasInWordSearch {

        @Test
        fun `given example 1`() {
            underTest.countOfXmasInWordSearch("""
                ..X...
                .SAMX.
                .A..A.
                XMAS.S
                .X....
            """.trimIndent()) shouldBe 4
        }

        @Test
        fun `given example 2`() {
            val input = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
            """.trimIndent()

            underTest.countOfXmasInWordSearch(input) shouldBe 18
        }

        @Test
        fun `part 1`() {
            val input = readPersonalInput(this)
            underTest.countOfXmasInWordSearch(input) shouldBe 2618
        }
    }
}