package com.bonial.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a cached brochure.
 * Stores the flat domain model fields — avoids serializing the nested API response structure.
 */
@Entity(tableName = "brochures")
data class BrochureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String?,
    val coverUrl: String?,
    val distance: Double?,
    val publisherName: String?,
    val contentType: String?,
)
