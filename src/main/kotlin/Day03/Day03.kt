package Day03

import readInput

fun main() {
    println("Day 03")
    val diagnostics = readInput("Day03/Day03_test_input.txt")
    processDiagnostics(diagnostics)
    verifyLifeSupport(diagnostics)
}

fun getBitsAtIndexByCriteria(index: Int, entries: List<String>, bitCriteria: (Int, Int) -> Int): Int {
    var zeroes = 0
    var ones = 0

    for(entry in entries) {
        if (entry[index] == '0') zeroes++ else ones++
    }

    return bitCriteria(zeroes, ones)
}

fun verifyLifeSupport(diagnostics: List<String>) {
    println("Verify Life Support")
    val oxygen = calculateOxygen(diagnostics)
    val co2 = calculateCO2(diagnostics)

    println(oxygen.toInt(2) * co2.toInt(2))
}

fun filterTo(diagnostics: List<String>, calculation: (Int, Int) -> Int): String {
    var remaining = diagnostics
    var index = 0
    do {
        remaining =  remaining.filter {
            getBitsAtIndexByCriteria(index, remaining, calculation) == it[index].toString().toInt()
        }
        index++
    } while(remaining.size > 1)
    println("Oxygen: $remaining")
    return remaining[0]
}

fun calculateOxygen(diagnostics: List<String>): String {
    val oxygen = filterTo(diagnostics, mostCommon)
    println("Oxygen: $oxygen")
    return oxygen
}

fun calculateCO2(diagnostics: List<String>): String {
    val co2 = filterTo(diagnostics, leastCommon)
    println("CO2: $co2")
    return co2
}

fun processDiagnostics(diagnostics: List<String>) {
    val gamma = calculateBinaryFromDiagnostics(diagnostics, mostCommon)
    val epsilon = calculateBinaryFromDiagnostics(diagnostics, leastCommon)
    println("Power consumption:")
    println(epsilon * gamma)
}

val leastCommon = { zeroes: Int, ones: Int ->
    if (zeroes > ones) 1 else 0
}

val mostCommon = { zeroes: Int, ones: Int ->
    if (zeroes > ones) 0 else 1
}

fun calculateBinaryFromDiagnostics(diagnostics: List<String>, calculation: (Int, Int) -> Int): Int {
    val sb = StringBuilder()
    (diagnostics[0].indices).forEach {
        sb.append(getBitsAtIndexByCriteria(it, diagnostics, calculation) )
    }
    val binaryStr = sb.toString()
    return binaryStr.toInt(2)
}

