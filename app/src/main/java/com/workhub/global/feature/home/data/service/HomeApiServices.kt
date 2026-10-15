package com.workhub.global.feature.home.data.service

import com.workhub.global.core.network.ApiResponse
import com.workhub.global.feature.home.data.remote.response.AttendanceResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeApiServices {
    @GET("attendance/today")
    suspend fun getAttendance(): ApiResponse<AttendanceResponse>
}