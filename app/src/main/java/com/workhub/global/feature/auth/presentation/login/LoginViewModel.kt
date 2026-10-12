package com.workhub.global.feature.auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.workhub.global.core.ui.BaseViewModel
import com.workhub.global.feature.auth.domain.model.LoginCredentials
import com.workhub.global.feature.auth.domain.repository.AuthResult
import com.workhub.global.feature.auth.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginUiState>(initialState = LoginUiState()) {

    fun onUsernameChanged(username: String) = updateState {
        copy(username = username, usernameError = null)
    }

    fun onPasswordChanged(password: String) = updateState {
        copy(password = password, passwordError = null)
    }

    fun onRememberChanged(isChecked: Boolean) = updateState {
        copy(isRememberChecked = isChecked)
    }

    fun login() {
        if (isLoading.value || !validateInput()) return

        val state = currentState
        viewModelScope.launch {
            updateState { copy(message = null) }

            val result = withLoading {
                loginUseCase(
                    LoginCredentials(
                        username = state.username.trim(),
                        password = state.password,
                        rememberMe = state.isRememberChecked
                    )
                )
            }

            updateState {
                copy(
                    message = when (result) {
                        is AuthResult.Success -> "Đăng nhập thành công"
                        is AuthResult.Failure -> result.message
                    }
                )
            }
        }
    }

    fun consumeMessage() = updateState { copy(message = null) }

    private fun validateInput(): Boolean {
        val usernameError = if (currentState.username.isBlank()) {
            "Tài khoản không được để trống"
        } else null
        val passwordError = if (currentState.password.isBlank()) {
            "Mật khẩu không được để trống"
        } else null

        updateState {
            copy(usernameError = usernameError, passwordError = passwordError)
        }
        return usernameError == null && passwordError == null
    }
}
