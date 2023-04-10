package ru.warships

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import ru.warships.features.createbattle.configureCreateBattleRouting
import ru.warships.features.createbattle.configureJoinBattleRouting
import ru.warships.features.loadingstep.configureloadshoot
import ru.warships.features.loadingwaitorwin.configureWaitWinOrLoseRouting
import ru.warships.features.login.configureAutoLoginWithToken
import ru.warships.features.login.configureLoginRouting
import ru.warships.features.login.configureRefreshToken
import ru.warships.features.registration.configureFindEmailInDb
import ru.warships.features.registration.configureRegisterRouting
import ru.warships.features.searchavailablegame.configureFindAvailableGames
import ru.warships.features.shootinbattle.configureShoutInBattle
import ru.warships.features.shootinbattle.configureWaitShootInBattle
import ru.warships.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)



}

fun Application.module() {
    Database.connect("jdbc:mysql:","com.mysql.jdbc.Driver",
        user = "", password = "")
    configureRouting()
    configureSerialization()
    configureLoginRouting()
    configureRegisterRouting()
    configureRefreshToken()
    configureAutoLoginWithToken()
    configureCreateBattleRouting()
    configureJoinBattleRouting()
    configureShoutInBattle()
    configureWaitWinOrLoseRouting()
    configureloadshoot()
    configureFindAvailableGames()
    configureWaitShootInBattle()
    configureFindEmailInDb()
}
