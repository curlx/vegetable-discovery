package com.challenge.vegetablediscovery.ui.vegetabledetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.vegetablediscovery.base.MainCoroutineScopeRule
import com.challenge.vegetablediscovery.base.returns
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.domain.model.VegetableDetail
import com.challenge.vegetablediscovery.mock.DomainModelMocks
import com.challenge.vegetablediscovery.domain.contract.VegetableRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class VegetableDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var observer: Observer<in VegetableDetail?>

    @Mock
    private lateinit var vegetableRepository: VegetableRepository

    private lateinit var sut: VegetableDetailViewModel

    @Before
    fun setup() {
        vegetableRepository.getVegetable(1L) returns flow<List<Vegetable>> { }
        sut = VegetableDetailViewModel(vegetableRepository)
    }

    @Test
    fun `get vegetable detail should get value from each emit response`() {
        // Arrange
        val vegetableId = 1L
        val firstVegetableDetailResponse = DomainModelMocks.vegetableDetail
        val secondVegetableDetailResponse = DomainModelMocks.vegetableDetail.copy(description = "new description")
        vegetableRepository.getVegetable(vegetableId) returns flow {
            emit(firstVegetableDetailResponse)
            delay(1000L)
            emit(secondVegetableDetailResponse)
        }
        // Act
        sut.getVegetableDetail(vegetableId).observeForever(observer)
        // Assert
        verify(observer).onChanged(firstVegetableDetailResponse)
        coroutineScope.advanceTimeBy(1000L)
        verify(observer).onChanged(secondVegetableDetailResponse)
    }
}