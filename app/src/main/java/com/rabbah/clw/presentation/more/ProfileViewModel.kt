package com.rabbah.clw.presentation.more

import androidx.lifecycle.ViewModel
import com.rabbah.core.preferences.PreferenceKeys
import com.rabbah.core.preferences.SharedPrefsManager

class ProfileViewModel(
    private val sharedPrefsManager: SharedPrefsManager
) : ViewModel() {

    fun saveLanguage(language: String) {
        sharedPrefsManager.setValue(PreferenceKeys.KEY_LANGUAGE, language)
    }

    fun getLanguage(): String {
        return sharedPrefsManager.getStringValue(PreferenceKeys.KEY_LANGUAGE, "en") ?: "en"
    }
}
