package com.imaan.store.feature_auth.domain.model

data class OTP(
    val value: Int,
    val message: Message? = null
) {
    companion object {
        fun fromString(value: String): OTP?{
            return try {
                val otp = value.toInt()
                OTP(otp)
            } catch (e: Exception){
                e.printStackTrace()
                null
            }
        }
    }
}

@JvmInline
value class Message(val value: String)