package dev.coldhands.adventofcode.day1

import kotlin.math.abs

class Solution {

    fun solvePart1(input: String): Int {

        return input
            .lineSequence()
            .filterNot { it.isBlank() }
            .map { line -> line.split("   ") }
            .map { items -> items[0].toInt() to items[1].toInt() }
            .fold(
                initial = LocationLists(mutableListOf(), mutableListOf()),
                operation = { acc: LocationLists, element: Pair<Int, Int> ->
                    acc.first.add(element.first)
                    acc.second.add(element.second)
                    acc
                }
            )
            .run {
                first.sort()
                second.sort()

                first.zip(second)
                    .sumOf { (first, second) -> abs(first - second) }
            }
    }

    data class LocationLists(val first: MutableList<Int>, val second: MutableList<Int>)

}