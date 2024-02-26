package com.imaan.product_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.imaan.products.model.IProductVariant
import com.imaan.products.model.ProductColorVariant
import com.imaan.products.model.ProductCustomVariant
import com.imaan.util.IfNotEmpty
import com.imaan.util.toColor
import com.imaan.util.toHex

@Composable
fun ProductDetailsBottomSheetComponent(
    modifier: Modifier = Modifier,
    onSelectButtonClick: () -> Unit,

    availableSizes: List<IProductVariant>?,
    availableColors: List<ProductColorVariant>?,
    availableCustomVariants: List<ProductCustomVariant>?,

    selectedColor: IProductVariant?,
    selectedSize: IProductVariant?,
    selectedVariant: IProductVariant?,

    onSizeSelected: (IProductVariant) -> Unit,
    onColorSelected: (IProductVariant) -> Unit,
    onVariantSelected: (IProductVariant) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
    ) {
        availableSizes?.IfNotEmpty {
            SizesSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                items = availableSizes,
                onSizeSelected = onSizeSelected,
                selectedSize = selectedSize
            )
        }

        availableColors?.IfNotEmpty {
            ColorsSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                items = it,
                onColorSelected = { hex ->
                    val color = it.find { color -> color.hexColor.equals(hex,true) }
                    onColorSelected(color as IProductVariant)
                },
                selectedColor = selectedColor as? ProductColorVariant
            )
        }

        availableCustomVariants?.IfNotEmpty {
            CustomVariantsSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                items = it,
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
    items: List<IProductVariant>,
    onSizeSelected: (IProductVariant) -> Unit,
    selectedSize: IProductVariant?
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
                    label = size.size?.label.toString(),
                    isSelected = selectedSize == size
                )
            }
        }
    }
}


@Composable
fun ColorsSection(
    modifier: Modifier,
    items: List<ProductColorVariant>,
    onColorSelected: (String) -> Unit,
    selectedColor: ProductColorVariant?
) {
    Column(
        modifier = modifier
    ) {
        ColorSelectorComponent(
            colors = items.map { it.hexColor.toColor() },
            selectedColor = selectedColor?.hexColor?.toColor(),
            onColorClicked = {
                // TODO(To Be Implemented )
                onColorSelected(it.toHex())
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
    items: List<ProductCustomVariant>,
    onVariantSelected: (IProductVariant) -> Unit,
    selectedVariant: IProductVariant?
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
