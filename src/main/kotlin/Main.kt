import java.io.File

fun main() {


    val homePath = System.getProperty("user.dir")
    val resourcesPath = "/src/main/resources"
    val path = homePath + resourcesPath


    run("$path/file.in", "$path/file.out")

}

fun run(inFile:String, outFile:String) {

    var readIndex = 0
    // File(fileName).forEachLine { println(it) }
    val lines = File(inFile).readLines()

    val first = lines[readIndex]
    readIndex++

    println(first)

    // traitement



    // SORTIE
    File(outFile).printWriter().use { out ->
        out.println("OUT")
    }

}