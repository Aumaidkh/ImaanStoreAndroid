package com.imaan.common.usecase

import com.imaan.common.ValidationResult
import com.imaan.common.model.FullName

interface IFullNameValidator {

    operator fun invoke(name: FullName): ValidationResult
    object EmptyFullNameException: Exception("full name can't be empty")
    object FullNameLengthException: Exception("full name should be greater than 2 chars")
    object InvalidFullNameException: Exception("invalid full name exception")
}