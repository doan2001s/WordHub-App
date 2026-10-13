package com.workhub.global.feature.auth.data.remote

import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("tokens")
    val tokens: LoginTokensDto?
)
