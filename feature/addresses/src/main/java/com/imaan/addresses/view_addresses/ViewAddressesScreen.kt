package com.imaan.addresses.view_addresses


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.addresses.Address
import com.imaan.addresses.view_addresses.components.AddressItemComponent
import com.imaan.addresses.view_addresses.components.NoAddressesView
import com.imaan.design_system.components.top_bars.ImaanAppTopBar

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
            ImaanAppTopBar(
                title = "Manage Addresses",
                onNavigationClick = onBackClick
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
        floatingActionButtonPosition = FabPosition.End,
    ) {
        if (state.addresses.isEmpty()) {
            NoAddressesView(
                modifier = Modifier
                    .fillMaxSize()
            )
        } else {
            AddressesFeed(
                modifier = Modifier
                    .padding(
                        top = it.calculateTopPadding() + 12.dp,
                        bottom = it.calculateBottomPadding()
                    )
                    .fillMaxWidth(),
                state = state,
                onAddressSelected = onAddressSelected,
                onDeliverToAddress = onDeliverToAddress,
                onEditClick = onEditAddressClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressesFeed(
    modifier: Modifier = Modifier,
    state: ViewAddressesUiState = ViewAddressesUiState(),
    onAddressSelected: (Address) -> Unit = {},
    onDeliverToAddress: (Address) -> Unit = {},
    onEditClick: (Address) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {

        item {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                query = "",
                onQueryChange = {},
                onSearch = {},
                active = false,
                onActiveChange = {},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = com.imaan.design_system.R.drawable.ic_search),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.5f
                        )
                    )
                },
                placeholder = {

                    Text(
                        text = "Find an Address",
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.5f
                        )
                    )
                }
            ) {

            }
        }

        items(
            items = state.addresses,
            key = { it.id?.value ?: "" }
        ) {
            AddressItemComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 12.dp
                    ),
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