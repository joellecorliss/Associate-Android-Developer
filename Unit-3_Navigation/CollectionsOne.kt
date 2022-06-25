fun main() {
    // Collection 1: List
    val numbers = listOf(0, 3, 8, 4, 0, 5, 5, 8, 9, 2)
    println("list: ${numbers}")
    println("list: ${numbers.sorted()}")
    
    //Collection 2: Set (will exclude duplicate numbers)
    val setOfNumbers = numbers.toSet()
    println("set: ${setOfNumbers}")
    val setOfNumbers2 = setOf(1,2,3,4,5,5)
    println("set2: ${setOfNumbers2}")
    
    //Collection 3: MutableSet
    val set1 = setOf(1,2,3)
    val set2 = mutableSetOf(3,2,1)
    println("$set1 == $set2: ${set1 == set2}")
    
    println("contains 7: ${setOfNumbers.contains(7)}")
    
    //Collection 4: Map/Dictionary
    val peopleAges = mutableMapOf<String, Int>(
    	"Fred" to 30,
        "Ann" to 25
    )
    println(peopleAges)
    peopleAges.put("Barbara", 23)
    peopleAges["Bob"] = 52
    peopleAges["Fred"] = 33
    println(peopleAges)
    
    //forEach: similar to for loop but more compact
    //use the special identifier it as each item
    peopleAges.forEach{println("${it.key} is ${it.value} years old, ")}
    
    //but there is a problem where the last item still has a comma
    //map: to print all in one line as well
    println(peopleAges.map{"${it.key} is ${it.value} years old"}.joinToString(", "))
    
    //filter: returns items in a collection that match, based on an expression
    //the returned type of the filtered map is a LinkedHashMap
    val filteredNames = peopleAges.filter{ it.key.length < 4 }
    println(filteredNames)
    
    //Lambda expressions or Lambdas: just functions with no name, just curly brackets
    //forEach, map, filter, sortedWith are higher-order functions
    val triple: (Int) -> Int = { a: Int -> a*3 }
    println(triple(3))
    val peopleNames = listOf("Fred", "Ann", "Barbara", "Joe")
    println(peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length })
}