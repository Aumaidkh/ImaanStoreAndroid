package com.imaan.store.core.domain.usecase.validation

import com.imaan.store.core.domain.utils.ValidationResult
import javax.inject.Inject

class FullNameValidator @Inject constructor() {

    private val FULL_NAME_REGEX = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}"

    operator fun invoke(param: String): ValidationResult {
        param.ifEmpty {
            return ValidationResult(
                isValid = false,
                error = ValidationException.EmptyFullNameException
            )
        }

        if (param.length < 3) {
            return ValidationResult(
                isValid = false,
                error = ValidationException.FullNameLengthException
            )
        }

        if (!param.matches(Regex(FULL_NAME_REGEX))) {
            return ValidationResult(
                isValid = false,
                error = ValidationException.InvalidFullNameException
            )
        }

        return ValidationResult(
            isValid = true,
            error = null
        )
    }
}