package ru.warships.features.shootinbattle
@kotlinx.serialization.Serializable
class ShootDTO(
    val token:String,//who
    val shootcoords:String,
    val tokenwar:String,
    val idbattle:String
)