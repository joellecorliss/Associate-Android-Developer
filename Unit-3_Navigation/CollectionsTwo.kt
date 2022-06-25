fun main() {
   	val words = listOf("about", "acute", "awesome", "balloon", "best", "brief", "class", "coffee", "creative")
    
    //to make a copy of a collection with the items randomly shuffled
    val filteredWords = words.filter{ it.startsWith("b", ignoreCase=true) }.shuffled()
    println(filteredWords)
    
    //to take the first 2 shuffled words
    val filteredWords2 = words.filter{ it.startsWith("b", ignoreCase=true) }.shuffled().take(2)
    println(filteredWords2)
}