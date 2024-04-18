package com.example.foodies.feature_foodies.data.remote.dto

import com.example.foodies.feature_foodies.domain.model.Category


data class CategoryDto(
    val id: Int? = null,
    val name: String = "",
)

fun CategoryDto.toCategory(): Category {
    return Category(
        id,
        name
    )
}