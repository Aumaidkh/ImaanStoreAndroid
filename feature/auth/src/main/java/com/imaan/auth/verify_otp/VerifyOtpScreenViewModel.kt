package com.imaan.auth.verify_otp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.common.model.OTP
import com.imaan.common.model.PhoneNumber
import com.imaan.common.repository.IOtpRepository
import com.imaan.common.wrappers.Result
import com.imaan.user.IUserRepository
import com.imaan.user.UserModel
import com.imaan.util.IDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "VerifyOtpScreenViewMode"

@HiltViewModel
class VerifyOtpScreenViewModel @Inject constructor(
    private val repository: IOtpRepository,
    private val userRepository: IUserRepository,
    private val dispatchers: IDispatcherProvider,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val MAX_RESEND_TIME_IN_SECONDS = 59

    private val _state = MutableStateFlow(VerifyOtpUiState())
    val state = _state.asStateFlow()

    private var registerNewUser: Boolean = false

    init {
        viewModelScope.launch(dispatchers.main) {
            registerNewUser = savedStateHandle.get<Boolean>("RegisterNewUser") == true
            startCounter()
        }
    }

    fun requestOtpOn(phone: PhoneNumber) {
        viewModelScope.launch(dispatchers.main) {
            repository.requestOtpOn(phone).also {
                when (it) {
                    is Result.Error -> {
                        _state.update { state ->
                            state.copy(
                                errorMessage = it.throwable.message
                            )
                        }
                    }

                    is Result.Success -> {
                        startCounter()
                    }
                }
            }
        }
    }

    private suspend fun startCounter() {
        for (i in MAX_RESEND_TIME_IN_SECONDS downTo 0) {
            delay(1000)
            _state.update {
                it.copy(
                    otpStatusMessage = if (i == 0) "Resend OTP" else "Resend OTP in $i seconds",
                    showResendOtp = (i == 0)
                )
            }
        }
    }

    fun verifyOtp() {
        viewModelScope.launch(dispatchers.main) {
            val otp = _state.value.otp ?: return@launch
            _state.update {
                it.copy(
                    loading = true
                )
            }

            repository.verifyOtp(
                phone = PhoneNumber(""),
                otp = otp
            ).also { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                errorMessage = result.throwable.message
                            )
                        }
                    }

                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                authenticated = result.data == IOtpRepository.VerificationStatus.SUCCESS
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun registerNewUser(userModel: UserModel) {
        withContext(dispatchers.io) {
            userRepository.registerUser(userModel)
        }
    }

    fun onOtpChange(value: String) {
        _state.update {
            it.copy(
                otp = OTP(value)
            )
        }
    }

    fun onErrorShown() {
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }

}
