package ru.warships.features.loadingwaitorwin

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.warships.features.createbattle.CreateBattleController

fun Application.configureWaitWinOrLoseRouting(){
    routing {
        post ("/waitwinlose"){
            val waitLoseOrWinController = WaitLoseOrWinController(call)
            waitLoseOrWinController.performWaitLoseOrWin()

        }
    }
}