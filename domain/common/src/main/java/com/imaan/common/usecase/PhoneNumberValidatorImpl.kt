package com.imaan.common.usecase

import com.imaan.common.ValidationResult
import com.imaan.common.model.PhoneNumber
import javax.inject.Inject

internal class PhoneNumberValidatorImpl @Inject constructor(): IPhoneNumberValidator {

    private val PHONE_REGEX by lazy {
        Regex("^[+]?[0-9]{10,13}\$")
    }
    override fun invoke(phoneNumber: PhoneNumber): ValidationResult {
        if (phoneNumber.value.isEmpty()){
            return ValidationResult(
                isValid = false,
                error = IPhoneNumberValidator.EmptyPhoneNumberException
            )
        }

        if (phoneNumber.value.length < 10){
            return ValidationResult(
                isValid = false,
                error = IPhoneNumberValidator.PhoneNumberLengthException
            )
        }

        if (!phoneNumber.value.matches(PHONE_REGEX)){
            return ValidationResult(
                isValid = false,
                error = IPhoneNumberValidator.InvalidPhoneNumberException
            )
        }

        return ValidationResult(
            isValid = true
        )
    }
}