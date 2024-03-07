package com.example.database.config


class Config(
    val host: String,
    val port: Int,
    val databaseHost: String,
    val databasePort: String
) {

    companion object {
        const val DATABASENAME: String = "ktordb"
        const val DATABASEUSER: String = "ktoruser"
        const val DATABASEPASSWORD: String = "ktorpassword"
    }
}