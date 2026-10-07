package com.workhub.global.feature.auth.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isRememberChecked: Boolean = false,
    val message: String? = null
) {
    val canSubmit: Boolean
        get() = username.isNotBlank() &&
                password.isNotBlank() &&
                !isLoading
}
