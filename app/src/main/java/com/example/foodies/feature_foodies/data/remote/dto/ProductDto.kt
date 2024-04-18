package com.example.foodies.feature_foodies.data.remote.dto

import com.example.foodies.feature_foodies.domain.model.Product


data class ProductDto(
    val id: Int? = null,
    val category_id: Int? = null,
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val price_current: Int = 0,
    val price_old: Int? = null,
    val measure: Int = 0,
    val measure_unit: String = "",
    val energy_per_100_grams: Float = 0f,
    val proteins_per_100_grams: Float = 0f,
    val fats_per_100_grams: Float = 0f,
    val carbohydrates_per_100_grams: Float = 0f,
    val tag_ids: List<Int> = emptyList(),
)

    fun ProductDto.toProduct(): Product {
        return Product(
            id,
            category_id,
            name,
            description,
            image,
            price_current,
            price_old,
            measure,
            measure_unit,
            energy_per_100_grams,
            proteins_per_100_grams,
            fats_per_100_grams,
            carbohydrates_per_100_grams,
            tag_ids
        )
    }
