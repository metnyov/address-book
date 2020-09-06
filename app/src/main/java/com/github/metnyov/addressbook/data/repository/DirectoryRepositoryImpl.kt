package com.github.metnyov.addressbook.data.repository

import com.github.metnyov.addressbook.data.network.AddressBookApi
import com.github.metnyov.addressbook.data.entity.NwDepartment
import com.github.metnyov.addressbook.data.entity.toDepartment
import com.github.metnyov.addressbook.data.entity.toEmployee
import com.github.metnyov.addressbook.domain.entity.Department
import com.github.metnyov.addressbook.domain.entity.Employee
import com.github.metnyov.addressbook.domain.source.AuthDao
import com.github.metnyov.addressbook.data.dao.DepartmentDao
import com.github.metnyov.addressbook.data.dao.EmployeeDao
import com.github.metnyov.addressbook.domain.source.isAuthorized
import com.github.metnyov.addressbook.domain.source.repository.DirectoryRepository

class DirectoryRepositoryImpl(
    private val api: AddressBookApi,
    private val authDao: AuthDao,
    private val departmentDao: DepartmentDao,
    private val employeeDao: EmployeeDao
) : DirectoryRepository {

    override suspend fun loadDirectory() {
        if (authDao.isAuthorized) {
            val rootDepartment = api.getAllData(authDao.username, authDao.password) ?: return
            val departments = rootDepartment.departments?.mapNotNull { it } ?: emptyList()
            insertDatabases(departments, null)
        }
    }

    override suspend fun getDepartments(parentId: String?): List<Department> =
        departmentDao.getByParentId(parentId)

    override suspend fun getEmployees(departmentId: String): List<Employee> =
        employeeDao.getByDepartmentId(departmentId)

    private suspend fun insertDatabases(nwDepartments: List<NwDepartment>, parentId: String?) {
        nwDepartments.forEach {
            val departmentId = it.id ?: return@forEach
            val innerItems = it.departments?.mapNotNull { item -> item } ?: emptyList()

            val domainDepartments = listOfNotNull(
                it.toDepartment(
                    parentId = parentId,
                    isParent = innerItems.isNotEmpty()
                )
            ).toTypedArray()

            departmentDao.insertAll(*domainDepartments)

            val domainEmployees = it.employees?.mapNotNull { nwEmployee ->
                nwEmployee?.toEmployee(departmentId = departmentId)
            }?.toTypedArray() ?: emptyArray()

            employeeDao.insertAll(*domainEmployees)

            insertDatabases(innerItems, departmentId)
        }
    }
}