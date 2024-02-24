package com.example.controllers

import com.example.api.InvalidUserException
import com.example.api.UserApi
import com.example.model.PostUser
import com.example.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class UserControllerImpl : UserController, KoinComponent, BaseController() {
    private val userApi by inject<UserApi>()

    override suspend fun updateProfile(userId: Int, putUser: PostUser): User? {
        var user: User? = null
        dbQuery {
            user = userApi.updateUserProfile(userId, putUser) ?: throw InvalidUserException()
        }

        return user
    }

    override suspend fun removeUser(userId: Int) {
        dbQuery {
            try {
                userApi.removeUser(userId)
            } catch (e: Exception) {
                throw UnknownError("Internal server error")
            }
        }
    }

    override suspend fun getUserById(userId: Int): User? {
        var user: User? = null
        dbQuery {
            try {
                user = userApi.getUserById(userId)
            } catch (e: Exception) {
                throw UnknownError("Internal server error")
            }
        }

        return user
    }

    override suspend fun getUserByName(userName: String) {
        dbQuery {
            try {
                userApi.getUserByUsername(userName)
            } catch (e: Exception) {
                throw UnknownError("Internal server error")
            }
        }
    }

    override suspend fun createUser(postUser: PostUser): User? {
        var user: User? = null
        dbQuery {
            user = userApi.createUser(postUser) ?: throw InvalidUserException()
        }
        return user
    }

    override suspend fun getAllUsers(): List<User> {
        var users: List<User> = emptyList()
        dbQuery { users = userApi.getAllUsers() }
        return users
    }
}