package com.challenge.vegetablediscovery

import com.challenge.vegetablediscovery.api.VegetableApi
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.data.AppDatabase
import com.challenge.vegetablediscovery.mocks.MockVegetableApi
import org.junit.rules.TestWatcher
import org.koin.java.KoinJavaComponent.inject

class CommonMockTestRule(
    val mockVegetableList: List<VegetableResult> = emptyList(),
    val clearDatabase: Boolean = true
): TestWatcher() {

    private val vegetableApi : VegetableApi by inject(VegetableApi::class.java)
    private val appDatabase : AppDatabase by inject(AppDatabase::class.java)

    init {
        (vegetableApi as? MockVegetableApi)?.mockFetchVegetableListResult = mockVegetableList

        if (clearDatabase) {
            appDatabase.clearAllTables()
        }
    }
}