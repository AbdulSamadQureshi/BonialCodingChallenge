package com.rabbah.domain.useCase.offers

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.OfferDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.repository.OffersRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class ActiveOffersUseCase(
    private val repository: OffersRepository
) : BaseUseCase<Any?, Flow<Request<MultiBaseDto<OfferDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<MultiBaseDto<OfferDto>>> {
        return repository.activeOffers(params as Int)
    }
}
