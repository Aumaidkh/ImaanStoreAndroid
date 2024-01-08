package com.imaan.store.feature_cart.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.design_system.composables.CircularIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartToolBar(
    onBackPressed: () -> Unit = {},
    onMorePressed: () -> Unit = {},
    title: String = "My Cart"
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            CircularIcon(
                modifier = Modifier
                    .padding(24.dp),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                onClick = onBackPressed
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back Button",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            CircularIcon(
                modifier = Modifier
                    .padding(24.dp),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                onClick = onMorePressed
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp),
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Back Button",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@Preview
@Composable
fun CartToolBarPreview(){
    CartToolBar()
}