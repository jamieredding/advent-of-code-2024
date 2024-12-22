package dev.coldhands.adventofcode.day4

import dev.coldhands.adventofcode.day4.Direction.*
import dev.coldhands.adventofcode.day4.Solution.Point
import dev.coldhands.adventofcode.day4.Solution.PointRange
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.text.lines

class Solution {

    private val regex = "XMAS|SAMX".toRegex()

    data class Point(val x: Int, val y: Int)

    data class PointRange(val direction: Direction, val start: Point, val end: Point) : Iterable<Point> {
        override fun iterator(): Iterator<Point> = object : Iterator<Point> {
            private var current = start
            private val target = end

            override fun hasNext(): Boolean {
                return when (direction) {
                    HORIZONTAL, VERTICAL, DIAGONAL_TOP_LEFT -> current.x <= target.x && current.y <= target.y
                    DIAGONAL_TOP_RIGHT -> current.x >= target.x && current.y <= target.y
                }
            }

            override fun next(): Point {
                val next = current
                current = direction.next(current)
                return next
            }
        }
    }

    fun xmasOccurrences(line: String): Int {
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
        return count
    }

    /*

    ..X...
    .SAMX.
    .A..A.
    XMAS.S
    .X....
     */

    fun countOfXmasInWordSearch(input: String): Int {
        val lines = input.lines()
        val xMax: Int = lines.first().length - 1
        val yMax: Int = lines.size - 1
        val lengthEitherSideInclusive = "XMAS".length

        var total = 0
        for (x in 0..xMax) {
            for (y in 0..yMax) {
                val pointToCheck = Point(x, y)
                val characterAtPoint = lines[pointToCheck.y][pointToCheck.x]
                if (characterAtPoint == 'X') {
                    total += input.lookAroundAt(pointToCheck, lengthEitherSideInclusive)
                        .sumOf { line -> xmasOccurrences(line) }
                }
            }
        }
        return total
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

}

enum class Direction { HORIZONTAL, VERTICAL, DIAGONAL_TOP_LEFT, DIAGONAL_TOP_RIGHT }

fun String.directionalSubstring(direction: Direction, centrePoint: Point, lengthEitherSideInclusive: Int): String {
    val lines = lines()
    val xMax: Int = lines.first().length - 1
    val yMax: Int = lines.size - 1

    val pointRange = when (direction) {
        HORIZONTAL -> {
            val startingIndex = max(0, centrePoint.x - (lengthEitherSideInclusive - 1))
            val endingIndex = min(xMax, centrePoint.x + (lengthEitherSideInclusive - 1))

            val startingPoint = Point(startingIndex, centrePoint.y)
            val endingPoint = Point(endingIndex, centrePoint.y)

            PointRange(HORIZONTAL, startingPoint, endingPoint)
        }

        VERTICAL -> {
            val startingIndex = max(0, centrePoint.y - (lengthEitherSideInclusive - 1))
            val endingIndex = min(yMax, centrePoint.y + (lengthEitherSideInclusive - 1))

            val startingPoint = Point(centrePoint.x, startingIndex)
            val endingPoint = Point(centrePoint.x, endingIndex)

            PointRange(VERTICAL, startingPoint, endingPoint)
        }

        DIAGONAL_TOP_LEFT -> {
            val tryMovingXToStart = max(0, centrePoint.x - (lengthEitherSideInclusive - 1))
            val tryMovingYToStart = max(0, centrePoint.y - (lengthEitherSideInclusive - 1))
            val validStepsToStart = min(abs(tryMovingXToStart - centrePoint.x), abs(tryMovingYToStart - centrePoint.y))

            val startingIndexX = centrePoint.x - validStepsToStart
            val startingIndexY = centrePoint.y - validStepsToStart

            val tryMovingXToEnd = min(xMax, centrePoint.x + (lengthEitherSideInclusive - 1))
            val tryMovingYToEnd = min(yMax, centrePoint.y + (lengthEitherSideInclusive - 1))
            val validStepsToEnd = min(abs(tryMovingXToEnd - centrePoint.x), abs(tryMovingYToEnd - centrePoint.y))

            val endingIndexX = centrePoint.x + validStepsToEnd
            val endingIndexY = centrePoint.y + validStepsToEnd

            val startingPoint = Point(startingIndexX, startingIndexY)
            val endingPoint = Point(endingIndexX, endingIndexY)

            PointRange(DIAGONAL_TOP_LEFT, startingPoint, endingPoint)
        }

        DIAGONAL_TOP_RIGHT -> {
            val tryMovingXToStart = min(xMax, centrePoint.x + (lengthEitherSideInclusive - 1))
            val tryMovingYToStart = max(0, centrePoint.y - (lengthEitherSideInclusive - 1))
            val validStepsToStart = min(abs(tryMovingXToStart - centrePoint.x), abs(tryMovingYToStart - centrePoint.y))

            val startingIndexX = centrePoint.x + validStepsToStart
            val startingIndexY = centrePoint.y - validStepsToStart


            val tryMovingXToEnd = max(0, centrePoint.x - (lengthEitherSideInclusive - 1))
            val tryMovingYToEnd = min(yMax, centrePoint.y + (lengthEitherSideInclusive - 1))
            val validStepsToEnd = min(abs(tryMovingXToEnd - centrePoint.x), abs(tryMovingYToEnd - centrePoint.y))

            val endingIndexX = centrePoint.x - validStepsToEnd
            val endingIndexY = centrePoint.y + validStepsToEnd

            val startingPoint = Point(startingIndexX, startingIndexY)
            val endingPoint = Point(endingIndexX, endingIndexY)

            PointRange(DIAGONAL_TOP_RIGHT, startingPoint, endingPoint)
        }
    }

    val stringBuilder = StringBuilder()
    for (point in pointRange) {
        stringBuilder.append(lines[point.y][point.x])
    }
    return stringBuilder.toString()
}

fun Direction.next(point: Point): Point {
    return when (this) {
        HORIZONTAL -> point.copy(x = point.x + 1)
        VERTICAL -> point.copy(y = point.y + 1)
        DIAGONAL_TOP_LEFT -> Point(x = point.x + 1, y = point.y + 1)
        DIAGONAL_TOP_RIGHT -> Point(x = point.x - 1, y = point.y + 1)
    }
}

fun String.lookAroundAt(centrePoint: Point, lengthEitherSideInclusive: Int): List<String> {
    return Direction.entries
        .map { direction ->
            this@lookAroundAt.directionalSubstring(
                direction,
                centrePoint,
                lengthEitherSideInclusive
            )
        }
        .toList()
}
