package com.imaan.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.common.model.Username
import com.imaan.common.usecase.IFullNameValidator
import com.imaan.common.usecase.IPhoneNumberValidator
import com.imaan.common.wrappers.Result
import com.imaan.user.IUserRepository
import com.imaan.user.UserModel
import com.imaan.util.IDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ScreenViewModel"
@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val repository: IUserRepository,
    private val dispatchers: IDispatcherProvider,
    private val phoneValidator: IPhoneNumberValidator,
    private val fullNameValidator: IFullNameValidator
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterScreenUiState())
    val state = _state.asStateFlow()

    fun onFullNameChange(value: String) {
        _state.update {
            it.copy(
                fullName = it.fullName.copy(
                    value = value.removeSuffix(" "),
                    error = null
                ),
                buttonEnabled = isButtonEnabled(
                    phone = it.phoneNumber.value,
                    name = value
                )
            )
        }
    }

    fun onPhoneNumberChange(value: String) {
        _state.update {
            it.copy(
                phoneNumber = it.phoneNumber.copy(
                    value = value,
                    error = null
                ),
                buttonEnabled = isButtonEnabled(
                    phone = value,
                    name = it.fullName.value
                )
            )
        }
    }

    fun onRegister() {
        viewModelScope.launch(dispatchers.main) {
            fullNameValidator(_state.value.fullName)
                .also { result ->
                    if (!result.isValid){
                        _state.update { state ->
                            state.copy(
                                fullName = state.fullName.copy(
                                    error = result.error?.message
                                )
                            )
                        }
                        return@launch
                    }
                }
            phoneValidator(_state.value.phoneNumber)
                .also { result ->
                    if (!result.isValid){
                        _state.update { state ->
                            state.copy(
                                phoneNumber = state.phoneNumber.copy(
                                    error = result.error?.message
                                )
                            )
                        }
                        return@launch
                    }
                }

            _state.update {
                it.copy(
                    loading = true
                )
            }
            repository.registerUser(
                user = UserModel(
                    username = Username(_state.value.fullName.value),
                    phone = _state.value.phoneNumber
                )
            ).also { result ->
                when(result){
                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                error = result.throwable.message
                            )
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                otp = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun onErrorShown(){
        _state.update {
            it.copy(
                error = null
            )
        }
    }

    fun otpConsumed(){
        _state.update {
            it.copy(
                otp = null
            )
        }
    }


    private fun isButtonEnabled(
        phone: String,
        name: String
    ): Boolean {
        return phone.isNotEmpty() && name.isNotEmpty()
    }
}