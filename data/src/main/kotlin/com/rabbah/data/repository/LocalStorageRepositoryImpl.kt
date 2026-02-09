package com.rabbah.data.repository

import com.rabbah.core.preferences.SharedPrefsManager
import com.rabbah.core.preferences.PreferenceKeys
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.repository.LocalStorageRepository

class LocalStorageRepositoryImpl(
    private val sharedPrefsManager: SharedPrefsManager
) : LocalStorageRepository {

    override fun saveUser(user: UserDto) {
        sharedPrefsManager.saveObject(PreferenceKeys.KEY_USER, user)
    }

    override fun getUser(): UserDto? {
        return sharedPrefsManager.getObject(PreferenceKeys.KEY_USER, UserDto::class.java)
    }

    override fun logout() {
        sharedPrefsManager.clear()
    }
}