package ru.warships.database.battles
@kotlinx.serialization.Serializable
class ArrayBattles(
    val battles:ArrayList<BattleDTO>
)
@kotlinx.serialization.Serializable
class BattleDTO(
    val id:String,
    val login1:String,
    val token1:String,
    val token2:String
)
