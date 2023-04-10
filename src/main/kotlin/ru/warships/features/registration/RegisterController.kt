package ru.warships.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.cache.InMemoryCache
import ru.warships.cache.TokenCache
import ru.warships.database.tokens.TokenDTO
import ru.warships.database.tokens.Tokens
import ru.warships.database.users.UserDTO
import ru.warships.database.users.Users
import ru.warships.utils.isvalidEmail
import java.util.*

class RegisterController (val call:ApplicationCall){

    suspend fun registerNewUser(){
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.login.isvalidEmail()){
            call.respond(HttpStatusCode.BadRequest,"Email is not valid")
        }
        val userDTO = Users.fetchUser(registerReceiveRemote.login)

        if (userDTO !=null){
            call.respond(HttpStatusCode.Conflict,"User already exist")
        }else{
            val token = UUID.randomUUID().toString()
            try {

                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        password = registerReceiveRemote.password,
                        firstname = registerReceiveRemote.firstname,
                        lastname = registerReceiveRemote.lastname
                    )
                )
                Tokens.insert(
                    TokenDTO(
                         login = registerReceiveRemote.login,
                        token = token,
                        firstname = registerReceiveRemote.firstname,
                        lastname = registerReceiveRemote.lastname
                    )
                )
                call.respond(RegisterResponseRemote(token = token))
            }catch (ex:Exception){call.respond(call.respond(HttpStatusCode.Conflict,"User already exist"))}
        }





    }
}