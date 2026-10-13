package com.workhub.global.feature.auth.data.remote

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("rememberMe")
    val rememberMe: Boolean
)
