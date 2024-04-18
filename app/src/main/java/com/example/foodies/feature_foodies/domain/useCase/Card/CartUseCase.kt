package com.example.foodies.feature_foodies.domain.useCase.Card

data class CartUseCase(
    val getAllCardItemsUseCase: GetAllCardItemsUseCase,
    val addItemInCardUseCase: AddItemInCardUseCase,
    val clearCardUseCase: ClearCardUseCase,
    val deleteItemFromCardUseCase: DeleteItemFromCardUseCase,
    val getItemFromCardByIdUseCase: GetItemFromCardByIdUseCase,
    val updateItemInCardUseCase: UpdateItemInCardUseCase
) {
}