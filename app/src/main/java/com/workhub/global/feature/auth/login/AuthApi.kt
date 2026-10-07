package com.workhub.global.feature.auth.login

import com.workhub.global.core.network.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): ApiResponse<LoginResponse>
}
