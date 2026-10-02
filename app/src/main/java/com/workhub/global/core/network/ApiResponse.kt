package com.workhub.global.core.network

/**
 * Common response body returned by WorkHub backend.
 *
 * Successful and failed API responses share this shape:
 * {
 *   "code": "BUSINESS_CODE",
 *   "message": "Display message",
 *   "data": {}
 * }
 */
data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T?
)
