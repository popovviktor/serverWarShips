package ru.warships.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.warships.cache.InMemoryCache
import ru.warships.cache.TokenCache
import ru.warships.features.login.LoginReciveRemote
import ru.warships.features.login.LoginResponseRemote
import ru.warships.utils.isvalidEmail
import java.util.*

fun Application.configureRegisterRouting(){
    routing {
        post ("/register"){
            val registerController = RegisterController(call)
            registerController.registerNewUser()


        }
    }
}