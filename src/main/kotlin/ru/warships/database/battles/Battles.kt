package ru.warships.database.battles

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.warships.database.tokens.TokenDTO
import ru.warships.database.tokens.Tokens
import ru.warships.database.users.UserDTO
import ru.warships.database.users.Users
import java.util.UUID

object Battles: Table("battles") {
    private val id = Battles.varchar("id",75)
    private val login1 = Battles.varchar("login1",25)
    private val token1 = Battles.varchar("token1",75)
    private val token2 = Battles.varchar("token2",75)

    fun inserttoken1(tokenDTO: TokenDTO):String?{
        return try {
        transaction {
            var idbattle = UUID.randomUUID().toString()
            Battles.insert {
                it[token1] = tokenDTO.token
                it[id] = idbattle
                it[token2] = "clear"
                it[login1] = tokenDTO.login
            }
        return@transaction idbattle}}catch (ex :Exception){return null}
    }

    fun inserttoken2(token1: String,newtoken2: String):String?{
        return try {

        transaction {

            val battlemodel = Battles.select { (Battles.token1 eq token1) and (Battles.token2 eq "clear") }.single()
            Battles.update ({(Battles.token1 eq token1) and (Battles.token2 eq "clear")}){
                it[token2] = newtoken2
            }
        return@transaction battlemodel[Battles.id]}}catch (ex:Exception){return null}
    }

    fun fetchBattleWaitStartGame(token1:String): BattleDTO? {
        return try {
            transaction {
                val allbarrles = Battles.selectAll()
                val battles = Battles.select { (Battles.token1.eq(token1))  }.single()
                return@transaction BattleDTO(
                    id = battles[Battles.id],
                    token1 = battles[Battles.token1],
                    token2 = battles[token2],
                    login1 = battles[login1]
                )
            }
        } catch (ex: Exception) {
            return null
        }
    }
    fun findgamesavailable():ArrayBattles?{
        return try {
        transaction {
        var arr = ArrayList<BattleDTO>()
        for (elem in Battles.selectAll()){
            if (elem[token2]=="clear"){
                arr.add(BattleDTO(id = elem[Battles.id],
                login1 = elem[login1],
                token1 = elem[token1],
                token2 = elem[token2]))
            }
        }
    return@transaction ArrayBattles(arr)}}catch (ex:Exception){return null }}


}