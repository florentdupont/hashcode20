data class Library(val id:Long,
                   val numberOfBooks:Long,
                   val signupProcess:Long,  // en nombre de jours
                    val numberOfBooksPerDay:Long // nombre de jours scanné par jours
                   ) {

    var books = arrayListOf<Int>()

    override fun toString(): String {
        return "{id : $id, numberOfBook : $numberOfBooks, signupProcess: $signupProcess, numberOfBooksPerDay: $numberOfBooksPerDay, books: $books}"
    }
}