package com.example.foodies.feature_foodies.domain.useCase.Card

import com.example.foodies.feature_foodies.domain.repository.CartRepository
import javax.inject.Inject

class DeleteItemFromCardUseCase @Inject constructor(
    private val cardRepository: CartRepository
) {


    operator fun invoke(id: Int){
        cardRepository.deleteProduct(id,)
    }
}