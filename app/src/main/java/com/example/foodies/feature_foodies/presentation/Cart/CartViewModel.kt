package com.example.foodies.feature_foodies.presentation.Cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.feature_foodies.domain.model.ProductInCart
import com.example.foodies.feature_foodies.domain.useCase.Card.DeleteItemFromCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.GetAllCardItemsUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.UpdateItemInCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllCardItemsUseCase: GetAllCardItemsUseCase,
    private val deleteItemFromCardUseCase: DeleteItemFromCardUseCase,
    private val updateItemInCardUseCase: UpdateItemInCardUseCase
): ViewModel() {

    private val _state = mutableStateOf(CartState())
    val state: State<CartState> = _state

    val cartList = mutableStateListOf<ProductInCart>()

    private var getCartJob: Job? = null

    init {
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            getCartJob?.cancel()
            getCartJob = getAllCardItemsUseCase().onEach { result ->
                if (state.value.cartList.isEmpty()){
                    _state.value = state.value.copy(
                        cartList = result.reversed(),
                        isLoading = false
                    )
                    cartList.clear()
                    cartList.addAll(state.value.cartList)
                }else{
                    _state.value = state.value.copy(
                        cartList = result.reversed(),
                        isLoading = false
                    )
                }

            }.launchIn(viewModelScope)
        }
    }


    fun onEvent(event: CartEvent){
        when(event){
            is CartEvent.MinusProductCount -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (state.value.cartList.find { it.id ==event.id }?.count==1){
                        deleteItemFromCardUseCase(event.id)
                    }else{
                        updateItemInCardUseCase(
                            event.id,
                            state.value.cartList.find { it.id == event.id }?.count?.minus(1) ?: 1
                        )
                    }
                }
            }
            is CartEvent.PlusProductCount -> {
                viewModelScope.launch(Dispatchers.IO) {
                    updateItemInCardUseCase(
                        event.id,
                        state.value.cartList.find { it.id == event.id }?.count?.plus(1) ?: 1
                    )
                }
            }
        }
    }


}