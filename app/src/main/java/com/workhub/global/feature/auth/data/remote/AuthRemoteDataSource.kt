package com.workhub.global.feature.auth.data.remote

import com.workhub.global.core.network.ApiResult

interface AuthRemoteDataSource {

    suspend fun login(request: LoginRequestDto): ApiResult<LoginResponseDto>
}
