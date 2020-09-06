package com.github.metnyov.addressbook.domain.interactor

import com.github.metnyov.addressbook.domain.entity.Department
import com.github.metnyov.addressbook.domain.source.repository.DirectoryRepository

class GetDepartmentsUseCase(
    private val directoryRepository: DirectoryRepository
) {

    suspend operator fun invoke(parentId: String?): List<Department> =
        directoryRepository.getDepartments(parentId)
}