package com.example.foodies.feature_foodies.presentation.Cart

import com.example.foodies.feature_foodies.domain.model.ProductInCart

data class CartState(
    val cartList: List<ProductInCart> = emptyList(),

    val isLoading: Boolean = true
) {
}