package com.github.metnyov.addressbook.domain.interactor

import com.github.metnyov.addressbook.domain.source.repository.DirectoryRepository

class LoadDirectoryUseCase(
    private val directoryRepository: DirectoryRepository
) {

    suspend operator fun invoke() = directoryRepository.loadDirectory()
}