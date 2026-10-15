package com.workhub.global.feature.home.domain.model.response

import com.workhub.global.feature.home.data.remote.response.LocationResponse

data class AttendanceResponseData(
    val workDate: String?,
    val state: String?,
    val checkInAt: String?,
    val syncStatus: String?,
    val dayStatus: String?,
    val location: LocationResponse?
)