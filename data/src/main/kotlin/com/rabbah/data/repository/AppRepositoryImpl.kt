package com.rabbah.data.repository

import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(private val appApiService: AppApiService) : AppRepository {
    override fun getAppVersion(): Flow<Request<SingleBaseDto<AppVersionDto>>> {
        return safeApiCall { appApiService.getAppVersion() }
    }
}
