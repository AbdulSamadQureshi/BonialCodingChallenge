package com.rabbah.domain.repository

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.BrochureDto
import com.rabbah.domain.model.network.response.Request
import kotlinx.coroutines.flow.Flow

interface BrochuresRepository {
    fun brochures(): Flow<Request<MultiBaseDto<BrochureDto>>>
}
