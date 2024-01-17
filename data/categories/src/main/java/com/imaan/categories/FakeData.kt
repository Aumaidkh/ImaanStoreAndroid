package com.imaan.categories

import com.imaan.common.model.ID
import com.imaan.common.model.Title
import java.net.URL


fun getDummyCategories(count: Int = 1): List<CategoryModel> {
    val categories = mutableListOf<CategoryModel>()
    repeat(count) {
        categories.add(CategoryModel(
            id = ID(it.toString()),
            label = Title("Category $it"),
            backgroundImageUrl = URL(
                "https://images.pexels.com/photos/1114883/pexels-photo-1114883.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
            iconUrl = URL("https://fontawesome.com/icons/house?f=classic&s=solid")
        ))
    }
    return categories
}