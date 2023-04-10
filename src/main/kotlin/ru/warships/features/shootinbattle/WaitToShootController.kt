package ru.warships.features.shootinbattle

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.stepInBattle.Steps
import ru.warships.features.loadingstep.Loading

class WaitToShootController (val call: ApplicationCall){

    suspend fun waitToShoot(){
        val cal = call.receive<WaitShootDTO>()
        var goneToShoot = Steps.loadstep(cal.token,cal.idbattle)!!
        if (goneToShoot.text == "clear" ){
            call.respond(goneToShoot)
        }else call.respond(Loading(text = "NOT SHOOT"))
    }
}