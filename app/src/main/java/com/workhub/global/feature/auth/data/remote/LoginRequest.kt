package com.workhub.global.feature.auth.data.remote

data class LoginRequest(
    val username: String,
    val password: String,
    val rememberMe: Boolean
)
