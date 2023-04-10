package ru.warships.database.users
@kotlinx.serialization.Serializable
class UserDTO(
    val login:String,
    val password:String,
    val firstname:String,
    val lastname:String

)
@kotlinx.serialization.Serializable
class UserInfo(
    val login: String,
    val firstname: String,
    val lastname: String
)