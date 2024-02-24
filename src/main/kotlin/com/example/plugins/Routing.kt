package com.example.plugins

import com.example.api.InvalidUserException
import com.example.database.config.DBContract
import com.example.routes.userModule
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    install(StatusPages) {
        exception<InvalidUserException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.localizedMessage)
        }

        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    val dbProvider by inject<DBContract>()
    dbProvider.init()

    routing {
        userModule()
    }
}
