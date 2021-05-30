package com.challenge.vegetablediscovery.ui.vegetablelist

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.vegetablediscovery.base.MainCoroutineScopeRule
import com.challenge.vegetablediscovery.base.returns
import com.challenge.vegetablediscovery.base.shouldEqual
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.domain.model.Vitamin
import com.challenge.vegetablediscovery.logger.Logger
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
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class VegetableListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var observer: Observer<in List<Vegetable>>

    @Mock
    private lateinit var vegetableRepository: VegetableRepository

    @Mock
    private lateinit var logger: Logger

    @Mock
    private lateinit var resources: Resources

    private lateinit var sut: VegetableListViewModel

    @Before
    fun setup() {
        vegetableRepository.getVegetableList() returns flow<List<Vegetable>> { }
        initViewModel()
    }

    @Test
    fun `observe vegetable list should get value from each emit response`() {
        // Arrange
        val firstVegetablesResponse = listOf(DomainModelMocks.vegetable)
        val secondVegetablesResponse = listOf(DomainModelMocks.vegetable.copy(id = 2L))
        vegetableRepository.getVegetableList() returns flow {
            emit(firstVegetablesResponse)
            delay(1000L)
            emit(secondVegetablesResponse)
        }
        initViewModel()
        // Act
        sut.vegetables.observeForever(observer)
        // Assert
        verify(observer).onChanged(firstVegetablesResponse)
        coroutineScope.advanceTimeBy(1000L)
        verify(observer).onChanged(secondVegetablesResponse)
    }

    @Test
    fun `observe vegetable list should get vegetables which matched selected filter only`() {
        // Arrange
        val firstVegetablesResponse = listOf(
            DomainModelMocks.vegetable.copy(mainVitamin = Vitamin.B12),
            DomainModelMocks.vegetable.copy(mainVitamin = Vitamin.B6)
        )
        vegetableRepository.getVegetableList() returns flow {
            emit(firstVegetablesResponse)
        }
        initViewModel()
        sut.setSelectedFilter(Vitamin.B6)
        // Act
        sut.vegetables.observeForever(observer)
        // Assert
        val argumentCaptor = argumentCaptor<List<Vegetable>>()
        verify(observer).onChanged(argumentCaptor.capture())
        argumentCaptor.firstValue.run {
            size shouldEqual 1
            get(0).mainVitamin shouldEqual Vitamin.B6
        }
    }

    private fun initViewModel() {
        sut = VegetableListViewModel(vegetableRepository, resources, logger)
    }
}