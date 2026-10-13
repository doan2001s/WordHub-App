package com.workhub.global.feature.auth.domain.validator

import com.workhub.global.feature.auth.domain.model.LoginCredentials

sealed interface LoginValidationResult {
    data object Valid : LoginValidationResult
    data object InvalidUsername : LoginValidationResult
    data object InvalidPassword : LoginValidationResult
}

class LoginValidator {

    fun validate(credentials: LoginCredentials): LoginValidationResult {
        return when {
            credentials.username.isBlank() -> LoginValidationResult.InvalidUsername
            credentials.password.isBlank() -> LoginValidationResult.InvalidPassword
            else -> LoginValidationResult.Valid
        }
    }
}
