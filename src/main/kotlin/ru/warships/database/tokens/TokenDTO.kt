package ru.warships.database.tokens
@kotlinx.serialization.Serializable
class TokenDTO(
    //val id:String,
    val login:String,
    val token:String,
    val firstname:String,
    val lastname:String
)