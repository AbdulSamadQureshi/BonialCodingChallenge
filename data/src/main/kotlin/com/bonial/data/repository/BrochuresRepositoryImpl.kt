package com.bonial.data.repository

import com.bonial.data.remote.service.BrochuresApiService
import com.bonial.domain.model.network.response.BrochureResponseDto
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.BrochuresRepository
import com.bonial.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BrochuresRepositoryImpl @Inject constructor(
    private val brochuresApiService: BrochuresApiService
) : BrochuresRepository {
    override fun brochures(): Flow<Request<BrochureResponseDto>> {
        return safeApiCall { brochuresApiService.brochures() }
    }
}