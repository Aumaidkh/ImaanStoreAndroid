package com.imaan.profile.edit_profile

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.common.model.Email
import com.imaan.common.model.FullName
import com.imaan.common.model.PhoneNumber
import com.imaan.components.ImaanAppButton
import com.imaan.design_system.components.input_fields.ImaanAppInputField
import com.imaan.profile.ProfileComponent
import java.net.URI

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    state: EditProfileUiState = EditProfileUiState(),
    onSave: () -> Unit = {},
    onPhotoChange: (URI?) -> Unit = {},
    onFullNameChange: (FullName) -> Unit = {},
    onEmailChange: (Email) -> Unit = {},
    onPhoneNumberChange: (PhoneNumber) -> Unit = {},
    onBackPressed: () -> Unit = {}
){
    val backCallback = rememberUpdatedState(onBackPressed)
    val onBackPressedCallback = rememberUpdatedState(
        object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                backCallback.value.invoke()
            }
        }
    )

    // Add the onBackPressedCallback to the back dispatcher
    val context = LocalContext.current
    DisposableEffect(context) {
        val callback = onBackPressedCallback.value
        val dispatcher = (context as ComponentActivity).onBackPressedDispatcher
        dispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileComponent()
        ImaanAppInputField(
            hint = "Full Name",
            value = state.fullName.value,
            error = state.fullName.error,
            onValueChange = {
                onFullNameChange(FullName(it,null))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ImaanAppInputField(
            hint = "Email",
            value = state.email?.value ?: "",
            onValueChange = {
                onEmailChange(Email(it))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ImaanAppInputField(
            hint = "Phone Number",
            value = state.phoneNumber.value,
            onValueChange = {
                onPhoneNumberChange(PhoneNumber(it))
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        ImaanAppButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Save Details",
            onClick = onSave,
            loading = state.loading
        )
    }
}

@Preview
@Composable
fun EditProfileScreenPreview(){
    EditProfileScreen()
}