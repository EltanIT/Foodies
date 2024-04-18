package com.example.foodies.feature_foodies.data.remote

import com.example.foodies.feature_foodies.data.remote.dto.CategoryDto
import retrofit2.http.GET

interface CategoriesApi {

    @GET("Categories.json")
    suspend fun getCategories(): List<CategoryDto>
}