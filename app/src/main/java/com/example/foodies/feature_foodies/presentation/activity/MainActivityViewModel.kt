package com.example.foodies.feature_foodies.presentation.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.common.Constants
import com.example.foodies.feature_foodies.domain.useCase.Card.ClearCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val clearCardUseCase: ClearCardUseCase
): ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO) {
            clearCart()
        }
        Log.i(Constants.LOG_TAG, "ActivityViewModel is created")
    }

    suspend fun clearCart() {
        clearCardUseCase()
    }
}