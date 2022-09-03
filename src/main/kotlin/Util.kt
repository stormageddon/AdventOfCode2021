import java.io.File

fun readInput(filename: String): List<String> {
    return File("src/main/kotlin", filename).readLines()
}