package com.example.foodies.feature_foodies.presentation.Cart

sealed class CartEvent {
    data class PlusProductCount(val id: Int): CartEvent()
    data class MinusProductCount(val id: Int): CartEvent()
}