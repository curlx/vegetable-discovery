package com.challenge.vegetablediscovery.mocks

import com.challenge.vegetablediscovery.api.VegetableApi
import com.challenge.vegetablediscovery.api.model.response.VegetableResult

class MockVegetableApi : VegetableApi {

    var mockFetchVegetableListResult: List<VegetableResult>? = null

    override suspend fun fetchVegetableList(): List<VegetableResult> =
        mockFetchVegetableListResult ?: emptyList()
}