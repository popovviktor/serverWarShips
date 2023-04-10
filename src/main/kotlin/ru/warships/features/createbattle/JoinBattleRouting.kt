package ru.warships.features.createbattle

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureJoinBattleRouting(){
    routing {
        post ("/joinbattle"){
            val joinBattleController = JoinBattleController(call)
            joinBattleController.performJoinBattle()

        }
    }
}