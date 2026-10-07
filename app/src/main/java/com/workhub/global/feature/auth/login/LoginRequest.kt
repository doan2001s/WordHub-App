package com.workhub.global.feature.auth.login

data class LoginRequest(
    val username: String,
    val password: String,
    val rememberMe: Boolean,

)
