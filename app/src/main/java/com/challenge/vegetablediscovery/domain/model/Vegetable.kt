package com.challenge.vegetablediscovery.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO: separate entity and domain model
@Entity(tableName = "vegetables")
data class Vegetable(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val mainVitamin: Vitamin = Vitamin.UNKNOWN
)