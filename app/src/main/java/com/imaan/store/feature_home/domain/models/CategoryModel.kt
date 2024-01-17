package com.imaan.store.feature_home.domain.models

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.store.core.domain.model.ID
import java.net.URL


@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryModel: CategoryModel,
    selected: Boolean = false,
    onClick: (CategoryModel) -> Unit = {}
) {
    val context = LocalContext.current
    val iconModel = ImageRequest
        .Builder(context)
        .data(categoryModel.iconUrl.toString())
        .build()
    Row(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.small)
            .background(if (selected) MaterialTheme.colorScheme.primary else Color.White)
            .border(
                width = if (!selected) 1.dp else 0.dp,
                color = if (!selected) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent,
                shape = MaterialTheme.shapes.small
            )
            .padding(12.dp)
            .clickable {
                onClick(categoryModel)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
//        AsyncImage(
//            modifier = Modifier
//                .size(24.dp),
//            model = iconModel,
//            contentDescription = ""
//        )
        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            text = categoryModel.label,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
        )
    }
}

data class CategoryModel(
    val id: ID,
    val label: String,
    val backgroundImageUrl: URL,
    val iconUrl: URL
)

fun getDummyCategories(count: Int = 1): List<CategoryModel> {
    val categories = mutableListOf<CategoryModel>()
    repeat(count) {
        categories.add(CategoryModel(
            id = ID(it.toString()),
            label = "Category $it",
            backgroundImageUrl = URL(
                "https://images.pexels.com/photos/1114883/pexels-photo-1114883.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
            iconUrl = URL("https://fontawesome.com/icons/house?f=classic&s=solid")
        ))
    }
    return categories
}


