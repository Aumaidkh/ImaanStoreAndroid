package com.imaan.store.feature_home.presentation.categories

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.store.design_system.composables.CustomToolBar
import com.imaan.store.feature_home.domain.models.CategoryModel
import com.imaan.store.feature_home.domain.models.getDummyCategories
import com.imaan.store.feature_home.presentation.composables.CategoryItem
import com.imaan.store.feature_home.presentation.composables.Size

@Composable
fun CategoriesScreen(
    categories: List<CategoryModel> = getDummyCategories(10),
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomToolBar(
                title = "Categories",
                onBackPressed = onBackClick
            )
        }
    ) {
        LazyVerticalStaggeredGrid(modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()),
            columns = StaggeredGridCells.Fixed(2)) {
            items(categories) { category ->
                CategoryItem(
                    modifier = Modifier
                        .padding(6.dp),
                    categoryModel = category,
                    size = Size.LARGE,
                    onClick = {},
                )
            }
        }
    }
}