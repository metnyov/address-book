package com.github.metnyov.addressbook.domain.interactor

import com.github.metnyov.addressbook.domain.source.repository.AuthRepository

class LogoutUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() {
        authRepository.logout()
    }
}