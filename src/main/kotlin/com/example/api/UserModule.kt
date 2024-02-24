package com.example.api

import org.koin.dsl.module

object UserModule{
    val beans = module { single<UserApi> { UserApiImpl } }
}