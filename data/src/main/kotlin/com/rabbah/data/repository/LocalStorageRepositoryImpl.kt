package com.rabbah.data.repository

import com.rabbah.core.preferences.SharedPrefsManager
import com.rabbah.domain.repository.LocalStorageRepository

class LocalStorageRepositoryImpl(
    private val sharedPrefsManager: SharedPrefsManager
) : LocalStorageRepository {

    override fun clearData() {
        sharedPrefsManager.clear()
    }
}