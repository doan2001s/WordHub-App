package com.workhub.global.feature.auth.di

import android.content.Context
import com.workhub.global.core.network.NetworkClient
import com.workhub.global.core.session.SecureTokenManager
import com.workhub.global.feature.auth.data.remote.AuthApi
import com.workhub.global.feature.auth.data.remote.AuthRemoteDataSourceImpl
import com.workhub.global.feature.auth.data.repository.AuthRepositoryImpl
import com.workhub.global.feature.auth.domain.usecase.LoginUseCase
import com.workhub.global.feature.auth.domain.validator.LoginValidator

class AuthDependencies(context: Context) {

    private val tokenManager = SecureTokenManager(context.applicationContext)
    private val networkClient = NetworkClient(tokenManager)

    val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(
            AuthRepositoryImpl(
                remoteDataSource = AuthRemoteDataSourceImpl(
                    authApi = networkClient.create(AuthApi::class.java)
                ),
                tokenStorage = tokenManager
            ),
            loginValidator = LoginValidator()
        )
    }
}
