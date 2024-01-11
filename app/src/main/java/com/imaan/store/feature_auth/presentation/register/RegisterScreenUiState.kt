package com.imaan.store.feature_auth.presentation.register

import kotlinx.serialization.Serializable

data class RegisterScreenUiState(
    val loading: Boolean = false,
    val buttonEnabled: Boolean = false,
    val fullName: FullName = FullName(),
    val email: Email = Email(),
    val phoneNumber: PhoneNumber = PhoneNumber()
)

data class FullName(
    val value: String = "",
    val error: String? = null
) {
    fun copyWith(
        value: String = this.value,
        error: String? = this.error
    ): FullName {
        return FullName(value, error)
    }
}

@Serializable
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

data class Email(
    val value: String = "",
    val error: String? = null
) {
    fun copyWith(
        value: String = this.value,
        error: String? = this.error
    ): FullName {
        return FullName(value, error)
    }
}