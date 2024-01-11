package com.imaan.store.feature_manage_addresses.presentation.screen.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.design_system.composables.LoadingButton
import com.imaan.store.design_system.ui.theme.ImaanTheme
import com.imaan.store.feature_manage_addresses.presentation.composables.AddressInputField
import com.imaan.store.feature_payment.presentation.utils.PhoneNumberVisualTransformation

@Composable
fun UpsertAddressForm(
    modifier: Modifier = Modifier,
    state: UpsertAddressScreenUiState = UpsertAddressScreenUiState(),
    onFullNameChange: (String) -> Unit = {},
    onLandmarkChange: (String) -> Unit = {},
    onCityChange: (String) -> Unit = {},
    onDistrictChange: (String) -> Unit = {},
    onStateChange: (String) -> Unit = {},
    onPhoneChange: (String) -> Unit = {},
    onCountryChange: (String) -> Unit = {},
    onPinCodeChange: (String) -> Unit = {},
    onFullAddressChange: (String) -> Unit = {},
    onSaveClick: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .imePadding()
    ) {

        AddressInputField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.fullName.value,
            error = state.fullName.error,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            onValueChange = onFullNameChange,
            title = "Full Name (Required)*"
        )

        AddressInputField(
            modifier = Modifier
                .fillMaxWidth(),
            hint = "+91",
            value = state.phone.value,
            error = state.phone.error,
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next,
            onValueChange = onPhoneChange,
            title = "Phone number (Required)*"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            ) {
                AddressInputField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.pin.value,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                    onValueChange = onPinCodeChange,
                    title = "Pincode (Required)*",
                    maxLength = 6
                )

                AddressInputField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.state.value,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    onValueChange = onStateChange,
                    title = "State (Required)*"
                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AddressInputField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.city.value,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    onValueChange = onCityChange,
                    title = "City (Required)*"
                )

                AddressInputField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.district.value,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    onValueChange = onDistrictChange,
                    title = "District (Required)*",
                )
            }
        }

        AddressInputField(
            modifier = Modifier
                .fillMaxWidth(),
            hint = "House No., Building Name (Required)*",
            value = state.fullAddress.value,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Default,
            maxLines = 3,
            onValueChange = onFullAddressChange,
            title = "Full Address (Required)*"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AddressInputField(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                value = state.landmark?.value ?: "",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                onValueChange = onLandmarkChange,
                title = "Landmark (Optional)"
            )

            AddressInputField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.country.value,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                onValueChange = onCountryChange,
                title = "Country (Required)*"
            )

        }

        LoadingButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 60.dp),
            text = "Save Address",
            onClick = onSaveClick
        )
    }
}

@Preview
@Composable
fun UpsertAddressFormPreview() {
    ImaanTheme {
        UpsertAddressForm()
    }
}