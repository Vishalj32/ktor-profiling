package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: Int, val name: String, val age: Int)

@Serializable
data class PostUser(val name: String, val age: Int)