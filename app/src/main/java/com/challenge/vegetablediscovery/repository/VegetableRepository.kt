package com.challenge.vegetablediscovery.repository

import com.challenge.vegetablediscovery.domain.model.Vegetable
import kotlinx.coroutines.flow.Flow

interface VegetableRepository {

    fun getVegetableList(): Flow<List<Vegetable>>
}