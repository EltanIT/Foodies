package com.example.foodies.feature_foodies.data.repository

import com.example.foodies.feature_foodies.data.remote.CategoriesApi
import com.example.foodies.feature_foodies.data.remote.dto.CategoryDto
import com.example.foodies.feature_foodies.domain.repository.CategoriesRepository

class CategoriesRepositoryImpl(
    private val api: CategoriesApi
): CategoriesRepository {

    override suspend fun getCategories(): List<CategoryDto> {
        return api.getCategories()
    }
}