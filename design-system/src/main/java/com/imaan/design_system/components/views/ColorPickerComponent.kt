package com.imaan.design_system.components.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSelectorComponent(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    selectedColor: Color?,
    onColorClicked: (Color) -> Unit,
    horizontalSpacing: Dp = 12.dp,
    boxSize: Dp = 50.dp,
    label: String = "Colors",
    labelStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(
        fontSize = 18.sp
    ),
    seeMoreCount: Int? = null,
    onSeeMore: () -> Unit = {},
    showSelectedColorOnComponent: Boolean = false
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = label,
            style = labelStyle
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(horizontalSpacing)
        ) {
            item {
                if (showSelectedColorOnComponent && selectedColor != null){
                    OutlinedCard(
                        modifier = Modifier
                            .size(boxSize)
                            .border(
                                width = 2.dp,
                                shape = MaterialTheme.shapes.medium,
                                color = MaterialTheme.colorScheme.background
                            ),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = selectedColor
                        ),
                        border = CardDefaults.outlinedCardBorder(true),
                        content = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Selected",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = Color.White
                                    )
                                )
                            }

                        },
                        elevation = CardDefaults.elevatedCardElevation(
                            defaultElevation = 3.dp
                        )
                    )
                }
            }
            items(
                items = colors
            ) {
                OutlinedCard(
                    modifier = Modifier
                        .size(boxSize),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.outlinedCardColors(
                        containerColor = it
                    ),
                    border = CardDefaults.outlinedCardBorder(selectedColor == it),
                    content = { },
                    onClick = {
                        onColorClicked(it)
                    }
                )
            }
            item {
                if (seeMoreCount != null) {
                    Card(
                        modifier = Modifier
                            .size(boxSize),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.outlinedCardColors(),
                        content = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "+$seeMoreCount",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        },
                        onClick = {
                            onSeeMore()
                        },
                        elevation = CardDefaults.elevatedCardElevation(
                            defaultElevation = 4.dp
                        )
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ColorSelectorComponentPreview() {
    var selectedColor by remember {
        mutableStateOf(Color.Red)
    }
    ColorSelectorComponent(
        colors = listOf(
            Color.Red,
            Color.Blue,
            Color.Cyan,
            Color.Magenta
        ),
        selectedColor = selectedColor,
        onColorClicked = {
            selectedColor = it
        }
    )
}