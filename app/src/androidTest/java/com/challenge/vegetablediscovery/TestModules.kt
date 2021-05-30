package com.challenge.vegetablediscovery

import com.challenge.vegetablediscovery.api.VegetableApi
import com.challenge.vegetablediscovery.data.AppDatabase
import com.challenge.vegetablediscovery.data.dao.VegetableDao
import com.challenge.vegetablediscovery.mocks.MockVegetableApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val testApiModule = module {
    single<VegetableApi> { MockVegetableApi() }
}

val testDatabaseModule = module {
    single<AppDatabase> { AppDatabase.getDatabase(androidContext(), DATABASE_NAME) }
    single<VegetableDao> { AppDatabase.getDatabase(androidContext(), DATABASE_NAME).vegetableDao() }
}

private const val DATABASE_NAME = "vegetable-discovery-test"