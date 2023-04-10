package ru.warships.features.shootinbattle

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureWaitShootInBattle(){
    routing {
        post ("/waitshoot"){
            var waitToShootController = WaitToShootController(call)
            waitToShootController.waitToShoot()


        }
    }
}