package dev.coldhands.adventofcode.day4

import dev.coldhands.adventofcode.readPersonalInput
import dev.coldhands.adventofcode.day4.Solution.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SolutionTest {

    private val underTest = Solution()

    @Nested
    inner class DirectionalSubstring {

        @Test
        fun horizontal() {
            """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                direction = Direction.HORIZONTAL,
                centrePoint = Point(1, 1),
                lengthEitherSide = 2
            ) shouldBe "456"
        }

        @Test
        fun `horizontal centred on left`() {
            """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                direction = Direction.HORIZONTAL,
                centrePoint = Point(0, 0),
                lengthEitherSide = 2
            ) shouldBe "12"
        }

        @Test
        fun `horizontal centred on right`() {
            """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                direction = Direction.HORIZONTAL,
                centrePoint = Point(2, 0),
                lengthEitherSide = 2
            ) shouldBe "23"
        }

        @Test
        fun vertical() {
            """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                direction = Direction.VERTICAL,
                centrePoint = Point(1, 1),
                lengthEitherSide = 2
            ) shouldBe "258"
        }

        @Test
        fun `vertical centred on top`() {
            """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                direction = Direction.VERTICAL,
                centrePoint = Point(0, 0),
                lengthEitherSide = 2
            ) shouldBe "14"
        }

        @Test
        fun `vertical centred on bottom`() {
            """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                direction = Direction.VERTICAL,
                centrePoint = Point(0, 2),
                lengthEitherSide = 2
            ) shouldBe "47"
        }
    }

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
            underTest.countOfXmasInWordSearch(
                """
                ..X...
                .SAMX.
                .A..A.
                XMAS.S
                .X....
            """.trimIndent()
            ) shouldBe 4
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