package com.workhub.global.feature.home.data.remote.reponse

import com.google.gson.annotations.SerializedName

data class ListServicesResponse(
    @SerializedName("servicesCode")
    val code: Int? = null,

    @SerializedName("servicesName")
    val name: String? = null
)