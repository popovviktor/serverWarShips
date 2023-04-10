package ru.warships.features.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val login:String,
    val password:String,
    val firstname:String,
    val lastname:String

)
@Serializable
data class FindEmailReceive(
    val login: String
)
@Serializable
data class FindEmailResponse(
    val result:String
)

@Serializable
data class RegisterResponseRemote(
    val token:String
)
