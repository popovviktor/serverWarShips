package ru.warships.features.loadingwaitorwin

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.stepInBattle.ResultEndBattle
import ru.warships.database.tokens.TokenDTO


class WaitLoseOrWinController (private val call: ApplicationCall) {

    suspend fun performWaitLoseOrWin(){
        val recieve = call.receive<TokenOnlyDTO>()
        var text = ResultEndBattle.waitEndBattle(recieve.token,recieve.idbattle)!!
        call.respond(WaitWinResponse(text = text))
    }

}