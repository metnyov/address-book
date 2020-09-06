package com.github.metnyov.addressbook.domain.source.repository

interface PhotoRepository {

    suspend fun getUrlByEmployeeId(employeeId: String): String
}