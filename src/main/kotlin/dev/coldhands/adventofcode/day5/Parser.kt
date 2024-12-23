package dev.coldhands.adventofcode.day5

class Parser {

    fun parse(inputString: String): Input {
        val (rulesString, updatesString) = inputString.split("\n\n")

        val rules = rulesString.lines()
            .map { line ->
                val (before, after) = line.split("|")
                Input.PageOrderingRule(before.toInt(), after.toInt())
            }.toList()

        val updates = updatesString.lines()
            .map { line -> Input.Update(line.split(",").map { it.toInt() }) }

        return Input(rules, updates)
    }
}