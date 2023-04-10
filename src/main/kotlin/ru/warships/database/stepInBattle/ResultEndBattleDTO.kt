package ru.warships.database.stepInBattle
@kotlinx.serialization.Serializable
class ResultEndBattleDTO (
    private val id:String,
    private val token1:String,
    private val token1result:String,
    private val token2:String,
    private val token2result:String
        )