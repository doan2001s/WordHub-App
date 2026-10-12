package com.workhub.global.feature.auth.data.repository

import com.workhub.global.core.network.ApiResult
import com.workhub.global.core.network.safeApiCall
import com.workhub.global.core.session.SecureTokenManager
import com.workhub.global.feature.auth.data.remote.AuthApi
import com.workhub.global.feature.auth.data.remote.LoginRequest
import com.workhub.global.feature.auth.domain.model.AuthTokens
import com.workhub.global.feature.auth.domain.model.LoginCredentials
import com.workhub.global.feature.auth.domain.repository.AuthRepository
import com.workhub.global.feature.auth.domain.repository.AuthResult

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val tokenManager: SecureTokenManager
) : AuthRepository {

    override suspend fun login(credentials: LoginCredentials): AuthResult<AuthTokens> {
        return when (val result = safeApiCall {
            authApi.login(
                LoginRequest(
                    username = credentials.username,
                    password = credentials.password,
                    rememberMe = credentials.rememberMe
                )
            )
        }) {
            is ApiResult.Success -> {
                val response = result.data
                val accessToken = response?.accessToken

                if (accessToken.isNullOrBlank()) {
                    AuthResult.Failure("Đăng nhập thành công nhưng máy chủ không trả về token")
                } else {
                    tokenManager.saveTokens(
                        accessToken = accessToken,
                        refreshToken = response.refreshToken
                    )
                    AuthResult.Success(
                        AuthTokens(
                            accessToken = accessToken,
                            refreshToken = response.refreshToken
                        )
                    )
                }
            }

            is ApiResult.Error -> AuthResult.Failure(result.message)
        }
    }
}
