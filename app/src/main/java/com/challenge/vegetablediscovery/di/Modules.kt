package com.challenge.vegetablediscovery.di

import com.challenge.vegetablediscovery.api.VegetableApi
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.data.AppDatabase
import com.challenge.vegetablediscovery.data.dao.VegetableDao
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.repository.VegetableRepository
import com.challenge.vegetablediscovery.repository.VegetableRepositoryImpl
import com.challenge.vegetablediscovery.repository.mapper.Mapper
import com.challenge.vegetablediscovery.repository.mapper.VegetableResultToVegetableMapper
import com.challenge.vegetablediscovery.ui.vegetabledetail.VegetableDetailViewModel
import com.challenge.vegetablediscovery.ui.vegetablelist.VegetableListViewModel
import com.challenge.vegetablediscovery.ui.vitaminfilter.VitaminFilterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { VegetableListViewModel(get()) }
    viewModel { VegetableDetailViewModel(get()) }
    viewModel { VitaminFilterViewModel() }
}

val repositoryModule = module {
    single<VegetableRepository> { VegetableRepositoryImpl(get(), get(), get()) }
}

val apiModule = module {
    single<VegetableApi> { VegetableApi.create() }
}

val mapperModule = module {
    single<Mapper<VegetableResult, Vegetable?>> { VegetableResultToVegetableMapper() }
}

val databaseModule = module {
    single<VegetableDao> { AppDatabase.getDatabase(androidContext()).vegetableDao() }
}
