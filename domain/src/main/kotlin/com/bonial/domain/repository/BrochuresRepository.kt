package com.bonial.domain.repository

import com.bonial.domain.model.network.response.BrochureResponse
import com.bonial.domain.model.network.response.Request
import kotlinx.coroutines.flow.Flow

interface BrochuresRepository {
    fun brochures(): Flow<Request<BrochureResponse>>
}
