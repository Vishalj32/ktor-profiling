package com.example.database

import com.example.database.dao.Users
import com.example.database.`interface`.UserDao
import com.example.model.User
import org.koin.dsl.module

object DaoModule{
    val beans = module{
        single<UserDao> { Users }
    }
}