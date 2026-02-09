package com.rabbah.data.remote.service

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.BrochureDto

interface BrochuresApiService {
    suspend fun brochures(): MultiBaseDto<BrochureDto>
}