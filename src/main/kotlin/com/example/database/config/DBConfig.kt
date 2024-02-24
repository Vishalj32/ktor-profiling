package com.example.database.config


class Config(
    val host: String,
    val port: Int,
    val databaseHost: String,
    val databasePort: String
) {

    companion object {
        const val DATABASENAME: String = "catalog"
        const val DATABASEUSER: String = "root"
        const val DATABASEPASSWORD: String = "admin"
    }
}