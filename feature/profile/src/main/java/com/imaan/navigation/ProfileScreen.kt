package com.imaan.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.components.ImaanTopAppBar
import com.imaan.design_system.components.dialogs.ImaanActionDialog
import com.imaan.profile.ViewProfileScreen
import com.imaan.profile.edit_profile.EditProfileScreen


fun NavGraphBuilder.profileNavigationProvider(
    onNavigateToAddresses: () -> Unit,
    onNavigateToEditProfile: () -> Unit,
    paddingValues: PaddingValues,
    onBackClick: () -> Unit
) {
    navigation(
        startDestination = Profile.ViewProfileRoute.route,
        route = Profile.feature
    ){
        composable(
            route = Profile.ViewProfileRoute.route
        ) {
            var dialogState by remember {
                mutableStateOf(ProfileScreenDialogState())
            }
            ViewProfileScreen(
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                onEditProfileClick = onNavigateToEditProfile,
                onAddressesClick = onNavigateToAddresses,
                onWishlistClick = { /*TODO*/ },
                onOrderHistoryClick = { /*TODO*/ },
                onDeleteAccountClick = {
                    dialogState = dialogState.copy(
                        showDeleteAccountDialog = true
                    )
                },
                onSignOutClick = {
                    dialogState = dialogState.copy(
                        showLogoutDialog = true
                    )
                }
            )
            AnimatedVisibility(dialogState.showDeleteAccountDialog) {
                ImaanActionDialog(
                    title = "Delete Account",
                    body = "Are you sure you want to delete your account?. This operation can't be undone",
                    positiveButtonText = "Yes",
                    negativeButtonText = "No",
                    onPositiveButtonClick = {},
                    onNegativeButtonClick = {
                        dialogState = dialogState.copy(
                            showDeleteAccountDialog = false
                        )
                    },
                    onDismissRequest = {
                        dialogState = dialogState.copy(
                            showDeleteAccountDialog = false
                        )
                    }

                )
            }

            AnimatedVisibility(
                visible = dialogState.showLogoutDialog,
            ) {
                ImaanActionDialog(
                    title = "Sign Out",
                    body = "Are you sure you want to sign out from your account?",
                    positiveButtonText = "Yes",
                    negativeButtonText = "No",
                    onPositiveButtonClick = {},
                    onNegativeButtonClick = {
                        dialogState = dialogState.copy(
                            showLogoutDialog = false
                        )
                    },
                    onDismissRequest = {
                        dialogState = dialogState.copy(
                            showLogoutDialog = false
                        )
                    }

                )
            }
        }

        composable(
            route = Profile.EditProfile.route
        ) {
            var showSaveChangesDialog by remember {
                mutableStateOf(false)
            }
            EditProfileScreen(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    )
                    .padding(32.dp),
                onBackPressed = {
                    showSaveChangesDialog = true
                }
            )

            AnimatedVisibility(visible = showSaveChangesDialog) {
                ImaanActionDialog(
                    title = "Unsaved Changes",
                    body = "All your changes are unsaved. Do you want to save them?",
                    positiveButtonText = "Yes",
                    negativeButtonText = "Discard",
                    onDismissRequest = { showSaveChangesDialog = false },
                    onNegativeButtonClick = {
                        showSaveChangesDialog = false
                        onBackClick()
                    },
                    onPositiveButtonClick = {
                        //Todo(Save Changes and Hide Dialog)
                        showSaveChangesDialog = false
                    }
                )
            }
        }
    }
}

private fun NavHostController.getScreenName(): String {
    val route = this.currentBackStackEntry?.destination?.route
    println("Route: $route")
    return when (this.currentBackStackEntry?.destination?.route) {
        Profile.EditProfile.route -> "Edit Profile"
        else -> "Profile"
    }
}


data class ProfileScreenDialogState(
    var showLogoutDialog: Boolean = false,
    var showDeleteAccountDialog: Boolean = false
)
