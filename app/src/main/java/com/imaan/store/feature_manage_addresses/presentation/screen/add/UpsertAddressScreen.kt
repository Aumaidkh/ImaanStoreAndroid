package com.imaan.store.feature_manage_addresses.presentation.screen.add

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.design_system.composables.CustomToolBar
import com.imaan.store.design_system.ui.theme.ImaanTheme

@Composable
fun UpsertAddressScreen(
    state: UpsertAddressScreenUiState = UpsertAddressScreenUiState(),
    scrollState: ScrollState = rememberScrollState(),
    onSaveClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onFullNameChange: (String) -> Unit = {},
    onLandmarkChange: (String) -> Unit = {},
    onCityChange: (String) -> Unit = {},
    onDistrictChange: (String) -> Unit = {},
    onStateChange: (String) -> Unit = {},
    onPhoneChange: (String) -> Unit = {},
    onCountryChange: (String) -> Unit = {},
    onPinCodeChange: (String) -> Unit = {},
    onFullAddressChange: (String) -> Unit = {}
){
    Scaffold(
        topBar = {
            CustomToolBar(
                title = "Add New Address",
                onBackPressed = onBackClick
            )
        }
    ) {
        UpsertAddressForm(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding() + 24.dp, bottom = it.calculateBottomPadding())
                .padding(horizontal = 40.dp)
                .verticalScroll(scrollState),
            state = state,
            onFullNameChange = onFullNameChange,
            onLandmarkChange = onLandmarkChange,
            onCityChange = onCityChange,
            onDistrictChange = onDistrictChange,
            onStateChange = onStateChange,
            onPhoneChange = onPhoneChange,
            onCountryChange = onCountryChange,
            onPinCodeChange = onPinCodeChange,
            onFullAddressChange = onFullAddressChange,
            onSaveClick = onSaveClick
        )
    }
}

@Preview
@Composable
fun UpsertAddressScreenPreview(){
    ImaanTheme {
        UpsertAddressScreen()
    }
}