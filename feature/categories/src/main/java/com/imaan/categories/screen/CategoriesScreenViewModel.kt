package com.imaan.categories.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.categories.ICategoryRepository
import com.imaan.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CategoriesScreenViewMod"
@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(
    private val repository: ICategoryRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CategoriesScreenUiState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<CategoriesScreenEvents>()
    val eventsFlow get() = eventChannel.receiveAsFlow()

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            repository.fetchCategoriesFlow().collect { emission ->
                when(emission){
                    is Result.Success -> {
                        Log.d(
                            TAG,
                            "Categories: ${emission.data.size}"
                        )
                        _state.update {
                            it.copy(
                                categories = emission.data
                            )
                        }
                    }
                    is Result.Error -> {
                        Log.d(
                            TAG,
                            "Error: ${emission.throwable.message}"
                        )
                        eventChannel.send(CategoriesScreenEvents.Error(emission.throwable.message.toString()))
                    }
                }
            }
        }
    }
}