package com.imaan.addresses.add_addresses


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.addresses.Address
import com.imaan.addresses.IAddressRepository
import com.imaan.common.model.City
import com.imaan.common.model.Country
import com.imaan.common.model.District
import com.imaan.common.model.FullAddress
import com.imaan.common.model.FullName
import com.imaan.common.model.ID
import com.imaan.common.model.Landmark
import com.imaan.common.model.PhoneNumber
import com.imaan.common.model.PinCode
import com.imaan.common.model.State
import com.imaan.navigation.AddressId
import com.imaan.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "UpsertAddressViewModel"
const val ADDRESS_KEY = "address"
@HiltViewModel
class UpsertAddressViewModel @Inject constructor(
    private val repository: IAddressRepository,
    private val savedStateHandle: SavedStateHandle,
): ViewModel(){
    private val _state = MutableStateFlow(UpsertAddressScreenUiState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(AddressId)?.let {
            if (it == "{$AddressId}"){
                return@let
            }
            _state.update { state ->
                state.copy(
                    addressId = ID(it)
                )
            }
            viewModelScope.launch {
                repository.getAddressByIdAsFlow(ID(it)).onEach { result ->
                    when(result){
                        is Result.Error -> {
                            Log.d(
                                TAG,
                                "Error : ${result.throwable.message}"
                            )
                        }
                        is Result.Success -> {
                            with(result.data){
                                _state.update {
                                    it.copy(
                                        fullName = fullName,
                                        landmark = landMark,
                                        city = city,
                                        district = district,
                                        state = state,
                                        country = country,
                                        pin = pinCode,
                                        phone = phoneNumber,
                                        fullAddress = fullAddress
                                    )
                                }
                            }
                        }
                    }
                }.launchIn(this)
            }

        }
    }

    fun saveAddress(){
        viewModelScope.launch {
            repository.upsertAddressWithResultFlow(_state.value.address).onEach { result ->
                when(result){
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                addressProcessed = true
                            )
                        }
                    }
                    is Result.Error -> {
                        // TODO: Log Needed
                        Log.d(
                            TAG,
                            "saveAddress: Error: ${result.throwable.message}"
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun addressProcessed(){
        _state.update {
            it.copy(
                addressProcessed = false
            )
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
                landmark = Landmark(value)
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