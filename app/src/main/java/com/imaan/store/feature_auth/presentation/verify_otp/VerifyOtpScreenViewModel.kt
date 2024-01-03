package com.imaan.store.feature_auth.presentation.verify_otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.store.core.domain.DispatcherProvider
import com.imaan.store.feature_auth.domain.model.AuthenticationStatus
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import com.imaan.store.core.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "VerifyOtpScreenViewMode"
@HiltViewModel
class VerifyOtpScreenViewModel @Inject constructor(
    private val repository: IAuthRepository,
    private val dispatchers: DispatcherProvider
): ViewModel(){
    private val MAX_RESEND_TIME_IN_SECONDS = 5

    private val _state = MutableStateFlow(VerifyOtpUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val event get() = _event.receiveAsFlow()

    init {
        viewModelScope.launch(dispatchers.main) {
            startCounter()
        }
    }

    fun resendOtp(phone: String){
        viewModelScope.launch(dispatchers.main) {
            repository.requestOtpOn(phone).also {
                it.error?.let { error ->
                    _event.send(UiEvent.Error(error))
                    return@launch
                }

                it.otp?.let {
                    startCounter()
                }
            }
        }
    }

    private suspend fun startCounter(){
        for (i in MAX_RESEND_TIME_IN_SECONDS downTo 0){
            delay(1000)
            _state.update {
                it.copy(
                    otpStatusMessage = if (i == 0) "Resend OTP" else "Resend OTP in $i seconds",
                    showResendOtp = (i == 0)
                )
            }
        }
    }

    fun verifyOtp(){
        viewModelScope.launch(dispatchers.main) {
            val otp = _state.value.otp
            setLoading(true)
            repository.verifyOtp(otp).also { status ->
                when(status){
                    is AuthenticationStatus.Error -> {
                        setLoading(false)
                        _event.send(
                            UiEvent.Error(status.throwable)
                        )
                    }
                    is AuthenticationStatus.Success -> {
                        setLoading(false)
                        _event.send(
                            UiEvent.Success(null)
                        )
                    }
                }
            }

        }
    }

    fun onOtpChange(value: String){
        _state.update {
            it.copy(
                otp = value
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

sealed interface Event{
    data class Navigation(
        val route: String
    ): Event

    data class Error(
        val throwable: Throwable
    ): Event{
        val message get() = throwable.message.toString()
    }
}