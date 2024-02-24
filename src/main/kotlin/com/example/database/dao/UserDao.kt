package com.example.database.dao

import com.example.database.`interface`.UserDao
import com.example.model.PostUser
import com.example.model.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

object Users : Table(), UserDao {
    val id = integer("id").autoIncrement()
    val name = varchar("name", length = 50)
    val age = integer("age")

    override val primaryKey = PrimaryKey(id)

    override fun getUserById(userId: Int): User? {
        return select {
            id eq userId
        }.mapNotNull {
            it.mapRowToUser()
        }.singleOrNull()
    }

    override fun insertUser(postUser: PostUser): Int {
        return (this.insert {
            it[name] = postUser.name
            it[age] = postUser.age
        })[this.id]
    }

    override fun updateUser(userId: Int, putUser: PostUser): User? {
        update({ id eq userId }) { user ->
            putUser.name.let { user[name] = it }
            putUser.age.let { user[age] = it }
        }
        return getUserById(userId)
    }

    override fun deleteUser(userId: Int): Boolean {
        return deleteWhere { this.id eq userId } > 0
    }

    override fun getUserByName(usernameValue: String): User? {
        return select {
            (name eq usernameValue)
        }.mapNotNull {
            it.mapRowToUser()
        }.singleOrNull()
    }

    override fun getAllUsers(): List<User> {
        return selectAll().mapNotNull { it.mapRowToUser() }
    }


}

fun ResultRow.mapRowToUser() =
    User(
        id = this[Users.id],
        name = this[Users.name],
        age = this[Users.age]
    )