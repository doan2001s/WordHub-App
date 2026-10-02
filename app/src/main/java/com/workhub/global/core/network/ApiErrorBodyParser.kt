package com.workhub.global.core.network

import org.json.JSONException
import org.json.JSONObject

internal object ApiErrorBodyParser {

    fun parse(errorBody: String?): ApiErrorPayload? {
        if (errorBody.isNullOrBlank()) {
            return null
        }

        return try {
            val json = JSONObject(errorBody)
            ApiErrorPayload(
                code = json.optString("code").takeIf { it.isNotBlank() },
                message = json.optString("message").takeIf { it.isNotBlank() },
                data = json.opt("data").takeUnless { json.isNull("data") }
            )
        } catch (_: JSONException) {
            null
        }
    }
}

internal data class ApiErrorPayload(
    val code: String?,
    val message: String?,
    val data: Any?
)
