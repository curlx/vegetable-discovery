package com.challenge.vegetablediscovery.repository

import com.challenge.vegetablediscovery.domain.model.Issue
import com.challenge.vegetablediscovery.domain.model.Vegetable
import kotlinx.coroutines.flow.Flow

interface VegetableRepository {

    fun getVegetableList(): Flow<List<Vegetable>>
    fun getVegetable(vegetableId: Long): Flow<Vegetable?>
    suspend fun refreshVegetableCache(): Issue
}