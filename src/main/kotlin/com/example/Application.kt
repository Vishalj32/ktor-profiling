package com.example

import com.example.plugins.configureHTTP
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.injectDependency
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

fun main() {
    embeddedServer(Netty, port = 8082, host = "0.0.0.0", module = {
        injectDependency()
        configureHTTP()
        configureSerialization()
        configureRouting()
    })
        .start(wait = true)
}

suspend fun PipelineContext<Unit, ApplicationCall>.sendOk() {
    call.respond(HttpStatusCode.OK)
}
