package com.github.metnyov.addressbook.data.repository

import com.github.metnyov.addressbook.common.exceptions.LoginException
import com.github.metnyov.addressbook.data.network.AddressBookApi
import com.github.metnyov.addressbook.domain.source.AuthDao
import com.github.metnyov.addressbook.domain.source.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AddressBookApi,
    private val authDao: AuthDao
) : AuthRepository {

    override suspend fun login(username: String, password: String) {
        val result = api.signIn(username, password)
        val isSuccessSignIn = result?.success == true

        if (!isSuccessSignIn) {
            throw LoginException(result?.message)
        }

        authDao.login(username, password)
    }

    override suspend fun logout() {
        authDao.logout()
    }
}