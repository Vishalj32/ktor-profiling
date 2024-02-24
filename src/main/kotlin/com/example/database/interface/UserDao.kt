package com.example.database.`interface`

import com.example.model.PostUser
import com.example.model.User


interface UserDao {
    fun getUserById(userId: Int): User?
    fun insertUser(postUser: PostUser): Int?
    fun updateUser(userId: Int, putUser: PostUser): User?
    fun deleteUser(userId: Int): Boolean
    fun getUserByName(usernameValue: String): User?
    fun getAllUsers(): List<User>
}