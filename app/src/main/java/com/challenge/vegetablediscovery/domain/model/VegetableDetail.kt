package com.challenge.vegetablediscovery.domain.model

data class VegetableDetail(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val mainVitamin: Vitamin = Vitamin.UNKNOWN
)