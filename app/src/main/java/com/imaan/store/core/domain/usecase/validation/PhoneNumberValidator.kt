package com.imaan.store.core.domain.usecase.validation

import com.imaan.store.core.domain.utils.ValidationResult
import javax.inject.Inject

class PhoneNumberValidator @Inject constructor(){

    private val PHONE_REGEX by lazy {
        Regex("^[+]?[0-9]{10,13}\$")
    }
     operator fun invoke(param: String): ValidationResult {
         if (param.isEmpty()){
             return ValidationResult(
                 isValid = false,
                 error = ValidationException.EmptyPhoneNumberException
             )
         }

         if (param.length < 10){
             return ValidationResult(
                 isValid = false,
                 error = ValidationException.PhoneNumberLengthException
             )
         }

         if (!param.matches(PHONE_REGEX)){
             return ValidationResult(
                 isValid = false,
                 error = ValidationException.InvalidPhoneNumberException
             )
         }

         return ValidationResult(
             isValid = true
         )
     }
}