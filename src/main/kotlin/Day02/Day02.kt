package Day02

import readInput

fun main() {
    println("Day 02!")
    val directions = readInput("Day02/Day02_test_input.txt")
    val (hzPos, depth) = processDirections(directions)
    println("Horizontal position: $hzPos, Depth: $depth")
    println("hzPos $hzPos * depth $depth == 150 is ${hzPos * depth == 150}")

}

data class Operation(val command: String, val value: Int)

fun processDirections(directions: List<String>): Pair<Int, Int> {
    var horizontal = 0
    var depth = 0

    val operations = directions.map { it.split(' ') }.map { Operation(it[0], it[1].toInt())}

    operations.forEach {
        when (it.command) {
          "forward" -> horizontal += it.value
          "down" -> depth += it.value
          "up" -> depth -= it.value
        }
    }

    return Pair(horizontal, depth)
}

fun processDirectionsWithAim(directions: List<String>): Pair<Int, Int> {
    var horizontal = 0
    var depth = 0
    var aim = 0

    val operations = directions.map { it.split(' ') }.map { Operation(it[0], it[1].toInt())}

    operations.forEach {
        when(it.command) {
            "forward" -> {
                horizontal += it.value
                depth += it.value * aim
            }
            "down" -> aim += it.value
            "up" -> aim -= it.value
        }
    }

    return Pair(horizontal, depth)
}
