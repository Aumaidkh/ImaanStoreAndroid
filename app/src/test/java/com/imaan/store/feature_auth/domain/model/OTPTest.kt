package com.imaan.store.feature_auth.domain.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class OTPTest {

    @Test
    fun `fromString creates otp`() {
        val otp = OTP.fromString("1234")
        assertThat(otp).isEqualTo(OTP(1234))
    }

    @Test
    fun `fromString input not number returns null`() {
        val otp = OTP.fromString("aaaa")
        assertThat(otp).isNull()
    }
}