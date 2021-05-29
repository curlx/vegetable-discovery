package com.challenge.vegetablediscovery.repository.mapper

import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.base.shouldEqual
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.domain.model.Vitamin
import com.challenge.vegetablediscovery.mock.NetworkModelMocks
import org.junit.Before
import org.junit.Test

class VegetableResultToVegetableMapperTest {

    private lateinit var sut: VegetableResultToVegetableMapper

    @Before
    fun setUp() {
        sut = VegetableResultToVegetableMapper()
    }

    @Test
    fun `map empty vegetableresult should return null`() {
        sut.map(VegetableResult()) shouldEqual null
    }

    @Test
    fun `map valid vegetableresult should return vegetable with information from vegetableresult`() {
        sut.map(validVegetableResult) shouldEqual
            Vegetable(
                id = 1L,
                name = "name",
                description = "description",
                imageUrl = "imageUrl",
                mainVitamin = Vitamin.B12
            )
    }

    @Test
    fun `map vegetableresult without id should return null`() {
        sut.map(validVegetableResult.copy(id = null)) shouldEqual null
    }

    @Test
    fun `map vegetableresult without name should return null`() {
        sut.map(validVegetableResult.copy(name = null)) shouldEqual null
    }

    @Test
    fun `map vegetableresult without image url should return null`() {
        sut.map(validVegetableResult.copy(imageUrl = null)) shouldEqual null
    }

    @Test
    fun `map vegetableresult without description should return vegetable with empty description`() {
        sut.map(validVegetableResult.copy(description = null))!!.description shouldEqual ""
    }

    @Test
    fun `map vegetableresult without main vitamin should return vegetable with unknown vitamin`() {
        sut.map(validVegetableResult.copy(mainVitamin = null))!!.mainVitamin shouldEqual Vitamin.UNKNOWN
    }

    @Test
    fun `map vegetableresult with unpredefine vitamin should return vegetable with unknown vitamin`() {
        sut.map(validVegetableResult.copy(mainVitamin = "NewVitamin"))!!.mainVitamin shouldEqual Vitamin.UNKNOWN
    }

    private val validVegetableResult = NetworkModelMocks.vegetableResult
}