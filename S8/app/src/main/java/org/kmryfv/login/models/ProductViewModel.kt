package org.kmryfv.login.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products : StateFlow<List<Product>> = _products

    fun addProduct(id : Int, name : String, price : Double, quantity : Int) {
        val newProduct = Product(
            id = id,
            name = name,
            price = price,
            quantity = quantity
        )
        _products.value += newProduct
    }

    fun showProducts() : List<Product> {
        return _products.value
    }

    fun listSize(): Int {
        return _products.value.size
    }
}