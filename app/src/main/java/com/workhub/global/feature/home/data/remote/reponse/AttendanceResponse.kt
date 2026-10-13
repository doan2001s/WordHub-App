package com.workhub.global.feature.home.data.remote.reponse

import com.google.gson.annotations.SerializedName

data class AttendanceReponse(
    @SerializedName(value = "workDate")
    val workDate: String? = null,
    @SerializedName(value = "state")
    val state: String? = null,
    @SerializedName(value = "checkInAt")
    val checkInAt: String? = null,
    @SerializedName(value = "syncStatus")
    val  syncStatus: String? = null,
    @SerializedName(value = "dayStatus")
    val  dayStatus: String? = null,
    @SerializedName("location")
    val location: LocationRespone? = null
)

data class LocationRespone(
    @SerializedName(value = "id")
    val id: String? = null,
    @SerializedName(value = "name")
    val name: String? =null,
)