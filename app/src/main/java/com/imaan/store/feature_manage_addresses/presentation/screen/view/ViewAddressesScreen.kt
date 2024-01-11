package com.imaan.store.feature_manage_addresses.presentation.screen.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.core.domain.model.Address
import com.imaan.store.core.presentation.NoAddresses
import com.imaan.store.design_system.composables.CustomToolBar

@Composable
fun ViewAddressesScreen(
    state: ViewAddressesUiState = ViewAddressesUiState(),
    onAddressSelected: (Address) -> Unit = {},
    onDeliverToAddress: (Address) -> Unit = {},
    onEditAddressClick: (Address) -> Unit = {},
    onAddNewAddressClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomToolBar(
                title = "Manage Addresses",
                onBackPressed = onBackClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddNewAddressClick() }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = ""
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        if (state.addresses.isEmpty()) {
            NoAddresses(
                modifier = Modifier
                    .fillMaxSize()
            )
        } else {
            AddressesFeed(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = it.calculateTopPadding()+12.dp, bottom = it.calculateBottomPadding())
                    .fillMaxWidth(),
                state = state,
                onAddressSelected = onAddressSelected,
                onDeliverToAddress = onDeliverToAddress,
                onEditClick = onEditAddressClick
            )
        }
    }
}

@Composable
fun AddressesFeed(
    modifier: Modifier = Modifier,
    state: ViewAddressesUiState = ViewAddressesUiState(),
    onAddressSelected: (Address) -> Unit = {},
    onDeliverToAddress: (Address) -> Unit = {},
    onEditClick: (Address) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(
            items = state.addresses,
            key = { it.id?.value ?: "" }
        ) {
            AddressItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                address = it,
                isSelected = state.selectedAddress == it,
                onAddressSelected = onAddressSelected,
                onDeliverToAddress = onDeliverToAddress,
                loading = state.loading,
                onEditClick = onEditClick
            )
        }
    }
}

@Preview
@Composable
fun ViewAddressesScreenPreview() {
    ViewAddressesScreen()
}