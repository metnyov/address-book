package com.github.metnyov.addressbook.data.repository

import com.github.metnyov.addressbook.data.network.AddressBookApi
import com.github.metnyov.addressbook.domain.source.AuthDao
import com.github.metnyov.addressbook.domain.source.isAuthorized
import com.github.metnyov.addressbook.domain.source.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val api: AddressBookApi,
    private val authDao: AuthDao
) : PhotoRepository {

    override suspend fun getUrlByEmployeeId(employeeId: String): String {
        return if (authDao.isAuthorized) {
            api.getPhotoByEmployeeId(authDao.username, authDao.password, employeeId) ?: ""
        } else {
            ""
        }
    }
}