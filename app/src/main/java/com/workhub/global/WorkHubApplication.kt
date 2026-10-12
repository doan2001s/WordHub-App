package com.workhub.global

import android.app.Application
import com.workhub.global.feature.auth.di.AuthDependencies

class WorkHubApplication : Application() {

    lateinit var authDependencies: AuthDependencies
        private set

    override fun onCreate() {
        super.onCreate()
        authDependencies = AuthDependencies(this)
    }
}
