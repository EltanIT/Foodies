package com.example.foodies.feature_foodies.presentation.ProductDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.common.Resource
import com.example.foodies.feature_foodies.domain.useCase.Card.AddItemInCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.DeleteItemFromCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.GetItemFromCardByIdUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.UpdateItemInCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Product.GetProductById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductById: GetProductById,

    private val getItemFromCardByIdUseCase: GetItemFromCardByIdUseCase,
    private val addItemInCardUseCase: AddItemInCardUseCase,
    private val updateItemInCardUseCase: UpdateItemInCardUseCase,
    private val deleteItemFromCardUseCase: DeleteItemFromCardUseCase
): ViewModel() {


    private val _state = mutableStateOf(ProductDetailsState())
    val state: State<ProductDetailsState> = _state


    //Обработка всевозможных действий, который пользователь может совершить
    fun onEvent(event: ProductsDetailsEvent){
        when(event){
            ProductsDetailsEvent.AddInCard -> {
                viewModelScope.launch(Dispatchers.IO) {
                    addItemInCardUseCase(state.value.product!!)
                }
            }

            ProductsDetailsEvent.MinusProductCount -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (state.value.cardProduct?.count==1){
                        deleteItemFromCardUseCase(state.value.cardProduct?.id?:0)
                    }else{
                        updateItemInCardUseCase(state.value.cardProduct?.id?:0,
                            state.value.cardProduct?.count?.minus(1) ?: 0
                        )
                    }

                }
            }
            ProductsDetailsEvent.PlusProductCount -> {
                viewModelScope.launch(Dispatchers.IO) {
                    updateItemInCardUseCase(state.value.cardProduct?.id?:0,
                        state.value.cardProduct?.count?.plus(1) ?: 0
                    )
                }
            }
        }
    }



    //Получение продукта из api по id
    fun getProduct(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            getCardItem(id)
        }

        viewModelScope.launch(Dispatchers.IO) {
            getProductById(id).onEach { result ->
                when(result){
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            error = result.message?:"Неизвестная ошибка!",
                            isLoading = false,
                            product = null
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            error = "",
                            isLoading = true,
                            product = null
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            error = "",
                            isLoading = false,
                            product = result.data
                        )

                        _state.value = state.value.copy(
                            productSpecificationList = listOf(
                                SpecificationProductItem(
                                    "Вес",
                                        state.value.product?.measure.toString() + " " + state.value.product?.measure_unit
                                    ),
                                SpecificationProductItem(
                                    "Энерг. ценность",
                                    state.value.product?.energy_per_100_grams.toString() + " ккал"
                                ),
                                SpecificationProductItem(
                                    "Белки",
                                    state.value.product?.proteins_per_100_grams.toString() + " г"
                                ),
                                SpecificationProductItem(
                                    "Жиры",
                                    state.value.product?.fats_per_100_grams.toString() + " г"
                                ),
                                SpecificationProductItem(
                                    "Углеводы",
                                    state.value.product?.carbohydrates_per_100_grams.toString() + " г"
                                ),

                            )
                        )
                    }
                }

            }.launchIn(viewModelScope)
        }
    }

    //Получение продукта из корзины по id
    private suspend fun getCardItem(id: Int) {
        getItemFromCardByIdUseCase(id).onEach { result ->
            _state.value = state.value.copy(
                cardProduct = result
            )
        }.launchIn(viewModelScope)
    }

}