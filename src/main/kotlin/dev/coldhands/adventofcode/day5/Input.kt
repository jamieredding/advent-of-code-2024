package dev.coldhands.adventofcode.day5

data class Input(
    val pageOrderingRules: List<PageOrderingRule>,
    val updates: List<Update>
) {
    data class PageOrderingRule(val before: Int, val after: Int)
    data class Update(val pages: List<Int>)
}