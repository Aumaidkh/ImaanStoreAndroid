package com.imaan.store.core.utils

import com.imaan.store.core.domain.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DefaultDispatchers @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val mainDispatcher: CoroutineDispatcher,
    private val defaultDispatcher: CoroutineDispatcher,
): DispatcherProvider {
    override val io: CoroutineDispatcher
        get() = ioDispatcher
    override val main: CoroutineDispatcher
        get() = mainDispatcher
    override val default: CoroutineDispatcher
        get() = defaultDispatcher
}