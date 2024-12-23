package dev.coldhands.adventofcode.day5

import dev.coldhands.adventofcode.day5.Input.Update
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ParserTest {

    @Test
    fun `parse input file`() {
        val underTest = Parser()

        underTest.parse("""
            47|53
            97|13

            75,47,61,53,29
            75,29,13
        """.trimIndent()) shouldBe Input(
            pageOrderingRules = listOf(
                Input.PageOrderingRule(before = 47, after = 53),
                Input.PageOrderingRule(before = 97, after = 13),
            ),
            updates = listOf(
                Update(pages = listOf(75,47,61,53,29)),
                Update(pages = listOf(75,29,13)),
            )
        )
    }
}