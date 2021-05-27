package com.challenge.vegetablediscovery.repository

import com.challenge.vegetablediscovery.api.VegetableApi
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.base.returns
import com.challenge.vegetablediscovery.base.shouldEqual
import com.challenge.vegetablediscovery.base.shouldNotEqual
import com.challenge.vegetablediscovery.data.dao.VegetableDao
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.mock.DomainModelMocks
import com.challenge.vegetablediscovery.mock.NetworkModelMocks
import com.challenge.vegetablediscovery.repository.mapper.Mapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class VegetableRepositoryImplTest {

    @Mock
    private lateinit var vegetableApi: VegetableApi

    @Mock
    private lateinit var vegetableDao: VegetableDao

    @Mock
    private lateinit var vegetableMapper: Mapper<VegetableResult, Vegetable?>

    private lateinit var sut: VegetableRepositoryImpl

    @Before
    fun setup() {
        sut = VegetableRepositoryImpl(vegetableApi, vegetableDao, vegetableMapper)
    }

    @Test
    fun `get vegetable list should return vegetable list from database`() = runBlockingTest {
        // Arrange
        val expectedVegetables = listOf(DomainModelMocks.vegetable)
        val vegetableResultsFlow = flow { emit(expectedVegetables) }
        vegetableDao.getVegetables() returns vegetableResultsFlow
        // Act
        val result = sut.getVegetableList()
        // Assert
        result.collect {
            it shouldEqual expectedVegetables
        }
    }

    @Test(expected = Throwable::class)
    fun `get vegetable list should throw an exception if there is an error from database`() = runBlockingTest {
        // Arrange
        val vegetableResultsFlow = flow<List<Vegetable>> { throw Throwable("database error") }
        vegetableDao.getVegetables() returns vegetableResultsFlow
        // Act
        val result = sut.getVegetableList()
        // Assert
        result.collect()
    }

    @Test
    fun `get vegetable by id should return vegetable with matched id from database`() = runBlockingTest {
        // Arrange
        val targetVegetableId = 1L
        val expectedVegetable = mockGettingVegetableFromDatabaseById(targetVegetableId)
        val unexpectedVegetable = mockGettingVegetableFromDatabaseById(targetVegetableId+1)
        // Act
        val result = sut.getVegetable(targetVegetableId)
        // Assert
        result.collect {
            it shouldEqual expectedVegetable
            it shouldNotEqual unexpectedVegetable
        }
    }

    @Test
    fun `get vegetable by id should return null if not found in database`() = runBlockingTest {
        // Arrange
        val targetVegetableId = 1L
        mockGettingVegetableFromDatabaseById(targetVegetableId, isFound = false)
        // Act
        val result = sut.getVegetable(targetVegetableId)
        // Assert
        result.collect {
            it shouldEqual null
        }
    }

    @Test(expected = Throwable::class)
    fun `get vegetable by id should throw an exception if there is an error from database`() = runBlockingTest {
        // Arrange
        val targetVegetableId = 1L
        mockGettingVegetableFromDatabaseById(targetVegetableId, hasError = true)
        // Act
        val result = sut.getVegetable(targetVegetableId)
        // Assert
        result.collect {
            it shouldEqual null
        }
    }

    @Test
    fun `when refresh vegetable cache should save new vegetable information from api into database`() = runBlockingTest {
        // Arrange
        val mockVegetableResult = NetworkModelMocks.vegetableResult
        val mockVegetable = DomainModelMocks.vegetable
        vegetableApi.fetchVegetableList() returns listOf(mockVegetableResult)
        vegetableMapper.map(mockVegetableResult) returns mockVegetable
        // Act
        sut.refreshVegetableCache()
        // Assert
        verify(vegetableDao).insertAll(listOf(mockVegetable))
    }

    @Test
    fun `when refresh vegetable cache should not save empty vegetable information from api into database`() = runBlockingTest {
        // Arrange
        val mockVegetableResult = NetworkModelMocks.vegetableResult
        vegetableApi.fetchVegetableList() returns listOf(mockVegetableResult)
        vegetableMapper.map(mockVegetableResult) returns null
        // Act
        sut.refreshVegetableCache()
        // Assert
        verify(vegetableDao, never()).insertAll(anyList())
    }

    private fun mockGettingVegetableFromDatabaseById(vegetableId: Long, isFound: Boolean = true, hasError: Boolean = false): Vegetable? =
        if (isFound) {
            DomainModelMocks.vegetable.copy(id = vegetableId)
        } else {
            null
        }.also {
            vegetableDao.getVegetable(vegetableId) returns flow<Vegetable?> {
                if (!hasError) {
                    emit(it)
                } else {
                    throw Throwable("database error")
                }
            }
        }
}