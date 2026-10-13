package com.workhub.global.feature.auth.domain.model

sealed interface AuthError {

    data object InvalidUsername : AuthError

    data object InvalidPassword : AuthError

    data object InvalidCredentials : AuthError

    data object EmptyToken : AuthError

    data object Network : AuthError

    data object Server : AuthError

    data object Unknown : AuthError
}
