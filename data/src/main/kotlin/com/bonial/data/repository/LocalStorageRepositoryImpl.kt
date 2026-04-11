package com.bonial.data.repository

import com.bonial.core.preferences.SharedPrefsManager
import com.bonial.domain.repository.LocalStorageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorageRepositoryImpl @Inject constructor(
    private val sharedPrefsManager: SharedPrefsManager
) : LocalStorageRepository {

    override fun clearData() {
        sharedPrefsManager.clear()
    }
}