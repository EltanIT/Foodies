package com.example.foodies.CartTest

import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.model.ProductInCart
import com.example.foodies.feature_foodies.domain.model.toProductInCart
import com.example.foodies.feature_foodies.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CartTestRepositoryImpl: CartRepository {

    private val cart = ArrayList<ProductInCart>()

    override suspend fun getAll(): Flow<List<ProductInCart>> {
        return flow { emit(cart) }
    }

    override suspend fun getItemById(id: Int): Flow<ProductInCart?> {
        return flow { emit(cart.find { it.id == id }) }
    }

    override fun addProduct(product: Product) {
        cart.add(product.toProductInCart())
    }

    override fun deleteProduct(id: Int) {
        cart.removeIf { it.id == id }
    }

    override fun updateCount(id: Int, count: Int) {
        val findingItem = cart.find { it.id == id }
        findingItem?.copy(count = count)?.let {
            cart.set(cart.indexOf(findingItem), it)
        }
    }

    override suspend fun clearCard() {
        cart.clear()
    }
}