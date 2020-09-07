package com.github.metnyov.addressbook.domain.source.repository

interface AuthRepository {

    suspend fun login(username: String, password: String)

    suspend fun logout()
}