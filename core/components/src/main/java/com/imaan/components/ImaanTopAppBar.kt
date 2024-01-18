package com.imaan.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Badge
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.imaan.user.UserModel

enum class Type {
    WithProfilePic,
    WithoutProfilePic
}

@Composable
fun ImaanTopAppBar(
    modifier: Modifier = Modifier,
    user: UserModel? = null,
    title: String? = null,
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    cartItemsCount: Int? = 4,
    loading: Boolean = false,
    actionIconResId: Int? = null,
    type: Type = Type.WithoutProfilePic
) {
    when(type){
        Type.WithProfilePic -> {
            ImaanHomeTopAppBar(
                user = user,
                modifier = modifier,
                onMenuClick = onNavigationClick,
                onCartClick = onActionClick,
                cartItemsCount = cartItemsCount,
                actionIconResId = actionIconResId
            )
        }
        Type.WithoutProfilePic -> {
            ImaanCustomTopAppBar(
                title = title ?: "",
                onBackPressed = onNavigationClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ImaanHomeTopAppBar(
    modifier: Modifier = Modifier,
    user: UserModel? = null,
    onMenuClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    cartItemsCount: Int? = 4,
    loading: Boolean = false,
    actionIconResId: Int?
) {
    TopAppBar(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 8.dp),
        title = {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "Hello, ${user?.username?.value}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "How are you today",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        navigationIcon = {
            CircularImage(
                modifier = Modifier
                    .size(45.dp),
                imageURL = user?.profilePicUrl,
                onClick = {
                    onMenuClick()
                }
            )
        },
        actions = {
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                CircularIcon(
                    iconResId = actionIconResId,
                    containerColor = MaterialTheme.colorScheme.primary,
                    tint = Color.White,
                    onClick = onCartClick
                )
                cartItemsCount?.let {
                    Badge {
                        Text(text = "$it")
                    }
                }
            }
        },
        colors = TopAppBarDefaults
            .topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ImaanCustomTopAppBar(
    onBackPressed: () -> Unit = {},
    onMorePressed: () -> Unit = {},
    title: String = "My Cart",
    showActions: Boolean = false
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            CircularIcon(
                modifier = Modifier
                    .padding(24.dp),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                onClick = onBackPressed,
                iconResId = null
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back Button",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            if (showActions){
                CircularIcon(
                    modifier = Modifier
                        .padding(24.dp),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    onClick = onMorePressed,
                    iconResId = null
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp),
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Back Button",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}