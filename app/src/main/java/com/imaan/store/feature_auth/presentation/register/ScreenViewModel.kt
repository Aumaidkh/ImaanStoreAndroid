package com.imaan.store.feature_auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.store.core.domain.DispatcherProvider
import com.imaan.store.core.domain.usecase.validation.FullNameValidator
import com.imaan.store.core.domain.usecase.validation.PhoneNumberValidator
import com.imaan.store.feature_auth.domain.model.AuthenticationStatus
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

private const val TAG = "ScreenViewModel"
@HiltViewModel
class ScreenViewModel @Inject constructor(
    private val repository: IAuthRepository,
    private val dispatchers: DispatcherProvider,
    private val phoneValidator: PhoneNumberValidator,
    private val fullNameValidator: FullNameValidator
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterScreenUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val event get() = _event.receiveAsFlow()

    fun onFullNameChange(value: String) {
        _state.update {
            it.copy(
                fullName = it.fullName.copy(
                    value = value.removeSuffix(" "),
                    error = null
                ),
                buttonEnabled = isButtonEnabled(
                    phone = it.phoneNumber.value,
                    name = it.fullName.value
                )
            )
        }
    }

    fun onPhoneNumberChange(value: String) {
        _state.update {
            it.copy(
                phoneNumber = it.phoneNumber.copy(
                    value = value.removeSuffix(" "),
                    error = null
                ),
                buttonEnabled = isButtonEnabled(
                    phone = it.phoneNumber.value,
                    name = it.fullName.value
                )
            )
        }
    }

    fun onRegister() {
        viewModelScope.launch(dispatchers.main) {
            fullNameValidator(_state.value.fullName.value)
                .also { result ->
                    if (!result.isValid){
                        _state.update { state ->
                            state.copy(
                                fullName = state.fullName.copy(
                                    error = result.errorMessage
                                )
                            )
                        }
                        return@launch
                    }
                }
            phoneValidator(_state.value.phoneNumber.value)
                .also { result ->
                    if (!result.isValid){
                        _state.update { state ->
                            state.copy(
                                phoneNumber = state.phoneNumber.copy(
                                    error = result.errorMessage
                                )
                            )
                        }
                        return@launch
                    }
                }

            // Fields are valid
            showLoading(true)

            repository.register(
                phone = _state.value.phoneNumber.value,
                fullName = _state.value.fullName.value
            ).also { authStatus ->
                when(authStatus){
                    is AuthenticationStatus.Error -> {
                        showLoading(false)
                        _event.send(
                            UiEvent.Error(
                                throwable = authStatus.throwable
                            )
                        )
                    }
                    is AuthenticationStatus.Success -> {
                        showLoading(false)
                        _event.send(
                            UiEvent.Success(
                                authStatus.data
                            )
                        )
                    }
                }
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        _state.update {
            it.copy(
                loading = loading
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