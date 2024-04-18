package com.example.foodies.feature_foodies.presentation.Catalog

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.common.Constants
import com.example.foodies.common.Resource
import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.useCase.Card.CartUseCase
import com.example.foodies.feature_foodies.domain.useCase.GetCategoriesUseCase
import com.example.foodies.feature_foodies.domain.useCase.Product.GetProducts
import com.example.foodies.feature_foodies.domain.useCase.Tags.GetTags
import com.example.foodies.feature_foodies.domain.util.ProductOrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getProducts: GetProducts,
    private val categoriesUseCase: GetCategoriesUseCase,
    private val getTagsUseCase: GetTags,

    private val cartUseCase: CartUseCase
): ViewModel() {

    private val _state = mutableStateOf(CatalogState())
    val state: State<CatalogState> = _state

    init {
        updateAllData()
    }

    private val fullGettingList: ArrayList<Product> = ArrayList()

    private var getProductsJob: Job? = null
    private var getTagsJob: Job? = null
    private var getCategoriesJob: Job? = null
    private var getCardJob: Job? = null


    fun onEvent(event: CatalogEvent){
        when(event){
            is CatalogEvent.EnteredSearch -> {
                _state.value = state.value.copy(
                    search = event.value
                )

                if (event.value.isNotEmpty()){
                    updateProductsList()
                }else{
                    getProductsJob?.cancel()
                    _state.value = state.value.copy(
                        products = emptyList(),
                        productIsLoading = false,
                        error = "Введите название блюда, \nкоторое ищете"
                    )
                }

            }
            is CatalogEvent.FilteredByCategoryId -> {
                _state.value = state.value.copy(
                    selectedCategory = event.value,
                    search = ""
                )
                updateProductsList()
            }
            CatalogEvent.UpdateProductList -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = state.value.copy(
                        refreshIsUpdating = true,
                    )
                    updateAllData()
                }
            }


            is CatalogEvent.AddInCard -> {
                viewModelScope.launch(Dispatchers.IO) {
                    cartUseCase.addItemInCardUseCase(event.value)
                }

            }
            is CatalogEvent.AddProductCount -> {
                viewModelScope.launch(Dispatchers.IO) {
                    cartUseCase.updateItemInCardUseCase(
                        event.id,
                        state.value.cardList.find { it.id == event.id }?.count?.plus(1) ?: 1
                    )
                }
            }
            is CatalogEvent.MinusProductCount -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (state.value.cardList.find { it.id == event.id }?.count == 1){
                        cartUseCase.deleteItemFromCardUseCase(event.id)
                    }else{
                        cartUseCase.updateItemInCardUseCase(
                            event.id,
                            state.value.cardList.find { it.id == event.id }?.count?.minus(1) ?: 1
                        )
                    }

                }
            }


            CatalogEvent.OpenSearchLine ->{
                if (state.value.search.isEmpty()){
                    _state.value = state.value.copy(
                        searchIsOpen = true,
                        products = emptyList(),
                        error = "Введите название блюда, \nкоторое ищете"
                    )
                }else{
                    _state.value = state.value.copy(
                        searchIsOpen = true,
                    )
                }
            }
            CatalogEvent.CloseSearchLine ->{
                if (state.value.products.isEmpty() && state.value.search.isEmpty()){
                    _state.value = state.value.copy(
                        searchIsOpen = false,
                        products = fullGettingList,
                        error = ""
                    )
                }else{
                    _state.value = state.value.copy(
                        searchIsOpen = false,
                    )
                }

            }


            CatalogEvent.TagsClick -> {
                if (state.value.tagsIsOpen){
                    _state.value = state.value.copy(
                        selectedTagsListIds = state.value.oldSelectedTagsListIds
                    )
                }

                _state.value = state.value.copy(
                    tagsIsOpen = !state.value.tagsIsOpen,
                )
            }
            is CatalogEvent.SelectTag -> {

                val selectedTagsList = ArrayList<Int>()
                selectedTagsList.addAll(state.value.selectedTagsListIds)

                if (selectedTagsList.contains(event.id)){
                    selectedTagsList.remove(event.id)
                    _state.value = state.value.copy(
                        selectedTagsListIds = selectedTagsList.toList()
                    )
                }else{
                    selectedTagsList.add(event.id)
                    _state.value = state.value.copy(
                        selectedTagsListIds = selectedTagsList.toList()
                    )
                }
            }
            CatalogEvent.TagsSelectedListConfirmationClick -> {
                _state.value = state.value.copy(
                    oldSelectedTagsListIds = state.value.selectedTagsListIds,
                    tagsIsOpen = !state.value.tagsIsOpen,
                )
                updateProductsList()
            }
        }
    }

    fun updateAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            launch {getCard()}
            launch {getTags()}
            launch {getCategories()}
        }
    }
    private fun updateProductsList(){
        viewModelScope.launch(Dispatchers.Main) {
            if (state.value.searchIsOpen || state.value.search.isNotEmpty()){
                if (state.value.oldSelectedTagsListIds.isNotEmpty()){
                    getProductsByOrder(
                        ProductOrderType.Search(state.value.search),
                        ProductOrderType.Tag(state.value.oldSelectedTagsListIds)
                    )
                }else{
                    getProductsByOrder(
                        ProductOrderType.Search(state.value.search)
                    )
                }
            }else if (state.value.oldSelectedTagsListIds.isNotEmpty()){
                getProductsByOrder(
                    ProductOrderType.Category(state.value.selectedCategory),
                    ProductOrderType.Tag(state.value.oldSelectedTagsListIds)
                )
            }else{
                getProductsByOrder(
                    ProductOrderType.Category(state.value.selectedCategory),
                )
            }
        }
    }

    private suspend fun getCategories() {
        getCategoriesJob?.cancel()
        getCategoriesJob = categoriesUseCase().onEach { result ->
            when(result){
                is Resource.Error -> {
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            error = result.message ?: " Произошла неизвестная ошибка",
                            isLoading = false,
                            categories = result.data ?: emptyList(),
                        )
                    }

                }
                is Resource.Loading -> {
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            isLoading = true,
                            error = "",
                            products = emptyList()
                        )
                    }

                }
                is Resource.Success -> {
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            categories = result.data ?: emptyList(),
                            error = "",
                            selectedCategory = result.data?.get(0)?.id ?: 0
                        )
                    }
                    updateProductsList()

                }
            }
        }.launchIn(viewModelScope)
    }
    private suspend fun getCard() {
        getCardJob?.cancel()
        getCardJob = cartUseCase.getAllCardItemsUseCase().onEach { result ->
            withContext(Dispatchers.Main){
                _state.value = state.value.copy(
                    cardList = result
                )
            }
            Log.i(Constants.LOG_TAG, "cardSize ${result.size}")
        }.launchIn(viewModelScope)
    }

    private fun getProductsByOrder(vararg order: ProductOrderType){
        viewModelScope.launch(Dispatchers.IO) {
            var errorText = ""
            var typeOrder: ProductOrderType = ProductOrderType.Category(0)


            for (type in order){
                typeOrder = type
                errorText = when(type){
                    is ProductOrderType.Category -> "Таких блюд нет :( \nПопробуйте изменить фильтры"
                    is ProductOrderType.Search -> {
                        "Ничего не нашлось :("
                    }
                    is ProductOrderType.Tag -> {
                        "Таких блюд нет :( \nПопробуйте изменить фильтры"
                    }
                }
            }
            getProductsJob?.cancel()
            getProductsJob = getProducts(order = order).onEach { result ->
                when(result){
                    is Resource.Error -> {
                        withContext(Dispatchers.Main){
                            _state.value = state.value.copy(
                                error = result.message ?: "Произошла неизвестная ошибка",
                                productIsLoading = false,
                                refreshIsUpdating = false,
                                isLoading = false,
                                products = emptyList(),
                            )
                        }

                    }
                    is Resource.Loading -> {
                        withContext(Dispatchers.Main){
                            _state.value = state.value.copy(
                                productIsLoading = true,
                                error = "",
                                products = emptyList()
                            )
                        }

                    }
                    is Resource.Success -> {
                        withContext(Dispatchers.Main){
                            _state.value = state.value.copy(
                                products = result.data ?: emptyList(),
                                productIsLoading = false,
                                refreshIsUpdating = false,
                                isLoading = false,
                                error = if (result.data?.isEmpty() == true) errorText else "",
                            )
                        }

                        when(typeOrder){
                            is ProductOrderType.Category -> {
                                fullGettingList.clear()
                                fullGettingList.addAll(state.value.products)
                            }
                            is ProductOrderType.Tag -> {
                                fullGettingList.clear()
                                fullGettingList.addAll(state.value.products)
                            }

                            is ProductOrderType.Search -> {
                                if (state.value.products.isNotEmpty()){
                                    fullGettingList.clear()
                                    fullGettingList.addAll(state.value.products)
                                }
                            }
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }

    }
    private suspend fun getTags(){

        getTagsJob?.cancel()
        getTagsJob = getTagsUseCase().onEach { result ->
            when(result){
                is Resource.Error -> {
                    withContext(Dispatchers.IO){
                        _state.value = state.value.copy(
                            tagsError = result.message ?: "Произошла неизвестная ошибка",
                            tagsList = emptyList(),
                        )
                    }

                }
                is Resource.Loading -> {
                    withContext(Dispatchers.IO){
                        _state.value = state.value.copy(
                            productIsLoading = true,
                            tagsError = "",
                            tagsList = emptyList()
                        )
                    }

                }
                is Resource.Success -> {
                    withContext(Dispatchers.IO){
                        _state.value = state.value.copy(
                            tagsList = result.data ?: emptyList(),
                            productIsLoading = false,
                            refreshIsUpdating = false,
                            tagsError = if (result.data?.isEmpty() == true) "Произошла неизвестная ошибка" else "",
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}