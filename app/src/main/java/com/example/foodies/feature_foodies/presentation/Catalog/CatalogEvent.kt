package com.example.foodies.feature_foodies.presentation.Catalog

import com.example.foodies.feature_foodies.domain.model.Product

//Все события для экрана Catalog
sealed class CatalogEvent {
    data class FilteredByCategoryId(val value: Int): CatalogEvent()
    data class AddInCard(val value: Product): CatalogEvent()
    data class AddProductCount(val id: Int): CatalogEvent()
    data class MinusProductCount(val id: Int): CatalogEvent()

    object TagsClick: CatalogEvent()
    object TagsSelectedListConfirmationClick: CatalogEvent()
    object UpdateProductList: CatalogEvent()

    object OpenSearchLine: CatalogEvent()
    object CloseSearchLine: CatalogEvent()

    data class SelectTag(val id: Int): CatalogEvent()

    data class EnteredSearch(val value: String): CatalogEvent()
}