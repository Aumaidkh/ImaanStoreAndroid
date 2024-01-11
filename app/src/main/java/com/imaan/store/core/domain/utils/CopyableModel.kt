package com.imaan.store.core.domain.utils

import kotlinx.serialization.Serializable

@Serializable
open class CopyableModel(
    val value: String,
    val error: String?
) {
    fun copyWith(
        value: String = this.value,
        error: String? = this.error
    ): CopyableModel {
        return CopyableModel(value, error)
    }
}

interface EmptyCompanion {
    companion object {
        val Empty = CopyableModel("",null)
    }
}