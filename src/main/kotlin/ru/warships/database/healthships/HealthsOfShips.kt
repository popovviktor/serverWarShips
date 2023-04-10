package ru.warships.database.healthships

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.warships.database.coordsships.CoordsShips
import java.util.*
import kotlin.collections.ArrayList

object HealthsOfShips:Table("healthofships") {
    private val id = HealthsOfShips.varchar("id",75)
    private val tokenowner = HealthsOfShips.varchar("tokenowner",75)
    private val healthship = HealthsOfShips.integer("healthship")
    private val size = HealthsOfShips.integer("sizeship")
    fun inserthealthofShips(tokenowner:String){
        transaction {

        }


    }

}