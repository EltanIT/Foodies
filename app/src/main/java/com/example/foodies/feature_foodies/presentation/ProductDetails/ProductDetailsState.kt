package com.example.foodies.feature_foodies.presentation.ProductDetails

import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.model.ProductInCart

data class ProductDetailsState(
    val product: Product? = null,
    val cardProduct: ProductInCart? = null,
    val productSpecificationList: List<SpecificationProductItem> = emptyList(),

    val isLoading: Boolean = false,
    val error: String = "",
) {
}