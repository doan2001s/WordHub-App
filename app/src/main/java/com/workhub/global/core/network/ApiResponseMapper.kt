package com.workhub.global.core.network

object ApiResponseMapper {

    fun <T> map(
        statusCode: Int,
        body: ApiResponse<T>?,
        errorBody: String? = null
    ): ApiResult<T> {
        if (statusCode == 204) {
            return ApiResult.Success(
                statusCode = statusCode,
                code = ApiContract.Code.SUCCESS,
                message = "",
                data = null
            )
        }

        return when {
            statusCode in 200..299 -> mapSuccessfulStatus(
                statusCode = statusCode,
                body = body
            )

            else -> mapErrorStatus(
                statusCode = statusCode,
                errorBody = errorBody
            )
        }
    }

    fun mapException(throwable: Throwable): ApiResult.Error {
        return ApiResult.Error(
            statusCode = null,
            code = ApiContract.Code.NETWORK_ERROR,
            message = throwable.message
                ?.takeIf { it.isNotBlank() }
                ?: ApiContract.Message.NETWORK_ERROR,
            cause = throwable
        )
    }

    private fun <T> mapSuccessfulStatus(
        statusCode: Int,
        body: ApiResponse<T>?
    ): ApiResult<T> {
        if (body == null) {
            return ApiResult.Error(
                statusCode = statusCode,
                code = ApiContract.Code.EMPTY_RESPONSE,
                message = ApiContract.Message.EMPTY_RESPONSE
            )
        }

        if (body.code != ApiContract.Code.SUCCESS) {
            return ApiResult.Error(
                statusCode = statusCode,
                code = body.code,
                message = body.message,
                data = body.data
            )
        }

        return ApiResult.Success(
            statusCode = statusCode,
            code = body.code,
            message = body.message,
            data = body.data
        )
    }

    private fun mapErrorStatus(
        statusCode: Int,
        errorBody: String?
    ): ApiResult.Error {
        val errorPayload = ApiErrorBodyParser.parse(errorBody)
        val fallback = fallbackError(statusCode)

        return ApiResult.Error(
            statusCode = statusCode,
            code = errorPayload?.code ?: fallback.code,
            message = errorPayload?.message ?: fallback.message,
            data = errorPayload?.data
        )
    }

    private fun fallbackError(statusCode: Int): FallbackError {
        return when (statusCode) {
            400 -> FallbackError(
                code = ApiContract.Code.BAD_REQUEST,
                message = ApiContract.Message.BAD_REQUEST
            )

            401 -> FallbackError(
                code = ApiContract.Code.UNAUTHORIZED,
                message = ApiContract.Message.UNAUTHORIZED
            )

            403 -> FallbackError(
                code = ApiContract.Code.FORBIDDEN,
                message = ApiContract.Message.FORBIDDEN
            )

            404 -> FallbackError(
                code = ApiContract.Code.NOT_FOUND,
                message = ApiContract.Message.NOT_FOUND
            )

            409 -> FallbackError(
                code = ApiContract.Code.CONFLICT,
                message = ApiContract.Message.CONFLICT
            )

            422 -> FallbackError(
                code = ApiContract.Code.VALIDATION_ERROR,
                message = ApiContract.Message.VALIDATION_ERROR
            )

            in 500..599 -> FallbackError(
                code = ApiContract.Code.SERVER_ERROR,
                message = ApiContract.Message.SERVER_ERROR
            )

            else -> FallbackError(
                code = ApiContract.Code.UNKNOWN_ERROR,
                message = ApiContract.Message.UNKNOWN_ERROR
            )
        }
    }
}

private data class FallbackError(
    val code: String,
    val message: String
)
