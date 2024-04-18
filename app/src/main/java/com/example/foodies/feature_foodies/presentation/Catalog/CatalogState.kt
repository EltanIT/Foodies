package com.example.foodies.feature_foodies.presentation.Catalog

import com.example.foodies.feature_foodies.domain.model.Category
import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.model.ProductInCart
import com.example.foodies.feature_foodies.domain.model.Tag

data class CatalogState(
    val categories: List<Category> = emptyList(),
    val products: List<Product> = emptyList(),
    val cardList: List<ProductInCart> = emptyList(),
    val tagsList: List<Tag> = emptyList(),
    val oldSelectedTagsListIds: List<Int> = emptyList(),
    val selectedTagsListIds: List<Int> = emptyList(),

    val search: String = "",
    val error: String = "",
    val tagsError: String = "",
    val selectedCategory: Int = 0,

    val isLoading: Boolean = false,
    val productIsLoading: Boolean = false,
    val refreshIsUpdating: Boolean = false,
    val searchIsOpen: Boolean = false,
    val tagsIsOpen: Boolean = false,
) {
}