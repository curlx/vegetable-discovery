package com.challenge.vegetablediscovery

import android.app.Application
import com.challenge.vegetablediscovery.di.apiModule
import com.challenge.vegetablediscovery.di.databaseModule
import com.challenge.vegetablediscovery.di.mapperModule
import com.challenge.vegetablediscovery.di.repositoryModule
import com.challenge.vegetablediscovery.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(this@MainApplication)
            modules(viewModelModule, repositoryModule, mapperModule, apiModule, databaseModule)
        }
    }
}

