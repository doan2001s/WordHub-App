package com.workhub.global.core.session

interface TokenStorage {

    fun saveTokens(
        accessToken: String,
        refreshToken: String?
    )

    fun getAccessToken(): String?

    fun getRefreshToken(): String?

    fun clearTokens()
}
