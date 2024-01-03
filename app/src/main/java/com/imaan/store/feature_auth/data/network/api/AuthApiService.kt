package com.imaan.store.feature_auth.data.network.api

import com.imaan.store.feature_auth.data.network.dto.response.RequestOtpResponse
import com.imaan.store.feature_auth.data.network.utils.ApiResponse
import com.imaan.store.feature_auth.data.network.utils.requestOtp
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.url

class AuthApiService {

    private val client by lazy {
        HttpClient(Android) {
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        }
    }

    suspend fun requestOtp(phoneNumber: String): ApiResponse<RequestOtpResponse> {
        return try {
            val response: RequestOtpResponse = client.get{
                url(requestOtp)
            }
            ApiResponse.Success(data = response)
        } catch (e: Exception){
            ApiResponse.Error(error = e)
        }
    }
}