package com.github.metnyov.addressbook.data.repository

import com.github.metnyov.addressbook.data.network.AddressBookApi
import com.github.metnyov.addressbook.domain.source.AuthDao
import com.github.metnyov.addressbook.domain.source.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val api: AddressBookApi,
    private val authDao: AuthDao
) : PhotoRepository {

    override suspend fun getUrlByEmployeeId(employeeId: String): String {
        return try {
            api.getPhotoByEmployeeId(
                authDao.username,
                authDao.password,
                employeeId
            )!!
        } catch (t: Throwable) {
            FAILURE_PHOTO_URL
        }
    }

    companion object {
        private const val FAILURE_PHOTO_URL =
            "https://rudnichka.ru/wp-content/uploads/2020/07/dquestion_app_widget_2_c.png"
    }
}