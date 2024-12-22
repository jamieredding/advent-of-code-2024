package dev.coldhands.adventofcode.day4

import dev.coldhands.adventofcode.day4.Direction.*
import dev.coldhands.adventofcode.readPersonalInput
import dev.coldhands.adventofcode.day4.Solution.*
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SolutionTest {

    private val underTest = Solution()

    @Nested
    inner class DirectionalSubstring {

        @Nested
        inner class Horizontal {

            @Test
            fun `length within bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = HORIZONTAL,
                    centrePoint = Point(1, 1),
                    lengthEitherSideInclusive = 2
                ) shouldBe "456"
            }

            @Test
            fun `start out of bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = HORIZONTAL,
                    centrePoint = Point(0, 0),
                    lengthEitherSideInclusive = 2
                ) shouldBe "12"
            }

            @Test
            fun `end out of bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = HORIZONTAL,
                    centrePoint = Point(2, 0),
                    lengthEitherSideInclusive = 2
                ) shouldBe "23"
            }
        }

        @Nested
        inner class Vertical {

            @Test
            fun `length within bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = VERTICAL,
                    centrePoint = Point(1, 1),
                    lengthEitherSideInclusive = 2
                ) shouldBe "258"
            }

            @Test
            fun `start out of bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = VERTICAL,
                    centrePoint = Point(0, 0),
                    lengthEitherSideInclusive = 2
                ) shouldBe "14"
            }

            @Test
            fun `end out of bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = VERTICAL,
                    centrePoint = Point(0, 2),
                    lengthEitherSideInclusive = 2
                ) shouldBe "47"
            }
        }

        @Nested
        inner class DiagonalTopLeft {

            @Test
            fun `length within bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = DIAGONAL_TOP_LEFT,
                    centrePoint = Point(1, 1),
                    lengthEitherSideInclusive = 2
                ) shouldBe "159"
            }

            @Test
            fun `start out of bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = DIAGONAL_TOP_LEFT,
                    centrePoint = Point(0, 0),
                    lengthEitherSideInclusive = 2
                ) shouldBe "15"
            }

            @Test
            fun `end out of bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = DIAGONAL_TOP_LEFT,
                    centrePoint = Point(2, 2),
                    lengthEitherSideInclusive = 2
                ) shouldBe "59"
            }

            @Test
            fun `broken case 1`() {
                """
                ..X...
                .SAMX.
                .A..A.
                XMAS.S
                .X....
            """.trimIndent().directionalSubstring(
                    direction = DIAGONAL_TOP_LEFT,
                    centrePoint = Point(0, 3),
                    lengthEitherSideInclusive = 4
                ) shouldBe "XX"
            }
        }

        @Nested
        inner class DiagonalTopRight {

            @Test
            fun `length within bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = DIAGONAL_TOP_RIGHT,
                    centrePoint = Point(1, 1),
                    lengthEitherSideInclusive = 2
                ) shouldBe "357"
            }

            @Test
            fun `start out of bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = DIAGONAL_TOP_RIGHT,
                    centrePoint = Point(2, 0),
                    lengthEitherSideInclusive = 2
                ) shouldBe "35"
            }

            @Test
            fun `end out of bounds`() {
                """
                123
                456
                789
            """.trimIndent().directionalSubstring(
                    direction = DIAGONAL_TOP_RIGHT,
                    centrePoint = Point(0, 2),
                    lengthEitherSideInclusive = 2
                ) shouldBe "57"
            }

            @Test
            fun `broken case 1`() {

                """
                ..X...
                .SAMX.
                .A..A.
                XMAS.S
                .X....
            """.trimIndent().directionalSubstring(
                    direction = DIAGONAL_TOP_RIGHT,
                    centrePoint = Point(0, 0),
                    lengthEitherSideInclusive = 4
                ) shouldBe "."
            }

            @Test
            fun `broken case 2`() {

                """
                ..X...
                .SAMX.
                .A..A.
                XMAS.S
                .X....
            """.trimIndent().directionalSubstring(
                    direction = DIAGONAL_TOP_RIGHT,
                    centrePoint = Point(1, 4),
                    lengthEitherSideInclusive = 4
                ) shouldBe "X.AX"
            }
        }

    }

    @Nested
    inner class LookAroundAtPoint {

        @Test
        fun `should find all lines in local area`() {
            """
                123
                456
                789
            """.trimIndent().lookAroundAt(
                centrePoint = Point(1, 1),
                lengthEitherSideInclusive = 2
            ) shouldContainExactlyInAnyOrder listOf(
                "456",
                "258",
                "159",
                "357"
            )
        }
    }

    @Nested
    inner class XmasOccurrences {

        @Test
        fun `no occurrences`() {
            underTest.xmasOccurrences("....") shouldBe 0
        }

        @Test
        fun `one occurrence`() {
            underTest.xmasOccurrences("XMAS") shouldBe 1
        }

        @Test
        fun `multiple occurrences`() {
            underTest.xmasOccurrences("XMASXMAS") shouldBe 2
        }

        @Test
        fun `allow SAMX as valid XMAS`() {
            underTest.xmasOccurrences("SAMX") shouldBe 1
        }

        @Test
        fun `count overlapping twice`() {
            underTest.xmasOccurrences("XMASAMX") shouldBe 2
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