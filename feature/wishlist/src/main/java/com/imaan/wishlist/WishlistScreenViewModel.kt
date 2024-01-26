package com.imaan.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.products.getDummyProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistScreenViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(WishlistScreenUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {

        }
    }

    private suspend fun populateState(){
        _state.update {
            it.copy(
                products = getDummyProducts(10)
            )
        }
    }
}