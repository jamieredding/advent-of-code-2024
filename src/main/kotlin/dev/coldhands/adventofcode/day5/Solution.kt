package dev.coldhands.adventofcode.day5

import dev.coldhands.adventofcode.day5.Input.*
import java.util.TreeSet

fun sort(update: Update, rules: List<PageOrderingRule>): Update {
    val sorted: TreeSet<Int> = sortedSetOf(comparator(rules), *update.pages.toTypedArray())
    return Update(sorted.toList())
}

fun comparator(rules: List<PageOrderingRule>): Comparator<Int> {
    return object : Comparator<Int> {
        override fun compare(o1: Int?, o2: Int?): Int {
            return rules
                .filter { it.before == o1 || it.after == o1 }
                .filter { it.before == o2 || it.after == o2 }
                .map { if (it.before == o1) -1 else 1 }
                .first()
        }
    }
}

fun getOrderedUpdates(rules: List<PageOrderingRule>, updates: List<Update>): List<Update> {
    return updates.filter { sort(it, rules) == it }
}

fun getUnorderedUpdates(rules: List<PageOrderingRule>, updates: List<Update>): List<Update> {
    return updates.filter { sort(it, rules) != it }
}

fun sumMiddlePageNumbers(updates: List<Update>): Int {
    return updates.sumOf {
        val size = it.pages.size
        assert(size % 2 == 1)
        it.pages[size / 2] // divide by 2 (don't need to add 1 as its zero indexed)
    }
}