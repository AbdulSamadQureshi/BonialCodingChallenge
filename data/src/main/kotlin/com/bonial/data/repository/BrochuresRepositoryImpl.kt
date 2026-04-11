package com.bonial.data.repository

import com.bonial.data.local.BrochureEntity
import com.bonial.data.local.BrochureLocalDataSource
import com.bonial.data.remote.service.BrochuresApiService
import com.bonial.domain.model.Brochure
import com.bonial.domain.model.network.response.ApiError
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.BrochuresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Offline-first implementation of [BrochuresRepository].
 *
 * Strategy:
 * 1. Emit [Request.Loading]
 * 2. Try the network — on success, map DTOs → domain models, cache to Room, emit [Request.Success]
 * 3. On network failure, fall back to the Room cache:
 *    - If cache is non-empty, emit [Request.Success] with stale data
 *    - If cache is also empty, emit [Request.Error]
 */
@Singleton
class BrochuresRepositoryImpl @Inject constructor(
    private val brochuresApiService: BrochuresApiService,
    private val localDataSource: BrochureLocalDataSource,
) : BrochuresRepository {

    override fun brochures(): Flow<Request<List<Brochure>>> = flow {
        emit(Request.Loading)
        try {
            val response = brochuresApiService.brochures()
            val contents = response.embedded?.contents ?: emptyList()
            val brochures = contents.flatMap { wrapper ->
                val contentType = wrapper.contentType
                wrapper.content.map { dto ->
                    Brochure(
                        title = dto.title,
                        coverUrl = dto.brochureImage,
                        distance = dto.distance,
                        publisherName = dto.publisher?.name,
                        contentType = contentType,
                    )
                }
            }
            localDataSource.cacheBrochures(brochures.map { it.toEntity() })
            emit(Request.Success(brochures))
        } catch (e: IOException) {
            emit(resolveFromCache(message = e.message))
        } catch (e: Exception) {
            emit(resolveFromCache(message = e.message))
        }
    }

    private suspend fun resolveFromCache(message: String?): Request<List<Brochure>> {
        val cached = localDataSource.getCachedBrochures()
        return if (cached.isNotEmpty()) {
            Request.Success(cached.map { it.toDomain() })
        } else {
            Request.Error(ApiError(code = "NetworkError", message = message ?: "Unknown error"))
        }
    }
}

private fun Brochure.toEntity() = BrochureEntity(
    title = title,
    coverUrl = coverUrl,
    distance = distance,
    publisherName = publisherName,
    contentType = contentType,
)

private fun BrochureEntity.toDomain() = Brochure(
    title = title,
    coverUrl = coverUrl,
    distance = distance,
    publisherName = publisherName,
    contentType = contentType,
)
