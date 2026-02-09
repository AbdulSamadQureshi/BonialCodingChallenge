package com.rabbah.domain.repository

import com.rabbah.domain.model.network.response.UserDto

interface LocalStorageRepository {
    fun saveUser(user: UserDto)
    fun getUser(): UserDto?

    fun logout()
}
