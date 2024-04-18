package com.example.foodies.feature_foodies.domain.useCase.Card

import com.example.foodies.feature_foodies.domain.repository.CartRepository
import javax.inject.Inject

class UpdateItemInCardUseCase @Inject constructor(
    private val cardRepository: CartRepository
) {


    operator fun invoke(id: Int, count: Int){
        cardRepository.updateCount(id, count)
    }
}