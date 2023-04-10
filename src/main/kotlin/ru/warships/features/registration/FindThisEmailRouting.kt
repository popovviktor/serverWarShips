package ru.warships.features.registration

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureFindEmailInDb(){
    routing {
        post ("/registerfindlogin"){
            val findThisEmailController = FindThisEmailController(call)
            findThisEmailController.findemeil()


        }
    }
}