package com.github.metnyov.addressbook.domain.interactor

import com.github.metnyov.addressbook.domain.entity.Employee
import com.github.metnyov.addressbook.domain.source.repository.DirectoryRepository

class GetEmployeesUseCase(
    private val directoryRepository: DirectoryRepository
) {

    suspend operator fun invoke(departmentId: String): List<Employee> =
        directoryRepository.getEmployees(departmentId)
}