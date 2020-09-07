package com.github.metnyov.addressbook.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Employee(
    @PrimaryKey
    val id: String,
    val departmentId: String,
    val name: String,
    val position: String,
    val email: String,
    val phone: String
) : Parcelable