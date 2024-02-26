package com.imaan.products.model

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
import org.mongodb.kbson.ObjectId
import java.net.URL

interface IProductModel {
    val id: ID
    val title: Title
    val description: Description
    val image: Image
    val category: String
    val price: Amount
}

data class DetailedProductModel(
    override val id: ID,
    override val title: Title,
    override val description: Description,
    override val image: Image,
    override val category: String = "",
    val variants: List<IProductVariant>
): IProductModel {

    override val price: Amount
        get() = variants.firstOrNull()?.price ?: Amount.ZERO
    val variantImages get() = run {
        val images = mutableListOf<Image>()
        variants.forEach {
            images.addAll(it.images)
        }
        images.toList()
    }

    val sizes get() = run {
        variants.filter { it.size?.label != null }
    }

    val colors get() = run {
        variants.filterIsInstance<ProductColorVariant>()
    }

    val customVariants get() = run {
        variants.filterIsInstance<ProductCustomVariant>()
    }

    constructor(
        product: Product,
        inventory: Map<ObjectId,Inventory>,
        variants: List<ProductVariant>
    ): this(
        id = ID(product._id.toHexString()),
        title = Title(product.name),
        description = Description(product.description),
        image = Image(thumbnail = URL(product.thumbnailUrl), original = URL(product.thumbnailUrl)), // Default Image URLs
        variants = variants.map {
            if (it.hexColor != null){
                 ProductColorVariant(
                     label = it.label,
                     stocks = Stocks(inventory[it._id]?.stocks ?: 0),
                     price = Amount(inventory[it._id]?.price ?: 0.0),
                     discount = Discount(inventory[it._id]?.discount?.toFloat() ?: 0.0f),
                     images = it.images.map { url -> Image(URL(url),URL(url)) },
                     size = it.size?.let { sizeString -> Size.values().find { size -> size.label.equals(sizeString,true) } },
                     hexColor = it.hexColor?:"",
                     id = ID(it._id.toHexString())
                 )
            } else {
                ProductCustomVariant(
                    label = it.label,
                    stocks = Stocks(inventory[it._id]?.stocks ?: 0),
                    price = Amount(inventory[it._id]?.price ?: 0.0),
                    discount = Discount(inventory[it._id]?.discount?.toFloat() ?: 0.0f),
                    images = it.images.map { url -> Image(URL(url),URL(url)) },
                    size = it.size?.let { sizeString -> Size.values().find { size -> size.label.equals(sizeString,true) } },
                    id = ID(it._id.toHexString())
                 )
            }
        }
    )
}