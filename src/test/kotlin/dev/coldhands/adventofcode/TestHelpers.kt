package dev.coldhands.adventofcode

fun readPersonalInput(testInstance: Any) : String {
    return testInstance::class.java.getResource("personalInput.txt")!!.readText()
}