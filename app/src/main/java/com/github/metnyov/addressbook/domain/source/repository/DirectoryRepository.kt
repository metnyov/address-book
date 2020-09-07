package com.github.metnyov.addressbook.domain.source.repository

import com.github.metnyov.addressbook.domain.entity.Department
import com.github.metnyov.addressbook.domain.entity.Employee

interface DirectoryRepository {

    suspend fun loadDirectory()

    suspend fun getDepartments(parentId: String?): List<Department>

    suspend fun getEmployees(departmentId: String): List<Employee>
}