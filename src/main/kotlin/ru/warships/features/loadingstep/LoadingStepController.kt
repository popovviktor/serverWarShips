package ru.warships.features.loadingstep

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.stepInBattle.Steps
import ru.warships.features.loadingwaitorwin.TokenOnlyDTO
import ru.warships.features.shootinbattle.ShootDTO

class LoadingStepController (val call: ApplicationCall){

    suspend fun performLoadingShoot(){
        val cal = call.receive<TokenOnlyDTO>()
        var text = Steps.loadstep(cal.token,cal.idbattle)!!
        call.respond(text)
    }
}