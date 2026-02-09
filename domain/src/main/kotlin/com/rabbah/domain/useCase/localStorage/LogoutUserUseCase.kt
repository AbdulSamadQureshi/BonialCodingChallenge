package com.rabbah.domain.useCase.localStorage

import com.rabbah.domain.repository.LocalStorageRepository
import com.rabbah.domain.useCase.LocalBaseUseCase

class LogoutUserUseCase(
    private val localStorageRepository: LocalStorageRepository
) : LocalBaseUseCase {
    fun invoke() {
        return localStorageRepository.logout()
    }
}
