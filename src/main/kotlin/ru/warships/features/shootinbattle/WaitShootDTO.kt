package ru.warships.features.shootinbattle
@kotlinx.serialization.Serializable
data class WaitShootDTO (
    val token:String,//who
    val idbattle:String
)
