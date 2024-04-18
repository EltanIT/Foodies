package com.example.foodies.feature_foodies.domain.useCase.Card

import com.example.foodies.feature_foodies.domain.model.ProductInCart
import com.example.foodies.feature_foodies.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemFromCardByIdUseCase @Inject constructor(
    private val cardRepository: CartRepository
) {


    suspend operator fun invoke(id: Int): Flow<ProductInCart?> {
        return cardRepository.getItemById(id)
    }
}