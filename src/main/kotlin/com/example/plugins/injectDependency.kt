package com.example.plugins

import com.example.api.UserModule
import com.example.controllers.UserControllerModule
import com.example.database.DaoModule
import com.example.database.config.Config
import com.example.database.config.DBContract
import com.example.database.config.DBProvider
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.injectDependency() {
    install(Koin) {
        modules(
            org.koin.dsl.module {
                single<DBContract> { DBProvider() }
                single {
                    Config(host = "db", 3500, "db", "3306")
                }
            },
            UserModule.beans,
            DaoModule.beans,
            UserControllerModule.beans
        )
    }
}