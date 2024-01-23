package com.imaan.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ProfileOptionComponent(
    modifier: Modifier = Modifier,
    option: ProfileOption = ProfileOption.EditProfile,
    onClick: () -> Unit = {}
){
    Box(
        modifier = modifier
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                painter = painterResource(id = option.iconResId),
                contentDescription = "${option.label} Icon",
                tint = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.6f
                )
            )
            Text(
                text = option.label,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 19.sp
                ),
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.9f
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "${option.label} Icon",
                    tint = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.4f
                    )
                )
            }
        }
    }
}



@Preview
@Composable
fun ProfileOptionComponentPreview(){
    ProfileOptionComponent()
}