package com.imaan.addresses.view_addresses.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    top = 24.dp,
                    end = 24.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 1.dp
                ),
                shape = CircleShape
            ) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = com.imaan.design_system.R.drawable.home),
                        contentDescription = "",
                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = address.title.value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(visible = isSelected) {
                TextButton(onClick = { onEditClick(address)}) {
                    Text(text = "Edit")
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 18.dp
                )
                .padding(
                    horizontal = 24.dp
                ),
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    text = address.fullName.value,
                    style = MaterialTheme.typography.bodyMedium
                )
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
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f),
                verticalArrangement = Arrangement.Top
            ) {

                Image(
                    modifier = Modifier
                        .padding(8.dp),
                    painter = painterResource(id = com.imaan.design_system.R.drawable.location_map),
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview
@Composable
fun AddressItemComponentPreview() {
    AddressItemComponent()
}