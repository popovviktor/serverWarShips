package ru.warships.features.createbattle

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.battles.Battles
import ru.warships.database.tokens.TokenDTO
import ru.warships.database.tokens.Tokens
import ru.warships.features.login.LoginReciveRemote
import ru.warships.features.login.LoginResponseRemote

class CreateBattleController (private val call: ApplicationCall) {

    suspend fun performCreateBattle(){
        val recieve = call.receive<LoginResponseRemote>()
        val tokenDTO = Tokens.fetchTokenToken(recieve.token)
        if (tokenDTO!=null){
            var d = Battles.inserttoken1(tokenDTO)!!
            call.respond(HttpStatusCode.OK,CreateBattleResponse(idbattle = d))
        }
    }
}