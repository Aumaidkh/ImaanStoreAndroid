package com.imaan.store.core.domain.usecase.validation

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FullNameValidatorTest {

    private lateinit var validator: FullNameValidator

    @Before
    fun setup(){
        validator = FullNameValidator()
    }
    @Test
    fun `returns invalid when input is empty`() {
        validator.invoke("")
            .also {
                assertThat(it.isValid).isFalse()
                assertThat(it.error).isInstanceOf(ValidationException.EmptyFullNameException::class.java)
            }
    }

    @Test
    fun `returns invalid when input is less than 3 characters`() {
        validator.invoke("He")
            .also {
                assertThat(it.isValid).isFalse()
                assertThat(it.error).isInstanceOf(ValidationException.FullNameLengthException::class.java)
            }
    }

    @Test
    fun `returns invalid when input is not a correct name`() {
        validator.invoke("He11")
            .also {
                assertThat(it.isValid).isFalse()
                assertThat(it.error).isInstanceOf(ValidationException.InvalidFullNameException::class.java)
            }
    }

    @Test
    fun `returns valid when input is correct name`() {
        validator.invoke("Nick Fury")
            .also {
                assertThat(it.isValid).isTrue()
                assertThat(it.error).isNull()
            }
    }
}