package com.github.metnyov.addressbook.data.network

import com.github.metnyov.addressbook.data.entity.NwDepartment
import com.github.metnyov.addressbook.data.entity.SignInResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressBookApi {

    @GET("/Contacts.svc/Hello/")
    suspend fun signIn(
        @Query("login") login: String,
        @Query("password") password: String
    ): SignInResponse?

    @GET("/Contacts.svc/GetAll/")
    suspend fun getAllData(
        @Query("login") login: String,
        @Query("password") password: String,
    ): NwDepartment?

    @GET("/Contacts.svc/GetWPhoto/")
    suspend fun getPhotoByEmployeeId(
        @Query("login") login: String,
        @Query("password") password: String,
        @Query("id") id: String
    ): String?
}