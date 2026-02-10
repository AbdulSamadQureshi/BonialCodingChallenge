package com.bonial.data.repository

import com.bonial.core.preferences.SharedPrefsManager
import com.bonial.domain.repository.LocalStorageRepository

class LocalStorageRepositoryImpl(
    private val sharedPrefsManager: SharedPrefsManager
) : LocalStorageRepository {

    override fun clearData() {
        sharedPrefsManager.clear()
    }
}