import java.io.File
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

fun main() {

    val homePath = System.getProperty("user.dir")
    val resourcesPath = "/src/main/resources"
    val path = homePath + resourcesPath

//    Process().run("$path/a_example.txt", "$path/a_example.out")
//    Process().run("$path/b_read_on.txt", "$path/b_read_on.out")
//    Process().run("$path/c_incunabula.txt", "$path/c_incunabula.out")
//    Process().run("$path/d_tough_choices.txt", "$path/d_tough_choices.out")
    Process().run("$path/e_so_many_books.txt", "$path/e_so_many_books.out")
    //Process().run("$path/f_libraries_of_the_world.txt", "$path/f_libraries_of_the_world.out")

}

class Process {

    val bookScores = hashMapOf<Long, Long>()

    fun run(inFile:String, outFile:String) {

        var readIndex = 0
        // File(fileName).forEachLine { println(it) }
        val lines = File(inFile).readLines()

        val first = lines[readIndex]
        readIndex++

//    println(first)
        val (bookNumbers, librairyNumbers, dayNumbers) = first.split(" ")
                .map { it.toLong() }


        println("Number of books : $bookNumbers")
        println("Number of libraries : $librairyNumbers")
        println("Number of days : $dayNumbers")

        var line = lines[readIndex]
        readIndex++

//   / println(line)
        val scoreOfBooks = line.split(" ").map { it.toLong() }


//    println(scoreOfBooks)
        scoreOfBooks.forEachIndexed { index, score -> bookScores[index.toLong()] = score }

        println("score of Books : $bookScores")


        val libraries = hashMapOf<Long, Library>()
        // itere sur les librairies
        (0L until librairyNumbers).forEach { libraryNumber ->
            var line = lines[readIndex]
            readIndex++

            val (nbBooks, signup, nbBookPerDay) = line.split(" ").map { it.toLong() }

            val library = Library(libraryNumber, nbBooks, signup, nbBookPerDay)
            libraries[libraryNumber] = library

            // les livres de la librarie
            line = lines[readIndex]
            readIndex++
            var books = ArrayList<Long>(line.split(" ").map { it.toLong() })

            library.books = ArrayList(books.sortedBy{ b -> bookScores[b] }.reversed())
        }

        println("libraries :")
//    println(libraries)
        libraries.forEach {
            println(it)
        }


        val libsForABook = hashMapOf<Long, ArrayList<Library>>()
//        (0 until bookNumbers).forEach { bookIndex ->
//
//        }


        libraries.forEach { (index, lib) ->
            lib.books.forEach {book->
                libsForABook.getOrPut(book) { arrayListOf<Library>() }.add(lib)
            }
        }

        println("libs for books :")
        libsForABook.forEach { b ->
            println("${b.key} -> ${b.value}")
        }


        val submissionLibs = arrayListOf<Library>()


        var sumSignup = 0L
        while(sumSignup < dayNumbers && libraries.isNotEmpty()) {

            var scoreMax = Pair<Long, ArrayList<Long>>(Long.MIN_VALUE, arrayListOf())
            var libMax:Library = libraries.iterator().next().value

            libraries.forEach { (key, lib) ->
                val score = score(lib, sumSignup, dayNumbers)
                if(score.first >= scoreMax.first) {
                    scoreMax = score
                    libMax = lib
                }
            }
            submissionLibs.add(libMax)
            // on la retire des librairies
            libraries.remove(libMax.id)

            val toRemove = arrayListOf<Library>()
            scoreMax.second.forEach { book ->
                libsForABook[book]!!.forEach { lib ->
                    if(lib != libMax)
                        lib.books.remove(book)
                        if (lib.books.isEmpty()) {
                            toRemove.add(lib)
                        }
                }
            }

            toRemove.forEach { lib -> libraries.remove(lib.id) }
            sumSignup += libMax.signupProcess
        }

        println("submissionLibs")
        submissionLibs.forEach { it ->
            println(it)
        }



//        // TRAITEMENT PRINCIPAL
//        libraries.forEach{
//            val score = score(it)
//        }









        // SORTIE
        File(outFile).printWriter().use { out ->
            out.println(submissionLibs.size)

            submissionLibs.forEach { lib ->

                out.println("${lib.id} ${lib.books.size}")
                out.println("${lib.books.joinToString(" ")}")
            }
        }

    }

    fun score(library: Library, sumSignup: Long, maxDays: Long): Pair<Long, ArrayList<Long>> {
        var sum = 0L
        val finish = sumSignup + library.signupProcess;
        val remainingDays = max(maxDays - finish, 0)
        val maxNbBook = min(remainingDays * library.numberOfBooksPerDay, library.books.size.toLong());
        val selectedBooks = library.books.subList(0, maxNbBook.toInt())
        selectedBooks.forEach {
            sum += bookScores[it]!!
        }
        return Pair(sum / library.signupProcess, ArrayList(selectedBooks))
    }

}
