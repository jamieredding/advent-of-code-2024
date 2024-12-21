package dev.coldhands.adventofcode.day4

import dev.coldhands.adventofcode.day4.Solution.Direction.*
import kotlin.math.max
import kotlin.math.min

class Solution {

    private val regex = "XMAS|SAMX".toRegex()

    fun countXmasInLines(lines: List<String>): Int {
        return lines.sumOf { line ->
            var count = 0
            var index = 0

            while (index < line.length) {
                if (index + 4 > line.length) {
                    break
                }
                if (line.substring(index, index + 4).matches(regex)) {
                    count++
                    index += 3
                } else {
                    index++
                }
            }
            count
        }
    }

    fun viewLines(direction: Direction, input: String): List<String> {
        return when (direction) {
            HORIZONTAL -> input.lines()
            VERTICAL -> input.verticalLines()
            DIAGONAL_TOP_LEFT -> input.diagonalTopLeftLines()
            DIAGONAL_TOP_RIGHT -> input.diagonalTopRightLines()
        }
    }

    fun countOfXmasInWordSearch(input: String): Int {
        return Direction.entries.asSequence()
            .map {
                viewLines(it, input)
            }
            .sumOf { lines -> countXmasInLines(lines) }
    }

    private fun String.verticalLines(): List<String> {
        val lines = mutableListOf<String>()

        val horizontalLines = lines()
        val xAxis = horizontalLines.first().length
        val yAxis = horizontalLines.size
        for (x in 0 until xAxis) {
            val stringBuilder = StringBuilder()
            for (y in 0 until yAxis) {
                val char = horizontalLines[y][x]
                stringBuilder.append(char)
            }
            lines.add(stringBuilder.toString())
        }
        return lines.toList()
    }

    private fun String.diagonalTopLeftLines(): List<String> {
        val lines = mutableListOf<String>()

        val horizontalLines = lines()
        val xAxis = horizontalLines.first().length
        val yAxis = horizontalLines.size

        var x = 0
        var y = 0
        var farthestX = 0
        var farthestY = 0
        val stringBuilder = StringBuilder()
        while (x < xAxis - 1 || y < yAxis) {
            val char = horizontalLines[y][x]
            stringBuilder.append(char)
            if (y < yAxis - 1 && x != 0) {
                y++
                x--
            } else {
                y = if (farthestX == xAxis - 1) {
                    farthestY++
                    farthestY
                } else {
                    0
                }
                x = min(farthestX + 1, xAxis - 1)
                farthestX = x
                lines.add(stringBuilder.toString())
                stringBuilder.clear()
            }
        }

        return lines.toList()
    }

    private fun String.diagonalTopRightLines(): List<String> {
        val lines = mutableListOf<String>()

        val horizontalLines = lines()
        val xAxis = horizontalLines.first().length
        val yAxis = horizontalLines.size

        var x = xAxis - 1
        var y = 0
        var farthestX = xAxis - 1
        var farthestY = 0
        val stringBuilder = StringBuilder()
        while (x > 0 || y < yAxis) {
            val char = horizontalLines[y][x]
            stringBuilder.append(char)
            if (y < yAxis - 1 && x != xAxis - 1) {
                y++
                x++
            } else {
                y = if (farthestX == 0) {
                    farthestY++
                    farthestY
                } else {
                    0
                }
                x = max(farthestX - 1, 0)
                farthestX = x
                lines.add(stringBuilder.toString())
                stringBuilder.clear()
            }
        }

        return lines.toList()
    }

    enum class Direction { HORIZONTAL, VERTICAL, DIAGONAL_TOP_LEFT, DIAGONAL_TOP_RIGHT }
}