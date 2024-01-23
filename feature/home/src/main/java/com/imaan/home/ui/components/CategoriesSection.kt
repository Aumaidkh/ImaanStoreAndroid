package com.imaan.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.categories.CategoryModel
import com.imaan.components.CategoryItem
import com.imaan.components.Size


@Composable
fun CategoriesSection(
    onSeeAllCategoriesClick: () -> Unit,
    categories: List<CategoryModel>,
    selectedCategory: CategoryModel?,
    onCategorySelected:(CategoryModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 19.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            TextButton(onClick = { onSeeAllCategoriesClick() }) {
                Text(text = "See all")
            }
        }
        LazyRow {
            items(
                items = categories
            ) {
                CategoryItem(
                    modifier = Modifier
                        .padding(8.dp),
                    categoryModel = it,
                    onClick = {onCategorySelected(it)},
                    size = Size.SMALL,
                    selected = selectedCategory == it
                )
            }
        }
    }
}