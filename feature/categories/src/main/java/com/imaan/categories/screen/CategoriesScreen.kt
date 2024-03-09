package com.imaan.categories.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.categories.CategoryModel
import com.imaan.components.CategoryItem
import com.imaan.components.Size

@Composable
fun CategoriesScreen(
    state: CategoriesScreenUiState = CategoriesScreenUiState(),
    paddingValues: PaddingValues,
    onCategoryClicked: (CategoryModel) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
        columns = GridCells.Fixed(2)
    ) {
        items(
            items = state.categories,
            key = { it.id.value }
        ) {
            CategoryItem(
                categoryModel = it,
                onClick = onCategoryClicked,
                modifier = Modifier
                    .padding(8.dp),
                size = Size.LARGE
            )
        }
    }

}