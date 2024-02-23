package com.imaan.product_details.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.common.model.Image
import com.imaan.design_system.components.views.ImaanAppPageIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductImageGalleryView(
    modifier: Modifier,
    images: List<Image>,
    pagerState: PagerState
) {
    images.ifEmpty { return }
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(images[pagerState.currentPage].original.toString())
        .build()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state = pagerState) {
            AsyncImage(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.tertiaryContainer.copy(
                            alpha = 0.04f
                        )
                    )
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
                    .height(300.dp),
                model = imageRequest,
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        ImaanAppPageIndicator(
            maxPageCount = images.size,
            currentPageNumber = pagerState.currentPage,
            itemSize = 8.dp,
            itemSpacing = 5.dp
        )
    }
}