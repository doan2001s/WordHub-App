package com.workhub.global.feature.auth.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.workhub.global.core.network.ApiResult
import com.workhub.global.core.network.NetworkClient
import com.workhub.global.core.network.safeApiCall
import com.workhub.global.core.ui.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel<LoginUiState>(
    initialState = LoginUiState()
) {

    private val authApi: AuthApi = NetworkClient.create(AuthApi::class.java)

    fun onUsernameChanged(username: String) {
        updateState {
            copy(
                username = username,
                usernameError = null
            )
        }
    }

    fun onPasswordChanged(password: String) {
        updateState {
            copy(
                password = password,
                passwordError = null
            )
        }
    }

    fun onRememberChanged(isChecked: Boolean) {
        updateState {
            copy(isRememberChecked = isChecked)
        }
    }

    fun login() {
        if (currentState.isLoading) {
            return
        }

        if (!validateInput()) {
            return
        }

        viewModelScope.launch {
            updateState {
                copy(
                    isLoading = true,
                    message = null
                )
            }

            val result = loginWithApi()

            updateState {
                copy(
                    isLoading = false,
                    message = result.message
                )
            }
        }
    }

    fun consumeMessage() {
        updateState {
            copy(message = null)
        }
    }

    private fun validateInput(): Boolean {
        val usernameError = if (currentState.username.isBlank()) {
            "Tài khoản không được để trống"
        } else {
            null
        }

        val passwordError = if (currentState.password.isBlank()) {
            "Mật khẩu không được để trống"
        } else {
            null
        }

        updateState {
            copy(
                usernameError = usernameError,
                passwordError = passwordError
            )
        }

        return usernameError == null && passwordError == null
    }

    private suspend fun loginWithApi(): ApiResult<LoginResponse> {
        val request = LoginRequest(
            username = currentState.username.trim(),
            password = currentState.password,
            rememberMe = currentState.isRememberChecked
        )

        Log.d("LoginApi", "request username=${request.username}, rememberMe=${request.rememberMe}")

        return safeApiCall {
            authApi.login(request = request)
        }.also { result ->
            Log.d("LoginApi", "result statusCode=${result.statusCode}, code=${result.code}, message=${result.message}")
        }
    }
}
