package com.imaan.store.feature_home.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.design_system.composables.CircularIcon
import com.imaan.store.design_system.ui.theme.ImaanTheme
import com.imaan.store.feature_home.presentation.composables.ProfileComponent
import com.imaan.store.feature_home.presentation.home.HomeScreen

@Composable
fun NavDrawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Open),
    onCloseIconClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.primary,
                content = {
                    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.systemBars))
                    CircularIcon(
                        modifier = Modifier
                            .padding(12.dp),
                        containerColor = MaterialTheme.colorScheme.background.copy(
                            alpha = 0.3f
                        ),
                        onClick = onCloseIconClick
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(8.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                    ProfileComponent(
                        modifier = Modifier
                            .padding(horizontal = 50.dp, vertical = 32.dp)
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    navDrawerItems.forEach { item ->
                        NavigationDrawerItem(
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding),
                            label = {
                                Text(
                                    text = item.label,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            selected = false,
                            onClick = { },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent,
                                selectedContainerColor = Color.Transparent,
                                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                unselectedIconColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.iconResId),
                                    contentDescription = "Home Icon"
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    NavigationDrawerItem(
                        modifier = Modifier
                            .padding(
                                horizontal = 28.dp
                            ),
                        label = {
                            Text(
                                text = "Sign Out",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        selected = false,
                        onClick = { },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color.Transparent,
                            selectedContainerColor = Color.Transparent,
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                contentDescription = "Home Icon"
                            )
                        }
                    )
                }
            )


        },
        gesturesEnabled = false,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NavDrawerPreview() {
    ImaanTheme {
        NavDrawer() {
            HomeScreen()
        }
    }
}
