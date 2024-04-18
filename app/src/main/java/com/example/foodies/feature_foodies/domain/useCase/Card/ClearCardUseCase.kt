package com.example.foodies.feature_foodies.domain.useCase.Card

import com.example.foodies.feature_foodies.domain.repository.CartRepository
import javax.inject.Inject

class ClearCardUseCase @Inject constructor(
    private val cardRepository: CartRepository
) {


    suspend operator fun invoke(){
        cardRepository.clearCard()
    }
}