package com.example.foodies.feature_foodies.presentation.ProductDetails

sealed class ProductsDetailsEvent {
    object AddInCard: ProductsDetailsEvent()


    object PlusProductCount: ProductsDetailsEvent()
    object MinusProductCount: ProductsDetailsEvent()
}