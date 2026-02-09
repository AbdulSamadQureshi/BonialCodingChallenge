package com.rabbah.data.repository

import com.rabbah.data.remote.service.BrochuresApiService
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.BrochureDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.repository.BrochuresRepository
import com.rabbah.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class BrochuresRepositoryImpl(private val brochuresApiService: BrochuresApiService): BrochuresRepository {
    override fun brochures(): Flow<Request<MultiBaseDto<BrochureDto>>> {
        return safeApiCall { brochuresApiService.brochures() }
    }


}