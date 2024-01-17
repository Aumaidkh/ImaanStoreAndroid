package com.imaan.store.feature_home.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.R
import com.imaan.store.design_system.composables.CircularImage
import com.imaan.store.design_system.ui.theme.ImaanTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImaanTopAppBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    cartItemsCount: Int? = 4,
    loading: Boolean = false
) {
    TopAppBar(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 8.dp),
        title = {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                    Text(
                        text = "Hello, Hopcape",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "How are you today",
                        style = MaterialTheme.typography.bodySmall
                    )
            }
        },
        navigationIcon = {
            CircularImage(
                modifier = Modifier
                    .size(45.dp),
                resId = R.drawable.dummy_pic,
                onClick = {
                    onMenuClick()
                }
            )
        },
        actions = {
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                CircularIconButton(
                    iconResId = R.drawable.ic_cart,
                    containerColor = MaterialTheme.colorScheme.primary,
                    tint = Color.White
                ) {
                    onCartClick()
                }
                cartItemsCount?.let {
                    Badge {
                        Text(text = "$it")
                    }
                }
            }
        },
        colors = TopAppBarDefaults
            .topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
    )
}

@Preview
@Composable
fun ImaanTopAppBarPreview() {
    ImaanTheme {
        ImaanTopAppBar()
    }
}