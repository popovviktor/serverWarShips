package ru.warships.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.tokens.TokenDTO
import ru.warships.database.tokens.Tokens
import ru.warships.database.users.Users
import java.util.*

class LoginController(private val call:ApplicationCall) {

    suspend fun performLogin(){
        val recieve = call.receive<LoginReciveRemote>()

        val userDTO = Users.fetchUser(recieve.login)
        val tokenDTO = Tokens.fetchTokenLogin(recieve.login.toString())
        System.out.println("tokenDTO->$tokenDTO")
        System.out.println("userDTO->$userDTO")

        if (userDTO==null){call.respond(HttpStatusCode.BadRequest,"User not found")}else{
            if (userDTO.password==recieve.password){
                if (tokenDTO == null){
                    val token = UUID.randomUUID().toString()
                    Tokens.insert(
                        TokenDTO( login = recieve.login,
                            token = token, firstname = userDTO.firstname, lastname = userDTO.lastname)
                    )
                    call.respond(LoginResponseRemote(token = token))
                }else{
                    call.respond(LoginResponseRemote(token = tokenDTO.token))
                }

            }else{call.respond(HttpStatusCode.BadRequest,"Invalid password")}
        }

    }
}