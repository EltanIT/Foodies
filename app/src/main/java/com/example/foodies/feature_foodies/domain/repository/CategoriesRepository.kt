package com.example.foodies.feature_foodies.domain.repository

import com.example.foodies.feature_foodies.data.remote.dto.CategoryDto

interface CategoriesRepository {

    suspend fun getCategories(): List<CategoryDto>
}