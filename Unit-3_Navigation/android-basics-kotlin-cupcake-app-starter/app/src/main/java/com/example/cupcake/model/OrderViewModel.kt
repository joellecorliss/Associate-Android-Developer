package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    private val _orderQty = MutableLiveData<Int>(0)
    private val _cupcakeFlavour = MutableLiveData<String>("")
    private val _pickUpDate = MutableLiveData<String>("")
    private val _totalPrice = MutableLiveData<Double>(0.0)

    val orderQty: LiveData<Int> = _orderQty
    val cupcakeFlavour: LiveData<String> = _cupcakeFlavour
    val dateOptions = getPickUpOptions()
    val pickUpDate: LiveData<String> = _pickUpDate
    val totalPrice: LiveData<String> = Transformations.map(_totalPrice) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        resetOrder()
    }

    fun setQuantity(noOfCupcakes: Int) {
        _orderQty.value = noOfCupcakes
        updatePrice()
    }

    fun setFlavour(desiredFlavour: String) {
        _cupcakeFlavour.value = desiredFlavour
    }

    fun hasNoFlavourSet(): Boolean {
        return _cupcakeFlavour.value.isNullOrEmpty()
    }

    fun setDate(pickUpDate: String) {
        _pickUpDate.value = pickUpDate
        updatePrice()
    }

    private fun getPickUpOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }

        return options
    }

    private fun updatePrice() {
        var updatedPrice = (_orderQty.value ?: 0) * PRICE_PER_CUPCAKE
        if (dateOptions[0] == _pickUpDate.value) {
            updatedPrice += SAME_DAY_PICKUP
        }
            _totalPrice.value = updatedPrice
    }

    fun resetOrder() {
        _orderQty.value = 0
        _cupcakeFlavour.value = ""
        _pickUpDate.value = dateOptions[0]
        _totalPrice.value = 0.0
    }

}