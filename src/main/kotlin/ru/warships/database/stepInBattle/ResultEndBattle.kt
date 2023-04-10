package ru.warships.database.stepInBattle

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object ResultEndBattle:Table("tablewaitwin") {
    private val idbattle = ResultEndBattle.varchar("idbattle",75)
    private val token1 = ResultEndBattle.varchar("token1",75)
    private val token1result = ResultEndBattle.varchar("token1result",15)
    private val token2 = ResultEndBattle.varchar("token2",75)
    private val token2result = ResultEndBattle.varchar("token2result",15)
    fun insertBattleInDbResultEndBattle(token1: String, token2: String,idbattle:String){
        transaction {
            ResultEndBattle.insert {
                it[this.idbattle] = idbattle
                it[ResultEndBattle.token1] = token1
                it[ResultEndBattle.token2] = token2
                it[token1result] = "wait"
                it[token2result] = "wait"
            }
        }
    }
    fun endgameinsertdata(token:String,idbattle: String){
        transaction {
            ResultEndBattle.update ({(ResultEndBattle.token1 eq token)and (ResultEndBattle.idbattle eq idbattle) }){
                it[ResultEndBattle.token1result] = "lose"
                it[ResultEndBattle.token2result] = "win"
            }
            ResultEndBattle.update ({(ResultEndBattle.token2 eq token)and (ResultEndBattle.idbattle eq idbattle) }){
                it[ResultEndBattle.token2result] = "lose"
                it[ResultEndBattle.token1result] = "win"
            }
        }
    }
    fun waitEndBattle(token:String,idbattle: String):String? {
    return try {

        transaction {
            var a = ResultEndBattle.select { ResultEndBattle.token1result.eq("wait")and (ResultEndBattle.idbattle eq idbattle) }.count()
            if (a < 1) {
                var a1 = ResultEndBattle.select { (ResultEndBattle.token1.eq(token))and (ResultEndBattle.idbattle eq idbattle) }.single() {
                    return@transaction it[ResultEndBattle.token1result].toString()
                }
                var a2 = ResultEndBattle.select { (ResultEndBattle.token2.eq(token))and (ResultEndBattle.idbattle eq idbattle) }.single() {
                    return@transaction it[ResultEndBattle.token2result].toString()
                }
            } else {
                return@transaction "wait"
            }
            return@transaction "wait"

        }}catch (ex:java.lang.Exception){ return null}
    }
}