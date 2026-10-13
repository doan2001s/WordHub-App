package com.workhub.global.feature.auth.domain.usecase

import com.workhub.global.feature.auth.domain.model.AuthError
import com.workhub.global.feature.auth.domain.model.LoginCredentials
import com.workhub.global.feature.auth.domain.repository.AuthResult
import com.workhub.global.feature.auth.domain.repository.AuthRepository
import com.workhub.global.feature.auth.domain.validator.LoginValidationResult
import com.workhub.global.feature.auth.domain.validator.LoginValidator

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val loginValidator: LoginValidator
) {

    suspend operator fun invoke(credentials: LoginCredentials): AuthResult {
        return when (loginValidator.validate(credentials)) {
            LoginValidationResult.Valid -> authRepository.login(credentials)
            LoginValidationResult.InvalidUsername -> {
                AuthResult.Failure(AuthError.InvalidUsername)
            }
            LoginValidationResult.InvalidPassword -> {
                AuthResult.Failure(AuthError.InvalidPassword)
            }
        }
    }
}
