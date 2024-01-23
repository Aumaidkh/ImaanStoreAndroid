package com.imaan.base_files

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open abstract class BaseScreenViewModel<VIEW_STATE: BaseScreenState> : ViewModel(){

    private val _state = MutableStateFlow(initializeState())
    val state get() = _state.asStateFlow()

    abstract fun initializeState(): BaseScreenState

}