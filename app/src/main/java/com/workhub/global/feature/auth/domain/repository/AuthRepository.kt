package com.workhub.global.feature.auth.domain.repository

import com.workhub.global.feature.auth.domain.model.AuthTokens
import com.workhub.global.feature.auth.domain.model.LoginCredentials

interface AuthRepository {

    suspend fun login(credentials: LoginCredentials): AuthResult<AuthTokens>
}

sealed interface AuthResult<out T> {

    data class Success<T>(val data: T) : AuthResult<T>

    data class Failure(val message: String) : AuthResult<Nothing>
}
