package com.example

import com.example.api.UserModule
import com.example.controllers.UserControllerModule
import com.example.database.DaoModule
import com.example.database.config.Config
import com.example.database.config.DBContract
import com.example.database.config.DBProvider
import com.example.plugins.configureHTTP
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = {
        org.koin.dsl.module {
            install(Koin) {
                modules(
                    org.koin.dsl.module {
                        single<DBContract> { DBProvider() }
                        single {
                            Config(host = "localhost", 3500, "localhost", "3306")
                        }
                    },
                    UserModule.beans,
                    DaoModule.beans,
                    UserControllerModule.beans
                )
            }
        }
        configureHTTP()
        configureSerialization()
        configureRouting()
    })
        .start(wait = true)
}

suspend fun PipelineContext<Unit, ApplicationCall>.sendOk() {
    call.respond(HttpStatusCode.OK)
}
