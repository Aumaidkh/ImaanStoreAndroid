package com.imaan.store.feature_home.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.design_system.ui.theme.ImaanTheme
import com.imaan.store.feature_home.presentation.composables.ImaanTopAppBar

@Composable
fun HomeScreen(
    onMenuClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(24.dp)
    ) {
        ImaanTopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            onMenuClick = onMenuClick,
            onCartClick = onCartClick
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ImaanTheme {
        HomeScreen()
    }
}