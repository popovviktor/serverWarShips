package ru.warships.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import kotlinx.serialization.Serializable
import javax.swing.Spring

@Serializable
data class Test(
    val text:String)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(Test(text = "HEllo from ktor"))
        }
    }
}
