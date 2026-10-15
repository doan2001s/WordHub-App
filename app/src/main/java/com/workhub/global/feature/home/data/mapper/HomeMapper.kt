package com.workhub.global.feature.home.data.mapper

import com.workhub.global.feature.home.data.remote.response.AttendanceResponse
import com.workhub.global.feature.home.domain.model.response.AttendanceResponseData

fun AttendanceResponse.toDomain(): AttendanceResponseData =
    AttendanceResponseData(
        workDate = workDate,
        state = state,
        checkInAt = checkInAt,
        syncStatus = syncStatus,
        dayStatus = dayStatus,
        location = location,
    )

