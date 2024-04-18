@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.foodies.feature_foodies.presentation.Catalog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodies.R
import com.example.foodies.common.components.CustomPrimaryButton
import com.example.foodies.feature_foodies.presentation.Catalog.components.CatalogProductsShimmerLoading
import com.example.foodies.feature_foodies.presentation.Catalog.components.CatalogShimmerScreen
import com.example.foodies.feature_foodies.presentation.Catalog.components.CatalogTagsSheet
import com.example.foodies.feature_foodies.presentation.Catalog.components.CategoryItem
import com.example.foodies.feature_foodies.presentation.Catalog.components.ProductItem
import com.example.foodies.feature_foodies.presentation.Catalog.components.SearchLine
import com.example.foodies.feature_foodies.presentation.Catalog.components.TopLine
import com.example.foodies.feature_foodies.presentation.navgraph.Route
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: CatalogViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    val sheetState = rememberModalBottomSheetState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.refreshIsUpdating)

    if (state.tagsIsOpen){
        ModalBottomSheet(
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
            dragHandle = {},
            onDismissRequest = {viewModel.onEvent(CatalogEvent.TagsClick)},
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CatalogTagsSheet(
                tagsList = state.tagsList,
                selectedTagsList = state.selectedTagsListIds,
                onTagClickListener = {viewModel.onEvent(CatalogEvent.SelectTag(it))}
            ){
                viewModel.onEvent(CatalogEvent.TagsSelectedListConfirmationClick)
            }
        }
    }
    if (state.error.isNotEmpty()){
        Box(Modifier.fillMaxSize()) {
            Text(
                text = state.error,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black.copy(0.6f)
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }

    Column(Modifier.fillMaxSize()) {
        Box() {
            TopLine(
                state = !state.searchIsOpen,
                countSelectedTags = state.oldSelectedTagsListIds.size,
                onFilterClickListener = {viewModel.onEvent(CatalogEvent.TagsClick)},
                onSearchClickListener = {viewModel.onEvent(CatalogEvent.OpenSearchLine)},
            )

            SearchLine(
                state = state.searchIsOpen,
                value = state.search,
                hilt = "Найти блюдо",
                onBackClickListener = { viewModel.onEvent(CatalogEvent.CloseSearchLine) },
                onValueChangeListener = { viewModel.onEvent(CatalogEvent.EnteredSearch(it)) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(CatalogEvent.UpdateProductList) },
            modifier = Modifier.weight(1f)
        ) {
            if (state.isLoading){
                CatalogShimmerScreen()
            }
            else{
                Column(Modifier.fillMaxSize()) {
                    AnimatedVisibility(
                        visible = !state.searchIsOpen,
                        modifier = Modifier.fillMaxWidth(),
                        enter = slideInVertically()
                                + expandVertically(expandFrom = Alignment.Bottom)
                                + fadeIn(),
                        exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight })
                                + shrinkVertically()
                                + fadeOut(),
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            contentPadding = PaddingValues(start=16.dp, end = 8.dp)
                        ){
                            items(
                                count = state.categories.size,
                                key = {state.categories[it].id?:0}
                            ){index ->
                                CategoryItem(
                                    name = state.categories[index].name,
                                    isSelected = state.categories[index].id == state.selectedCategory
                                ) {
                                    viewModel.onEvent(CatalogEvent.FilteredByCategoryId(state.categories[index].id?:0))
                                }
                            }
                        }
                    }

                    if (state.productIsLoading){
                        CatalogProductsShimmerLoading()
                    }
                    else{
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(16.dp),
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            items(state.products.size){ index ->
                                val item = state.products[index]
                                ProductItem(
                                    title = item.name,
                                    measure = item.measure.toString() + " " + item.measure_unit,
                                    currentPrice = item.price_current/100,
                                    oldPrice = item.price_old?.div(100),
                                    image = item.image,
                                    icon = if (item.tag_ids.isNotEmpty()){
                                        if (item.tag_ids.contains(1) || item.tag_ids.contains(3) || item.tag_ids.contains(5)){
                                            R.drawable.ic_type_discount
                                        }else if (item.tag_ids.contains(2)){
                                            R.drawable.ic_type_vegan
                                        }else if (item.tag_ids.contains(4)){
                                            R.drawable.ic_type_sharp
                                        }else{null}
                                    }else{null},
                                    isSavingInCard =state.cardList.find { it.id == item.id } != null,
                                    countInCard = state.cardList.find { it.id == item.id }?.count?:1,
                                    onCardClickListener = {
                                        navController.navigate(Route.ProductDetails.route.replace("{id}", item.id.toString()))
                                    },
                                    onAddInCardClickListener = { viewModel.onEvent(CatalogEvent.AddInCard(item)) },
                                    onMinusClickListener = { viewModel.onEvent(CatalogEvent.MinusProductCount(item.id?:0)) },
                                    onPlusClickListener = { viewModel.onEvent(CatalogEvent.AddProductCount(item.id?:0)) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

        }
        AnimatedVisibility(
            visible = state.cardList.isNotEmpty(),
            enter = slideInVertically()
                    + expandVertically(expandFrom = Alignment.Top)
                    + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight })
                    + shrinkVertically()
                    + fadeOut()
        ) {
            val currentPrice = state.cardList.map { it.price_current.times(it.count?:1) }.toList().sum()/100
            CustomPrimaryButton(
                icon = R.drawable.ic_cart,
                title = if (currentPrice!=0) "$currentPrice ₽" else "Корзина пуста :)",
                onClickListener = { navController.navigate(Route.Card.route) }
            )
        }

    }



}