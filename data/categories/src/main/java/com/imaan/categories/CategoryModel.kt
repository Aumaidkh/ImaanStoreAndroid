package com.imaan.categories

import com.imaan.common.model.ID
import com.imaan.common.model.Title
import java.net.URL

data class CategoryModel(
    val id: ID,
    val label: Title,
    val backgroundImageUrl: URL,
    val iconUrl: URL
)