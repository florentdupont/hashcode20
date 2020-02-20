data class Library(val numberOfBooks:Int,
                   val signupProcess:Int,  // en nombre de jours
                    val numberOfBooksPerDay:Int // nombre de jours scanné par jours
                   ) {

    var books = listOf<Int>()

    override fun toString(): String {
        return "{numberOfBook : $numberOfBooks, signupProcess: $signupProcess, numberOfBooksPerDay: $numberOfBooksPerDay, books: $books}"
    }
}