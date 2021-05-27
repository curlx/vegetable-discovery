package com.challenge.vegetablediscovery.repository.mapper

import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.base.shouldEqual
import com.challenge.vegetablediscovery.domain.model.Vegetable
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
                imageUrl = "imageUrl"
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
        sut.map(validVegetableResult.copy(description = null)) shouldEqual
            Vegetable(
                id = 1L,
                name = "name",
                description = "",
                imageUrl = "imageUrl"
            )
    }

    private val validVegetableResult = NetworkModelMocks.vegetableResult
}