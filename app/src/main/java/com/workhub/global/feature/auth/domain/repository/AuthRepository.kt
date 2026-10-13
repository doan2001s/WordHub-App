package com.workhub.global.feature.auth.domain.repository

import com.workhub.global.feature.auth.domain.model.AuthTokens
import com.workhub.global.feature.auth.domain.model.AuthError
import com.workhub.global.feature.auth.domain.model.LoginCredentials

interface AuthRepository {

    suspend fun login(credentials: LoginCredentials): AuthResult
}

sealed interface AuthResult {

    data class Success(val data: AuthTokens) : AuthResult

    data class Failure(val error: AuthError) : AuthResult
}
