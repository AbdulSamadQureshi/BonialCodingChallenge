package com.rabbah.data.remote.service

import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.AppVersionDto

interface AppApiService {
    suspend fun getAppVersion(): SingleBaseDto<AppVersionDto>
}