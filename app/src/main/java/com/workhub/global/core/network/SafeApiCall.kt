package com.workhub.global.core.network

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> ApiResponse<T>
): ApiResult<T> {
    return try {
        ApiResponseMapper.map(
            statusCode = HTTP_SUCCESS,
            body = apiCall()
        )
    } catch (throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                ApiResponseMapper.map(
                    statusCode = throwable.code(),
                    body = null,
                    errorBody = throwable.response()
                        ?.errorBody()
                        ?.string()
                )
            }

            is IOException -> {
                ApiResult.Error(
                    statusCode = null,
                    code = ApiContract.Code.NETWORK_ERROR,
                    message = ApiContract.Message.NETWORK_ERROR,
                    cause = throwable
                )
            }

            else -> ApiResponseMapper.mapException(throwable)
        }
    }
}

private const val HTTP_SUCCESS = 200
