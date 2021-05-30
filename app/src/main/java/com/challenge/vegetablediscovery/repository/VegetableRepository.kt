package com.challenge.vegetablediscovery.repository

import com.challenge.vegetablediscovery.domain.model.Issue
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.domain.model.VegetableDetail
import kotlinx.coroutines.flow.Flow

interface VegetableRepository {

    fun getVegetableList(): Flow<List<Vegetable>>
    fun getVegetable(vegetableId: Long): Flow<VegetableDetail?>
    suspend fun refreshVegetableCache(): Issue
}