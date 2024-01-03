package com.imaan.store.feature_auth.presentation.register

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.imaan.store.core.domain.usecase.validation.FullNameValidator
import com.imaan.store.core.domain.usecase.validation.PhoneNumberValidator
import com.imaan.store.core.domain.usecase.validation.ValidationException
import com.imaan.store.core.utils.TestDispatchers
import com.imaan.store.feature_auth.domain.repository.FakeAuthRepository
import com.imaan.store.core.presentation.utils.UiEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


class ScreenViewModelTest{
    private lateinit var repository: FakeAuthRepository
    private lateinit var dispatchers: TestDispatchers

    private lateinit var viewModel: ScreenViewModel

    @Before
    fun setup(){
        repository = FakeAuthRepository()
        dispatchers = TestDispatchers()
        viewModel = ScreenViewModel(
            repository = repository,
            dispatchers = dispatchers,
            phoneValidator = PhoneNumberValidator(),
            fullNameValidator = FullNameValidator()
        )

    }

    @Test
    fun `onFullNameChange updates state correctly`(): Unit = runBlocking {
        val fullName = FullName("Hope")
        val job = launch {
            viewModel.state.test {
                assertThat(awaitItem().fullName).isEqualTo(fullName)
            }
        }
        viewModel.onFullNameChange(fullName.value)
        job.join()
        job.cancel()
    }

    @Test
    fun `onRegister invalid fullName updates state correctly`(): Unit = runBlocking {
        val job = launch {
            viewModel.state.test {
                awaitItem()
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                val emission = awaitItem()
                assertThat(emission.fullName.error).isNotNull()
                assertThat(emission.fullName.error).isEqualTo(ValidationException.EmptyFullNameException.message)
            }
        }
        viewModel.onRegister()
        job.join()
        job.cancel()
    }

    @Test
    fun `onRegister invalid phoneNumber updates state correctly`(): Unit = runBlocking {
        val job = launch {
            viewModel.state.test {
                awaitItem()
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                val emission = awaitItem()
                assertThat(emission.phoneNumber.error).isNotNull()
                assertThat(emission.phoneNumber.error).isEqualTo(ValidationException.EmptyPhoneNumberException.message)
            }
        }
        viewModel.onFullNameChange("Hope")
        viewModel.onRegister()
        job.join()
        job.cancel()
    }

    @Test
    fun `onRegister error while registering eventFlow emits error`(): Unit = runBlocking {
        val testException = Exception("Test Exception")
        val job = launch {
            viewModel.event.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                val emission = awaitItem()
                assertThat((emission as UiEvent.Error).throwable).isEqualTo(testException)
            }
        }
        repository.error = testException
        viewModel.onFullNameChange("Hope")
        viewModel.onPhoneNumberChange("1234512345")
        viewModel.onRegister()
        job.join()
        job.cancel()
    }

    @Test
    fun `onRegister works correctly`(): Unit = runBlocking {
        val phone = PhoneNumber("1234512345")
        val name = FullName("Hope")

        val stateJob = launch {
            viewModel.state.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                awaitItem()
                assertThat(awaitItem().loading).isTrue()
                assertThat(awaitItem().loading).isFalse()
                cancelAndConsumeRemainingEvents()
            }
        }

        viewModel.onPhoneNumberChange(phone.value)
        viewModel.onFullNameChange(name.value)
        viewModel.onRegister()
        stateJob.join()
        stateJob.cancel()
    }

    @Test
    fun `register event emits success when registration is done`(): Unit = runBlocking {
        val phone = PhoneNumber("1234512345")
        val name = FullName("Hope")
        val eventJob = launch {
            viewModel.event.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                val emission = awaitItem()
                assertThat(emission).isInstanceOf(UiEvent.Success::class.java)
            }
        }
        viewModel.onPhoneNumberChange(phone.value)
        viewModel.onFullNameChange(name.value)
        viewModel.onRegister()
        eventJob.join()
        eventJob.cancel()
    }

    @After
    fun tearDown(){
        repository.error = null
    }
}