package com.imaan.design_system.components.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.common.model.Title
import com.imaan.design_system.R
import com.imaan.design_system.components.buttons.CircularIcon

@Composable
fun ImaanAppDropDownMenu(
    modifier: Modifier = Modifier,
    iconResId: Int = R.drawable.filter,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    expanded: Boolean = false,
    label: String? = null,
    onClick: () -> Unit = {},
    dropDownMenuItems: List<DropDownMenuItem> = emptyList(),
    onMenuItemClick: (DropDownMenuItem) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {

    val rotationState by animateFloatAsState(targetValue = if (expanded) 90f else 0f,
        label = "iconRotation"
    )

    Column(
        modifier = modifier
    ) {
        Surface(
            shape = MaterialTheme.shapes.small,
            tonalElevation = 0.4.dp,
            onClick = onClick
        ) {
            Row(
                modifier = Modifier
                    .padding(end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CircularIcon(
                    modifier = Modifier
                        .rotate(rotationState),
                    iconResId = iconResId,
                    tint = iconTint,
                    onClick = onClick
                )

                label?.let {
                    Text(text = it)
                }
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest) {
            dropDownMenuItems.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it.label.value)
                    },
                    onClick = { onMenuItemClick(it) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = it.iconResId),
                            contentDescription = "",
                        )
                    }
                )
            }
        }
    }

}

@Preview
@Composable
fun FilterComponentPreview() {
    Column {
        ImaanAppDropDownMenu(expanded = false)
        Spacer(modifier = Modifier.height(34.dp))
        ImaanAppDropDownMenu(expanded = true)
    }
}

interface DropDownMenuItem {
    val iconResId: Int
    val label: Title
}


