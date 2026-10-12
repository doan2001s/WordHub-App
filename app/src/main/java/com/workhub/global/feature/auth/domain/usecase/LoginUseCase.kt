package com.workhub.global.feature.auth.domain.usecase

import com.workhub.global.feature.auth.domain.model.LoginCredentials
import com.workhub.global.feature.auth.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(credentials: LoginCredentials) =
        authRepository.login(credentials)
}
