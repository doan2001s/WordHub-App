package com.workhub.global.feature.auth.data.remote

import com.workhub.global.core.network.ApiResult
import com.workhub.global.core.network.safeApiCall

class AuthRemoteDataSourceImpl(
    private val authApi: AuthApi
) : AuthRemoteDataSource {

    override suspend fun login(
        request: LoginRequestDto
    ): ApiResult<LoginResponseDto> {
        return safeApiCall {
            authApi.login(request)
        }
    }
}
