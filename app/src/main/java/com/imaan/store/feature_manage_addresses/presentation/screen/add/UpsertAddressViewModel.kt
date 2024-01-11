package com.imaan.store.feature_manage_addresses.presentation.screen.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.imaan.store.core.domain.model.Address
import com.imaan.store.core.domain.model.Address.Companion.decodeFromString
import com.imaan.store.core.domain.model.City
import com.imaan.store.core.domain.model.Country
import com.imaan.store.core.domain.model.District
import com.imaan.store.core.domain.model.FullAddress
import com.imaan.store.core.domain.model.FullName
import com.imaan.store.core.domain.model.LandMark
import com.imaan.store.core.domain.model.PinCode
import com.imaan.store.core.domain.model.State
import com.imaan.store.feature_auth.presentation.register.PhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val TAG = "UpsertAddressViewModel"
const val ADDRESS_KEY = "address"
@HiltViewModel
class UpsertAddressViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _state = MutableStateFlow(UpsertAddressScreenUiState())
    val state = _state.asStateFlow()

    private var address: Address? = null

    init {
        savedStateHandle.get<String>(ADDRESS_KEY)?.let {
            if (it == "{$ADDRESS_KEY}"){
                return@let
            }
            address = it.decodeFromString()
            prePopulateState()
        }
    }

    private fun prePopulateState(){
        address?.let {
            _state.update { state ->
                state.copy(
                    fullName = it.fullName,
                    landmark = it.landMark,
                    city = it.city,
                    district = it.district,
                    state = it.state,
                    country = it.country,
                    pin = it.pinCode,
                    phone = it.phoneNumber,
                    fullAddress = it.fullAddress
                )
            }
        }
    }

    fun onFullNameChange(value: String){
        _state.update {
            it.copy(
                fullName = FullName(value,null)
            )
        }
    }

    fun onLandmarkChange(value: String){
        _state.update {
            it.copy(
                landmark = LandMark(value)
            )
        }
    }

    fun onCityChange(value: String){
        _state.update {
            it.copy(
                city = City(value)
            )
        }
    }

    fun onDistrictChange(value: String){
        _state.update {
            it.copy(
                district = District(value)
            )
        }
    }

    fun onStateChange(value: String){
        _state.update {
            it.copy(
                state = State(value)
            )
        }
    }

    fun onPhoneChange(value: String){
        _state.update {
            it.copy(
                phone = PhoneNumber(value)
            )
        }
    }

    fun onCountryChange(value: String){
        _state.update {
            it.copy(
                country = Country(value)
            )
        }
    }

    fun onPostalCodeChange(value: String){
        _state.update {
            it.copy(
                pin = PinCode(value)
            )
        }
    }

    fun onFullAddressChange(value: String){
        _state.update {
            it.copy(
                fullAddress = FullAddress(value)
            )
        }
    }
}