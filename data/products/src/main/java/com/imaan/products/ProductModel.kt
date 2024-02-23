package com.imaan.products

import com.imaan.common.model.Amount
import com.imaan.common.model.Description
import com.imaan.common.model.Discount
import com.imaan.common.model.ID
import com.imaan.common.model.Image
import com.imaan.common.model.Stocks
import com.imaan.common.model.Title
import com.imaan.remote.dto.Inventory
import com.imaan.remote.dto.Product
import com.imaan.remote.dto.ProductVariant
import java.net.URL
import java.util.UUID


data class ProductModel(
    val id: ID,
    val imageUrl: URL,
    val title: Title,
    val description: Description,
    val price: Amount,
    val stocks: Stocks,
    val discount: Discount?,
    val images: List<Image>,
    val sizes: List<SizeVariant> = getDummySizes(),
    val colors: List<ColorVariant> = getDummyColors(),
    val customVariants: List<CustomVariant> = emptyList()
) {
    val primaryImage get() = images.firstOrNull()
    constructor(
        product: Product,
        inventories: List<Inventory>,
        variants: List<ProductVariant>
    ) : this(
        id = ID(product._id.toHexString()),
        imageUrl = URL(product.thumbnailUrl),
        title = Title(product.name),
        description = Description(product.description),
        price = Amount(inventories.firstOrNull()?.price ?: 0.0),
        stocks = Stocks(inventories.firstOrNull()?.stocks ?: 0),
        discount = Discount(inventories.firstOrNull()?.discount?.toFloat() ?: 0.0f),
        images = variants.firstOrNull()?.images?.map { Image(URL(it),URL(it)) } ?: emptyList(),
        colors = variants.filter { it.hexColor != null }.map {
            object : ColorVariant {
                override val available: Boolean
                    get() = it.hexColor != null
                override val hexValue: String
                    get() = it.hexColor.toString()
            }
        },
        sizes = variants.filter { it.size != null }.map { productVariant ->
            object : SizeVariant {
                override val available: Boolean
                    get() = productVariant.size != null
                override val size: String
                    get() = productVariant.size.toString()
            }
        },
        customVariants = emptyList()
    )
}


/**
 * The Product can be of any variant of this
 * 1. Color -> Contains the hex color as value
 * 2. Size -> Contains the size as value
 * 3. Custom -> Contains some value as value*/
interface ImaanAppProductVariant {
    val available: Boolean
}

interface ColorVariant: ImaanAppProductVariant {
    val hexValue: String
    companion object {
        fun fromHex(value: String): ColorVariant {
            return object : ColorVariant {
                override val available: Boolean
                    get() = true
                override val hexValue: String
                    get() = value
            }
        }
    }
}

interface SizeVariant: ImaanAppProductVariant {
    val size: String
}

interface CustomVariant: ImaanAppProductVariant {
    val label: String
}


//
//
//enum class Size(
//    val label: String
//) {
//    SMALL("Small"),
//    MEDIUM("Medium"),
//    LARGE("Large"),
//    EXTRA_LARGE("Extra Large"),
//    EXTRA_EXTRA_LARGE("XXL")
//}

fun getDummyColors(
    hexColors: List<String> = listOf("37B5B6","FE7A36","0A1D56","E1F0DA","FDE767","D9EDBF","FF004D")
): List<ColorVariant>{
    val colors = mutableListOf<ColorVariant>()
    hexColors.forEach {
        colors.add(
            object : ColorVariant {
                override val available: Boolean
                    get() = true
                override val hexValue: String
                    get() = "#$it"
            }
        )
    }
    return colors
}

fun getDummySizes(
    availableSizes: List<String> = listOf("Small","Medium","Large","Extra Large","XXL")
): List<SizeVariant>{
    val sizes = mutableListOf<SizeVariant>()
    availableSizes.forEach {
        sizes.add(
            object : SizeVariant {
                override val available: Boolean
                    get() = true
                override val size: String
                    get() = it
            }
        )
    }
    return sizes
}


fun getDummyVariants(
    availableVariants: List<String> = listOf("2GB-16GB","4GB-32GB","6GB-64GB","8GB-128GB")
): List<CustomVariant>{
    val variants = mutableListOf<CustomVariant>()
    availableVariants.forEach {
        variants.add(
            object : CustomVariant {
                override val available: Boolean
                    get() = true
                override val label: String
                    get() = it

            }
        )
    }
    return variants
}
