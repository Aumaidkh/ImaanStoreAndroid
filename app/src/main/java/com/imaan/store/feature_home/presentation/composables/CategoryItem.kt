package com.imaan.store.feature_home.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.store.feature_home.domain.models.CategoryModel
import kotlin.random.Random

enum class Size {
    LARGE,
    SMALL,
    MEDIUM
}

@Composable
fun CategoryItemLarge(
    modifier: Modifier,
    categoryModel: CategoryModel,
    onClick: (CategoryModel) -> Unit
) {
    val context = LocalContext.current
    val image = ImageRequest.Builder(context)
        .data(categoryModel.backgroundImageUrl.toString())
        .build()
    val makeExpanded = Random.nextBoolean()
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (makeExpanded) 190.dp else 150.dp),
            model = image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiaryContainer, blendMode = BlendMode.DstIn)
        )

        Text(
            text = categoryModel.label,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

@Composable
fun CategoryItemMedium(
    categoryModel: CategoryModel,
    onClick: (CategoryModel) -> Unit
) {

}

@Composable
fun CategoryItemSmall(
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
            text = categoryModel.label,
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

@Composable
fun CategoryItem(
    modifier: Modifier,
    categoryModel: CategoryModel,
    onClick: (CategoryModel) -> Unit,
    isSelected: Boolean = false,
    size: Size,
) {
    when {
        size == Size.SMALL -> {
            CategoryItemSmall(modifier = modifier,categoryModel = categoryModel, onClick = onClick,selected = isSelected)
        }

        size == Size.MEDIUM -> {
            CategoryItemMedium(categoryModel = categoryModel, onClick = onClick)
        }

        size == Size.LARGE -> {
            CategoryItemLarge(modifier = modifier, categoryModel = categoryModel, onClick = onClick)
        }
    }
}