package ru.warships.database.battles
@kotlinx.serialization.Serializable
class BattleJoin (
    val token1:String,
    val token2: String,
    val idbattle:String
        )