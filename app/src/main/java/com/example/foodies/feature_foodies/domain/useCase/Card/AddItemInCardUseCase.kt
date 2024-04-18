package com.example.foodies.feature_foodies.domain.useCase.Card

import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.repository.CartRepository
import javax.inject.Inject

class AddItemInCardUseCase @Inject constructor(
    private val cardRepository: CartRepository
) {


    operator fun invoke(product: Product){
        cardRepository.addProduct(product)
    }
}