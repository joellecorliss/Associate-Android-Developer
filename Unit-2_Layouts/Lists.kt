class Order(val orderNumber: Int) {  
    private var orderItems = mutableListOf<Item>()
    
    fun addItem(newItem: Item) {
        orderItems.add(newItem)
    }
    
    fun addAll(newItems: List<Item>) {
        orderItems.addAll(newItems)
    }
    
    fun printOrder() {
        println("Order #${orderNumber}")
        println("====================")
        var totalPrice: Double = 0.00
        for (item in orderItems) {
            println("${item.name}: $${item.price}")
            totalPrice += item.price
        }
        println("====================")
        println("Total: $${totalPrice}\n\n")
        
    }
}

open class Item(val name: String, val price: Int) 

class Noodles : Item("Noodles", 10)

class Vegetables(vararg val toppings: String) : Item("Vegetables", 5) {
    override fun toString(): String {
        if (toppings.isEmpty()) {
            return "${name} Chef's Choice"
        } else {
            return name + " " + toppings.joinToString()
        }
    }
}

fun main() {
    val ordersList = mutableListOf<Order>()
    val orderOne = Order(1)
    val veggies = Vegetables("Sprouts", "Carrots", "Onions")
    val items = listOf(Noodles(), veggies)
    orderOne.addAll(items)
    //orderOne.printOrder()
    val orderTwo = Order(2)
    orderTwo.addItem(veggies)
    //orderTwo.printOrder()
    ordersList.addAll(listOf(orderOne, orderTwo))

    for (order in ordersList) {
        order.printOrder()
    }

}