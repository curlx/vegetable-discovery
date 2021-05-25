package com.challenge.vegetablediscovery

import android.app.Application
import android.content.Context

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MainApplication.applicationContext = applicationContext
    }

    companion object {
        // TODO: provide applicationContext with Koin
        var applicationContext: Context? = null
    }
}

