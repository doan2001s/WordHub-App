package com.workhub.global.feature.auth.presentation.login

sealed interface LoginEvent {

    data object LoginSuccess : LoginEvent

    data class ShowMessage(val message: String) : LoginEvent
}
