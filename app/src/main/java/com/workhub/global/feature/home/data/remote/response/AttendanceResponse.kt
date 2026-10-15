package com.workhub.global.feature.home.data.remote.response

import com.google.gson.annotations.SerializedName

data class AttendanceResponse(
    @SerializedName("workDate")
    val workDate: String? = null,

    @SerializedName("state")
    val state: String? = null,

    @SerializedName("checkInAt")
    val checkInAt: String? = null,

    @SerializedName("syncStatus")
    val syncStatus: String? = null,

    @SerializedName("dayStatus")
    val dayStatus: String? = null,

    @SerializedName("location")
    val location: LocationResponse? = null
)
data class LocationResponse(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null
)