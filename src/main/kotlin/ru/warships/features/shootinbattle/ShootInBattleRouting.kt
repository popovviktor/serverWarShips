package ru.warships.features.shootinbattle

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.warships.features.registration.RegisterController

fun Application.configureShoutInBattle(){
    routing {
        post ("/shoot"){
            var shootInBattleController =ShootInBattleController(call)
            shootInBattleController.shoot()


        }
    }
}