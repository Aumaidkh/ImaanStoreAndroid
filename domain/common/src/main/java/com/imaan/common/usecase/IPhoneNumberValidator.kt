package com.imaan.common.usecase

import com.imaan.common.ValidationResult
import com.imaan.common.model.PhoneNumber

interface IPhoneNumberValidator {

    operator fun invoke(phoneNumber: PhoneNumber): ValidationResult

    object EmptyPhoneNumberException: Exception("phone number can't be empty")
    object InvalidPhoneNumberException: Exception("invalid phone number exception")
    object PhoneNumberLengthException: Exception("phone number should be of 10 digits only")
}
