package ru.warships.features.shootinbattle

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.coordsships.CoordsShips
import ru.warships.database.coordsships.ShootResultDTO
import ru.warships.database.stepInBattle.ResultEndBattle
import ru.warships.database.stepInBattle.Steps

class ShootInBattleController (val call: ApplicationCall){

    suspend fun shoot(){
        val cal = call.receive<ShootDTO>()
        var goneToShoot = Steps.insertStep(cal.token,cal.shootcoords,cal.idbattle)!!
        if (goneToShoot.gonetoshoot == true ){
            var a = CoordsShips.shoot(cal.tokenwar,cal.shootcoords,cal.idbattle)
            if (a!=null){
                if (CoordsShips.endgameexam(token = cal.tokenwar,cal.idbattle)<1){
                    ResultEndBattle.endgameinsertdata(cal.tokenwar,cal.idbattle)
                }
                call.respond(HttpStatusCode.OK,ShootResultDTO(tokenowner = a.tokenowner,
                    shootcoords = a.shootcoords,
                    result = a.result,
                    idbattle = a.idbattle))

            }
        }else{call.respond(HttpStatusCode.BadRequest,"Not Good Request")}


    }
}