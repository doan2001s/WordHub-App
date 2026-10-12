package com.workhub.global.core.network

import android.util.Log
import com.workhub.global.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okio.Buffer
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    private val loggingInterceptor = Interceptor { chain ->
        val request = chain.request()
        val startedAt = System.currentTimeMillis()

        logRequest(request)

        try {
            val response = chain.proceed(request)
            val durationMs = System.currentTimeMillis() - startedAt

            Log.d(
                TAG,
                "<-- ${response.code()} ${response.message()} " +
                    "${request.method()} ${request.url()} (${durationMs}ms)"
            )

            logResponseBody(response)

            response
        } catch (throwable: Throwable) {
            val durationMs = System.currentTimeMillis() - startedAt
            Log.e(
                TAG,
                "<-- HTTP FAILED ${request.method()} ${request.url()} (${durationMs}ms): " +
                    "${throwable.javaClass.simpleName}: ${throwable.message}",
                throwable
            )
            throw throwable
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Log.d(TAG, "Retrofit baseUrl=${NetworkConfig.BASE_URL}, debug=${BuildConfig.DEBUG}")

        Retrofit.Builder()
            .baseUrl(NetworkConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    private fun logResponseBody(response: Response) {
        val responseBody = response.peekBody(RESPONSE_BODY_LOG_LIMIT).string()
            .ifBlank { "<empty>" }
            .maskSensitiveValues()

        val message = "body ${response.request().method()} ${response.request().url()}: $responseBody"

        if (response.isSuccessful) {
            Log.d(TAG, message)
        } else {
            Log.e(TAG, message)
        }
    }

    private fun logRequest(request: okhttp3.Request) {
        val requestBody = request.body()?.let { body ->
            runCatching {
                Buffer().apply { body.writeTo(this) }.readUtf8()
            }.getOrElse { "<unavailable: ${it.javaClass.simpleName}>" }
        } ?: "<empty>"

        Log.d(
            TAG,
            "--> ${request.method()} ${request.url()} " +
                "headers=${request.headers().toString().maskSensitiveValues()} " +
                "body=${requestBody.maskSensitiveValues()}"
        )
    }

    private fun String.maskSensitiveValues(): String {
        return replace(TOKEN_VALUE_REGEX) { matchResult ->
            "${matchResult.groupValues[1]}***${matchResult.groupValues[3]}"
        }.replace(AUTHORIZATION_HEADER_REGEX) { matchResult ->
            "${matchResult.groupValues[1]}***"
        }
    }

    private val TOKEN_VALUE_REGEX = Regex(
        "(\"(?:accessToken|refreshToken|token|password)\"\\s*:\\s*\")([^\"]+)(\")",
        RegexOption.IGNORE_CASE
    )
    private val AUTHORIZATION_HEADER_REGEX = Regex(
        "(Authorization:\\s*(?:Bearer\\s+)?)\\S+",
        RegexOption.IGNORE_CASE
    )
    private const val RESPONSE_BODY_LOG_LIMIT = 64L * 1024L
    private const val TAG = "NetworkApi"
}
