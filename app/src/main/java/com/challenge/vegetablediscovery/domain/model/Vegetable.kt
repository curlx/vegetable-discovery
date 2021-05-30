package com.challenge.vegetablediscovery.domain.model

data class Vegetable(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val mainVitamin: Vitamin = Vitamin.UNKNOWN
)