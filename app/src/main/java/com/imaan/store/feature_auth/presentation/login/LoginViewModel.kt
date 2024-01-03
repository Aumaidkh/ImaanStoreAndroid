package com.imaan.store.feature_auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.store.core.domain.DispatcherProvider
import com.imaan.store.core.domain.usecase.validation.PhoneNumberValidator
import com.imaan.store.feature_auth.domain.model.Message
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import com.imaan.store.core.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LoginViewModel"
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: IAuthRepository,
    private val dispatchers: DispatcherProvider,
    private val phoneValidator: PhoneNumberValidator
): ViewModel(){
    private val _state = MutableStateFlow(LoginScreenUiState())
    val state = _state.asStateFlow()


    private val _eventChannel = Channel<UiEvent>()
    val eventFlow get() = _eventChannel.receiveAsFlow()


    fun requestOtp(){
        viewModelScope.launch(dispatchers.main) {
            val validationResult = phoneValidator(_state.value.phone.value)
            if (!validationResult.isValid){
                _state.update { state ->
                    state.copy(
                        phone = state.phone.copy(
                            error = validationResult.errorMessage
                        )
                    )
                }
                return@launch
            }
            setLoading(true)
            val otpResult = repository.requestOtpOn(
                phone = _state.value.phone.value
            )
            otpResult.error?.let {
                setLoading(false)
                _eventChannel.send(
                    element = UiEvent.Error(throwable = it)
                )
                return@launch
            }

            otpResult.otp?.let {
                setLoading(false)
                _eventChannel.send(
                    element = UiEvent.Success(
                        data = it.copy(
                            message = Message("OTP sent to ${_state.value.phone.value}")
                        )
                    )
                )
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

    private fun setLoading(loading: Boolean){
        _state.update {
            it.copy(
                loading = loading
            )
        }
    }
}