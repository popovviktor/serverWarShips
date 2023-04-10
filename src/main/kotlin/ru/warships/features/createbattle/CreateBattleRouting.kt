package ru.warships.features.createbattle

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.warships.features.login.LoginController

fun Application.configureCreateBattleRouting(){
    routing {
        post ("/createbattle"){
            val createBattleController = CreateBattleController(call)
            createBattleController.performCreateBattle()

        }
    }
}