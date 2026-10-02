package com.workhub.global.core.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ApiResponseMapperTest {

    @Test
    fun `map returns success when backend returns success body`() {
        val result = ApiResponseMapper.map(
            statusCode = 200,
            body = ApiResponse(
                code = ApiContract.Code.SUCCESS,
                message = "Đăng nhập thành công",
                data = AuthToken(
                    accessToken = "access-token",
                    refreshToken = "refresh-token"
                )
            )
        )

        assertTrue(result is ApiResult.Success)
        result as ApiResult.Success
        assertEquals(200, result.statusCode)
        assertEquals(ApiContract.Code.SUCCESS, result.code)
        assertEquals("Đăng nhập thành công", result.message)
        assertEquals("access-token", result.data?.accessToken)
    }

    @Test
    fun `map returns success with null data for 204`() {
        val result = ApiResponseMapper.map<Unit>(
            statusCode = 204,
            body = null
        )

        assertTrue(result is ApiResult.Success)
        result as ApiResult.Success
        assertEquals(204, result.statusCode)
        assertEquals(ApiContract.Code.SUCCESS, result.code)
        assertNull(result.data)
    }

    @Test
    fun `map uses backend error contract message when available`() {
        val result = ApiResponseMapper.map<Unit>(
            statusCode = 422,
            body = null,
            errorBody = """
                {
                  "code": "VALIDATION_ERROR",
                  "message": "Dữ liệu đầu vào không hợp lệ",
                  "data": {
                    "username": [
                      "Tài khoản không được để trống"
                    ]
                  }
                }
            """.trimIndent()
        )

        assertTrue(result is ApiResult.Error)
        result as ApiResult.Error
        assertEquals(422, result.statusCode)
        assertEquals(ApiContract.Code.VALIDATION_ERROR, result.code)
        assertEquals("Dữ liệu đầu vào không hợp lệ", result.message)
    }

    @Test
    fun `map uses status fallback when error body is empty`() {
        val result = ApiResponseMapper.map<Unit>(
            statusCode = 401,
            body = null,
            errorBody = null
        )

        assertTrue(result is ApiResult.Error)
        result as ApiResult.Error
        assertEquals(401, result.statusCode)
        assertEquals(ApiContract.Code.UNAUTHORIZED, result.code)
        assertEquals(ApiContract.Message.UNAUTHORIZED, result.message)
    }
}

private data class AuthToken(
    val accessToken: String,
    val refreshToken: String
)
