package ru.warships.database.coordsships
@kotlinx.serialization.Serializable
class ShootResultDTO (
    val tokenowner:String,
    val shootcoords:String,
    val result: Boolean,
    val idbattle:String

        )