package com.example.foodies.feature_foodies.data.remote

import com.example.foodies.feature_foodies.data.remote.dto.ProductDto
import retrofit2.http.GET

interface ProductsApi {

    @GET("Products.json")
    suspend fun getProducts(): List<ProductDto>
}