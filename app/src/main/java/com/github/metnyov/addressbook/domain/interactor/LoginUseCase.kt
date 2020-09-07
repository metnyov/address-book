package com.github.metnyov.addressbook.domain.interactor

import com.github.metnyov.addressbook.domain.source.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(username: String, password: String) {
        authRepository.login(username, password)
    }
}