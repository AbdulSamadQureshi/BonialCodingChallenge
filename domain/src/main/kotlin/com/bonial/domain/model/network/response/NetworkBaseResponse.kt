package com.bonial.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseDto {
    @SerialName("message")
    var message: String? = null

    @SerialName("success")
    var success: Boolean = false

    @SerialName("error")
    val error: ApiError? = null
}

@Serializable
data class ApiError(
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("details")
    val details: ErrorDetails? = null
)

@Serializable
data class ErrorDetails(
    @SerialName("resource")
    val resource: String? = null,
    @SerialName("identifier")
    val identifier: String? = null
)

@Serializable
class SingleBaseDto<T>(
    @SerialName("data")
    val data: T? = null,
) : BaseDto()


@Serializable
open class MultiBaseDto<T>(
    @SerialName("data")
    open var data: List<T?>? = ArrayList(),
) : BaseDto()


@Serializable
open class PaginatedDto<T>(
    @SerialName("page")
    open val page: Int = 0,
    @SerialName("page_size")
    val pageSize: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_items")
    val totalItems: Int,
    @SerialName("has_next")
    val hasNext: Boolean,
) : MultiBaseDto<T>()
