package com.workhub.global.core.session

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SecureTokenManager {
    private const val PREF_NAME = "secure_tokens"
    private const val KEY_ACCESS_TOKEN = "access_token"
    private const val KEY_REFRESH_TOKEN = "refresh_token"

    // lớp context dùng chung của app
    private lateinit var appContext: Context

    //Gọi khi mà app mở dùng applicationContext để tránh giữ context lâu
    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private val securePrefs by lazy {
        val masterKey =
            MasterKey.Builder(appContext).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        EncryptedSharedPreferences.create(
            appContext,
            PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveToken(accessToken: String, refetchToken: String) {
        securePrefs.edit()
            .putString(KEY_ACCESS_TOKEN,accessToken)
            .putString(KEY_REFRESH_TOKEN, refetchToken)
            .apply()
    }
    fun getAccessToken(): String? {
         return securePrefs.getString(KEY_ACCESS_TOKEN,null)
    }

}