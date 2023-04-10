package ru.warships.features.loadingstep

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.warships.features.shootinbattle.ShootInBattleController

fun Application.configureloadshoot(){
    routing {
        post ("/loadstep"){
            var loadingStepController = LoadingStepController(call)
            loadingStepController.performLoadingShoot()


        }
    }
}