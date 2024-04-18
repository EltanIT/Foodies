package com.example.foodies.feature_foodies.data.repository

import com.example.foodies.feature_foodies.data.data_sourse.CartDao
import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.model.ProductInCart
import com.example.foodies.feature_foodies.domain.model.toProductInCart
import com.example.foodies.feature_foodies.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val cardDao: CartDao
): CartRepository {

    override suspend fun getAll(): Flow<List<ProductInCart>> {
        return cardDao.getAll()
    }

    override suspend fun getItemById(id: Int): Flow<ProductInCart?> {
        return cardDao.getItemById(id)
    }

    override fun addProduct(product: Product) {
        cardDao.addProduct(product.toProductInCart())
    }

    override fun deleteProduct(id: Int) {
        cardDao.deleteProduct(id)
    }

    override fun updateCount(id: Int, count: Int) {
        cardDao.updateCount(id, count)
    }

    override suspend fun clearCard() {
        cardDao.clearCard()
    }
}