package ru.warships.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.warships.cache.InMemoryCache
import ru.warships.cache.TokenCache
import ru.warships.features.registration.RegisterReceiveRemote
import java.util.UUID

fun Application.configureLoginRouting(){
    routing {
        post ("/login"){
            val loginController = LoginController(call)
            loginController.performLogin()

        }
    }
}