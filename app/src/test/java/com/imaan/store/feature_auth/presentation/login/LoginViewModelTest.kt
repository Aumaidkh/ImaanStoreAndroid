package com.imaan.store.feature_auth.presentation.login

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.imaan.store.core.domain.usecase.validation.PhoneNumberValidator
import com.imaan.store.core.domain.usecase.validation.ValidationException
import com.imaan.store.core.utils.TestDispatchers
import com.imaan.store.feature_auth.domain.repository.FakeAuthRepository
import com.imaan.store.feature_auth.presentation.UiEvent
import com.imaan.store.feature_auth.presentation.register.PhoneNumber
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var repository: FakeAuthRepository
    private lateinit var dispatchers: TestDispatchers
    private lateinit var phoneNumberValidator: PhoneNumberValidator

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup(){
        repository = FakeAuthRepository()
        dispatchers = TestDispatchers()
        phoneNumberValidator = PhoneNumberValidator()
        viewModel = LoginViewModel(
            repository = repository,
            dispatchers = dispatchers,
            phoneValidator = phoneNumberValidator
        )

    }

    @Test
    fun `onPhoneChange updates state correctly`(): Unit = runBlocking {
        val phone = PhoneNumber("1231231231")
        val job = launch {
            viewModel.state.test {
                val emission = awaitItem()
                assertThat(emission.phone).isEqualTo(phone)
            }
        }

        viewModel.onPhoneNumberChange(phone.value)
        job.join()
        job.cancel()
    }

    @Test
    fun `requestOtp phone invalid updates state correctly`(): Unit = runBlocking {
        val job = launch {
            viewModel.state.test {
                awaitItem()
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                assertThat(awaitItem().phone.error).isEqualTo(ValidationException.EmptyPhoneNumberException.message)
                cancelAndIgnoreRemainingEvents()
            }
        }
        viewModel.requestOtp()
        job.join()
        job.cancel()
    }

    @Test
    fun `requestOtp otpResult error emits error event`(): Unit = runBlocking {
        val testException = Exception("Test Exception")
        val job = launch {
            viewModel.eventFlow.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                val emission = awaitItem()
                assertThat(emission).isInstanceOf(UiEvent.Error::class.java)
                assertThat((emission as UiEvent.Error).throwable.message).isEqualTo(testException.message)
            }
        }
        repository.error = testException
        viewModel.onPhoneNumberChange("1234123412")
        viewModel.requestOtp()
        job.join()
        job.cancel()
    }

    @Test
    fun `requestOtp otpResult success emits success event updates state correctly`(): Unit = runBlocking {
        val phone = PhoneNumber("1234123412")
        val job = launch {
            viewModel.state.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                assertThat(awaitItem().phone).isEqualTo(phone)
                assertThat(awaitItem().loading).isTrue()
                assertThat(awaitItem().loading).isFalse()
                cancelAndConsumeRemainingEvents()
            }
        }
        val job1 = launch {
            viewModel.eventFlow.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                assertThat(awaitItem()).isInstanceOf(UiEvent.Success::class.java)
            }
        }
        viewModel.onPhoneNumberChange(phone.value)
        viewModel.requestOtp()
        job.join()
        job1.join()
        job.cancel()
        job1.cancel()
    }
}