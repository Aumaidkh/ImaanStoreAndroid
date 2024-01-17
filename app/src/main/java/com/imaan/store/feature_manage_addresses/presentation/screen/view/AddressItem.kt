package com.imaan.store.feature_manage_addresses.presentation.screen.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.R
import com.imaan.store.core.domain.model.Address
import com.imaan.store.core.domain.model.dummyAddress
import com.imaan.store.design_system.composables.LoadingButton
import com.imaan.store.design_system.ui.theme.ImaanTheme

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
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = address.fullName.value,
                            style = MaterialTheme.typography.titleMedium
                        )

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = address.phoneNumber.value,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.6f
                        )
                    )
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = address.readableAddress,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.6f
                        )
                    )
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

                Divider(
                    thickness = 0.3.dp
                )
            }
        }
    }
}

@Preview
@Composable
fun AddressItemPreview() {
    AddressItem()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewAddressItem(
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

@Composable
fun AddressLine(
    modifier: Modifier = Modifier,
    iconResId: Int,
    text: String,

    ) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .width(48.dp),
            painter = painterResource(id = iconResId),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                alpha = 0.8f
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.6f
                )
            )
        )
    }
}

@Preview
@Composable
fun NewAddressItemPreview() {
    ImaanTheme {
        NewAddressItem(
            isSelected = true
        )
    }
}