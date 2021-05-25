package com.challenge.vegetablediscovery.domain.model

sealed class UpdateVegetableListResult {
    object Success: UpdateVegetableListResult()
    data class Failed(val message: String): UpdateVegetableListResult()
}