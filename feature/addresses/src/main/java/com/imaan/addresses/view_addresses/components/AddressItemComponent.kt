package com.imaan.addresses.view_addresses.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.addresses.Address
import com.imaan.addresses.dummyAddress


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressItemComponent(
    modifier: Modifier = Modifier,
    address: Address = dummyAddress(),
    isSelected: Boolean = false,
    onAddressSelected: (Address) -> Unit = {},
    onDeliverToAddress: (Address) -> Unit = {},
    onEditClick: (Address) -> Unit = {},
    loading: Boolean = false
) {
    Card(onClick = {onAddressSelected(address) }, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp, horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = address.fullName.value,
                    style = MaterialTheme.typography.bodyLarge
                )
                AnimatedVisibility(visible = isSelected) {
                    TextButton(onClick = { onEditClick(address)}) {
                        Text(text = "Edit")
                    }
                }
            }
            Text(
                text = address.phoneNumber.value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.7f
                ),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(vertical = 4.dp)
            )
            Text(
                text = address.readableAddress,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.7f
                ),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 8.dp)
            )

            AnimatedVisibility(visible = isSelected) {
                FilledTonalButton(onClick = { onDeliverToAddress(address) }) {
                    Text(text = "Deliver Here")
                }
            }
        }
    }
}