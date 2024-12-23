package dev.coldhands.adventofcode.day5

import dev.coldhands.adventofcode.day5.Input.PageOrderingRule
import dev.coldhands.adventofcode.day5.Input.Update
import dev.coldhands.adventofcode.readPersonalInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SolutionTest {

    val rules = listOf(
        PageOrderingRule(before = 47, after = 53),
        PageOrderingRule(before = 97, after = 13),
        PageOrderingRule(before = 97, after = 61),
        PageOrderingRule(before = 97, after = 47),
        PageOrderingRule(before = 75, after = 29),
        PageOrderingRule(before = 61, after = 13),
        PageOrderingRule(before = 75, after = 53),
        PageOrderingRule(before = 29, after = 13),
        PageOrderingRule(before = 97, after = 29),
        PageOrderingRule(before = 53, after = 29),
        PageOrderingRule(before = 61, after = 53),
        PageOrderingRule(before = 97, after = 53),
        PageOrderingRule(before = 61, after = 29),
        PageOrderingRule(before = 47, after = 13),
        PageOrderingRule(before = 75, after = 47),
        PageOrderingRule(before = 97, after = 75),
        PageOrderingRule(before = 47, after = 61),
        PageOrderingRule(before = 75, after = 61),
        PageOrderingRule(before = 47, after = 29),
        PageOrderingRule(before = 75, after = 13),
        PageOrderingRule(before = 53, after = 13)
    )

    val updates = listOf(
        Update(pages = listOf(75, 47, 61, 53, 29)),
        Update(pages = listOf(97, 61, 53, 29, 13)),
        Update(pages = listOf(75, 29, 13)),
        Update(pages = listOf(75, 97, 47, 61, 53)),
        Update(pages = listOf(61, 13, 29)),
        Update(pages = listOf(97, 13, 75, 29, 47)),
    )

    @Test
    fun `sort update using rules 1`() {
        val unsorted = Update(listOf(29, 47, 53, 61, 75))

        sort(unsorted, rules) shouldBe Update(listOf(75, 47, 61, 53, 29))
    }

    @Test
    fun `sort update using rules 2`() {
        val unsorted = Update(listOf(13, 29, 53, 61, 97))

        sort(unsorted, rules) shouldBe Update(listOf(97, 61, 53, 29, 13))
    }

    @Test
    fun `filters only ordered updates`() {
        getOrderedUpdates(rules, updates) shouldBe listOf(
            Update(pages = listOf(75, 47, 61, 53, 29)),
            Update(pages = listOf(97, 61, 53, 29, 13)),
            Update(pages = listOf(75, 29, 13)),
        )
    }

    @Test
    fun `filters only unordered updates`() {
        getUnorderedUpdates(rules, updates) shouldBe listOf(
            Update(pages = listOf(75, 97, 47, 61, 53)),
            Update(pages = listOf(61, 13, 29)),
            Update(pages = listOf(97, 13, 75, 29, 47)),
        )
    }

    @Test
    fun `sum middle page numbers`() {
        sumMiddlePageNumbers(listOf(
            Update(pages = listOf(75, 47, 61, 53, 29)),
            Update(pages = listOf(97, 61, 53, 29, 13)),
            Update(pages = listOf(75, 29, 13)),
        )) shouldBe 143
    }

    @Test
    fun `part 1` () {
        val inputString = readPersonalInput(this)

        val input = Parser().parse(inputString)

        val result = getOrderedUpdates(input.pageOrderingRules, input.updates)
            .run { sumMiddlePageNumbers(this) }

        result shouldBe 6267
    }

    @Test
    fun `part 2` () {
        val inputString = readPersonalInput(this)

        val input = Parser().parse(inputString)

        val result = getUnorderedUpdates(input.pageOrderingRules, input.updates)
            .map { update -> sort(update, input.pageOrderingRules) }
            .run { sumMiddlePageNumbers(this) }

        result shouldBe 5184
    }
}