package com.challenge.vegetablediscovery.di

import com.challenge.vegetablediscovery.api.VegetableApi
import com.challenge.vegetablediscovery.api.model.response.VegetableResult
import com.challenge.vegetablediscovery.data.AppDatabase
import com.challenge.vegetablediscovery.data.dao.VegetableDao
import com.challenge.vegetablediscovery.data.entities.VegetableEntity
import com.challenge.vegetablediscovery.repository.VegetableRepository
import com.challenge.vegetablediscovery.repository.VegetableRepositoryImpl
import com.challenge.vegetablediscovery.repository.mapper.Mapper
import com.challenge.vegetablediscovery.repository.mapper.VegetableResultToVegetableEntityMapper
import com.challenge.vegetablediscovery.ui.vegetabledetail.VegetableDetailViewModel
import com.challenge.vegetablediscovery.ui.vegetablelist.VegetableListViewModel
import com.challenge.vegetablediscovery.ui.vitaminfilter.VitaminFilterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { VegetableListViewModel(get(), androidContext().resources) }
    viewModel { VegetableDetailViewModel(get()) }
    viewModel { VitaminFilterViewModel() }
}

val repositoryModule = module {
    single<VegetableRepository> { VegetableRepositoryImpl(get(), get(), get()) }
}

val apiModule = module {
    single<String>(named(ModuleKeys.BASE_URL)) { "https://europe-west2-vegetable-discovery.cloudfunctions.net/" }
    single<VegetableApi> { VegetableApi.create(get(named(ModuleKeys.BASE_URL))) }
}

val mapperModule = module {
    single<Mapper<VegetableResult, VegetableEntity?>> { VegetableResultToVegetableEntityMapper() }
}

val databaseModule = module {
    single<AppDatabase> { AppDatabase.getDatabase(androidContext()) }
    single<VegetableDao> { AppDatabase.getDatabase(androidContext()).vegetableDao() }
}