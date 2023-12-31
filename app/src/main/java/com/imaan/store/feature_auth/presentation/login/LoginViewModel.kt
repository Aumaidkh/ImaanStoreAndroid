package com.imaan.store.feature_auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.store.core.domain.DispatcherProvider
import com.imaan.store.core.domain.ValidationResult
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: IAuthRepository,
    private val dispatchers: DispatcherProvider
): ViewModel(){
    private val _state = MutableStateFlow<LoginUiState>(LoginUiState.Initial())
    val state = _state.asStateFlow()

    private val _phone = MutableStateFlow("")
    val phone get() = _phone.asStateFlow()

    fun login(){
        viewModelScope.launch(dispatchers.main) {
            val validationResult = validatePhoneNumber(_phone.value)
            if (!validationResult.isValid){
                _state.value = LoginUiState.PhoneError(error = validationResult.error)
                return@launch
            }

            val otpModel = repository.requestOtpOn(
                phone = _phone.value
            )
            otpModel.error?.let {
                _state.value = LoginUiState.Error(
                    error = it
                )
                println("Throwing error: $it")
                return@launch
            }

            otpModel.otp?.let {
                _state.value = LoginUiState.OtpSent(
                    message = "OTP (${it.value}) sent on ${_phone.value}",
                    otp = it
                )
            }
        }
    }

    fun onPhoneNumberChange(value: String){
        _phone.value = value
    }

    private fun validatePhoneNumber(phone: String): ValidationResult{
        if (phone.isEmpty()){
            return ValidationResult(
                isValid = false,
                error = Exception("Phone Number can't be empty")
            )
        }

        if (phone.length < 10){
            return ValidationResult(
                isValid = false,
                error = Exception("Phone Number should be of 10 digits")
            )
        }

        val phoneRegex = Regex("^[+]?[0-9]{10,13}\$")
        if (!phone.matches(phoneRegex)){
            return ValidationResult(
                isValid = false,
                error = Exception("Invalid Phone Number")
            )
        }

        return ValidationResult(
            isValid = true
        )
    }
}