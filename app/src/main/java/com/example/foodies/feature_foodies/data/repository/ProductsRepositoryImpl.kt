package com.example.foodies.feature_foodies.data.repository

import com.example.foodies.feature_foodies.data.remote.ProductsApi
import com.example.foodies.feature_foodies.data.remote.dto.ProductDto
import com.example.foodies.feature_foodies.domain.repository.ProductsRepository
import com.example.foodies.feature_foodies.domain.util.ProductOrderType

class ProductsRepositoryImpl(
    private val productsApi: ProductsApi
): ProductsRepository {

    override suspend fun getProducts(): List<ProductDto> {
        return productsApi.getProducts()
    }

    override suspend fun getProductById(id: Int): ProductDto {
        return productsApi.getProducts().find {
            it.id == id
        }?: ProductDto()
    }
}