package com.example.foodies.feature_foodies.domain.util

sealed class ProductOrderType {
    data class Search(val value: String): ProductOrderType()
    data class Category(val id: Int): ProductOrderType()
    data class Tag(val ids: List<Int>): ProductOrderType()
}