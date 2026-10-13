package com.workhub.global.feature.home.data.service

import com.workhub.global.core.network.ApiResponse
import com.workhub.global.feature.home.data.remote.reponse.AttendanceReponse
import com.workhub.global.feature.home.data.remote.reponse.ListServicesResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeApiServices {
    @GET("attendance/today")
    suspend fun getAttendance(): ApiResponse<AttendanceReponse>
    @POST("services")
    suspend fun getServices(): ApiResponse<ListServicesResponse>
}