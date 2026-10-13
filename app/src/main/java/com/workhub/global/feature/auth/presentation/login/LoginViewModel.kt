package com.workhub.global.feature.auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.workhub.global.core.ui.BaseViewModel
import com.workhub.global.feature.auth.domain.model.AuthError
import com.workhub.global.feature.auth.domain.model.LoginCredentials
import com.workhub.global.feature.auth.domain.repository.AuthResult
import com.workhub.global.feature.auth.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginUiState>(initialState = LoginUiState()) {

    private val _events = MutableSharedFlow<LoginEvent>()
    val events = _events.asSharedFlow()

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
        if (isLoading.value) return

        val state = currentState
        viewModelScope.launch {
            val result = withLoading {
                loginUseCase(
                    LoginCredentials(
                        username = state.username.trim(),
                        password = state.password,
                        rememberMe = state.isRememberChecked
                    )
                )
            }

            when (result) {
                is AuthResult.Success -> _events.emit(LoginEvent.LoginSuccess)
                is AuthResult.Failure -> {
                    handleFailure(result.error)
                }
            }
        }
    }

    private suspend fun handleFailure(error: AuthError) {
        when (error) {
            AuthError.InvalidUsername -> updateState {
                copy(usernameError = "Tài khoản không được để trống")
            }

            AuthError.InvalidPassword -> updateState {
                copy(passwordError = "Mật khẩu không được để trống")
            }

            else -> _events.emit(LoginEvent.ShowMessage(error.toMessage()))
        }
    }

    private fun AuthError.toMessage(): String {
        return when (this) {
            AuthError.InvalidUsername -> "Tài khoản không được để trống"
            AuthError.InvalidPassword -> "Mật khẩu không được để trống"
            AuthError.InvalidCredentials -> "Tài khoản hoặc mật khẩu không đúng"
            AuthError.EmptyToken -> "Máy chủ không trả về token"
            AuthError.Network -> "Không thể kết nối đến máy chủ"
            AuthError.Server -> "Hệ thống đang gặp sự cố, vui lòng thử lại sau"
            AuthError.Unknown -> "Đã có lỗi xảy ra, vui lòng thử lại"
        }
    }
}
