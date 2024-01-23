package com.imaan.common.usecase

import com.imaan.common.ValidationResult
import com.imaan.common.model.FullName
import javax.inject.Inject

internal class FullNameValidatorImpl @Inject constructor(): IFullNameValidator {

    private val FULL_NAME_REGEX = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}"
    override fun invoke(name: FullName): ValidationResult {
        name.value.ifEmpty {
            return ValidationResult(
                isValid = false,
                error = IFullNameValidator.EmptyFullNameException
            )
        }

        if (name.value.length < 3) {
            return ValidationResult(
                isValid = false,
                error = IFullNameValidator.FullNameLengthException
            )
        }

        if (!name.value.matches(Regex(FULL_NAME_REGEX))) {
            return ValidationResult(
                isValid = false,
                error = IFullNameValidator.InvalidFullNameException
            )
        }

        return ValidationResult(
            isValid = true,
            error = null
        )
    }
}