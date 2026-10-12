package com.workhub.global.feature.auth.domain.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String?
)
