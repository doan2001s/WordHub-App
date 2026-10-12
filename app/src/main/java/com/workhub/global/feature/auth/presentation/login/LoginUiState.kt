package com.workhub.global.feature.auth.presentation.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isRememberChecked: Boolean = false,
    val message: String? = null
) {
    val canSubmit: Boolean
        get() = username.isNotBlank() && password.isNotBlank()
}
