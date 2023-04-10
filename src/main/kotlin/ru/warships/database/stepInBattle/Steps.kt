package ru.warships.database.stepInBattle

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.warships.features.loadingstep.Loading

object Steps:Table("steps"){
    private val idbattle = Steps.varchar("idbattle",75)
    private val token1 = Steps.varchar("token1",75)
    private val steptoken1 = Steps.varchar("steptoken1",5)
    private val token2 = Steps.varchar("token2",75)
    private val steptoken2 = Steps.varchar("steptoken2",5)
    fun insertBattleInDbSteps(token1: String, token2: String,idbattle:String){
        transaction {
            Steps.insert {
                it[Steps.idbattle] = idbattle
                it[Steps.token1] = token1
                it[Steps.token2] = token2
                it[steptoken1] = "clear"
                it[steptoken2] = "wait"
            }
        }
    }
    fun insertStep(token: String, step: String,idbattle:String): QueShootDTO? {
        return try {
            transaction {
                var s0 = Steps.update ({(Steps.token1 eq token) and (Steps.steptoken1 eq "clear") and (Steps.idbattle eq idbattle)}){
                    it[steptoken1] = step
                    it[steptoken2] = "clear"
                }

                var s =Steps.update({(Steps.token2 eq token) and (Steps.steptoken2 eq "clear") and (Steps.idbattle eq idbattle)}){
                    it[steptoken2] = step
                    it[steptoken1] = "clear"
                }
                var queShootDTO: QueShootDTO? = null
                if (s>0 || s0>0){
                    var que = QueShootDTO(gonetoshoot = true)
                    queShootDTO = que
                }
                return@transaction queShootDTO
            }
            }catch (ex:java.lang.Exception){ return null}
    }
    fun loadstep(token: String,idbattle:String):Loading?{
        return try {

        transaction {
            Steps.select{(Steps.token1.eq(token)) and (Steps.idbattle eq idbattle) }.count(){
                return@transaction Loading(text = it[steptoken1])
            }
            Steps.select{(Steps.token2.eq(token)) and (Steps.idbattle eq idbattle)}.count(){
                return@transaction Loading(text = it[steptoken2])
            }
        return@transaction null}}catch (ex:Exception){return null
        }
    }

}