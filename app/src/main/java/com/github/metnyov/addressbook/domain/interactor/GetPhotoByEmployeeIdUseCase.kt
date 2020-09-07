package com.github.metnyov.addressbook.domain.interactor

import com.github.metnyov.addressbook.domain.source.repository.PhotoRepository

class GetPhotoByEmployeeIdUseCase(
    private val photoRepository: PhotoRepository
) {

    suspend operator fun invoke(employeeId: String): String =
        photoRepository.getUrlByEmployeeId(employeeId)
}