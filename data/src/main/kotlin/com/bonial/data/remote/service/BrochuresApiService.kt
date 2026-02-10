package com.bonial.data.remote.service

import com.bonial.domain.model.network.response.BrochureResponseDto
import retrofit2.http.GET

interface BrochuresApiService {
    @GET("shelf.json")
    suspend fun brochures(): BrochureResponseDto
}
