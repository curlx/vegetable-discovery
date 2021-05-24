package com.challenge.vegetablediscovery.repository

import com.challenge.vegetablediscovery.api.VegetableApi
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.repository.mapper.Mapper
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.repository.mapper.VegetableResultToVegetableMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VegetableRepositoryImpl : VegetableRepository {

    private val vegetableApi: VegetableApi by lazy { VegetableApi.create() }
    private val vegetableMapper: Mapper<VegetableResult, Vegetable?> by lazy { VegetableResultToVegetableMapper() }

    override fun getVegetableList(): Flow<List<Vegetable>> = flow {
        vegetableApi.fetchVegetableList()
            .mapNotNull(vegetableMapper::map)
            .also { emit(it) }
    }
}