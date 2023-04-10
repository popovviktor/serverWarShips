package ru.warships.cache

import ch.qos.logback.core.subst.Token
import io.ktor.util.collections.*
import ru.warships.features.registration.RegisterReceiveRemote
import java.util.ArrayList
data class TokenCache(
    val login:String,
    val token: String)
object InMemoryCache {
    val userList:MutableList<RegisterReceiveRemote> = sharedListOf()
    val token:MutableList<TokenCache> = sharedListOf()
}