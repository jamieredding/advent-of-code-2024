package dev.coldhands.adventofcode.day1

import kotlin.math.abs

class Solution {

    fun solvePart1(input: String): Int {
        return parseInput(input)
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

    fun solvePart2(input: String): Int {
        return parseInput(input)
            .fold(
                initial = SimilarityComponents(mutableListOf(), mutableMapOf()),
                operation = { acc: SimilarityComponents, element: Pair<Int, Int> ->
                    acc.locationIds.add(element.first)
                    acc.locationOccurrences.merge(element.second, 1) { oldValue, seed ->
                        oldValue + seed
                    }
                    acc
                }
            )
            .run {
                locationIds.sumOf { locationOccurrences[it]?.times(it) ?: 0 }
            }
    }

    private fun parseInput(input: String): Sequence<Pair<Int, Int>> = input
        .lineSequence()
        .filterNot { it.isBlank() }
        .map { line -> line.split("   ") }
        .map { items -> items[0].toInt() to items[1].toInt() }

    data class LocationLists(val first: MutableList<Int>, val second: MutableList<Int>)

    data class SimilarityComponents(val locationIds: MutableList<Int>, val locationOccurrences: MutableMap<Int, Int>)

}