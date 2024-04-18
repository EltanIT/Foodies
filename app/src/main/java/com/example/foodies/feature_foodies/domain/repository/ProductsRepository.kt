package com.example.foodies.feature_foodies.domain.repository

import com.example.foodies.feature_foodies.data.remote.dto.ProductDto
import com.example.foodies.feature_foodies.domain.util.ProductOrderType

interface ProductsRepository {
    suspend fun getProducts(): List<ProductDto>
    suspend fun getProductById(id: Int): ProductDto
}