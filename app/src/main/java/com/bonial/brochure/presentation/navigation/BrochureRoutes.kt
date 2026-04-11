package com.bonial.brochure.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object BrochureListRoute

@Serializable
data class BrochureDetailRoute(
    val title: String?,
    val coverUrl: String?,
    val publisherName: String?,
    val distance: Double?,
)
