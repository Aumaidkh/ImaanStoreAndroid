package com.imaan.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.components.ImaanTopAppBar

@Composable
internal fun ViewProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileScreenUiState = ProfileScreenUiState(),
    onEditProfileClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onWishlistClick: () -> Unit,
    onOrderHistoryClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    onSignOutClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.weight(1.3f))
        ProfileComponent(
            userModel = state.user
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 24.dp),
        ) {
            ProfileOption.values().forEach { option ->
                ProfileOptionComponent(
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .fillMaxWidth(),
                    option = option,
                    onClick = {
                        when (option) {
                            ProfileOption.EditProfile -> onEditProfileClick()
                            ProfileOption.ShippingAddresses -> {
                                onAddressesClick()
                            }

                            ProfileOption.Wishlist -> onWishlistClick()
                            ProfileOption.OrderHistory -> onOrderHistoryClick()
                            ProfileOption.DeleteAccount -> onDeleteAccountClick()
                            ProfileOption.SignOut -> onSignOutClick()
                            else -> {}
                        }
                    }
                )
            }
        }

    }

}

@Preview
@Composable
fun ViewProfileScreenPreview() {
    ViewProfileScreen(
        onEditProfileClick = { /*TODO*/ },
        onAddressesClick = { /*TODO*/ },
        onWishlistClick = { /*TODO*/ },
        onOrderHistoryClick = {/*TODO*/},
        onSignOutClick = {/*TODO*/},
        onDeleteAccountClick = {/*TODO*/}
    )
}