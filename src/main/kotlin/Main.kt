import java.io.File
import kotlin.math.sign

fun main() {

    val homePath = System.getProperty("user.dir")
    val resourcesPath = "/src/main/resources"
    val path = homePath + resourcesPath


   // run("$path/a_example.txt", "$path/a_example.out")
    run("$path/b_read_on.txt", "$path/b_read_on.out")

}

fun run(inFile:String, outFile:String) {

    var readIndex = 0
    // File(fileName).forEachLine { println(it) }
    val lines = File(inFile).readLines()

    val first = lines[readIndex]
    readIndex++

    println(first)
    val (bookNumbers, librairyNumbers, dayNumbers) = first.split(" ")
                                                                         .map { Integer.parseInt(it) }


    println("Number of books : $bookNumbers")
    println("Number of libraries : $librairyNumbers")
    println("Number of days : $dayNumbers")

    var line = lines[readIndex]
    readIndex++

    println(line)
    val scoreOfBooks = line.split(" ").map { Integer.parseInt(it) }

    val bookScores = hashMapOf<Int, Int>()
    println(scoreOfBooks)
    scoreOfBooks.forEachIndexed { index, score -> bookScores[index] = score }

    println(bookScores)


    val libraries = hashMapOf<Int, Library>()
    // itere sur les librairies
    (0 until librairyNumbers).forEach { libraryNumber ->
        var line = lines[readIndex]
        readIndex++

        val (nbBooks, signup, nbBookPerDay) = line.split(" ").map { Integer.parseInt(it) }

        val library = Library(nbBooks, signup, nbBookPerDay)
        libraries[libraryNumber] = library

        // les livres de la librarie
        line = lines[readIndex]
        readIndex++
        val books = line.split(" ").map { Integer.parseInt(it) }

        library.books = books
    }

    println(libraries)

    
    // traitement
    







    // SORTIE
    File(outFile).printWriter().use { out ->
        out.println("OUT")
    }

}