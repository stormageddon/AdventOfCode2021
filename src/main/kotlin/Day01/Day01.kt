package Day01

import java.io.File

fun readInputAsInt(filename: String): List<Int> {
    val file = File(filename)
    val ints = mutableListOf<Int>()
    file.forEachLine {
        ints.add(it.toInt())
    }
    return ints
}

fun main() {
    println("Day 01")
    val readings = readInputAsInt("/Users/mike/code/personal/AdventOfCode2021/src/Day01.main/kotlin/Day01_input.txt")
    val (increases, decreases) = processWindowedReadings(readings)
    println(increases)
}

fun processReadings(readings: List<Int>): Pair<Int, Int> {
    var increases = 0
    var decreases = 0
    readings.windowed(2) {
        if (it[0] < it[1]) increases += 1

        if (it[0] > it[1]) decreases += 1
    }

    return Pair(increases, decreases)
}

fun processWindowedReadings(readings: List<Int>): Pair<Int, Int> {
    var increases = 0
    var decreases = 0
    readings.windowed(3).windowed(2) {
        if (it[0].sum() < it[1].sum()) increases += 1
    }

    return Pair(increases, decreases)
}
