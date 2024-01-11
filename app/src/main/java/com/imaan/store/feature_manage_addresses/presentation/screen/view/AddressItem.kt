package com.imaan.store.feature_manage_addresses.presentation.screen.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.core.domain.model.Address
import com.imaan.store.core.domain.model.dummyAddress
import com.imaan.store.design_system.composables.LoadingButton

@Composable
fun AddressItem(
    modifier: Modifier = Modifier,
    address: Address = dummyAddress(),
    isSelected: Boolean = false,
    onAddressSelected: (Address) -> Unit = {},
    onDeliverToAddress: (Address) -> Unit = {},
    onEditClick: (Address) -> Unit = {},
    loading: Boolean = false
) {
    Surface(
        modifier = modifier,
        onClick = {
            onAddressSelected(address)
        },
        shadowElevation = 0.4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = {
                    onAddressSelected(address)
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = address.fullName.value,
                        style = MaterialTheme.typography.titleMedium
                    )

                    AnimatedVisibility(visible = isSelected) {
                        TextButton(onClick = {
                            onEditClick(address)
                        }) {
                            Text(
                                text = "EDIT",
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = address.phoneNumber.value,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = address.readableAddress,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                AnimatedVisibility(visible = isSelected) {
                    LoadingButton(
                        text = "Deliver Here",
                        onClick = {
                            onDeliverToAddress(address)
                        },
                        loading = loading
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AddressItemPreview() {
    AddressItem()
}