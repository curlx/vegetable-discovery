package com.challenge.vegetablediscovery.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.vegetablediscovery.domain.model.Vitamin

@Entity(tableName = "vegetables")
data class VegetableEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val mainVitamin: Vitamin = Vitamin.UNKNOWN
)