package ru.warships.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


object Users:Table("users"){
    private val login = Users.varchar("login",25)
    private val password = Users.varchar("password",25)
    private val firstname = Users.varchar("firstname",25)
    private val lastname = Users.varchar("lastname",25)



    fun insert(userDTO: UserDTO){
    transaction {
        Users.insert {
            it[login] = userDTO.login
            it[password] = userDTO.password
            it[firstname] = userDTO.firstname
            it[lastname] = userDTO.lastname
        }
    }
    }
    fun fetchUser(login:String): UserDTO? {
        return try {
            transaction {
                val userModel = Users.select { Users.login.eq(login) }.single()
                return@transaction UserDTO(
                    login = userModel[Users.login],
                    password = userModel[password],
                    firstname = userModel[firstname],
                    lastname = userModel[lastname]
                )
            }
        } catch (ex: Exception) {
            return null
        }
    }
}
