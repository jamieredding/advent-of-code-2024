package dev.coldhands.adventofcode.day3

class Solution {

    private val regex: Regex = """mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""".toRegex()

    fun parseInput(input: String): List<Operation> {
        return regex.findAll(input)
            .map {
                when {
                    it.value.startsWith("mul") -> parseMul(it)
                    it.value.startsWith("do()") -> parseDo()
                    it.value.startsWith("don't()") -> parseDont()
                    else -> throw IllegalArgumentException("Unknown operation")
                }
            }
            .toList()
    }

    private fun parseMul(input: MatchResult): Mul = input.groupValues.drop(1).let { args ->
        Mul(args[0].toInt(), args[1].toInt())
    }

    private fun parseDo(): Do = Do

    private fun parseDont(): Dont = Dont

    fun computeInput(input: String): Int {
        return parseInput(input).asSequence()
            .filter { it is Mul }
            .map { it as Mul }
            .map { mul -> mul.first * mul.second }
            .sum()
    }

    fun runProgram(input: String): Int {
        val program = parseInput(input)

        var internalState = InternalState(
            enabled = true,
            total = 0
        )

        program.forEach {
            internalState = it.process(internalState)
        }
        return internalState.total
    }

    sealed interface Operation {
        fun process(internalState: InternalState): InternalState
    }

    data class Mul(val first: Int, val second: Int) : Operation {
        override fun process(internalState: InternalState): InternalState {
            if (internalState.enabled) {
                val oldTotal = internalState.total
                val result = first * second
                return internalState.copy(total = oldTotal + result)
            }
            return internalState
        }
    }

    data object Do : Operation {
        override fun process(internalState: InternalState): InternalState {
            return internalState.copy(enabled = true)
        }
    }

    data object Dont : Operation {
        override fun process(internalState: InternalState): InternalState {
            return internalState.copy(enabled = false)
        }
    }

    data class InternalState(
        val enabled: Boolean,
        val total: Int
    )
}