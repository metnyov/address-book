package com.github.metnyov.addressbook.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Department(
    @PrimaryKey
    val id: String,
    val parentId: String?,
    val isParent: Boolean,
    val name: String
)