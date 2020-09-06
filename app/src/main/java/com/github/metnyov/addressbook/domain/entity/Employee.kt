package com.github.metnyov.addressbook.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @PrimaryKey
    val id: String,
    val departmentId: String,
    val name: String,
    val position: String,
    val email: String,
    val phone: String
)