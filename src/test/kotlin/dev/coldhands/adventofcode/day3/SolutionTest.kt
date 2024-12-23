package dev.coldhands.adventofcode.day3

import dev.coldhands.adventofcode.readPersonalInput
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SolutionTest {

    private val underTest = Solution()

    @Nested
    inner class ParseInput {

        @Nested
        inner class ValidCases {

            @Test
            fun `single mul`() {
                underTest.parseInput("mul(1,2)") shouldBe listOf(
                    Solution.Mul(1, 2)
                )
            }

            @Test
            fun `three digit mul`() {
                underTest.parseInput("mul(100,200)") shouldBe listOf(
                    Solution.Mul(100, 200)
                )
            }

            @Test
            fun `multiple muls`() {
                underTest.parseInput("mul(1,2)mul(3,4)") shouldBe listOf(
                    Solution.Mul(1, 2),
                    Solution.Mul(3, 4),
                )
            }

            @Test
            fun `multiple muls with characters in between`() {
                underTest.parseInput("mul(1,2)kjhasdmul(3,4)") shouldBe listOf(
                    Solution.Mul(1, 2),
                    Solution.Mul(3, 4),
                )
            }

            @Test
            fun `do`() {
                underTest.parseInput("do()") shouldBe listOf(Solution.Do)
            }

            @Test
            fun `don't`() {
                underTest.parseInput("don't()") shouldBe listOf(Solution.Dont)
            }

        }

        @Nested
        inner class InvalidCases {

            @ParameterizedTest
            @ValueSource(
                strings = [
                    "100,200)",
                    "mul(,200)",
                    "mul(x,2)",
                    "mul(1000,2)",
                    "mul(12)",
                    "mul(1,)",
                    "mul(1,x)",
                    "mul(1,1000)",
                    "mul(1,2",
                    "d()",
                    "do(",
                    "do)",
                    "o()",
                    "dont()",
                    "don'()",
                    "don't)",
                ]
            )
            fun `invalid input`(input: String) {
                underTest.parseInput(input).shouldBeEmpty()
            }
        }
    }

    @Nested
    inner class ComputeInputPart1 {

        @Test
        fun `single mul`() {
            underTest.computeInput("mul(2,2)") shouldBe 4
        }

        @Test
        fun `multiple mul`() {
            underTest.computeInput("mul(2,2)mul(2,4)") shouldBe 12
        }

        @Test
        fun `given example`() {
            val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

            underTest.computeInput(input) shouldBe 161
        }

        @Test
        fun `part 1`() {
            val input = readPersonalInput(this)

            underTest.computeInput(input) shouldBe 155955228
        }
    }

    @Nested
    inner class RunProgramPart2 {

        @Test
        fun `enabled by default`() {
            val input = "mul(2,2)"
            underTest.runProgram(input) shouldBe 4
        }

        @Test
        fun `will add all muls`() {
            val input = "mul(2,2) mul(2,4)"
            underTest.runProgram(input) shouldBe 12
        }

        @Test
        fun `do leaves mul enabled`() {
            val input = "do() mul(2,2)"
            underTest.runProgram(input) shouldBe 4
        }

        @Test
        fun `dont disables mul`() {
            val input = "don't() mul(2,2)"
            underTest.runProgram(input) shouldBe 0
        }

        @Test
        fun `dont disables subsequent muls`() {
            val input = "don't() mul(2,2) mul(2,2)"
            underTest.runProgram(input) shouldBe 0
        }

        @Test
        fun `do re-enables after dont`() {
            val input = "don't() mul(2,2) do() mul(2,3)"
            underTest.runProgram(input) shouldBe 6
        }

        @Test
        fun `multiple dos`() {
            val input = "do() do() mul(2,3)"
            underTest.runProgram(input) shouldBe 6
        }

        @Test
        fun `multiple donts`() {
            val input = "mul(2,2) don't() don't() mul(2,3)"
            underTest.runProgram(input) shouldBe 4
        }

        @Test
        fun `only do`() {
            val input = "do()"
            underTest.runProgram(input) shouldBe 0
        }

        @Test
        fun `only dont`() {
            val input = "don't()"
            underTest.runProgram(input) shouldBe 0
        }

        @Test
        fun `given example`() {
            val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
            underTest.runProgram(input) shouldBe 48
        }

        @Test
        fun `part 2`() {
            val input = readPersonalInput(this)
            underTest.runProgram(input) shouldBe 100189366
        }
    }
}