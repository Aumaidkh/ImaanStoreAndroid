package com.imaan.store.core.domain.usecase.validation

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class PhoneNumberValidatorTest{

    private lateinit var validator: PhoneNumberValidator

    @Before
    fun setup(){
        validator = PhoneNumberValidator()
    }

    @Test
    fun `returns invalid when input is empty`() {
        validator.invoke("")
            .also {
                Truth.assertThat(it.isValid).isFalse()
                Truth.assertThat(it.error).isInstanceOf(ValidationException.EmptyPhoneNumberException::class.java)
            }
    }

    @Test
    fun `returns invalid when input is less than 10 characters`() {
        validator.invoke("123412")
            .also {
                Truth.assertThat(it.isValid).isFalse()
                Truth.assertThat(it.error).isInstanceOf(ValidationException.PhoneNumberLengthException::class.java)
            }
    }

    @Test
    fun `returns invalid when input is not a number`() {
        validator.invoke("78895343ag")
            .also {
                Truth.assertThat(it.isValid).isFalse()
                Truth.assertThat(it.error).isInstanceOf(ValidationException.InvalidPhoneNumberException::class.java)
            }
    }

    @Test
    fun `returns valid when input is a valid phone number`() {
        validator.invoke("1231231231")
            .also {
                Truth.assertThat(it.isValid).isTrue()
                Truth.assertThat(it.error).isNull()
            }
    }
}