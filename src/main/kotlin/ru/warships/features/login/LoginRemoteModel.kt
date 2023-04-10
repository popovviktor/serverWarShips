package ru.warships.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReciveRemote(
    val login:String,
    val password:String)

@Serializable
data class LoginResponseRemote(
    var token:String
)