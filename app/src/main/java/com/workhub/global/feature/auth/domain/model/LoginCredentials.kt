package com.workhub.global.feature.auth.domain.model

data class LoginCredentials(
    val username: String,
    val password: String,
    val rememberMe: Boolean
)
