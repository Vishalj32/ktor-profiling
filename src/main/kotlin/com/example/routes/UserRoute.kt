package com.example.routes

import com.example.controllers.UserController
import com.example.model.PostUser
import com.example.sendOk
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userModule() {
    val controller by inject<UserController>()

    get("/users") {
        call.respond(controller.getAllUsers())
    }

    get("/user/{id}") {
        val id = call.parameters["id"]
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, mapOf("message" to "invalid user id"))
        } else {
            controller.getUserById(id.toInt())?.let {
                call.respond(it)
            } ?: call.respond(HttpStatusCode.InternalServerError, mapOf("message" to "internal server error"))

        }
    }

    get("/user/{name}") {
        val id = call.parameters["name"]
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, mapOf("message" to "invalid user name"))
        } else {
            call.respond(controller.getUserByName(id))
        }
    }

    post("/user") {
        val postUser = call.receive<PostUser>()
        val user = controller.createUser(postUser)
        user?.let {
            call.respond(user)
        } ?: call.respond(HttpStatusCode.InternalServerError, mapOf("message" to "internal server error"))
    }

    put("/user/updateProfile/{id}") {
        val id = call.parameters["id"]
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, mapOf("message" to "invalid user id"))
        } else {
            val putUser = call.receive<PostUser>()
            val user = controller.updateProfile(id.toInt(), putUser)
            user?.let {
                call.respond(user)
            } ?: call.respond(HttpStatusCode.InternalServerError, mapOf("message" to "internal server error"))
        }

    }

    delete("/user/{id}") {
        val id = call.parameters["id"]
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, mapOf("message" to "invalid user id"))
        } else {
            controller.removeUser(id.toInt())
            sendOk()
        }

    }
}