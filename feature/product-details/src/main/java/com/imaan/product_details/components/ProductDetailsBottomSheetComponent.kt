package com.imaan.product_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.design_system.components.buttons.ImaanAppButton
import com.imaan.design_system.components.views.ColorSelectorComponent
import com.imaan.products.ColorVariant
import com.imaan.products.CustomVariant
import com.imaan.products.SizeVariant
import com.imaan.util.toColor
import com.imaan.util.toHex

@Composable
fun ProductDetailsBottomSheetComponent(
    sizes: List<SizeVariant>? = null,
    colors: List<ColorVariant> = emptyList(),
    variants: List<CustomVariant> = emptyList(),

    selectedSize: SizeVariant?,
    selectedColor: ColorVariant?,
    selectedVariant: CustomVariant?,

    onSizeSelected: (SizeVariant) -> Unit,
    onColorSelected: (ColorVariant) -> Unit,
    onVariantSelected: (CustomVariant) -> Unit,

    onSelectButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
    ) {
        if (sizes?.isNotEmpty() == true){
            SizesSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                items = sizes,
                onSizeSelected = onSizeSelected,
                selectedSize = selectedSize
            )
        }
        if (colors.isNotEmpty()){
            ColorsSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                items = colors,
                onColorSelected = onColorSelected,
                selectedColor = selectedColor
            )
        }
        if (variants.isNotEmpty()){
            CustomVariantsSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                items = variants,
                onVariantSelected = onVariantSelected,
                selectedVariant = selectedVariant
            )
        }
        ImaanAppButton(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth(),
            text = "Select this size",
            onClick = {
                onSelectButtonClick()
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SizesSection(
    modifier: Modifier,
    items: List<SizeVariant>,
    onSizeSelected: (SizeVariant) -> Unit,
    selectedSize: SizeVariant?
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Select Size",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEach { size ->
                SizeSelectorComponent(
                    modifier = Modifier,
                    onClick = {
                        onSizeSelected(size)
                    },
                    label = size.size,
                    isSelected = selectedSize == size
                )
            }
        }
    }
}


@Composable
fun ColorsSection(
    modifier: Modifier,
    items: List<ColorVariant>,
    onColorSelected: (ColorVariant) -> Unit,
    selectedColor: ColorVariant?
) {
    Column(
        modifier = modifier
    ) {
        ColorSelectorComponent(
            colors = items.map { it.hexValue.toColor() },
            selectedColor = selectedColor?.hexValue?.toColor(),
            onColorClicked = {
                onColorSelected(ColorVariant.fromHex(it.toHex()))
            },
            label = "Select Color",
            boxSize = 80.dp
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomVariantsSection(
    modifier: Modifier,
    items: List<CustomVariant>,
    onVariantSelected: (CustomVariant) -> Unit,
    selectedVariant: CustomVariant?
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Select Model",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEach { variant ->
                SizeSelectorComponent(
                    modifier = Modifier,
                    onClick = {
                        onVariantSelected(variant)
                    },
                    label = variant.label,
                    isSelected = variant == selectedVariant
                )
            }
        }
    }
}
