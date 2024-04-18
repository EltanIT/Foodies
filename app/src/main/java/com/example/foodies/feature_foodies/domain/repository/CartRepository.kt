package com.example.foodies.feature_foodies.domain.repository

import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.model.ProductInCart
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun getAll(): Flow<List<ProductInCart>>

    suspend fun getItemById(id: Int): Flow<ProductInCart?>

    fun addProduct(product: Product)

    fun deleteProduct(id: Int)

    fun updateCount(id: Int, count: Int)

    suspend fun clearCard()
}