package ru.warships.features.registration

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.tokens.Tokens

class FindThisEmailController (val call: ApplicationCall){

    suspend fun findemeil(){
        val login = call.receive<FindEmailReceive>()
        var result = Tokens.fetchTokenLogin(login.login)
        if (result == null){
            call.respond(FindEmailResponse(result = "true"))
        }else{call.respond(FindEmailResponse(result = "false"))}
    }
}