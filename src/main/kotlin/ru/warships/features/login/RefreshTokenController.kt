package ru.warships.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.tokens.Tokens
import java.util.UUID

class RefreshTokenController (private val call: ApplicationCall) {

    suspend fun refreshToken(){
        val recieve = call.receive<LoginResponseRemote>()
        val tokenDTO = Tokens.fetchTokenToken(recieve.token)
        if (tokenDTO!=null){
        val newToken = UUID.randomUUID().toString()
        Tokens.updateToken(tokenDTO,newToken)
        call.respond(LoginResponseRemote(token = newToken))
        }else{call.respond(HttpStatusCode.Conflict,"Not save in memory token")}
    }
}