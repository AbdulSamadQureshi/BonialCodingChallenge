package com.rabbah.domain.repository

import com.rabbah.domain.model.network.response.AppVersionDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getAppVersion(): Flow<Request<SingleBaseDto<AppVersionDto>>>
}
