package com.example.foodies.CartTest

import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.repository.CartRepository
import com.example.foodies.feature_foodies.domain.useCase.Card.AddItemInCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.CartUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.ClearCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.DeleteItemFromCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.GetAllCardItemsUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.GetItemFromCardByIdUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.UpdateItemInCardUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CartTest {

    private lateinit var useCase: CartUseCase

    @Before
    fun setUp(){

        val repository: CartRepository = CartTestRepositoryImpl()

        useCase = CartUseCase(
            GetAllCardItemsUseCase(repository),
            AddItemInCardUseCase(repository),
            ClearCardUseCase(repository),
            DeleteItemFromCardUseCase(repository),
            GetItemFromCardByIdUseCase(repository),
            UpdateItemInCardUseCase(repository)
        )
    }


    @Test
    fun getAllItemsFromCart(){
        runBlocking {
            useCase.clearCardUseCase()
            useCase.addItemInCardUseCase(
                Product(id = 1)
            )

           useCase.getAllCardItemsUseCase().collect{ result ->
               assert(result.isNotEmpty())
           }
        }
    }

    @Test
    fun getItemById() {
        runBlocking {
            useCase.clearCardUseCase()
            useCase.addItemInCardUseCase(
                Product(id = 1)
            )
            assert(useCase.getItemFromCardByIdUseCase(1).first()?.id == 1)
        }
    }

    @Test
    fun addProduct() {
        runBlocking {
            useCase.clearCardUseCase()
            val sizeCartBefore = useCase.getAllCardItemsUseCase().first().size
            useCase.addItemInCardUseCase(
                Product(id = 1)
            )
            val sizeCartAfter = useCase.getAllCardItemsUseCase().first().size

            assert(sizeCartAfter != sizeCartBefore)
        }
    }

    @Test
    fun deleteProduct() {
        runBlocking {
            useCase.clearCardUseCase()
            useCase.addItemInCardUseCase(
                Product(id = 1)
            )
            val sizeCartBefore = useCase.getAllCardItemsUseCase().first().size
           useCase.deleteItemFromCardUseCase(1)
            val sizeCartAfter = useCase.getAllCardItemsUseCase().first().size

            assert(sizeCartAfter != sizeCartBefore)
        }
    }

    @Test
    fun updateCount() {
        runBlocking {
            useCase.clearCardUseCase()
            useCase.addItemInCardUseCase(
                Product(id = 1)
            )
            val sizeCountBefore = useCase.getItemFromCardByIdUseCase(1).first()?.count
            useCase.updateItemInCardUseCase(1, 2)
            val sizeCountAfter = useCase.getItemFromCardByIdUseCase(1).first()?.count

            assert(sizeCountBefore != sizeCountAfter)
        }
    }

    @Test
    fun clearCard() {
        runBlocking {
            useCase.addItemInCardUseCase(Product(id = 1))
            useCase.addItemInCardUseCase(Product(id = 2))
            useCase.addItemInCardUseCase(Product(id = 3))

            val sizeCartBefore = useCase.getAllCardItemsUseCase().first().size
            useCase.clearCardUseCase()
            val sizeCartAfter = useCase.getAllCardItemsUseCase().first().size

            assert(sizeCartBefore > 0)
            assert(sizeCartAfter == 0)
        }
    }
}