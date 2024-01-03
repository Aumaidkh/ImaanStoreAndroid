package com.imaan.store.feature_auth.presentation.verify_otp

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.imaan.store.core.utils.TestDispatchers
import com.imaan.store.feature_auth.domain.model.OTP
import com.imaan.store.feature_auth.domain.repository.FakeAuthRepository
import com.imaan.store.core.presentation.utils.UiEvent
import com.imaan.store.feature_auth.presentation.register.PhoneNumber
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class VerifyOtpScreenViewModelTest {

    private lateinit var repository: FakeAuthRepository
    private lateinit var dispatchers: TestDispatchers

    private lateinit var viewModel: VerifyOtpScreenViewModel

    @Before
    fun setup(){
        repository = FakeAuthRepository()
        dispatchers = TestDispatchers()
        viewModel = VerifyOtpScreenViewModel(
            repository = repository,
            dispatchers = dispatchers
        )
    }
    @Test
    fun `onOtpChange updates state correctly`(): Unit = runBlocking {
        val otp = OTP.fromString("1234")
        val job = launch {
            viewModel.state.test {
                val emission = awaitItem()
                assertThat(emission.otp).isEqualTo(otp?.value.toString())
            }
        }
        viewModel.onOtpChange(otp!!.value.toString())
        job.join()
        job.cancel()
    }

    @Test
    fun `resendOtp request error emits error event`(): Unit = runBlocking {
        val phone = PhoneNumber("7889534384")
        val testException = Exception("Test Exception")
        repository.error = testException
        val job = launch {
            viewModel.event.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                val emission = awaitItem()
                assertThat(emission).isInstanceOf(UiEvent.Error::class.java)
            }
        }
        viewModel.resendOtp(phone.value)
        job.join()
        job.cancel()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `resendOtp requestSuccess starts counter`(): Unit = runBlocking {
        val job = launch {
            viewModel.state.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                awaitItem() // Skipping first emission as this is emitted when declaring the state
                for(i in 59 downTo 0){
                    dispatchers.testDispatcher.scheduler.advanceTimeBy(1000)
                    val emission = awaitItem()
                    val result = i==0
                    assertThat(emission.showResendOtp).isEqualTo(result)
                }
            }
        }
        viewModel.resendOtp("1234512345")
        job.join()
        job.cancel()
    }

    @Test
    fun `verifyOtp something goes wrong emits error event`(): Unit = runBlocking {
        val testException = Exception("Test Exception")
        repository.error = testException
        val job = launch {
            viewModel.event.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                val emission = awaitItem()
                assertThat(emission).isInstanceOf(UiEvent.Error::class.java)
            }
        }
        viewModel.onOtpChange("1234")
        viewModel.verifyOtp()
        job.join()
        job.cancel()
    }

    @Test
    fun `verifyOtp verification success emits success event`(): Unit = runBlocking {
        val job = launch {
            viewModel.event.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                val emission = awaitItem()
                assertThat(emission).isInstanceOf(UiEvent.Success::class.java)
            }
        }
        viewModel.onOtpChange("1234")
        viewModel.verifyOtp()
        job.join()
        job.cancel()
    }

    @Test
    fun `verifyOtp updates state correctly`(): Unit = runBlocking {
        val job = launch {
            viewModel.state.test {
                dispatchers.testDispatcher.scheduler.advanceUntilIdle()
                awaitItem()
                val emission = awaitItem()
                assertThat(emission.loading).isTrue()
                assertThat(awaitItem().loading).isFalse()
                cancelAndConsumeRemainingEvents()
            }
        }
        viewModel.onOtpChange("1234")
        viewModel.verifyOtp()
        job.join()
        job.cancel()
    }

    @After
    fun tearDown(){
        repository.error = null
    }
}