package com.imaan.product_details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.design_system.components.section.LazyColumnSurfaceSection
import com.imaan.design_system.components.top_bars.ImaanAppTopBar
import com.imaan.design_system.components.top_bars.Type
import com.imaan.design_system.components.views.ColorSelectorComponent
import com.imaan.design_system.components.views.ProductCard
import com.imaan.design_system.components.views.ProductCardSize
import com.imaan.product_details.components.BuyNowButton
import com.imaan.product_details.components.ProductDetailsBottomSheetComponent
import com.imaan.product_details.components.ProductImageGalleryView
import com.imaan.products.ColorVariant
import com.imaan.products.CustomVariant
import com.imaan.products.ProductModel
import com.imaan.products.SizeVariant
import com.imaan.util.toColor
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ProductDetailsScreen(
    paddingValues: PaddingValues = PaddingValues(),
    uiState: ProductDetailsScreenUiState = ProductDetailsScreenUiState(),
    onSizeSelected: (SizeVariant) -> Unit = {},
    onVariantSelected: (CustomVariant) -> Unit = {},
    onColorSelected: (ColorVariant?) -> Unit = {},
    onBuyNow: (ProductModel) -> Unit = {},
    onAddToCart: (ProductModel) -> Unit = {},
    onAddToFavorites: (ProductModel,Boolean) -> Unit = {_, _ -> },
    onRecommendedItemClick: (ProductModel) -> Unit = {}
) {

    val sheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        uiState.product?.images?.size ?: 0
    }
    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetPeekHeight = 0.dp,
        sheetSwipeEnabled = true,
        sheetShadowElevation = 0.9.dp,
        sheetContent = {
            ProductDetailsBottomSheetComponent(
                sizes = uiState.product?.sizes ?: emptyList(),
                colors = uiState.product?.colors ?: emptyList(),
                variants = uiState.product?.customVariants ?: emptyList(),
                selectedSize = uiState.selectedSize,
                selectedColor = uiState.selectedColor,
                selectedVariant = uiState.selectedVariant,
                onSizeSelected = onSizeSelected,
                onColorSelected = onColorSelected,
                onVariantSelected = onVariantSelected,
                onSelectButtonClick = {
                    scope.launch {
                        sheetState.bottomSheetState.partialExpand()
                    }
                }
            )
        },
        modifier = Modifier
            .padding(
                bottom = paddingValues.calculateBottomPadding()
            ),
        topBar = {
            ImaanAppTopBar(
                modifier = Modifier
                    .fillMaxWidth(),
                type = Type.WithoutProfilePic,
                title = uiState.product?.title?.value.toString(),
                onNavigationClick = {

                },
                actionIconResId = com.imaan.design_system.R.drawable.ic_favorite,
                onActionClick = {
                    uiState.product?.let { onAddToFavorites(it,true) }
                },
                transparentBackground = true
            )
        },

        ) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    contentPadding = PaddingValues(
                        start = 24.dp,
                        end = 24.dp
                    ),
                    containerColor = Color.Transparent
                ) {
                    Column {
                        Text(
                            text = "Total Price",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.4f
                                )
                            )
                        )
                        Text(
                            text = uiState.product?.price?.inRupees ?: "",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    BuyNowButton(
                        onBuyNowClick = {
                            uiState.product?.let(onBuyNow)
                        },
                        onAddToCartClick = {
                            uiState.product?.let(onAddToCart)
                        }
                    )
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        bottom = it.calculateBottomPadding()
                    ),
            ) {
                item {
                    ProductImageGalleryView(
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxWidth(),
                        images = uiState.product?.images ?: emptyList(),
                        pagerState = pagerState
                    )
                }
                item {
                    LazyColumnSurfaceSection(
                        modifier = Modifier
                            .padding(top = 12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                text = uiState.product?.title?.value.toString(),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 20.sp
                                )
                            )

                            Text(
                                modifier = Modifier
                                    .padding(
                                        top = 8.dp,
                                        bottom = 12.dp
                                    ),
                                text = "Electronics",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onBackground.copy(
                                        alpha = 0.4f
                                    )
                                )
                            )
                            VariantInfo(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                selectedSize = uiState.selectedSize,
                                selectedVariant = uiState.selectedVariant,
                                selectedColor = uiState.selectedColor,
                                onClick = {
                                    scope.launch {
                                        sheetState.bottomSheetState.expand()
                                    }
                                }
                            )
                            if (uiState.selectedColor != null && uiState.product?.colors != null) {
                                ColorSelectorComponent(
                                    modifier = Modifier,
                                    colors = if (uiState.product.colors.size > 3) uiState.product.colors.subList(
                                        0,
                                        3
                                    ).map { it.hexValue.toColor() } else uiState.product?.colors?.map { it.hexValue.toColor() } ?: emptyList(),
                                    selectedColor = uiState.selectedColor.hexValue.toColor(),
                                    onColorClicked = {
                                        scope.launch {
                                            sheetState.bottomSheetState.expand()
                                        }
                                    },
                                    seeMoreCount = if ((uiState.product.colors.size - 3) > 0) uiState.product.colors.size - 4 else null,
                                    onSeeMore = {
                                        scope.launch {
                                            sheetState.bottomSheetState.expand()
                                        }
                                    },
                                    boxSize = 70.dp,
                                    showSelectedColorOnComponent = true
                                )
                            }
                        }
                    }
                }

                item {
                    LazyColumnSurfaceSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        verticalPadding = 12.dp,
                        label = "Description",
                        content = {
                            Text(
                                modifier = Modifier,
                                text = uiState.product?.description?.value.toString(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onBackground.copy(
                                        alpha = 0.4f
                                    )
                                ),
                                // maxLines = 3,
                                softWrap = true
                            )
                        }
                    )
                }

                item {
                    LazyColumnSurfaceSection(
                        label = "Items You May Like",
                        horizontalPadding = 0.dp,
                        labelHorizontalPadding = 40.dp,
                        verticalPadding = 12.dp
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            items(uiState.recommendedProducts) {
                                ProductCard(
                                    modifier = Modifier
                                        .width(200.dp)
                                        .height(300.dp),
                                    size = ProductCardSize.Small,
                                    product = it,
                                    onClick = onRecommendedItemClick
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
private fun NewGalleryLayout(uiState: ProductDetailsScreenUiState) {
    Row(
        modifier = Modifier
            .padding(12.dp)
    ) {
        val primaryImageRequest = ImageRequest.Builder(
            LocalContext.current
        ).data(uiState.product?.primaryImage?.original.toString())
            .build()
        Column(
            modifier = Modifier
                .fillMaxWidth(0.15f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            uiState.product?.images?.forEach { product ->
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer.copy(0.4f))
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val imageRequest = ImageRequest.Builder(
                        LocalContext.current
                    ).data(product.thumbnail.toString())
                        .build()
                    AsyncImage(
                        modifier = Modifier
                            .size(40.dp),
                        model = imageRequest,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(
                    1f,
                    true
                ),
            model = primaryImageRequest,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VariantInfo(
    modifier: Modifier,
    selectedSize: SizeVariant?,
    selectedVariant: CustomVariant?,
    selectedColor: ColorVariant?,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (selectedSize != null) {
            Variant(
                label = "Size",
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClick()
                        },
                    value = selectedSize.size,
                    onValueChange = {},
                    enabled = false,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = ""
                        )
                    }
                )
            }
        }
        if (selectedVariant != null) {
            Variant(
                label = "Variant",
                modifier = Modifier
                    .weight(0.5f)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .clickable {
                            onClick()
                        },
                    value = selectedVariant.label,
                    onValueChange = {},
                    enabled = false,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = ""
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun Variant(
    modifier: Modifier = Modifier,
    label: String,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@PreviewLightDark
@Composable
fun ProductDetailsScreenPreview() {
    ProductDetailsScreen()
}


val dummyHtml = "<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    <title>Camera Specifications</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "    <h1>Camera Specifications</h1>\n" +
        "\n" +
        "    <h2>Main Features:</h2>\n" +
        "    <ul>\n" +
        "        <li>Resolution: 12 MP</li>\n" +
        "        <li>Zoom: 3x Optical Zoom</li>\n" +
        "        <li>Video Recording: 4K at 30fps</li>\n" +
        "    </ul>\n" +
        "\n" +
        "    <h2>Additional Features:</h2>\n" +
        "    <ul>\n" +
        "        <li>Image Stabilization: Yes</li>\n" +
        "        <li>Connectivity: USB, Wi-Fi</li>\n" +
        "        <li>Waterproof: No</li>\n" +
        "    </ul>\n" +
        "\n" +
        "    <h2>Technical Specifications:</h2>\n" +
        "    <table border=\"1\">\n" +
        "        <tr>\n" +
        "            <th>Resolution</th>\n" +
        "            <th>Zoom</th>\n" +
        "            <th>Video Recording</th>\n" +
        "        </tr>\n" +
        "        <tr>\n" +
        "            <td>12 MP</td>\n" +
        "            <td>3x Optical Zoom</td>\n" +
        "            <td>4K at 30fps</td>\n" +
        "        </tr>\n" +
        "    </table>\n" +
        "    \n" +
        "</body>\n" +
        "</html>\n"