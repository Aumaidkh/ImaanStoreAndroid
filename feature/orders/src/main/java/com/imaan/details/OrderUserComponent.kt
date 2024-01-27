package com.imaan.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.design_system.R
import com.imaan.design_system.components.buttons.CircularIcon
import com.imaan.design_system.components.views.CircularAsyncImage
import com.imaan.user.UserModel


@Composable
internal fun UserComponent(
    modifier: Modifier,
    user: UserModel
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Thank you for Shopping",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularAsyncImage(
                    modifier = Modifier
                        .size(50.dp),
                    imageURL = user.profilePicUrl,
                    onClick = {}
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = user.username.value,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = user.phone.value,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            CircularIcon(
                modifier = Modifier
                    .size(48.dp),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                iconResId = R.drawable.flag,
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}