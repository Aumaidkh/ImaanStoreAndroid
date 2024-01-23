package com.imaan.common.model


data class PhoneNumber(
    val value: String = "",
    val error: String? = null
) {

    companion object{
        val Empty = PhoneNumber(
            ""
        )
    }
    fun copyWith(
        value: String = this.value,
        error: String? = this.error
    ): FullName {
        return FullName(value, error)
    }
}