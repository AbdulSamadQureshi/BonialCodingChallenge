package com.rabbah.domain.useCase.localStorage

import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.model.network.response.WalletDto
import com.rabbah.domain.repository.LocalStorageRepository
import com.rabbah.domain.repository.WalletRepository
import com.rabbah.domain.useCase.BaseUseCase
import com.rabbah.domain.useCase.LocalBaseUseCase
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val localStorageRepository: LocalStorageRepository
) : LocalBaseUseCase {
    fun invoke(): UserDto? {
        return localStorageRepository.getUser()
    }
}
