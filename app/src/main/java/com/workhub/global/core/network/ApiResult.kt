package com.workhub.global.core.network

sealed interface ApiResult<out T> {

    val statusCode: Int?
    val code: String
    val message: String

    data class Success<T>(
        override val statusCode: Int,
        override val code: String,
        override val message: String,
        val data: T?
    ) : ApiResult<T>

    data class Error(
        override val statusCode: Int?,
        override val code: String,
        override val message: String,
        val data: Any? = null,
        val cause: Throwable? = null
    ) : ApiResult<Nothing>
}
