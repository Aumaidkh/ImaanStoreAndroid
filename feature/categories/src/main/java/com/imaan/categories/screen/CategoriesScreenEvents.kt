package com.imaan.categories.screen

sealed interface CategoriesScreenEvents {
    data class Error(val message: String): CategoriesScreenEvents
}