package com.rabbah.domain.useCase

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Any? = null): Result
}
