package ru.warships.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.tokens.Tokens
import ru.warships.database.users.UserInfo
import ru.warships.database.users.Users

class AutoLoginWithTokenController (private val call: ApplicationCall) {

    suspend fun performAutoLogin(){
        val recieve = call.receive<LoginResponseRemote>()
        val tokenDTO = Tokens.fetchTokenToken(recieve.token)
        val userDTO = Users.fetchUser(tokenDTO?.login!!)
        if (tokenDTO!=null && userDTO!=null){
            call.respond(UserInfo(
            login = userDTO.login,
            firstname = userDTO.firstname,
            lastname = userDTO.lastname
            ))
        }else{call.respond(HttpStatusCode.BadRequest,"error server")}
    }
}