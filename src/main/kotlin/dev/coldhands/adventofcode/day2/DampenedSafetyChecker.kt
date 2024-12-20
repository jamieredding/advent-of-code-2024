package dev.coldhands.adventofcode.day2

class DampenedSafetyChecker : SafetyChecker {
    private val delegate = NaiveSafetyChecker()

    override fun isSafe(levels: List<Int>): Boolean {
        return delegate.isSafe(levels) ||
                levels.asSequence()
                    .filterIndexed { index, _ ->
                        delegate.isSafe(levels.dampen(index))
                    }.firstOrNull() != null
    }

    private fun List<Int>.dampen(index: Int): List<Int> {
        return this.toMutableList().also { it.removeAt(index) }
    }

}