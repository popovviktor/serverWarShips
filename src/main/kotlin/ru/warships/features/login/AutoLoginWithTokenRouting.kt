package ru.warships.features.login

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAutoLoginWithToken(){
    routing {
        post ("/autologintoken"){
            val autoLoginWithTokenController = AutoLoginWithTokenController(call)
            autoLoginWithTokenController.performAutoLogin()

        }
    }
}