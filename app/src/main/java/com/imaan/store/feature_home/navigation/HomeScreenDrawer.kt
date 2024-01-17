package com.imaan.store.feature_home.navigation

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.imaan.store.design_system.composables.CircularIcon
import com.imaan.store.feature_home.presentation.composables.ProfileComponent
import com.imaan.store.feature_home.presentation.home.HomeScreen
import kotlinx.coroutines.launch
import androidx.compose.ui.util.lerp
import com.imaan.store.core.domain.model.ProductModel
import com.imaan.store.feature_home.domain.models.CategoryModel
import com.imaan.store.feature_home.presentation.home.HomeScreenUiState

enum class DrawerState {
    OPEN,
    CLOSE
}

private val DrawerWidth = 300.dp

@Composable
fun HomeScreenDrawer(
    state: HomeScreenUiState = HomeScreenUiState(),
    onCartClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(),
    onSeeAllCategoriesClick: () -> Unit = {},
    onAddToCart: (ProductModel) -> Unit = {},
    onCategoryClicked: (CategoryModel) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        var drawerState by remember {
            mutableStateOf(DrawerState.CLOSE)
        }

        val translationX = remember {
            Animatable(0f)
        }

        val rotationZ = remember {
            Animatable(0f   )
        }

        val drawerWidth = with(LocalDensity.current){
            DrawerWidth.toPx()
        }

        translationX.updateBounds(0f,drawerWidth)

        val coroutineScope = rememberCoroutineScope()

        fun toggleDrawerState() {
            coroutineScope.launch {
                if (drawerState == DrawerState.OPEN) {
                    translationX.animateTo(0f)
                    rotationZ.animateTo(0f)
                } else {
                    translationX.animateTo(drawerWidth)
                    rotationZ.animateTo(-10f)
                }

                drawerState = if (drawerState == DrawerState.OPEN) {
                    DrawerState.CLOSE
                } else {
                    DrawerState.OPEN
                }
            }
        }

        HomeNavDrawerContents(
            onCloseClick = {
                toggleDrawerState()
            }
        )

        HomeScreen(
            modifier = Modifier
                .graphicsLayer {
                    this.translationX = translationX.value
                    val scale = lerp(1f, 0.7f, translationX.value / drawerWidth)
                    this.scaleX = scale
                    this.scaleY = scale
                    val roundedCorners = lerp(0f, 32.dp.toPx(), translationX.value / drawerWidth)
                    this.shape = RoundedCornerShape(roundedCorners)
                    this.clip = true
                    this.shadowElevation = 42f
                    this.rotationZ = lerp(
                        start = 0f,
                        stop = -10f,
                        fraction = translationX.value/drawerWidth
                    )
                },
            state = state,
            onMenuClick = {
                toggleDrawerState()
            },
            paddingValues = paddingValues,
            onCartClick = onCartClick,
            onSeeAllCategoriesClick = onSeeAllCategoriesClick,
            onAddToCart = onAddToCart,
            onCategoryClicked = onCategoryClicked

        )
    }
}

@Composable
fun HomeNavDrawerContents(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.systemBars))
        CircularIcon(
            modifier = Modifier
                .padding(12.dp),
            containerColor = MaterialTheme.colorScheme.background.copy(
                alpha = 0.3f
            ),
            onClick = {onCloseClick()}
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
}