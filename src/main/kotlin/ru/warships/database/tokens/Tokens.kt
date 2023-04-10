package ru.warships.database.tokens

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import ru.warships.database.users.Users
import java.util.UUID


object Tokens:Table("tokens"){
   // private val id = Tokens.varchar("id",75)
    private val login = Tokens.varchar("login",25)
    private val token = Tokens.varchar("token",75)
    private val firstname = Tokens.varchar("firstname",25)
    private val lastname = Tokens.varchar("lastname",25)

    fun updateToken(tokenDTO: TokenDTO,newToken:String){
        transaction {
            val oldToken = tokenDTO.token
            Tokens.update ({Tokens.token eq oldToken}){
                it[token] = newToken
            }
        }
    }

    fun insert(tokenDTO: TokenDTO){
    transaction {
        Tokens.insert {
            //it[id] = tokenDTO.id
            it[login] = tokenDTO.login
            it[token] = tokenDTO.token
            it[firstname] = tokenDTO.firstname
            it[lastname] = tokenDTO.lastname
        }
    }
    }
    fun fetchTokenLogin(login:String): TokenDTO? {
        return try {
            transaction {
                val tokenModel = Tokens.select { Tokens.login.eq(login) }.single()
                return@transaction TokenDTO(
                   // id = tokenModel[Tokens.id],
                    login = tokenModel[Tokens.login],
                    token = tokenModel[token],
                    firstname = tokenModel[firstname],
                    lastname = tokenModel[lastname]
                )
            }
    } catch (ex: Exception) {
        return null
    }
    }
    fun fetchTokenToken(token:String): TokenDTO? {
        return try {
            transaction {
                val tokenModel = Tokens.select { Tokens.token.eq(token) }.single()
                return@transaction TokenDTO(
                    //id = tokenModel[Tokens.id],
                    login = tokenModel[login],
                    token = tokenModel[Tokens.token],
                    firstname = tokenModel[firstname],
                    lastname = tokenModel[lastname]
                )
            }
        } catch (ex: Exception) {
            return null
        }
    }
}
