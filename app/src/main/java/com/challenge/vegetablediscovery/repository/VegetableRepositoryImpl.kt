package com.challenge.vegetablediscovery.repository

import com.challenge.vegetablediscovery.MainApplication
import com.challenge.vegetablediscovery.api.VegetableApi
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.data.AppDatabase
import com.challenge.vegetablediscovery.data.dao.VegetableDao
import com.challenge.vegetablediscovery.domain.model.UpdateVegetableListResult
import com.challenge.vegetablediscovery.repository.mapper.Mapper
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.repository.mapper.VegetableResultToVegetableMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.lang.Exception
import java.net.UnknownHostException

class VegetableRepositoryImpl : VegetableRepository {

    private val vegetableApi: VegetableApi by lazy { VegetableApi.create() }
    private val vegetableDao: VegetableDao by lazy { AppDatabase.getDatabase(MainApplication.applicationContext!!).vegetableDao() }
    private val vegetableMapper: Mapper<VegetableResult, Vegetable?> by lazy { VegetableResultToVegetableMapper() }

    override fun getVegetableList(): Flow<List<Vegetable>> =
        vegetableDao.getVegetables()

    override fun getVegetable(vegetableId: Long): Flow<Vegetable?> =
        vegetableDao.getVegetable(vegetableId).distinctUntilChanged()

    override suspend fun refreshVegetableCache(): UpdateVegetableListResult {
        return try {
            vegetableApi.fetchVegetableList()
                .mapNotNull(vegetableMapper::map)
                .also { vegetableDao.insertAll(it) }
            UpdateVegetableListResult.Success
        } catch (e: UnknownHostException) {
            // TODO: change this to string resource and also send log to crashlytics
            UpdateVegetableListResult.Failed("Cannot update vegetable information, please check the Internet connection and try again")
        } catch (e: Exception) {
            // TODO: change this to string resource and also send log to crashlytics
            UpdateVegetableListResult.Failed("There is a technical issue, we will fix the issue soon")
        }
    }
}
