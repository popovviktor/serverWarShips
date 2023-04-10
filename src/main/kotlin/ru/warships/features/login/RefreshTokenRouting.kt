package ru.warships.features.login

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRefreshToken(){
    routing {
        post ("/refreshtoken"){
            val refreshTokenController = RefreshTokenController(call)
            refreshTokenController.refreshToken()
        }
    }
}