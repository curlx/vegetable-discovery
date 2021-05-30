package com.challenge.vegetablediscovery.repository

import com.challenge.vegetablediscovery.api.VegetableApi
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.data.dao.VegetableDao
import com.challenge.vegetablediscovery.data.entities.VegetableEntity
import com.challenge.vegetablediscovery.domain.model.Issue
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.domain.model.VegetableDetail
import com.challenge.vegetablediscovery.domain.contract.VegetableRepository
import com.challenge.vegetablediscovery.logger.Logger
import com.challenge.vegetablediscovery.repository.mapper.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.net.UnknownHostException

class VegetableRepositoryImpl(
    private val vegetableApi: VegetableApi,
    private val vegetableDao: VegetableDao,
    private val vegetableEntityMapper: Mapper<VegetableResult, VegetableEntity?>,
    private val logger: Logger
) : VegetableRepository {

    override fun getVegetableList(): Flow<List<Vegetable>> =
        vegetableDao.getVegetables()

    override fun getVegetable(vegetableId: Long): Flow<VegetableDetail?> =
        vegetableDao.getVegetable(vegetableId).distinctUntilChanged()

    override suspend fun refreshVegetableCache(): Issue {
        return try {
            vegetableApi.fetchVegetableList()
                .mapNotNull(vegetableEntityMapper::map)
                .takeIf { it.isNotEmpty() }
                ?.also { vegetableDao.insertAll(it) }
            Issue.NO_ISSUE
        } catch (e: UnknownHostException) {
            logger.logException(e)
            Issue.INTERNET_CONNECTION
        } catch (e: Exception) {
            logger.logException(e)
            Issue.UNKNOWN
        }
    }
}
