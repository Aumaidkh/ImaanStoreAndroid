package com.imaan.profile.edit_profile

import com.imaan.common.model.Email
import com.imaan.common.model.FullName
import com.imaan.common.model.PhoneNumber
import java.net.URI

data class EditProfileUiState(
    val fullName: FullName = FullName.Empty,
    val email: Email? = null,
    val phoneNumber: PhoneNumber = PhoneNumber.Empty,
    val imageURI: URI? = null,
    val loading: Boolean = false
)
