package ru.warships.features.searchavailablegame

import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.warships.database.battles.Battles

class SearchAvailableGameContoller (val call: ApplicationCall){

    suspend fun findavailablegames(){
        var arrfind = Battles.findgamesavailable()
        if (arrfind!= null){
            call.respond(arrfind)
        }else{
            call.respond("Доступных игр не найдено")
        }


    }
}