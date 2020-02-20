data class Library(val id:Int,
                   val numberOfBooks:Int,
                   val signupProcess:Int,  // en nombre de jours
                    val numberOfBooksPerDay:Int // nombre de jours scanné par jours
                   ) {

    var books = arrayListOf<Int>()

    override fun toString(): String {
        return "{id : $id, numberOfBook : $numberOfBooks, signupProcess: $signupProcess, numberOfBooksPerDay: $numberOfBooksPerDay, books: $books}"
    }
}