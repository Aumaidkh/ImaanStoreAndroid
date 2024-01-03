package com.imaan.store.core.domain.usecase.validation

sealed class ValidationException(val message: String){
    object EmptyFullNameException: Exception("Full name can't be empty")

    object FullNameLengthException: Exception("Full name can't be less than 3 characters")

    object InvalidFullNameException: Exception("Invalid full name")

    object EmptyPhoneNumberException: Exception("Phone can't be empty")

    object PhoneNumberLengthException: Exception("Phone Number should be of 10 digits")

    object InvalidPhoneNumberException: Exception("Invalid Phone Number")
}
