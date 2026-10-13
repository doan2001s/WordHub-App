package com.workhub.global.feature.auth.data.remote

import com.google.gson.annotations.SerializedName

data class LoginTokensDto(
    @SerializedName("accessToken")
    val accessToken: String?,

    @SerializedName("refreshToken")
    val refreshToken: String?
)
