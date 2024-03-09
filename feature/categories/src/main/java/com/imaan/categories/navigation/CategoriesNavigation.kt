package com.imaan.categories.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imaan.categories.CategoryModel
import com.imaan.categories.screen.CategoriesScreen
import com.imaan.categories.screen.CategoriesScreenEvents
import com.imaan.categories.screen.CategoriesScreenViewModel

fun NavGraphBuilder.categoriesNavigation(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    onCategoryClicked: (CategoryModel) -> Unit
){
    composable(
        route = CategoriesRoute.route
    ){
        val viewModel = hiltViewModel<CategoriesScreenViewModel>()
        val state = viewModel.state.collectAsState()
        val event = viewModel.eventsFlow.collectAsState(null)
        event.value?.let {
            LaunchedEffect(it){
                when(it){
                    is CategoriesScreenEvents.Error -> {
                        snackbarHostState.showSnackbar(it.message)
                    }
                }
            }
        }

        CategoriesScreen(
            state = state.value,
            onCategoryClicked = onCategoryClicked,
            paddingValues = paddingValues
        )
    }
}