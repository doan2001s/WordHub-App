package com.workhub.global.feature.auth.data.repository

import com.workhub.global.core.network.ApiResult
import com.workhub.global.core.network.ApiContract
import com.workhub.global.core.session.TokenStorage
import com.workhub.global.feature.auth.data.remote.AuthRemoteDataSource
import com.workhub.global.feature.auth.data.remote.LoginRequestDto
import com.workhub.global.feature.auth.domain.model.AuthError
import com.workhub.global.feature.auth.domain.model.AuthTokens
import com.workhub.global.feature.auth.domain.model.LoginCredentials
import com.workhub.global.feature.auth.domain.repository.AuthRepository
import com.workhub.global.feature.auth.domain.repository.AuthResult

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val tokenStorage: TokenStorage
) : AuthRepository {

    override suspend fun login(credentials: LoginCredentials): AuthResult {
        return when (val result = remoteDataSource.login(
            LoginRequestDto(
                username = credentials.username,
                password = credentials.password,
                rememberMe = credentials.rememberMe
            )
        )) {
            is ApiResult.Success -> {
                val tokens = result.data?.tokens
                val accessToken = tokens?.accessToken

                if (accessToken.isNullOrBlank()) {
                    AuthResult.Failure(AuthError.EmptyToken)
                } else {
                    tokenStorage.saveTokens(
                        accessToken = accessToken,
                        refreshToken = tokens.refreshToken
                    )

                    AuthResult.Success(
                        AuthTokens(
                            accessToken = accessToken,
                            refreshToken = tokens.refreshToken
                        )
                    )
                }
            }

            is ApiResult.Error -> AuthResult.Failure(result.toAuthError())
        }
    }

    private fun ApiResult.Error.toAuthError(): AuthError {
        return when (code) {
            ApiContract.Code.NETWORK_ERROR -> AuthError.Network
            ApiContract.Code.SERVER_ERROR -> AuthError.Server
            ApiContract.Code.UNAUTHORIZED,
            ApiContract.Code.BAD_REQUEST -> AuthError.InvalidCredentials
            else -> AuthError.Unknown
        }
    }
}
