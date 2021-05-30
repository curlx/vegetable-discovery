package com.challenge.vegetablediscovery

import com.challenge.vegetablediscovery.di.loggerModule
import com.challenge.vegetablediscovery.di.mapperModule
import com.challenge.vegetablediscovery.di.repositoryModule
import com.challenge.vegetablediscovery.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TestApplication : MainApplication() {

    override fun startKoin() {
        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(this@TestApplication)
            modules(viewModelModule, repositoryModule, mapperModule, testApiModule, testDatabaseModule, loggerModule)
        }
    }
}