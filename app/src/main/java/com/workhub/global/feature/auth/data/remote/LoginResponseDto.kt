package com.workhub.global.feature.auth.data.remote

data class LoginResponseDto(
    val accessToken: String?,
    val refreshToken: String?
)
