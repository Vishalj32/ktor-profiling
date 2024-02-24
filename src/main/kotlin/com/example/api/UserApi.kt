package com.example.api

import com.example.model.PostUser
import com.example.model.User

interface UserApi {
    fun getUserById(id: Int): User?
    fun getUserByUsername(username: String): User?
    fun updateUserProfile(userId: Int, putUserBody: PostUser): User?
    fun removeUser(userId: Int): Boolean
    fun createUser(postUser: PostUser): User?
    fun getAllUsers(): List<User>
}