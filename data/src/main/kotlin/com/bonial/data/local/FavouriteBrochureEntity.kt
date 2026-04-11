package com.bonial.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_brochures")
data class FavouriteBrochureEntity(
    @PrimaryKey
    val coverUrl: String,
)
