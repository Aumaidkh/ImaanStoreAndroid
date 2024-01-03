package com.imaan.store.feature_auth.data.network.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestOtpResponse(
    @SerialName("otp")
    val otp: String?
)
