package com.workhub.global.feature.auth.data.remote

data class LoginResponseDto(
    val tokens: LoginTokensDto?
)

data class LoginTokensDto(
    val accessToken: String?,
    val refreshToken: String?
)
