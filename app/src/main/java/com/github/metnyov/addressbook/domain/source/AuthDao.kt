package com.github.metnyov.addressbook.domain.source

interface AuthDao {

    val username: String

    val password: String

    fun login(username: String, password: String)

    fun logout()
}

val AuthDao.isAuthorized: Boolean
    get() = username.isNotEmpty() && password.isNotEmpty()