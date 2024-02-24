package com.example.api

import com.example.database.`interface`.UserDao
import com.example.model.PostUser
import com.example.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


object UserApiImpl : UserApi, KoinComponent {
    private val usersDao by inject<UserDao>()

    override fun getUserById(id: Int): User? {
        return usersDao.getUserById(id)
    }

    override fun getUserByUsername(username: String): User? {
        return usersDao.getUserByName(username)
    }

    override fun updateUserProfile(userId: Int, putUserBody: PostUser): User? {
        return usersDao.updateUser(userId, putUserBody)
    }

    override fun removeUser(userId: Int): Boolean {
        return usersDao.deleteUser(userId)
    }

    override fun createUser(postUser: PostUser): User {
        val key: Int? = usersDao.insertUser(postUser)
        return key?.let {
            usersDao.getUserById(it)
        } ?: throw InvalidUserException("Error while creating user")
    }

    override fun getAllUsers(): List<User> {
        return usersDao.getAllUsers()
    }

}

data class InvalidUserException(override val message: String = "Invalid user") : Exception()