package com.imaan.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.common.repository.IOtpRepository
import com.imaan.common.usecase.IPhoneNumberValidator
import com.imaan.common.wrappers.Result
import com.imaan.util.IDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LoginViewModel"
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val otpRepository: IOtpRepository,
    private val dispatchers: IDispatcherProvider,
    private val phoneValidator: IPhoneNumberValidator
): ViewModel(){
    private val _state = MutableStateFlow(LoginScreenUiState())
    val state = _state.asStateFlow()

    fun requestOtp(){
        viewModelScope.launch(dispatchers.main) {
            val validationResult = phoneValidator(_state.value.phone)
            if (!validationResult.isValid){
                _state.update { state ->
                    state.copy(
                        phone = state.phone.copy(
                            error = validationResult.error?.message
                        )
                    )
                }
                return@launch
            }
            _state.update {
                it.copy(
                    loading = true
                )
            }
            otpRepository.requestOtpOn(
                phone = _state.value.phone
            ).also { result ->
                when(result){
                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                message = result.throwable.message
                            )
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                message = "OTP was sent to ${_state.value.phone.value}",
                                otp = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun onPhoneNumberChange(value: String){
        _state.update { state ->
            state.copy(
                phone = state.phone.copy(
                    error = null,
                    value = value
                )
            )
        }
    }

}