package ru.warships.database.coordsships

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.warships.database.battles.Battles
import ru.warships.database.stepInBattle.Steps
import ru.warships.database.tokens.TokenDTO
import ru.warships.features.shootinbattle.ShootDTO
import java.util.*
import kotlin.collections.ArrayList

object CoordsShips:Table("coordsofships") {
    private val idbattle = CoordsShips.varchar("idbattle",75)
    private val id = CoordsShips.varchar("id",75)
    private val coords = CoordsShips.varchar("coords",5)
    private val size = CoordsShips.integer("size")
    private val token = CoordsShips.varchar("token",75)
    fun insertcoords(tokenowner:String,idbattle:String){
        var arr1 = arrayListOf<String>("A1","B1","C1")
        var arr2 = arrayListOf<String>("A3","B3")
        var arrall = ArrayList<ArrayList<String>>()
        arrall.add(arr1)
        arrall.add(arr2)

        for (elem in arrall){
            for (elemelem in elem){
                transaction {
                    CoordsShips.insert {
                        it[CoordsShips.idbattle] = idbattle
                        it[id] = UUID.randomUUID().toString()
                        it[coords] = elemelem
                        it[size] = elem.size
                        it[token] = tokenowner
                    }
                }
            }
        }

    }
    fun shoot(token:String,shootcoord:String,idbattle: String):ShootResultDTO?{
        return try {

        transaction {
            var d = CoordsShips.deleteWhere { (CoordsShips.coords eq shootcoord) and (CoordsShips.token eq token) and(CoordsShips.idbattle eq idbattle)}
            System.out.println(d)
            var c = false
            if (d>0){
                c = true
            }
                System.out.println("ВЫстрел попал")
                return@transaction ShootResultDTO(
                    tokenowner = token,
                    shootcoords = shootcoord,
                    result = c,
                    idbattle = idbattle
                )

        }}catch (ex: Exception){
            return null
        }

        }
        fun endgameexam(token:String,idbattle: String):Long{
            return try {

            transaction {
                var sum = CoordsShips.select {CoordsShips.token.eq(token) and(CoordsShips.idbattle eq idbattle)}.count()
                System.out.println(sum)
            return@transaction sum
            }
            }catch (ex:Exception ){return 100}
        }
        }


