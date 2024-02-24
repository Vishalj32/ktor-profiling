package com.example.controllers

import com.example.model.PostUser
import com.example.model.User

interface UserController {
    suspend fun updateProfile(userId: Int, putUser: PostUser): User?
    suspend fun removeUser(userId: Int)
    suspend fun getUserById(userId: Int): User?
    suspend fun getUserByName(userName: String)
    suspend fun createUser(postUser: PostUser): User?
    suspend fun getAllUsers(): List<User>
}