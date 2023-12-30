package com.imaan.store.feature_auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.store.core.domain.DispatcherProvider
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: IAuthRepository,
    private val dispatchers: DispatcherProvider
): ViewModel(){
    private val _state = MutableStateFlow<LoginUiState>(LoginUiState.Initial())
    val state = _state.asStateFlow()

    private val _phoneNumberFlow = MutableStateFlow("")

    fun login(){
        viewModelScope.launch(dispatchers.main) {
            val otpModel = repository.requestOtpOn(
                phone = _phoneNumberFlow.value
            )
            otpModel.error?.let {
                _state.value = LoginUiState.Error(
                    error = it
                )
                println("Throwing error: $it")
                return@launch
            }

            otpModel.otp?.let {
                println("Sending OTP")
                _state.value = LoginUiState.OtpSent(
                    message = "OTP (${it.value}) sent on ${_phoneNumberFlow.value}",
                )
            }
        }
    }

    fun onPhoneNumberChange(value: String){
        _phoneNumberFlow.value = value
    }
}