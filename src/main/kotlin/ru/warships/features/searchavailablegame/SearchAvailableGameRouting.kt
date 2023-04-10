package ru.warships.features.searchavailablegame

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.warships.features.registration.RegisterController

fun Application.configureFindAvailableGames(){
    routing {
        get ("/findgames"){
            val searchAvailableGameContoller = SearchAvailableGameContoller(call)
            searchAvailableGameContoller.findavailablegames()


        }
    }
}