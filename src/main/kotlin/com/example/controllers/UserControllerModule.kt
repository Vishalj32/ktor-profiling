package com.example.controllers

import org.koin.dsl.module

object UserControllerModule {
    val beans = module {
        single<UserController> { UserControllerImpl() }
    }
}