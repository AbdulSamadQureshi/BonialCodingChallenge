package com.rabbah.domain.useCase.localStorage

import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.repository.LocalStorageRepository
import com.rabbah.domain.useCase.LocalBaseUseCase

class SaveUserUseCase(
    private val localStorageRepository: LocalStorageRepository
) : LocalBaseUseCase {
    fun invoke(userDto: UserDto) {
        return localStorageRepository.saveUser(userDto)
    }
}
