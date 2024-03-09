package com.imaan.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.categories.CategoryModel
import kotlin.random.Random


enum class Size {
    LARGE,
    SMALL,
    MEDIUM
}

@Composable
fun CategoryItem(
    modifier: Modifier,
    categoryModel: CategoryModel,
    onClick: (CategoryModel) -> Unit,
    selected: Boolean = false,
    size: Size
) {
    when(size){
        Size.LARGE -> CategoryItemLarge(modifier = modifier, categoryModel = categoryModel, onClick = onClick)
        Size.SMALL -> CategoryItemSmall(modifier = modifier, categoryModel = categoryModel, onClick = onClick, selected = selected)
        Size.MEDIUM -> CategoryItemMedium(modifier = modifier, categoryModel = categoryModel, onClick = onClick)
    }
}

@Composable
private fun CategoryItemLarge(
    modifier: Modifier,
    categoryModel: CategoryModel,
    onClick: (CategoryModel) -> Unit
) {
    val context = LocalContext.current
    val image = ImageRequest.Builder(context)
        .data(categoryModel.backgroundImageUrl.toString())
        .build()
    Box(modifier = modifier.clickable { onClick(categoryModel) }, contentAlignment = Alignment.Center) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            model = image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(color = Color.Black.copy(alpha = 0.01f), blendMode = BlendMode.Darken)
        )

        Text(
            text = categoryModel.label.value,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.background
        )
    }
}

@Composable
private fun CategoryItemMedium(
    modifier: Modifier,
    categoryModel: CategoryModel,
    onClick: (CategoryModel) -> Unit
) {

}

@Composable
private fun CategoryItemSmall(
    modifier: Modifier,
    categoryModel: CategoryModel,
    onClick: (CategoryModel) -> Unit,
    selected: Boolean = false
) {

    OutlinedButton(
        modifier = modifier,
        onClick = { onClick(categoryModel) },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
        ),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.4f
            )
        )
    ) {
        Text(
            text = categoryModel.label.value,
            style = MaterialTheme.typography.bodySmall.copy(
                color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.4f
                ),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        )
    }

}
