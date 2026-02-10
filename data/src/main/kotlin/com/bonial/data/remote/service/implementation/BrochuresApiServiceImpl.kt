package com.bonial.data.remote.service.implementation

import com.bonial.data.remote.service.BrochuresApiService
import com.bonial.domain.model.network.response.BrochureResponseDto
import retrofit2.Retrofit

class BrochuresApiServiceImpl(private val retrofit: Retrofit) : BrochuresApiService {

    private val api = retrofit.create(BrochuresApiService::class.java)

    override suspend fun brochures(): BrochureResponseDto {
        return api.brochures()
    }
}
