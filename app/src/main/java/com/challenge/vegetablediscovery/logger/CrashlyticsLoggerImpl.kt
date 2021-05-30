package com.challenge.vegetablediscovery.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashlyticsLoggerImpl : Logger {

    override fun logException(throwable: Throwable) {
        FirebaseCrashlytics.getInstance().recordException(throwable)
    }
}