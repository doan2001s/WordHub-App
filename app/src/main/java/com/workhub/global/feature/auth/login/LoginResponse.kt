package com.workhub.global.feature.auth.login

data class LoginResponse(
    val accessToken: String?,
    val refreshToken: String?
)
