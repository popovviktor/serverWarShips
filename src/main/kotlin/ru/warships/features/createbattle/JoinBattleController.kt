package ru.warships.features.createbattle

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.warships.database.battles.BattleJoin
import ru.warships.database.battles.Battles
import ru.warships.database.coordsships.CoordsShips
import ru.warships.database.stepInBattle.ResultEndBattle
import ru.warships.database.stepInBattle.Steps

class JoinBattleController (private val call: ApplicationCall) {

    suspend fun performJoinBattle(){
        val recieve = call.receive<BattleJoin>()
        var idbattle = Battles.inserttoken2(recieve.token1,recieve.token2)!!
        Steps.insertBattleInDbSteps(recieve.token1,recieve.token2,idbattle)
        ResultEndBattle.insertBattleInDbResultEndBattle(recieve.token1,recieve.token2,idbattle)
        CoordsShips.insertcoords(recieve.token1,recieve.idbattle)
        CoordsShips.insertcoords(recieve.token2,recieve.idbattle)

        call.respond(HttpStatusCode.OK,JoinBattleResponse(idbattle = idbattle))
    }
}